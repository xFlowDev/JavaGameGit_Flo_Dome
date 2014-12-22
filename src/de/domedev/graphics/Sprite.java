package de.domedev.graphics;

public class Sprite {

	public static int ccSIZE;
	private int ccX, ccY;
	public int[] ccPixel;
	private SpriteSheet ccSheet;

	public static Sprite ccGrass = new Sprite(32, 0, 1, SpriteSheet.ccSpriteSheet); 

	//x und y sagen der methode wo auf dem bild nach dem Sprite gesucht werden muss, die Größe ist offensichtlich die größe des Sprites
	public Sprite(int xSize, int X, int Y, SpriteSheet xSheet) {
		ccSIZE = xSize;
		ccPixel = new int[xSize * xSize];
		ccX = X * xSize;
		ccY = Y * xSize;
		ccSheet = xSheet;

		loadSprite();
	}

	private void loadSprite() {
		for (int yy = 0; yy < ccSIZE; yy++) {
			for (int xx = 0; xx < ccSIZE; xx++) {
				ccPixel[xx + yy * ccSIZE] = ccSheet.ccPixel[(xx + ccX) + (yy + ccY) * ccSheet.ccSIZE];
			}
		}
	}
}
