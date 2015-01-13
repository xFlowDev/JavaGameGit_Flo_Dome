package de.domedev.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.domedev.level.tile.Tile;

public class SpawnLevel extends Level {

	private Tile[] ccTiles;
	private int[] ccLevelPixel;

	public SpawnLevel(String xPath) {
		super(xPath);

	}

	protected void loadLevel(String xPath) {

		try {
			BufferedImage lImage = ImageIO.read(SpawnLevel.class.getResource(xPath));
			int lWidth = lImage.getWidth();
			int lHeight = lImage.getHeight();
			ccTiles = new Tile[lWidth * lHeight];
			lImage.getRGB(0, 0, lWidth, lHeight, ccLevelPixel, 0, lWidth);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fehler beim Laden der Level Datei!");
		}

	}

	// Grass = 0x00FF00
	// Blumen = 0xFFFF00
	// Steine = 0x777777
	// Wasser = 0x0000FF
	// Baum = 0x555500
	protected void generateLevel() {
		for (int i = 0; i < ccTiles.length; i++) {
			if (ccLevelPixel[i] == 0x00FF00)
				ccTiles[i] = Tile.ccGrassTile;
			if (ccLevelPixel[i] == 0x00FF00)
				ccTiles[i] = Tile.ccGrassTile;
//			if (ccLevelPixel[i] == 0x00FF00)
//				ccTiles[i] = Tile.ccGrassTile;
//			if (ccLevelPixel[i] == 0x00FF00)
//				ccTiles[i] = Tile.ccGrassTile;
		}
	}

}
