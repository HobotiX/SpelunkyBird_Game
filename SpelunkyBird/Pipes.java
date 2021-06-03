/***************************************************
	Name:		Daniel Adams
	Class:		CS162 - Winter 2018
	Project:	Final Assignment , Week 09 - 11
	Time:		TT 10am
	Date:		March 20th, 2018
	Program:	Spelunky Bird
	Description:	This script sets up the pipes
		and established how they are spawned as well
		as how they move. Also created a floating
		opsticle that must be avoided.
***************************************************/
package SpelunkyBird;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Random;

public class Pipes implements Updatable, Renderable{
	
	private int
		pipeWidth = 420,
		pipeVerticalSpacing = 300,
		islandWidth = 100,
		islandHeight = 50,
		currentPipe;	// The pipe that is closest to the bird
	private float
		xVel = -5.0f,
		x1, x2, x3,xi,
		y1, y2, y3, yi;
	private float[] islandCoords = new float[2];
	private float[][] pipeCoords = new float[4][2];	// An array to hold the pipes coordinates
	private Random rando;	// Randomly generate pipe locations
	private Color pipeColor = new Color(5, 126, 248);
	
	public Pipes() {
		rando = new Random();
		resetPipes();
	}
	
	// This resets the pipes when the game comes to an end and spawns in the new pipes.
	public void resetPipes() {
		currentPipe = 0;
		
		// starting positions
		x1 = SpelunkyBird.WIDTH * 2;
		x2 = x1 + pipeWidth;
		x3 = x2 + pipeWidth;
		xi = SpelunkyBird.WIDTH + (SpelunkyBird.WIDTH / 2); 
		
		// getting new random positions
		y1 = getRandomY();
		y2 = getRandomY();
		y3 = getRandomY();
		yi = getRandomY();
	}
	
	// These are set up to be called by Bird.js
	public float[] getCurrentPipe() {
		return pipeCoords[currentPipe];
	}
	public int getCurrentPipeID() {
		return currentPipe;
	}
	public int getPipeWidth() {
		return pipeWidth;
	}
	public int getPipeVerticalSpacing() {
		return pipeVerticalSpacing;
	}
	public float[] islandLocation() {
			return islandCoords;
		}
	public int getIslandWidth() {
		return islandWidth;
	}
	public int getIslandHeight() {
		return islandHeight;
	}
	
	// Random height for the cave
	private int getRandomY() {
		return rando.nextInt((int)(SpelunkyBird.HEIGHT * 0.30f)) + (SpelunkyBird.HEIGHT / 10);
	}
	// Random height for the obsticle
	private int getRandomIsland() {
		return rando.nextInt(SpelunkyBird.HEIGHT) + islandHeight;
	}
	
	@Override
	public void update(Input input) {
		// Advances all of the pipes locations by 1
		x1 += xVel;
		x2 += xVel;
		x3 += xVel;
		xi += xVel;
		
		// Detexts when the pipe goes off screen and makes a new one.
		if (x1 + pipeWidth < 0) {
			x1 = SpelunkyBird.WIDTH;
			y1 = getRandomY();
		}
		if (x2 + pipeWidth < 0) {
			x2 = SpelunkyBird.WIDTH;
			y2 = getRandomY();
		}
		if (x3 + pipeWidth < 0) {
			x3 = SpelunkyBird.WIDTH;
			y3 = getRandomY();
		}
		if (xi + islandWidth < 0) {
			xi = SpelunkyBird.WIDTH;
			yi = getRandomIsland();
		}
		
		if (x1 + pipeWidth < 150) {
			currentPipe = 1;
		}
		if (x2 + pipeWidth < 150) {
			currentPipe = 2;
		}
		if (x3 + pipeWidth < 150) {
			currentPipe = 0;
		}
		
		// Update the pipe coordinates for hit box
		switch (currentPipe) {
			case 0:
				pipeCoords[0][0] = x1;
				pipeCoords[0][1] = y1;
				break;
			case 1:
				pipeCoords[1][0] = x2;
				pipeCoords[1][1] = y2;
				break;
			case 2:
				pipeCoords[2][0] = x3;
				pipeCoords[2][1] = y3;
				break;
		}
		// update the obsticles coordinates for hit box
		islandCoords[0] = xi;
		islandCoords[1] = yi;
	}
	
	// Draws the boxes
	@Override
	public void render(Graphics2D g, float interpolation) {
		g.setColor(pipeColor);
		
		// --- Draws Pipes 1 ---
		g.fillRect((int)(x1 + (xVel + interpolation)), 0, pipeWidth, (int) y1);	// Top Pipe
		g.fillRect((int)(x1 + (xVel + interpolation)), (int) (y1 + pipeVerticalSpacing), pipeWidth, SpelunkyBird.HEIGHT);	// Bottom Pipe
		
		// --- Draws Pipes 2 ---
		g.fillRect((int)(x2 + (xVel + interpolation)), 0, pipeWidth, (int) y2);	//Top Pipe
		g.fillRect((int)(x2 + (xVel + interpolation)), (int) (y2 + pipeVerticalSpacing), pipeWidth, SpelunkyBird.HEIGHT);	// Bottom Pipe
		
		// --- Draws Pipes 3 ---
		g.fillRect((int)(x3 + (xVel + interpolation)), 0, pipeWidth, (int) y3);	// Top Pipe
		g.fillRect((int)(x3 + (xVel + interpolation)), (int) (y3 + pipeVerticalSpacing), pipeWidth, SpelunkyBird.HEIGHT);	// Bottom Pipe
		
		// --- Draws Island ---
		g.fillRect((int)(xi + (xVel + interpolation)), (int) yi, islandWidth, islandHeight);
	}
}