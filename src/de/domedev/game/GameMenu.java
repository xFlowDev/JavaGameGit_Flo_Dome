package de.domedev.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/* [29.11.14, Dome]
 * Rein für das Menü zuständig.
 * Leider nicht Dynamisch mit verschiedene Fenstergrößen. (ToDo?) 
 * 
 * Es werden nur Boxen mit Text erstellt. Richtige Buttons sind es nicht.
 * */
public class GameMenu {
	
	// Buttons erstellen
	public Rectangle StartButton = new Rectangle(Game.ccWindow_WIDTH / 2 - 100, 150, 220, 50);
	public Rectangle OptionButton = new Rectangle(Game.ccWindow_WIDTH / 2 - 100, 225, 220, 50);
	public Rectangle HelpButton = new Rectangle(Game.ccWindow_WIDTH / 2 - 100, 300, 220, 50);
	public Rectangle ExitButton = new Rectangle(Game.ccWindow_WIDTH / 2 - 100, 375, 220, 50);
	
	public void renderMenu(Graphics g){
		Graphics2D g2d = (Graphics2D) g; 
		
		// Titel ausgeben
		Font fnt0 = new Font("Arial", Font.BOLD, 25);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString(Game.ccWindow_TITLE, Game.ccWindow_WIDTH / 3, 100);
		
		// Buttons ausgeben
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Spiel starten", StartButton.x + 15, StartButton.y + 35);
		g2d.draw(StartButton);
		g.drawString("Optionen", OptionButton.x + 15, OptionButton.y + 35);
		g2d.draw(OptionButton);
		g.drawString("Hilfe", HelpButton.x + 15, HelpButton.y + 35);
		g2d.draw(HelpButton);
		g.drawString("Spiel beenden", ExitButton.x + 15, ExitButton.y + 35);
		g2d.draw(ExitButton);

	}
		
}
