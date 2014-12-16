package de.domedev.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/* [29.11.14, Dome]
 * Momentan brauchen wir die Maus nur im Menü.
 * Das Modul ist nur für das Menü ausgelegt. Hier haben wir keine API.
 * 
 * Zukünftig ändern? Wie oft brauchen wir noch die Maus? Hmmmm.
 * */
public class MouseInput implements MouseListener {

	private Game game;
	
	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent e) {

		int MouseX = e.getX();
		int MouseY = e.getY();

		/*
		 * x y w h public Rectangle StartButton = new
		 * Rectangle(game.Window_WIDTH / 2 - 100, 150, 220, 50); public
		 * Rectangle OptionButton = new Rectangle(game.Window_WIDTH / 2 - 100,
		 * 225, 220, 50); public Rectangle HelpButton = new
		 * Rectangle(game.Window_WIDTH / 2 - 100, 300, 220, 50); public
		 * Rectangle ExitButton = new Rectangle(game.Window_WIDTH / 2 - 100,
		 * 375, 220, 50);
		 */
		// StartButton
		if (MouseX >= game.ccWindow_WIDTH / 2 - 100
				&& MouseX <= game.ccWindow_WIDTH / 2 + 120) {
			if (MouseY >= 150 && MouseY <= 200) {
				// Button gedrueckt!
				game.STATUS = Game.STATE.GAME;
			}
		}

		// OptionButton
		if (MouseX >= game.ccWindow_WIDTH / 2 - 100
				&& MouseX <= game.ccWindow_WIDTH / 2 + 120) {
			if (MouseY >= 225 && MouseY <= 275) {
				// Button gedrï¿½ckt!
				System.out.println("Option");
			}
		}

		// HelpButton
		if (MouseX >= game.ccWindow_WIDTH / 2 - 100
				&& MouseX <= game.ccWindow_WIDTH / 2 + 120) {
			if (MouseY >= 300 && MouseY <= 350) {
				// Button gedrï¿½ckt!
				System.out.println("Help");
			}
		}

		// ExitButton
		if (MouseX >= game.ccWindow_WIDTH / 2 - 100
				&& MouseX <= game.ccWindow_WIDTH / 2 + 120) {
			if (MouseY >= 375 && MouseY <= 425) {
				// Button gedrï¿½ckt!
				/* stopGame() verursacht Fehler. Keine Ahnung warum => Keine Prio momentan*/
				//game.stopGame();
				System.exit(1);
			}
		}

	}


	public void mouseReleased(MouseEvent arg0) {
	}

}
