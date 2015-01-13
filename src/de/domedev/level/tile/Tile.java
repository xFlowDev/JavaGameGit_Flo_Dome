package de.domedev.level.tile;

import de.domedev.graphics.Screen;
import de.domedev.graphics.Sprite;

public class Tile {

	public int ccX,ccY;
	public Sprite ccSprite;
	
	public static Tile ccGrassTile = new GrassTile(Sprite.ccGrassSprite);
	public static Tile ccVoidTile = new VoidTile(Sprite.ccVoidSprite);
	public static Tile ccSteinTile = new SteinTile(Sprite.ccSteinSprtie);
	public static Tile ccWasserTile = new WasserTile(Sprite.ccWasserSprite);
	
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
