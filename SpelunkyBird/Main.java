/***************************************************
	Name:		Daniel Adams
	Class:		CS162 - Winter 2018
	Project:	Final Assignment , Week 09 - 11
	Time:		TT 10am
	Date:		March 20th, 2018
	Program:	Spelunky Bird
	Description:	This script is the main script
		that connects all of the other scripts and
		runs the application. Pulls in hud, bird,
		and pipes.
***************************************************/
package SpelunkyBird;

public class Main {
	// Graphics2D pulls a graphics package, interpolation looks at the speed of the computer.
	public static void main(String[] args) {
		SpelunkyBird g = new SpelunkyBird();
		
		// Initialise game objects
		Pipes p = new Pipes();
		Bird b = new Bird(p);
		Hud h = new Hud();
		
		//add updateables and renerables
		// renders and updates the bird (bottom layer
		g.addRenderable(b);
		g.addUpdatable(b);
		// renders and updates the pipes (middle layer)
		g.addRenderable(p);
		g.addUpdatable(p);
		// remders and updates the hud (top layer)
		g.addRenderable(h);
		g.addUpdatable(h);
		
		//starts
		g.start();
	}
	
}