package de.domedev.enities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import de.domedev.Input.KeyboardInput;
import de.domedev.graphics.Screen;
import de.domedev.inventar.Inventar;
import de.domedev.level.tile.Tile;

/* [29.11.14, Dome]
 * Spieler-Classe 
 * Child-Class von Charakter
 * 
 * 
 * */
public class Player extends Charakter {

	/* Variablen die f�r die Geschwindigkeit des Spielers sorgen */
	private int ccSpeedX = 0;
	private int ccSpeedY = 0;
	private int ccStep = 2;
	Inventar ccInventar = new Inventar(); 
	
	
	public Player(int xPosY, int xPosX, BufferedImage xSprite, int xMaxHealth, int xHealth, int xLevel) {
		super(xPosY, xPosX, xSprite,xMaxHealth, xHealth, xLevel);
	}

	public void movePlayer(KeyboardInput xKey, Charakter xChar, Screen xScreen){
		ccSpeedX = xChar.getPosX();
		ccSpeedY = xChar.getPosY();
		ccStep = 2;
		// Sprint!
		if(xKey.ccShift){
			if(xChar.getAusdauer() > 0){
				ccStep = 4;
				xChar.setAusdauer(xChar.getAusdauer() - 4);
				// System.out.println(xChar.getAusdauer());
			}		
		}
		
		if(xKey.ccUp){
			ccSpeedY-=ccStep;
			xChar.setPosY(ccSpeedY);
			setDirection("UP");
		}
		if(xKey.ccDown){
			ccSpeedY+=ccStep;
			xChar.setPosY(ccSpeedY);
			setDirection("DOWN");
		}
		if(xKey.ccLeft){
			ccSpeedX-=ccStep;
			xChar.setPosX(ccSpeedX);
			setDirection("LEFT");
		}
		if(xKey.ccRight){
			ccSpeedX+=ccStep;
			xChar.setPosX(ccSpeedX);
			setDirection("RIGHT");
		}		

	}

	public void checkIfStrike(KeyboardInput xKey, Charakter xChar) {
		if(xKey.ccSpace){
			xChar.setStrike(true);	
			if(xChar.getStrikePower() < 100){
				xChar.setStrikePower(xChar.getStrikePower() + 1);
			}
			// System.out.println(xChar.getStrikePower());
			// Wir Returnen, damit wir die Methode beenden und den Rest nicht ausführen
			return;
		}
		xChar.setStrikePower(0);
	}
	
	
	/* Inventarsteuerung  */
	/* Inventar anzeigen? */
	public void checkIfShowInventory(KeyboardInput xKey, Charakter xChar) {
		if(xKey.ccI){
			System.out.println(ccInventar.isShowInventar());
			if(ccInventar.isShowInventar() == true){
				ccInventar.setShowInventar(false);
			}else{
				ccInventar.setShowInventar(true);
			}
		}	
	}

	public boolean isShowInventar(){
		return ccInventar.isShowInventar();
	}
	
	public void ShowInventar(Graphics g){
		ccInventar.ShowInventar(g);
	}
	
	/* Gold */
	public int getPlayerGold(){
		return ccInventar.getGold();
	}
	
	public void setPlayerGold(int xPlayerGold){
		ccInventar.setGold(xPlayerGold);
	}

	public void drawUI(int xWindowHeight, int xWindowWidth, Graphics xGraphics) {
		
		if(getStrikePower() > 0 && getStrike()){
			xGraphics.setColor(Color.YELLOW);
			double StrikePowerBarWidth = 0;
			StrikePowerBarWidth = (double)32 / 100 * getStrikePower();
			xGraphics.fillRect(getPosX(), getPosY() + 32, (int)StrikePowerBarWidth, 5);	
		}
		
		
		/* Player spezifische Anzeige */
		/*Inventar anzeige */
			if(isShowInventar()){
				ShowInventar(xGraphics);
			}
		
		/* Playerbar */
			Font fnt1 = new Font("arial", Font.BOLD, 15);
			xGraphics.setColor(Color.LIGHT_GRAY); 
			xGraphics.fillRect(0, xWindowHeight -21, xWindowWidth + 20, 21);
			
		/* Healthbar */
			xGraphics.setColor(Color.GRAY); 
			xGraphics.fillRect(10, xWindowHeight - 20, 250, 20);
			xGraphics.setColor(Color.RED); 
			double HealthBarWidth = (double)250 / getMaxHealth() * getHealth();
			xGraphics.fillRect(10, xWindowHeight - 20, (int)HealthBarWidth, 20);
			xGraphics.setFont(fnt1);
			xGraphics.setColor(Color.BLACK);
			xGraphics.drawString(getHealth() + "/" + getMaxHealth(), 100, xWindowHeight  - 2);
		
		/* Ausdauerbar */
			xGraphics.setColor(Color.GRAY); 
			xGraphics.fillRect(280, xWindowHeight - 20, 250, 20);
			xGraphics.setColor(Color.CYAN);
			double AusdauerBarWidth = (double)250 / getMaxAusdauer() * getAusdauer();
			xGraphics.fillRect(280, xWindowHeight - 20, (int)AusdauerBarWidth, 20);
			xGraphics.setFont(fnt1);
			xGraphics.setColor(Color.BLACK);
			xGraphics.drawString(getAusdauer() + "/" + getMaxAusdauer(), 400, xWindowHeight - 2);
			
		/* Erfahrungsbar */
			xGraphics.setColor(Color.GRAY); 
			xGraphics.fillRect(580, xWindowHeight - 20, 250, 20);
			xGraphics.setColor(Color.YELLOW); 
			double ExpBarWidth = (double)250 / getNextExpLevel(getLevel()) * getErfahrung();
			xGraphics.fillRect(580, xWindowHeight - 20, (int)ExpBarWidth, 20);
			xGraphics.setFont(fnt1);
			xGraphics.setColor(Color.BLACK);
			xGraphics.drawString(getErfahrung() + "/" + getNextExpLevel(getLevel()), 700, xWindowHeight - 2);	
		
		/* Gold und Level*/
			xGraphics.setColor(Color.GRAY); 
			xGraphics.fillRect(880, xWindowHeight - 20, 130, 20);
			xGraphics.setFont(fnt1);
			xGraphics.setColor(Color.BLACK);
			xGraphics.drawString("Level:" + getLevel() + " Gold:" + getPlayerGold(), 900, xWindowHeight - 2);
		
	}
	
	
}
