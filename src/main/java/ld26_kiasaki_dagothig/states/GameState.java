package ld26_kiasaki_dagothig.states;

import ld26_kiasaki_dagothig.helpers.KeyListenerImpl;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameState extends BasicGameState {

	public static final int ID = 3;
	private GameContainer gc;
	private StateBasedGame sbg;
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.gc = gc;
		this.sbg = sbg;
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
		Input tI = gc.getInput();
		tI.addKeyListener(new KeyListenerImpl(){
			@Override
			public void keyPressed(int key, char c) {
				if (key == Input.KEY_ESCAPE) {
					exitGame();					
				}
			} 
		});
		//world.update(gc, sbg, delta);
	}
	
	public void exitGame(){
		sbg.enterState(GameOverState.ID, new FadeOutTransition(Color.white, 700), new FadeInTransition(Color.white));
	}

	public int getID() {
		return ID;
	}

}
