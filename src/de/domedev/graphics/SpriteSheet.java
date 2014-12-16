package de.domedev.graphics;

import java.awt.image.BufferedImage;

/* [29.11.14, Dome]: uh, Mitternacht. :) 
 * Hiermit kannst du die Bilder auf dem SpriteSheets laden. 
 *  
 * Lade erst das SpriteSheet in ein Object (Obj = new SpriteSheet() ) 
 * Per grabSprite() "schneidest" du dir das Bild raus.(var = Obj.grabSprite() )
 * 
 *  */
public class SpriteSheet {
	
	public BufferedImage ImageFile = null;
	
	public SpriteSheet(BufferedImage xSpriteSheet){
		this.ImageFile = xSpriteSheet;
	}
	
	public BufferedImage grabSprite(int xPosX, int xPosY, int xWidth, int xHeight){
		BufferedImage Sprite = ImageFile.getSubimage(xPosX, xPosY, xWidth, xHeight);
		return Sprite;
	}
	
}
