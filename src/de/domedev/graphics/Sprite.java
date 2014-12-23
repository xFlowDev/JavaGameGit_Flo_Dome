package de.domedev.graphics;

public class Sprite {

	public static int ccSIZE;
	private int x, y;
	public int[] ccPixel;
	private SpriteSheet ccSheet;

	public static Sprite ccGrass = new Sprite(32, 0, 1, SpriteSheet.ccSpriteSheet); 

	//x und y sagen der methode wo auf dem bild nach dem Sprite gesucht werden muss, die Größe ist offensichtlich die größe des Sprites
	public Sprite(int xSize, int X, int Y, SpriteSheet xSheet) {
		ccSIZE = xSize;
		ccPixel = new int[xSize * xSize];
		x = X * xSize;
		y = Y * xSize;
		ccSheet = xSheet;

		loadSprite();
	}

	private void loadSprite() {
		for (int yy = 0; yy < ccSIZE; yy++) {
			for (int xx = 0; xx < ccSIZE; xx++) {
				ccPixel[xx + yy * ccSIZE] = ccSheet.ccPixel[(xx + this.x) + (yy + this.y) * ccSheet.ccSIZE];
			}
		}
	}
}
