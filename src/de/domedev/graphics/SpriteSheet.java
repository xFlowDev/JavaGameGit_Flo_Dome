package de.domedev.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/** [29.11.14, Dome]: uh, Mitternacht. :) 
 * Hiermit kannst du die Bilder auf dem SpriteSheets laden. 
 *  
 * Lade erst das SpriteSheet in ein Object (Obj = new SpriteSheet() ) 
 * Per grabSprite() "schneidest" du dir das Bild raus.(var = Obj.grabSprite() )
 * 
 * [17.12.14,Flo]: SpriteSheet Map loader
 * 
 * 
 *  */
public class SpriteSheet {
	
	public BufferedImage ccImageFile = null;
	
	public SpriteSheet(BufferedImage xSpriteSheet){
		this.ccImageFile = xSpriteSheet;
	}
	
	public BufferedImage grabSprite(int xPosX, int xPosY, int xWidth, int xHeight){
		BufferedImage Sprite = ccImageFile.getSubimage(xPosX, xPosY, xWidth, xHeight);
		return Sprite;
	}

//--------------------------------------------------------------------------------------------	
	
	
	private String ccPath;
	public int ccSIZE;
	public int[] ccPixel;
	
	public static SpriteSheet ccSpriteSheet = new SpriteSheet("/sprite_sheet.png",512);

	public SpriteSheet(String xPath, int xSize){
		ccPath = xPath;
		ccSIZE = xSize;
		
		ccPixel = new int[ccSIZE * ccSIZE];
		
		loadImage();
	}
	
	private void loadImage(){
		try {
			BufferedImage lImage = ImageIO.read(SpriteSheet.class.getResource(ccPath));
			int lWidth = lImage.getWidth();
			int lHeigth = lImage.getHeight();
			lImage.getRGB( 0, 0, lWidth, lHeigth, ccPixel, 0, lWidth);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
