package ld26_kiasaki_dagothig.states;

import ld26_kiasaki_dagothig.helpers.KeyListenerImpl;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameOverState extends BasicGameState {

	public static final int ID = 4;
	private GameContainer gc;
	private StateBasedGame sbg;
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.gc = gc;
		this.sbg = sbg;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input tI = container.getInput();
		tI.addKeyListener(new KeyListenerImpl(){
			@Override
			public void keyPressed(int key, char c) {
				if (key == Input.KEY_ESCAPE) {
					exitGame();					
				}
			} 
		});
		
	}

	public void exitGame(){
		sbg.enterState(MenuState.ID, new FadeOutTransition(Color.white, 700), new FadeInTransition(Color.white));
	}

	@Override
	public int getID() {
		return ID;
	}
	
}
