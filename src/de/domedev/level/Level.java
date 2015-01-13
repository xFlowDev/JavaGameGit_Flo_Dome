package de.domedev.level;

import de.domedev.graphics.Screen;
import de.domedev.level.tile.Tile;

public class Level {

	protected Tile[] ccTiles;
	protected int ccWidth, ccHeight;
	protected int[] ccTilesInt;
	

	public Level(int xHeight, int xWidth) {
		ccWidth = xWidth;
		ccHeight = xHeight;
		ccTilesInt = new int[ccWidth * ccHeight];

		generateLevel();
	}

	public Level(String xPath) {
		loadLevel(xPath);
	}

	protected void loadLevel(String xPath) {

	}

	protected void generateLevel() {
		// vllt random Levels
	}

	public void update() {
		// wird aus der Game.update() methode aufgerufen
	}

	public void render(int xPosition, int yPosition, Screen xScreen) {
		int x0 = xPosition >> 5;
		int x1 = (xPosition + xScreen.ccWidth + 32) >> 5;

		int y0 = yPosition >> 5;
		int y1 = (yPosition + xScreen.ccHeight + 32) >> 5;
		// diese Variablen geben an welcher Bereich gerendert wird, damit wird verhindert das eine unendlich große
		// Map entsteht

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
//				getTile(x, y).render(x, y, xScreen);
				ccTiles[x + y * 32].render(x,y,xScreen);
			}

		}
	}

//	public Tile getTile(int xPos, int yPos) {
//		return Tile.ccGrassTile;
//
//		// return Tile.ccVoidTile;
//
//	}
}
