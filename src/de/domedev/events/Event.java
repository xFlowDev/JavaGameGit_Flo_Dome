package de.domedev.events;

import de.domedev.enities.Player;

public class Event {
	
	/* Main-Vars */
	private int ccIndex; 
	private String ccType;
	private int ccPosX;
	private int ccPosY;
	private int ccHeight;
	private int ccWidth;
	
	/* Teleport Event Vars */
	private int ccGoToMapIndex;
	private int ccGoToPosX;
	private int ccGoToPosY;
	
	/* Player verlinken */
	private Player ccPlayer;
	
	public Event(int xIndex, String xType, int xPosX, int xPosY, int xHeight, int xWidth, int xGoToMapIndex, int xGoToPosX, int xGoToPosY){
		this.setIndex(xIndex);	
		this.setType(xType);
		this.setPosX(xPosX);
		this.setPosY(xPosY);	
		this.setHeight(xHeight);	
		this.setWidth(xWidth);
		this.setGoToMapIndex(xGoToMapIndex);
		this.setGoToPosX(xGoToPosX);
		this.setGoToPosY(xGoToPosY);		
	}

	public int getIndex() {
		return ccIndex;
	}

	public void setIndex(int ccIndex) {
		this.ccIndex = ccIndex;
	}

	public String getType() {
		return ccType;
	}

	public void setType(String ccType) {
		this.ccType = ccType;
	}

	public int getPosX() {
		return ccPosX;
	}

	public void setPosX(int ccPosX) {
		this.ccPosX = ccPosX;
	}

	public int getPosY() {
		return ccPosY;
	}

	public void setPosY(int ccPosY) {
		this.ccPosY = ccPosY;
	}

	public int getWidth() {
		return ccWidth;
	}

	public void setWidth(int ccWidth) {
		this.ccWidth = ccWidth;
	}

	public int getHeight() {
		return ccHeight;
	}

	public void setHeight(int ccHeight) {
		this.ccHeight = ccHeight;
	}

	public int getGoToMapIndex() {
		return ccGoToMapIndex;
	}

	public void setGoToMapIndex(int ccGoToMapIndex) {
		this.ccGoToMapIndex = ccGoToMapIndex;
	}

	public int getGoToPosX() {
		return ccGoToPosX;
	}

	public void setGoToPosX(int ccGoToPosX) {
		this.ccGoToPosX = ccGoToPosX;
	}

	public int getGoToPosY() {
		return ccGoToPosY;
	}

	public void setGoToPosY(int ccGoToPosY) {
		this.ccGoToPosY = ccGoToPosY;
	}	
}