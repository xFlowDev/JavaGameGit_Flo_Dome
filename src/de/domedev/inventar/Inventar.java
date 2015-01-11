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
	// Item-List. 80 + 4 = 84 Maximal an Items. + Boolischer Wert (1(true) und 0(false))für an und ausgezogen.
	protected int ccMaxItems = 84;
	private int[] ccItemsInInventory = new int[ccMaxItems];
	private Item ccItem;
	
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
	
	private int AutoIncrement() {
		int i;
		for(i = 0; i < ccItemsInInventory.length; i++){
			if(ccItemsInInventory[i] == 0){
				break;
			}	
		}
		System.out.println(i);
		return i;
	}

	public void addItem2Inventar(int xIndex){
		ccItemsInInventory[AutoIncrement()] = xIndex;
		//System.out.println(ccItem.getItem(xIndex));
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
		

		
		int counter = 0;
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 10; y++){
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(ccFillRectX + ccFillRectWidth - (ccFillRectWidth / 2) + (40 * y), ccFillRectY + 20 + (40 * x), 35, 35);

				if(ccItemsInInventory[counter] != 0){					
					g.setColor(Color.WHITE);
					String ItemName = "Name"; /*ccItem.getItemName(ccItemsInInventory[counter]);*/
					System.out.println(ItemName);
					g.drawString(ItemName, ccFillRectX + ccFillRectWidth - (ccFillRectWidth / 2) + (40 * y), ccFillRectY + 40 + (40 * x));
				}else{
					System.out.println(counter);
				}
				counter++;
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
