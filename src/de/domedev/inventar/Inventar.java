package de.domedev.inventar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Inventar {
	private int ccGold;
	private boolean ccShowInventar = false; 
	private int ccFillRectX = 50,  
				ccFillRectY = 150,
				ccFillRectWidth = 850, 
				ccFillRectHeight = 350;
	
	public Inventar(){
		setGold(0);
	}

	public int getGold() {
		return ccGold;
	}

	public void setGold(int ccGold) {
		this.ccGold = ccGold;
	}

	public boolean isShowInventar() {
		return ccShowInventar;
	}

	public void setShowInventar(boolean ccShowInventar) {
		this.ccShowInventar = ccShowInventar;
	}

	public void ShowInventar(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 

		
		g.setColor(Color.GRAY); 
		g.fillRect(ccFillRectX, ccFillRectY, ccFillRectWidth, ccFillRectHeight);
		/* Titel */
		g.setColor(Color.LIGHT_GRAY); 
		g.fillRect(ccFillRectX, ccFillRectY, ccFillRectWidth, 12);
		g.setColor(Color.BLACK);
		Font fnt1 = new Font("arial", Font.BOLD, 12);
		g.setFont(fnt1);
		g.drawString("Inventar", ccFillRectWidth / 2 + 60, ccFillRectY + 10);
		
		/* Links - Angezogene Sachen */
		g.setColor(Color.LIGHT_GRAY); 
		g.fillRect(ccFillRectX + 10 , ccFillRectY + 20, 390, ccFillRectHeight - 35);
		
		
		/* Rechts - Itemtasche*/
		g.setColor(Color.LIGHT_GRAY);
		for(int i = 0; i < 8; i++){
			for(int x = 0; x < 10; x++){
				g.fillRect(ccFillRectX + ccFillRectWidth - (ccFillRectWidth / 2) + (40 * x), ccFillRectY + 20 + (40 * i), 35, 35);
			}
		}
		
		
		
		//g.drawString("Inventar", ccFillRectX + 15, ccFillRectY + 25);
		
		/* Gold anzeige 
		Font fnt2 = new Font("arial", Font.BOLD, 15);
		g.setFont(fnt2);
		g.drawString("Gold: " + getGold(), ccFillRectX + 15, ccFillRectY + 40);
		*/
		
		
	}
	
}
