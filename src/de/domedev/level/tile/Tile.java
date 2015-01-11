package de.domedev.level.tile;

import de.domedev.graphics.Screen;
import de.domedev.graphics.Sprite;

public class Tile {

	public int ccX,ccY;
	public Sprite ccSprite;
	
	public static Tile ccGrassTile = new GrassTile(Sprite.ccGrass);
	public static Tile ccVoidTile = new VoidTile(Sprite.ccVoidSprite);
	
	public Tile(Sprite xSprite){
		ccSprite = xSprite;
	}
	
	public void render(int xRenderPosX, int xRenderPosY, Screen xScreen){
		//jede unterklasse bekommt diese methode auch und beschreibt wie und was gerendert werden soll
		
	}
	
	public boolean isSolid(){
		return false;
	}
}