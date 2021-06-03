/***************************************************
	Name:		Daniel Adams
	Class:		CS162 - Winter 2018
	Project:	Final Assignment , Week 09 - 11
	Time:		TT 10am
	Date:		March 20th, 2018
	Program:	Spelunky Bird
	Description:	This script sets up the hud
		that tracks your progress through the game
		and saves your high score. It then displays
		this above the background in the game.
***************************************************/
package SpelunkyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Hud implements Updatable, Renderable{
	private Font gameFont = new Font("Verdana", Font.BOLD, 20);
	private Bird bird;
	private int highScore = 0;
	private Color scoreColor = new Color(249, 228, 117);
					
	public Hud() {}
	
	@Override
		public void update(Input input) {
			// saves high score.
			if (bird.score > highScore) {
				highScore++;
			}
		}
		
	@Override
	public void render(Graphics2D g, float interpolation) {
		// draws the hud on screen
		g.setColor(scoreColor);
		g.setFont(gameFont);
		g.drawString("Score: " + bird.score, ((SpelunkyBird.WIDTH / 2) - 200), (SpelunkyBird.HEIGHT - 30));
		g.drawString("/ High Score: " + highScore, (SpelunkyBird.WIDTH / 2), (SpelunkyBird.HEIGHT - 30));	
	}
}