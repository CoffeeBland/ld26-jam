package ld26_kiasaki_dagothig.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {

	public static final int ID = 3;
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.setMinimumLogicUpdateInterval(1000/60);
		gc.setMaximumLogicUpdateInterval(1000/60);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, gc.getWidth(), gc.getWidth());
		//world.render(gc, sbg, g);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//world.update(gc, sbg, delta);
	}

	public int getID() {
		return ID;
	}

}
