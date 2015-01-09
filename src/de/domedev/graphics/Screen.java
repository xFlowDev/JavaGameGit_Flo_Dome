package de.domedev.graphics;

import java.util.Random;

import de.domedev.level.tile.Tile;

/* Dome - 28.11.14 - 22:59:
 * WOFÜR ist dies Classe jetzt genau?
 * Die Classe sollte doch für das Rendern der Map sein oder seh ich das falsch?
 * Ich steig hier langsam nicht mehr durch! Lass bitte die Experimente sein.
 * 
 * Mach das Ding so fertig, dass hier endlich eine Map zusehen ist. 
 * Performance ist egal, das ist nicht unsere Priorität.  
 * */

public class Screen {

	int ccWidth, ccHeight;
	public int[] ccPixel;
	private final int ccTILE_SIZE = 32; //Ein Tile ist 32*32 rechtecke groß wobei ein rechteck 16x16 pixel hat
	private final int ccTILE_COUNTER = ccTILE_SIZE - 1;
	public int[] ccTiles = new int[ccTILE_SIZE * ccTILE_SIZE];

	private Random lRandom = new Random();

	public Screen(int xWidth, int xHeight) {
		ccWidth = xWidth;
		ccHeight = xHeight;
		ccPixel = new int[ccHeight * ccWidth];

		/* Dome: Augenkrebs Farben entfernt... */
		for (int ii = 0; ii < ccTILE_SIZE * ccTILE_SIZE; ii++) {
			ccTiles[0] = 0x000000; // Besser zu erkenn wo ein Tile anfängt, immer das erste schwarz
			ccTiles[ii] = 0x618722;
		}

	}

	public void clearScreen() {
		for (int ii = 0; ii < ccPixel.length; ii++) {
			ccPixel[ii] = 0;
		}
	}

	public void renderScreen() {
		for (int yy = 0; yy < ccHeight; yy++) {
			int ly = yy;
			if (ly >= ccHeight || ly < 0)
				break;
			for (int xx = 0; xx < ccWidth; xx++) {
				int lx = xx;
				if (lx >= ccWidth || lx < 0)
					break;
				ccPixel[xx + yy * ccWidth] = Sprite.ccGrass.ccPixel[(xx & 31) + (yy & 31) * Sprite.ccSIZE];
			}
		}
	}

	public void renderTile(int xPosX, int xPosY, Tile xTile) {
		for (int y = 0; y < xTile.ccSprite.ccSIZE; y++) {
			int yAbsolute = y + xPosY;
			for (int x = 0; x < xTile.ccSprite.ccSIZE; x++) {
				int xAbsolute = x + xPosX;
				if (xAbsolute < 0 || xAbsolute >= ccWidth || yAbsolute < 0 || yAbsolute >= ccHeight)
					break;
			}
		}
	}
}
