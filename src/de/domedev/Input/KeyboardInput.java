package de.domedev.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * #[11.12.14, Florian]
 * Keylistener und Bewegung der spieler hinzugef�gt 
 * die Richtungsvariable wird immer dann auf true gesetzt wenn die zugewiesene Taste gedr�ckt wird in der Game
 * Klasse wird dann in der Methode tick() ein Abfrage gemacht welche denn True ist und dann wird die position
 * der figur ver�ndert je nachdem welche taste gedr�ckt wurde
 * 
 * */

public class KeyboardInput implements KeyListener {

	private boolean[] ccKeys = new boolean[126]; // Festlegen wie viele Tasten verf�gbar sind
	public boolean ccUp, ccDown, ccRight, ccLeft, ccSpace, ccI, ccShift, ccE;

	public void checkIfKeyPressed() {
		ccUp = ccKeys[KeyEvent.VK_UP] || ccKeys[KeyEvent.VK_W];
		ccDown = ccKeys[KeyEvent.VK_DOWN] || ccKeys[KeyEvent.VK_S];
		ccRight = ccKeys[KeyEvent.VK_RIGHT] || ccKeys[KeyEvent.VK_D];
		ccLeft = ccKeys[KeyEvent.VK_LEFT] || ccKeys[KeyEvent.VK_A];
		ccSpace = ccKeys[KeyEvent.VK_SPACE];
		ccI = ccKeys[KeyEvent.VK_I];
		ccShift = ccKeys[KeyEvent.VK_SHIFT];
		ccE = ccKeys[KeyEvent.VK_E];

	}

	public void keyPressed(KeyEvent xEvent) {
		ccKeys[xEvent.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent xEvent) {
		ccKeys[xEvent.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent xEvent) {	
//		System.out.println(ccKeys[xEvent.getKeyCode()]);
//		if(xEvent.getKeyCode() == KeyEvent.VK_I){
//			System.out.println(ccKeys[KeyEvent.VK_I]);
//			if(!ccKeys[KeyEvent.VK_I]){
//				ccKeys[KeyEvent.VK_I] = true;
//			}	 
//		}else{
//			ccKeys[xEvent.getKeyCode()] = false;
//		}
	}
}
