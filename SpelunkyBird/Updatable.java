/***************************************************
	Name:		Daniel Adams
	Class:		CS162 - Winter 2018
	Project:	Final Assignment , Week 09 - 11
	Time:		TT 10am
	Date:		March 20th, 2018
	Program:	Spelunky Bird
	Description:	This script updates the elements
		for each frame. The bird, pipes, and hud
		pass through this script to have their elements
		be updated.
***************************************************/
package SpelunkyBird;

public interface Updatable {
	
	public void update(Input input);
	
}