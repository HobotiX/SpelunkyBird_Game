/***************************************************
	Name:		Daniel Adams
	Class:		CS162 - Winter 2018
	Project:	Final Assignment , Week 09 - 11
	Time:		TT 10am
	Date:		March 20th, 2018
	Program:	Spelunky Bird
	Description:	This script sets up the key press
		listener which lets the game be opperated with
		a press of the space bar.
***************************************************/
package SpelunkyBird;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
	
	private boolean
		spacePressed = false,
		spaceReleased = true;
	
	public boolean isSpacePressed() {
		boolean
			s = spacePressed;
		spacePressed = false;
		return s;
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_SPACE && spaceReleased) {
			spacePressed = true;
			spaceReleased = false;
		} 
	}
	
	@Override
	public void keyReleased(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
			spaceReleased = true;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent ke) {}
	
}