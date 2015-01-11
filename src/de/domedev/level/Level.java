package de.domedev.level;

import de.domedev.graphics.Screen;
import de.domedev.level.tile.Tile;

public class Level {

	private int ccWidth, ccHeight;
	private int[] ccTiles;

	public Level(int xHeight, int xWidth) {
		ccWidth = xWidth;
		ccHeight = xHeight;
		ccTiles = new int[ccWidth * ccHeight];

		generateLevel();
	}

	public Level(String xPath) {
		loadLevel(xPath);
	}

	private void loadLevel(String xPath) {
		//zum Laden einer fertigen Map

	}

	private void generateLevel() {
		//vllt random Levels
	}

	public void update() {
		// wird aus der Game.update() methode aufgerufen 
	}

	public void render(int xScrollX, int xScrollY, Screen xScreen) {
		int x0 = xScrollX >> 5;
		int x1 = (xScrollX + xScreen.ccWidth) >> 5;

		int y0 = xScrollY >> 5;
		int y1 = (xScrollY + xScreen.ccHeight) >> 5;
		// diese Variablen geben an welcher Bereich gerendert wird, damit wird verhindert das eine unendlich große Map entsteht
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x,y).render(x , y, xScreen);
			}
			
		}
	}

	public Tile getTile(int xPos, int yPos) {
		if (ccTiles[xPos + yPos * ccWidth] == 0)
			return Tile.ccGrassTile;

		return Tile.ccVoidTile;

	}
}
