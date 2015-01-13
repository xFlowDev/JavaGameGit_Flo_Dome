package de.domedev.level;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import de.domedev.level.tile.Tile;

public class SpawnLevel extends Level {

	public int[] ccLevelPixel;

	public SpawnLevel(String xPath) {
		super(xPath);

	}

	protected void loadLevel(String xPath) {

		try {
			BufferedImage lImage = ImageIO.read(SpawnLevel.class.getResource("/SpawnLevel.png"));
			int lWidth = lImage.getWidth();
			int lHeight = lImage.getHeight();
			ccTiles = new Tile[lWidth * lHeight];
			lImage.getRGB(0, 0, lWidth, lHeight, ccLevelPixel, 0, lWidth);

		} catch (Exception e) {
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
		for (int i = 0; i < ccLevelPixel.length; i++) {
			if (ccLevelPixel[i] == 0x00FF00)
				ccTiles[i] = Tile.ccGrassTile;
			if (ccLevelPixel[i] == 0x777777)
				ccTiles[i] = Tile.ccSteinTile;
			if (ccLevelPixel[i] == 0)
				ccTiles[i] = Tile.ccVoidTile;
			//			if (ccLevelPixel[i] == 0x00FF00)
			//				ccTiles[i] = Tile.ccGrassTile;
			//			if (ccLevelPixel[i] == 0x00FF00)
			//				ccTiles[i] = Tile.ccGrassTile;
		}
	}

}
