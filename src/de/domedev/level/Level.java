package de.domedev.level;

import de.domedev.graphics.Screen;

public class Level {

	private int ccWidth, ccHeight;
	private int[] ccTiles;
	
	
	public Level(int xHeight, int xWidth){
		ccWidth = xWidth;
		ccHeight = xHeight;
		ccTiles = new int[ccWidth*ccHeight];
		
		generateLevel();
	}

	public Level(String xPath){
		loadLevel(xPath);
	}

	private void loadLevel(String xPath) {
		//zum Laden einer fertigen Map
		
	}

	private void generateLevel() {
		//vllt random Levels
	}
	
	public void update(){
		// wird aus der Game.update() methode aufgerufen 
	}
	
	public void render(int xScrollX, int xScrollY, Screen xScreen){
		
	}
	
}
