package de.domedev.enities;

import java.awt.image.BufferedImage;

/* [29.11.14, Dome] 
 * Haupt-Classe
 * Alle charakter/Wesen/NPC/Mobs/Player durchgehen diese Classe.
 * 
 * */
public class Charakter {
	private int ccPosY;
	private int ccPosX;
	private BufferedImage ccSprite;
	private boolean ccNPC;

	public Charakter(int xPosY, int xPosX, BufferedImage xSprite, boolean xNPC) {
		ccPosY = xPosY;
		ccPosX = xPosX;

		System.out.println("Ich wurde erstellt: X='" + ccPosX + "' Y='" + ccPosY + "' ");
	}

	public BufferedImage getSprite() {
		return ccSprite;
	}

	public int getPosY() {
		return ccPosY;
	}

	public int getPosX() {
		return ccPosX;
	}

	public void setPosX(int xPosX) {
		ccPosX = xPosX;
	}

	public void setPosY(int xPosY) {
		ccPosY = xPosY;
	}

	public void setDirection(String xWalkingDirection) {
		System.out.println(xWalkingDirection);
	}
}
