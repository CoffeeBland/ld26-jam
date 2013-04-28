package ld26_kiasaki_dagothig.states;

import ld26_kiasaki_dagothig.helpers.ColorTools;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class LoadingState extends BasicGameState {

	public static final int ID = 50;

    public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {

    }

    public void update(GameContainer container, StateBasedGame sbg, int deltaMS) throws SlickException {
    	sbg.enterState(LogoState.ID, new FadeOutTransition(Color.white, 700), new FadeInTransition(Color.white));
    }

	public int getID() {
		return ID;
	}

}
