package de.domedev.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.domedev.level.tile.Tile;

public class SpawnLevel extends Level {

	private int[] ccLevelPixel;

	public SpawnLevel(String xPath) {
		super(xPath);


	}

	protected void loadLevel(String xPath) {
		try {
			BufferedImage lImage = ImageIO.read(SpawnLevel.class.getResource("/SpawnLevel.png"));
			int lWidth = lImage.getWidth();
			int lHeight = lImage.getHeight();
			ccTiles = new Tile[lWidth * lHeight];
			ccLevelPixel = new int[lWidth * lHeight];
			lImage.getRGB(0, 0, lWidth, lHeight, ccLevelPixel, 0, lWidth);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fehler beim Laden der Level Datei!");
		}
	}

	// Grass = 0xFF00FF00
	// Blumen = 0xFFFFFF00
	// Steine = 0xFF777777
	// Wasser = 0xFF0000FF
	// Baum = 0xFF555500
	protected void generateLevel() {
		for (int i = 0; i < ccLevelPixel.length; i++) {
			if (ccLevelPixel[i] == 0xff00ff00)
				ccTiles[i] = Tile.ccGrassTile;
			else if (ccLevelPixel[i] == 0xff777777)
				ccTiles[i] = Tile.ccSteinTile;
			else if (ccLevelPixel[i] == 0)
				ccTiles[i] = Tile.ccVoidTile;
			else if (ccLevelPixel[i] == 0xFF0000FF)
				ccTiles[i] = Tile.ccWasserTile;
			else{
				ccTiles[i]= Tile.ccVoidTile;
			}
			
			//			if (ccLevelPixel[i] == 0x00FF00)
			//				ccTiles[i] = Tile.ccGrassTile;
			//			if (ccLevelPixel[i] == 0x00FF00)
			//				ccTiles[i] = Tile.ccGrassTile;
		}
	}

}
