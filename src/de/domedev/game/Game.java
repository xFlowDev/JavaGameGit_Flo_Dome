package de.domedev.game;

import java.awt.Canvas;
import java.awt.Dimension;
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

/**
 * Allgemeines: 
 * #[28.11.14, Dome]
 *  Zum Glück muss ich das Projekt nicht mein Chef zeigen.
 *  Der würde mich für den Haufen Müll hier umhauen. 
 *  
 *  Wir haben uns hier ganz schön zu weit ausm Fenster gelehnt.
 *  Keiner von uns beiden konnte Java und wir sind nur zu zweit. 
 *  Wir missachten alle Regeln und die vorige Planung war ebenfalls für Nüsse. 
 *  Aber Hey, immer hin haben wir uns täglich gedanken gemacht.   
 *  
 *  Ich bin unzufrieden und ich bin mir sicher, dass das eine peinliche Sache wird, wenn Dau sich das alles hier anschaut.
 *  Wenn wir allerdings so weiter arbeiten, wie die letzten 2 Wochen, dann schaffen wir das bis zum Abgabetermin. 
 *  
 * 
 * Ach und bitte, lass diese eigenartige Aufteilung der Code-Blöcke (-> "*****").   
 * 
 * ToDo's:
 * Letzter Stand: 28.11.14 / Dome 
 * Absteigende Priorität!
 * - Erneutes Planen => Klassendiagramm anfertigen/anpassen, API-Verfahren besprechen...etc (@Alle)
 * - Simples Map-Rendering, grüne Fläche mit Grässern (SpriteSheets) reicht vollkommen aus (@Flo)
 * - Player-Movement + Animation (@Dome)
 * - Sprite_Sheet.png erweitern, mehr Gegenstände für Flo's ToDo (@Dome)
 * - Map-Gegenstände, Haus?! Wasser?! Felsen?! (@Flo)
 * - NPCs (@Dome)
 * - Interaktion mit NPCs, einfache Dialoge und vielleicht Quests (@Dome)
 * - Menü erweitern (@Dome)
 * 
 * Ist-Stand:
 * Letzter Stand: 28.11.14 / Dome 
 * - !Meilenstein nicht erreicht!
 * - Grundgerüst steht mehr oder weniger
 * - SpritesSheet Logik bereit für Einsatz
 * - Keyboard Eingaben vorbereitet für Mehrzweck
 * - Dome hat Java langsam verstanden
 * 
 * Veraenderungen bite hier notieren!
 * Changelog:
 * #[28.11.14, Dome] 
 * - SpriteSheet-Logik eingebunden. (SpriteSheet.class)
 * - BufferedImageLoader eingebunden. (BufferedImageLoader.class)  
 * - KeyboardInput eingebunden / nicht vollständig. (KeyboardInput.class)
 * - Charakter/NPC/Mobs Logik angefangen. 
 * - Sprite_Sheet.png fertiggestellt + Test-Figure.
 * - Module kommentiert.
 * - ToDo's und Stand erstellt.
 * #[28.11.14, Florian] 
 * - Bisschen für Struktur gesorgt und slle Kommentare bei den Variablen veraendert/erstellt 
 * 
 * 
 */

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 6133432984074892968L;

	/* Variablen fürs Fenster */
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
	/* Das Bild "image" wird zu einem Array aus Pixel gemacht, dadurch kann jeder Pixel einzeln verändert werden */

	/* Status des Programms => Im Game oder im Menu? */
	public static enum STATE { GAME, MENU }
	public static STATE STATUS = STATE.MENU;
	private GameMenu menu;
	

	/* Charakter Var*/
    public Charakter[] ccAllChars = new Charakter[2];
    public Player ccPlayer;
	public int PlayerArrayID = 1;
	
    // "synchronized" sorgt dafür das Threads sich nicht gegenseitig behindern
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
		
		//Aktiviert den KeyListener für unseren GameLoop
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
		
		// Player
		ccSprite = Sprite.grabSprite(0, 0, 32, 32);
		ccPlayer = new Player(0, 0, ccSprite, true);
		
		// Charakter erstellen => Alle in ein Array
		ccAllChars[0] = new Charakter(300, 300, ccSprite, false);
		ccAllChars[1] = new Charakter(350, 300, ccSprite, false);

		
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
			// Länge eines Updates
			lDelta += (lNow - lLastTime) / lNS; 
			lLastTime = lNow;
			// Summierung aller Updates in einer Sekunde
			while (lDelta >= 1) { 
				tick();
				lUpdates++;
				lDelta--;
			}
			
			// update();
			render();
			lFrames++;

			if ((System.currentTimeMillis() - lTimer)/60 > 1000) {
				lTimer += 1000;
				System.out.println("FPS: " + lFrames + " Updates: " + lUpdates);
				ccFrame.setTitle(ccWindow_TITLE + "Updates: " + lUpdates + " FPS: " + lFrames);
				lFrames = 0;
				lUpdates = 0;
			}
		}
	}

	public void tick() {
		ccKey.checkIfKeyPressed();
		ccPlayer.move(ccKey,ccAllChars[1]);
	}

	/* public void update(){

	}   */

	/* Dome: die komplette Methode soll nur für die Grafikausgabe zustaendig sein. Keine Logiken(s.u.) 
	 * Map rendern!
	 * Player rendern!
	 * NPC rendern!
	 * */
	
	/**
	 * #[12.12.14, Flo]
	 * - @Dome Die Grafik ausgabe wird in der render() methode gemacht
	 * - weil die Render methode für ne flüssige Grafik ausgabe sorgt,
	 * - die Render methode wird durch den Gameloop auf eine fps von über 200 gebracht(auf meinem Rechner, das variert je nach rechner)
	 * - aber die updates (oder ticks in unserem fall) sind auf 60 limietiert,
	 * - solange die updates auf 60 bleiben läuft die Logik immer gleich wie oft ein Bild angezeigt wird ist ja dann egal
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
		
		// Dome: @Flo: render Map? Zukünftig? Siehe Class Screen
		ccScreen.clearScreen();
		ccScreen.renderScreen();
		for (int ii = 0; ii < ccPixel.length; ii++) {
			ccPixel[ii] = ccScreen.ccPixel[ii];
		}
		
		Graphics g = bs.getDrawGraphics();
		/* erstellt eine Verbindung zwischen BufferStrategy und Grafics */
		g.drawImage(ccBufferedImage, 0, 0, getWidth(), getHeight(), this);

		
		/* Dome: Das ist zwar von mir. Gefällt mir hier nicht. (s.o. warum.)  */
		if (STATUS == STATE.GAME) {
			// Testausgabe

			// Dome: Charakter zeichnen => Array wird durchlaufen
			for(int i = 0;  i < ccAllChars.length; i++){
				g.drawImage(ccSprite, ccAllChars[i].getPosX(), ccAllChars[i].getPosY(), 32, 32, null);
			}
			
			
		} else if (STATUS == STATE.MENU) {
			menu.renderMenu(g);
		}
		
		g.dispose();
		// Naechster Buffer
		bs.show(); 
	}
}
