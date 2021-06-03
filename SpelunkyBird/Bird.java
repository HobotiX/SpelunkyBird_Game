/***************************************************
	Name:		Daniel Adams
	Class:		CS162 - Winter 2018
	Project:	Final Assignment , Week 09 - 11
	Time:		TT 10am
	Date:		March 20th, 2018
	Program:	Spelunky Bird
	Description:	This script contains information
		on the bird and its behavior. Pulls in
		environmental data and establishes a hit box.
		Also sets up the background and bird images.
***************************************************/
package SpelunkyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

public class Bird implements Updatable, Renderable{
	
	private float
		x,
		y,
		yVel,
		baseYVel = -7.0f,
		gravity = 0.3f;
	private Pipes pipes;
	private int
		scoredPipe = 0,
		xHB = 20,
		yHB = 15;
	public static int score = 0;
	private Font gameFont = new Font("Verdana", Font.BOLD, 20);
	private BufferedImage
		flapUp,
		flapDown,
		bgImage;
	private Color helpColor = new Color(5, 126, 248);

	// sets up the bird sprite.
	public Bird(Pipes pipes) {
		
		resetBird();
		this.pipes = pipes;
		
		try {
			flapUp = Sprite.getSprite("bird_up.png");
			flapDown = Sprite.getSprite("bird_down.png");
			bgImage = Sprite.getSprite("gameBG.png");
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			System.exit(1);
		}
	}
	
	// establishes a start position for the bird
	private void resetBird() {
		x = 100;
		y = 100;
		yVel = baseYVel;
	}
	
	// the birds movement up when spacebar is pressed
	private void flap() {
		yVel = baseYVel;
	}
	
	
	// updates the birds position, sprite, and velocity
	@Override
	public void update(Input input) {
		// bird acceleration equation
		y += yVel;
		yVel += gravity;
		
		// detect the top of the screen
		if (y < 0) {
			y = 0;
			yVel = 0;
		}
		
		// changes the birds velocity.
		if (input.isSpacePressed()) {
			flap();
		}
		
		float[]
			pipeCoords = pipes.getCurrentPipe(),
			islandCoords = pipes.islandLocation();
		float
			pipeX = pipeCoords[0],
			pipeY = pipeCoords[1],
			islandX = islandCoords[0],
			islandY = islandCoords[1];
			
		// Hit Box Detection. Resets the game if it is a hit
		if (((x + xHB) >= pipeX && (x + xHB) <= pipeX + pipes.getPipeWidth())
			&& ((y + yHB) <= pipeY || (y + yHB) >= pipeY + pipes.getPipeVerticalSpacing())
			|| ((x + xHB) >= islandX && (x + xHB) <= islandX + pipes.getIslandWidth())
			&& ((y + yHB) >= islandY && (y + yHB) <= islandY + pipes.getIslandHeight())
			|| ((y + yHB) >= (SpelunkyBird.HEIGHT - 50))) {
			// resets the bird if hit
			pipes.resetPipes();
			resetBird();
			score = 0;
			
		} else {
			// looks for a chance to incrament the score.
			int
				currentPipeID = pipes.getCurrentPipeID();
			score = (scoredPipe != currentPipeID) ? score + 1 : score;
			scoredPipe = currentPipeID;
			
		}
	}
	
	// draws the bird, background, and instructions onto the screen
	@Override
	public void render(Graphics2D g, float interpolation) {
		g.setColor(helpColor);
		// draws background image
		g.drawImage(bgImage, 0, 0, null);
		// draws text instructions
		g.setFont(gameFont);
		g.drawString("Click Screen & [ SPACEBAR ]", 20, 30);
		g.drawString("SPELUNKY BIRD!", 584, 30);
		// draws bird
		g.drawImage(yVel <= 0 ? flapUp : flapDown, (int) x, (int) (y + (yVel * interpolation)), null);
		
		
	}
}