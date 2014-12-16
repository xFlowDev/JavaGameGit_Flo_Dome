package de.domedev.enities;

import java.awt.image.BufferedImage;

import de.domedev.Input.KeyboardInput;

/* [29.11.14, Dome]
 * Spieler-Classe 
 * Child-Class von Charakter
 * 
 * 
 * */
public class Player extends Charakter {

	/* Variablen die für die Geschwindigkeit des Spielers sorgen */
	private int ccSpeedX = 0;
	private int ccSpeedY = 0;

	
	public Player(int xPosY, int xPosX, BufferedImage xSprite, boolean xNPC) {
		super(xPosY, xPosX, xSprite, xNPC);
		// TODO Auto-generated constructor stub
	}

	public void move(KeyboardInput xKey, Charakter xChar){
		ccSpeedX = xChar.getPosX();
		ccSpeedY = xChar.getPosY();
		
		if(xKey.ccUp){
			ccSpeedY-=2;
			xChar.setPosY(ccSpeedY);
			setDirection("UP");
		}
		if(xKey.ccDown){
			ccSpeedY+=2;
			xChar.setPosY(ccSpeedY);
			setDirection("DOWN");
		}
		if(xKey.ccLeft){
			ccSpeedX-=2;
			xChar.setPosX(ccSpeedX);
			setDirection("LEFT");
		}
		if(xKey.ccRight){
			ccSpeedX+=2;
			xChar.setPosX(ccSpeedX);
			setDirection("RIGHT");
		}
	}

}
