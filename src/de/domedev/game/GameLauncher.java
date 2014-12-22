/*
 * Alles beginnt ab hier. 
 * Das Spiel startet hier und  ein Init wird aufgerufen
 * */
package de.domedev.game;

import java.applet.Applet;
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameLauncher extends Applet {

	private static final long serialVersionUID = 318096046124211081L;

	private static Game game = new Game();


	public static void main(String[] args) {

		game.setMinimumSize(Game.ccWindow_DIMENSIONS);
		game.setMaximumSize(Game.ccWindow_DIMENSIONS);
		game.setPreferredSize(Game.ccWindow_DIMENSIONS);

		// Fenster wird erstellt, anhand mit den Daten aus der Game Classe
		game.ccFrame = new JFrame(Game.ccWindow_TITLE);

		game.ccFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.ccFrame.setLayout(new BorderLayout());

		game.ccFrame.add(game, BorderLayout.CENTER);
		game.ccFrame.pack();

		game.ccFrame.setResizable(false);
		game.ccFrame.setLocationRelativeTo(null);
		game.ccFrame.setVisible(true);

		// Starte Game!
		game.startGame();

	}

}
