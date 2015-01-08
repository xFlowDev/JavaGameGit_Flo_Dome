package de.domedev.enities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import de.domedev.Input.KeyboardInput;
import de.domedev.inventar.Inventar;

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
	
	
	public Player(int xPosY, int xPosX, BufferedImage xSprite, boolean xNPC, int xMaxHealth, int xHealth, int xLevel) {
		super(xPosY, xPosX, xSprite, xNPC,xMaxHealth, xHealth, xLevel);
	}

	public void movePlayer(KeyboardInput xKey, Charakter xChar){
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
		
		if(xChar.getStrikePower() > 0){
			xChar.Strike();
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

	public void addItem(int xIndex) {
		ccInventar.addItem2Inventar(xIndex);	
	}
	
	
}
