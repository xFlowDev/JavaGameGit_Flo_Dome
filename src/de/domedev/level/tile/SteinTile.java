package de.domedev.level.tile;

import de.domedev.graphics.Screen;
import de.domedev.graphics.Sprite;

public class SteinTile extends Tile {

	public SteinTile(Sprite xSprite) {
		super(xSprite);
	}

	public void render(int xRenderPosX, int xRenderPosY, Screen xScreen) {
		xScreen.renderTile(xRenderPosX << 5, xRenderPosY << 5, this);
	}

}
