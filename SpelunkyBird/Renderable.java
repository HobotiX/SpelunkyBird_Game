/***************************************************
	Name:		Daniel Adams
	Class:		CS162 - Winter 2018
	Project:	Final Assignment , Week 09 - 11
	Time:		TT 10am
	Date:		March 20th, 2018
	Program:	Spelunky Bird
	Description:	This script renders each frame
		as it comes in. the bird, pipes, and hud
		pass through this script to be displayed on
		screen.
***************************************************/
package SpelunkyBird;

import java.awt.Graphics2D;

public interface Renderable {
	// Graphics2D pulls a graphics package, interpolation looks at the speed of the computer.
	public void render(Graphics2D g, float interpolation);
	
}