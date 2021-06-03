/***************************************************
	Name:		Daniel Adams
	Class:		CS162 - Winter 2018
	Project:	Final Assignment , Week 09 - 11
	Time:		TT 10am
	Date:		March 20th, 2018
	Program:	Spelunky Bird
	Description:	This script contains the JFrame
		information and sets up the primary game
		loop that this program will use to run. The
		commented out sections were an attempt to 
		add a start button, but stopped due to time.
***************************************************/
package SpelunkyBird;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.event.*;

// --- Start Game ---
public class SpelunkyBird {
	public final static int
		WIDTH = 800,
		HEIGHT = 600;
	private String gameName = "Spelunky Bird";
	private Canvas game = new Canvas();	
	private Input input;
	private ArrayList<Updatable> updatables = new ArrayList<>();
	private ArrayList<Renderable> renderables = new ArrayList<>();
		
	public void addUpdatable(Updatable u) {
		updatables.add(u);
	}
	public void removeUpdatable(Updatable u) {
		updatables.remove(u);
	}
	public void addRenderable(Renderable r) {
		renderables.add(r);
	}
	public void removeRenderable(Renderable r) {
		renderables.remove(r);
	}
	
	// Starts the game when it is called.
	public void start() {
		// --- Initialize Game Windows ---
		Dimension gameSize = new Dimension(SpelunkyBird.WIDTH, SpelunkyBird.HEIGHT);
		JFrame gameWindow = new JFrame(gameName);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setSize(gameSize);
		gameWindow.setResizable(false);
		game.setSize(gameSize);
		game.setMinimumSize(gameSize);
		game.setMaximumSize(gameSize);
		game.setPreferredSize(gameSize);
		gameWindow.add(game);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);
		
		// --- Initialize Imput ---
		input = new Input();
		game.addKeyListener(input);
		
		// --- Game Loop ---
		final int 
			TICKS_PER_SECOND = 60,
			SKIP_TICKS = 1000 / TICKS_PER_SECOND,
			MAX_FRAMESKIPS = 5;	
		long
			nextGameTick = System.currentTimeMillis(),
			timeAtLastFPSCheck = 0;
		int
			loops,
			ticks = 0;
		float interpolation;
		boolean running = true;
			
		while(running) {
			loops = 0;
				
			// --- Updating ---
			while (System.currentTimeMillis() > nextGameTick && loops < MAX_FRAMESKIPS) {
				update();
				ticks++;	
				nextGameTick += SKIP_TICKS;
				loops++;
			}
					
			// --- Rendering ---
			interpolation = (float) (System.currentTimeMillis() + SKIP_TICKS - nextGameTick) / (float) SKIP_TICKS;
			render(interpolation);
				
			// --- FPS Checker ---
			if (System.currentTimeMillis() - timeAtLastFPSCheck >= 1000) {
				System.out.println("FPS: " + ticks);
				gameWindow.setTitle(gameName + " - FPS: " + ticks);
				ticks = 0;
				timeAtLastFPSCheck = System.currentTimeMillis();
			}
		} 

		// --- Game End ---
	}
	
	// updates the objects positions
	private void update() {
		for (Updatable u : updatables) {
			u.update(input);
		}
	}
	
	// renders the objects
	private void render(float interpolation) {
		BufferStrategy bs = game.getBufferStrategy();
		if (bs == null) {
			game.createBufferStrategy(2);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.clearRect(0, 0, game.getWidth(), game.getHeight());
		
		for (Renderable r : renderables) {
			r.render(g, interpolation);
		}
		g.dispose();
		bs.show();
	}
}