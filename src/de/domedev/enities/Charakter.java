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
	private int ccHealth;
	private int ccMaxHealth;
	private boolean ccStrike;
	private int ccStrikePower = 0;
	private int ccAusdauer = 10;
	private int ccMaxAusdauer = 300;
	private int ccLevel = 1;
	private int ccErfahrung;
	private int[] ccNextExpLevel = {0,10,15,40,90,180,300}; // lvl 0 - 7 ; wobei level 0 nichts gibt
	private String ccDirection;
	
	public Charakter(int xPosY, int xPosX, BufferedImage xSprite, boolean xNPC,int xMaxHealth, int xHealth, int xLevel) {
		ccPosY = xPosY;
		ccPosX = xPosX;
		ccHealth = xHealth;
		ccMaxHealth = xMaxHealth;
		setLevel(xLevel);
		
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
		ccDirection = xWalkingDirection;
	}
	
	public String getDirection() {
		return ccDirection;
	}
	
	public int getHealth() {
		return ccHealth;
	}

	public int getMaxHealth() {
		return ccMaxHealth;
	}

	public void setHealth(int xHealth) {
		ccHealth = xHealth;
	}

	public void setStrike(boolean xStrike) {
		ccStrike = xStrike;	
	}
	
	public boolean getStrike(){
		return ccStrike;
	}
	
	public void setStrikePower(int xPowerPlus){
		ccStrikePower = xPowerPlus;	
	}
	
	public int getStrikePower(){
		return ccStrikePower;
	}
	
	public void Strike() {
		System.out.println(getDirection());
	}

	public int getAusdauer() {
		return ccAusdauer;
	}

	public void setAusdauer(int xAusdauer) {
		this.ccAusdauer = xAusdauer;	
	}	
	public int getMaxAusdauer() {
		return ccMaxAusdauer;
	}

	public void setMaxAusdauer(int xMaxAusdauer) {
		this.ccMaxAusdauer = xMaxAusdauer;
	}

	public int getLevel() {
		return ccLevel;
	}

	public void setLevel(int ccLevel) {
		this.ccLevel = ccLevel;
	}

	public int getNextExpLevel(int xLevel) {
		return ccNextExpLevel[xLevel];
	}

	public void setNextExpLevel(int[] xNextLevel) {
		this.ccNextExpLevel = xNextLevel;
	}

	public int getErfahrung() {
		return ccErfahrung;
	}

	public void setErfahrung(int ccErfahrung) {
		this.ccErfahrung = ccErfahrung;
	}
}
