package de.domedev.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
/* [29.11.14, Dome]
 * ~API
 * Lädt Image. 
 * Benutzt den relativen Pfad der Datei.
 * 
 * */
public class BufferedImageLoader {
		
	public BufferedImage LoadImage(String xDateiPfad) throws IOException {
		URL RelativerPfad = this.getClass().getResource(xDateiPfad);
		BufferedImage img = ImageIO.read(RelativerPfad);
		return img;
	}
	
}
