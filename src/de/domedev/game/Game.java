package de.domedev.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.swing.JFrame;

import de.domedev.Input.KeyboardInput;
import de.domedev.enities.Charakter;
import de.domedev.enities.Player;
import de.domedev.graphics.BufferedImageLoader;
import de.domedev.graphics.Screen;
import de.domedev.graphics.SpriteSheet;
import de.domedev.inventar.Item;

/**
 * ToDo:
 * #[7.1.2015, Dome Flo]
 * - Map,  9.1.2014 -> Eine Karte
 * - Inventar, Bugfix 
 * - Angriff auf Test-Mob
 * - Simples Event-System 
 * - Gegener
 * - Spritesheet zeichnen 
 * Nice-To-Have 
 * - Quest
 * 
 * Allgemeines: 
 * #[28.11.14, Dome]
 *  Zum Gl�ck muss ich das Projekt nicht mein Chef zeigen.
 *  Der w�rde mich f�r den Haufen M�ll hier umhauen. 
 *  
 *  Wir haben uns hier ganz sch�n zu weit ausm Fenster gelehnt.
 *  Keiner von uns beiden konnte Java und wir sind nur zu zweit. 
 *  Wir missachten alle Regeln und die vorige Planung war ebenfalls f�r N�sse. 
 *  Aber Hey, immer hin haben wir uns t�glich gedanken gemacht.   
 *  
 *  Ich bin unzufrieden und ich bin mir sicher, dass das eine peinliche Sache wird, wenn Dau sich das alles hier anschaut.
 *  Wenn wir allerdings so weiter arbeiten, wie die letzten 2 Wochen, dann schaffen wir das bis zum Abgabetermin. 
 *  
 * 
 * Ach und bitte, lass diese eigenartige Aufteilung der Code-Bl�cke (-> "*****").   
 *  
 * ToDo's:
 * Letzter Stand: 17.12.14 / Dome und Flo
 * Absteigende Priorit�t!
 * - Erneutes Planen => Klassendiagramm anfertigen/anpassen, API-Verfahren besprechen...etc (@Alle)
 * - Simples Map-Rendering, gr�ne Fl�che mit Gr�ssern (SpriteSheets) reicht vollkommen aus (@Flo)
 * - Player-Movement + Animation (@Dome)
 * - Sprite_Sheet.png erweitern, mehr Gegenst�nde f�r Flo's ToDo (@Dome)
 * - Map-Gegenst�nde, Haus?! Wasser?! Felsen?! (@Flo)
 * - NPCs (@Dome)
 * - Interaktion mit NPCs, einfache Dialoge und vielleicht Quests (@Dome)
 * - Men� erweitern (@Dome)
 * - Charakter Render Methode auslagern in die Charakter Class (@Dome)
 * 
 * Ist-Stand: 
 * Stand: 28.11.14 / Dome 
 * - !Meilenstein nicht erreicht!
 * - Grundger�st steht mehr oder weniger
 * - SpritesSheet Logik bereit f�r Einsatz
 * - Keyboard Eingaben vorbereitet f�r Mehrzweck
 * - Dome hat Java langsam verstanden
 * 
 * Veraenderungen bitte hier notieren!
 * Changelog:
 * #[17.12.14, Dome]
 * - Inventar angefangen
 * - Power Schlag vorbereitet
 * #[28.11.14, Dome] 
 * - SpriteSheet-Logik eingebunden. (SpriteSheet.class)
 * - BufferedImageLoader eingebunden. (BufferedImageLoader.class)  
 * - KeyboardInput eingebunden / nicht vollst�ndig. (KeyboardInput.class)
 * - Charakter/NPC/Mobs Logik angefangen. 
 * - Sprite_Sheet.png fertiggestellt + Test-Figure.
 * - Module kommentiert.
 * - ToDo's und Stand erstellt.
 * #[28.11.14, Florian] 
 * - Bisschen f�r Struktur gesorgt und slle Kommentare bei den Variablen veraendert/erstellt 
 * 
 * 
 */

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 6133432984074892968L;

	/* Variablen f�rs Fenster */
	public static final int ccWindow_WIDTH = 1024;
	public static final int ccWindow_HEIGHT = ccWindow_WIDTH / 12 * 9;
	public static final Dimension ccWindow_DIMENSIONS = new Dimension(ccWindow_WIDTH, ccWindow_HEIGHT);
	public static final String ccWindow_TITLE = "The Real Java Game Project ";
	public static Game ccGame;
	public JFrame ccFrame;

	
	private KeyboardInput ccKey;
	private Thread ccThread;
	private boolean ccRunning = false;

	/* Variablen zur Tile Berechnung und Rendering */
	private Screen ccScreen;
	private BufferedImage ccBufferedImage = new BufferedImage(ccWindow_WIDTH, ccWindow_HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] ccPixel = ((DataBufferInt) ccBufferedImage.getRaster().getDataBuffer()).getData();
	private BufferedImage ccSprite;
	/* Das Bild "image" wird zu einem Array aus Pixel gemacht, dadurch kann jeder Pixel einzeln ver�ndert werden */

	/* Status des Programms => Im Game oder im Menu? */
	public static enum STATE { GAME, MENU }
	public static STATE STATUS = STATE.MENU;
	private GameMenu menu;
	

	/* Charakter Var*/
    private Charakter[] ccAllChars = new Charakter[2];
    private Player ccPlayer;
    private Player ccCharakter;
    private int ccPlayerID = 0;
    
    /* Item List */
	private Item ccItem = new Item(); 
	
	/*Charakter Health anzeigen */
    
    // "synchronized" sorgt daf�r das Threads sich nicht gegenseitig behindern
	public synchronized void startGame() {
		ccThread = new Thread(this);
		ccRunning = true;
		// ruft automatisch die run() Methode auf
		ccThread.start(); 
		System.out.println("Spiel startet");
	}

	public synchronized void stopGame() {
		ccRunning = false;
		try {
			ccThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Spiel stoppt");
	}

	public void init() {
		menu = new GameMenu();
		this.addMouseListener(new MouseInput());		
		ccScreen = new Screen(ccWindow_WIDTH, ccWindow_HEIGHT);
		
		//Aktiviert den KeyListener f�r unseren GameLoop
		ccKey = new KeyboardInput();
		addKeyListener(ccKey);
		
		
		/* SpriteSheet laden */
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage SpriteSheet = null;
		try {
			SpriteSheet = loader.LoadImage("/sprite_sheet.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpriteSheet Sprite = new SpriteSheet(SpriteSheet);
		
		// Items List laden
		System.out.println("Erstelle Items!");
		// Attribute: ItemName, ItemType, SpriteSheet, MinLevel, Armor, minDMG, maxDMG 
		ccItem.makeItem(ccItem.AutoIncrement(), "Schwert aus Holz", "Schwert", null , "1", "0", "1", "3");
		ccItem.makeItem(ccItem.AutoIncrement(), "Schild aus Holz", "Schild", null , "1", "3", "0", "0");
		ccItem.makeItem(ccItem.AutoIncrement(), "Brustschutz aus Holz", "Brust", null , "1", "10", "0", "0");
		ccItem.makeItem(ccItem.AutoIncrement(), "Beinschutz aus Holz", "Bein", null , "1", "7", "0", "0");
		
		// Player
		ccSprite = Sprite.grabSprite(0, 0, 32, 32);
		ccPlayer = new Player(0, 0, ccSprite, true, 120, 100, 4);
		// Charakter erstellen => Alle in ein Array
		ccAllChars[0] = new Charakter(300, 300, ccSprite, false, 100, 100, 4);
		ccAllChars[1] = new Charakter(350, 300, ccSprite, false, 150, 100, 7);
		ccAllChars[ccPlayerID].setErfahrung(26);
		
		// beispiel aufruf eines items
		ccPlayer.addItem(1);
	}

	// run() Methode ist durch den Start des Threads aufgerufen, also thread.start()
	public void run() {
		init();

		long lLastTime = System.nanoTime();
		long lTimer = System.currentTimeMillis();
		double lNS = 1000000000.0 / 60.;
		double lDelta = 0;
		int lFrames = 0;
		int lUpdates = 0;

		while (ccRunning) {
			long lNow = System.nanoTime();
			// L�nge eines Updates
			lDelta += (lNow - lLastTime) / lNS; 
			lLastTime = lNow;
			// Summierung aller Updates in einer Sekunde
			while (lDelta >= 1) { 
				update();
				lUpdates++;
				lDelta--;
			}
			
			// update();
			render();
			lFrames++;

			if ((System.currentTimeMillis() - lTimer) > 1000) {
				lTimer += 1000;
				System.out.println("FPS: " + lFrames + " Updates: " + lUpdates);
				ccFrame.setTitle(ccWindow_TITLE + "Updates: " + lUpdates + " FPS: " + lFrames);
				lFrames = 0;
				lUpdates = 0;
			}
		}
	}

	// update methode @flo
	public void update() {
		if (STATUS == STATE.GAME) {
			ccKey.checkIfKeyPressed();
			ccPlayer.movePlayer(ccKey,ccAllChars[ccPlayerID]); 
			ccPlayer.checkIfStrike(ccKey,ccAllChars[ccPlayerID]);	
			ccPlayer.checkIfShowInventory(ccKey, ccAllChars[ccPlayerID]);
			
			// Ausduaer regenieren
			if(ccAllChars[ccPlayerID].getAusdauer() < ccAllChars[ccPlayerID].getMaxAusdauer()){
				ccAllChars[ccPlayerID].setAusdauer(ccAllChars[ccPlayerID].getAusdauer() + 1);
			}
		}
	}

	/* Dome: die komplette Methode soll nur f�r die Grafikausgabe zustaendig sein. Keine Logiken(s.u.) 
	 * Map rendern!
	 * Player rendern!
	 * NPC rendern!
	 * */
	
	/**
	 * #[12.12.14, Flo]
	 * - @Dome Die Grafik ausgabe wird in der render() methode gemacht
	 * - weil die Render methode f�r ne fl�ssige Grafik ausgabe sorgt,
	 * - die Render methode wird durch den Gameloop auf eine fps von �ber 200 gebracht(auf meinem Rechner, das variert je nach rechner)
	 * - aber die updates (oder ticks in unserem fall) sind auf 60 limietiert,
	 * - solange die updates auf 60 bleiben l�uft die Logik immer gleich wie oft ein Bild angezeigt wird ist ja dann egal
	 * -
	 * - Wenn du eine Update() Methode haben willst benenn Sie um musst mir nur bescheid sagen
	 */
	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			/* Durch createBufferStrategy(3) haben wir immer 2 Bilder vorraus geladen */
			createBufferStrategy(3);
			return;
		}
		
		// Dome: @Flo: render Map? Zuk�nftig? Siehe Class Screen
		ccScreen.clearScreen();
		ccScreen.renderScreen();
		for (int ii = 0; ii < ccPixel.length; ii++) {
			ccPixel[ii] = ccScreen.ccPixel[ii];
		}
		
		Graphics g = bs.getDrawGraphics();
		/* erstellt eine Verbindung zwischen BufferStrategy und Grafics */
		g.drawImage(ccBufferedImage, 0, 0, getWidth(), getHeight(), this);

		
		/* Dome: Das ist zwar von mir. Gef�llt mir hier nicht. (s.o. warum.)  */
		if (STATUS == STATE.GAME) {
			// Testausgabe

			// Dome: Charakter zeichnen => Array wird durchlaufen
			for(int i = 0;  i < ccAllChars.length; i++){				
				g.drawImage(ccSprite, ccAllChars[i].getPosX(), ccAllChars[i].getPosY(), 32, 32, null);
				
				// Healthbar Anzeigen; Nur bei unter 100% .... NICHT BEIM PLAYER 
				if(ccAllChars[i].getHealth() < ccAllChars[i].getMaxHealth() && i != ccPlayerID) {
					g.setColor(Color.RED); 
					double HealthBarWidth = 0;
					HealthBarWidth = (double)32 / ccAllChars[i].getMaxHealth() * ccAllChars[i].getHealth();
					g.fillRect(ccAllChars[i].getPosX(), ccAllChars[i].getPosY() - 10, (int)HealthBarWidth, 5);	
				}
				
				// Powerschlag anzeigen
				if(ccAllChars[i].getStrikePower() > 0 && ccAllChars[i].getStrike()){
					g.setColor(Color.YELLOW);
					double StrikePowerBarWidth = 0;
					StrikePowerBarWidth = (double)32 / 100 * ccAllChars[i].getStrikePower();
					g.fillRect(ccAllChars[i].getPosX(), ccAllChars[i].getPosY() + 32, (int)StrikePowerBarWidth, 5);	
				}
								
			}			
						
		/* Player spezifische Anzeige */
		/*Inventar anzeige */
			if(ccPlayer.isShowInventar()){
				ccPlayer.ShowInventar(g);
			}
		
		/* Playerbar */
			Font fnt1 = new Font("arial", Font.BOLD, 15);
			g.setColor(Color.LIGHT_GRAY); 
			g.fillRect(0, ccWindow_HEIGHT -21, ccWindow_WIDTH + 20, 21);
			
		/* Healthbar */
			g.setColor(Color.GRAY); 
			g.fillRect(10, ccWindow_HEIGHT - 20, 250, 20);
			g.setColor(Color.RED); 
			double HealthBarWidth = (double)250 / ccAllChars[ccPlayerID].getMaxHealth() * ccAllChars[ccPlayerID].getHealth();
			g.fillRect(10, ccWindow_HEIGHT - 20, (int)HealthBarWidth, 20);
			g.setFont(fnt1);
			g.setColor(Color.BLACK);
			g.drawString(ccAllChars[ccPlayerID].getHealth() + "/" + ccAllChars[ccPlayerID].getMaxHealth(), 100, ccWindow_HEIGHT  - 2);
		
		/* Ausdauerbar */
			g.setColor(Color.GRAY); 
			g.fillRect(280, ccWindow_HEIGHT - 20, 250, 20);
			g.setColor(Color.CYAN);
			double AusdauerBarWidth = (double)250 / ccAllChars[ccPlayerID].getMaxAusdauer() * ccAllChars[ccPlayerID].getAusdauer();
			g.fillRect(280, ccWindow_HEIGHT - 20, (int)AusdauerBarWidth, 20);
			g.setFont(fnt1);
			g.setColor(Color.BLACK);
			g.drawString(ccAllChars[ccPlayerID].getAusdauer() + "/" + ccAllChars[ccPlayerID].getMaxAusdauer(), 400, ccWindow_HEIGHT - 2);
			
		/* Erfahrungsbar */
			g.setColor(Color.GRAY); 
			g.fillRect(580, ccWindow_HEIGHT - 20, 250, 20);
			g.setColor(Color.YELLOW); 
			double ExpBarWidth = (double)250 / ccAllChars[ccPlayerID].getNextExpLevel(ccAllChars[ccPlayerID].getLevel()) * ccAllChars[ccPlayerID].getErfahrung();
			g.fillRect(580, ccWindow_HEIGHT - 20, (int)ExpBarWidth, 20);
			g.setFont(fnt1);
			g.setColor(Color.BLACK);
			g.drawString(ccAllChars[ccPlayerID].getErfahrung() + "/" + ccAllChars[ccPlayerID].getNextExpLevel(ccAllChars[ccPlayerID].getLevel()), 700, ccWindow_HEIGHT - 2);	
		
		/* Gold und Level*/
			g.setColor(Color.GRAY); 
			g.fillRect(880, ccWindow_HEIGHT - 20, 130, 20);
			g.setFont(fnt1);
			g.setColor(Color.BLACK);
			g.drawString("Level:" + ccAllChars[ccPlayerID].getLevel() + " Gold:" + ccPlayer.getPlayerGold(), 900, ccWindow_HEIGHT - 2);	

			
		} else if (STATUS == STATE.MENU) {
			menu.renderMenu(g);
		}
		
		g.dispose();
		// Naechster Buffer
		bs.show(); 
	}
}
