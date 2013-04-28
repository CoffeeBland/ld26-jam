package ld26_kiasaki_dagothig;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import ld26_kiasaki_dagothig.states.*;

public class GameStateController extends StateBasedGame {

	private boolean inited = false;
	
	public GameStateController(String name) {
		super(name);
	}

	@Override
    public void initStatesList(GameContainer gc) throws SlickException {        
        if (!inited){
        	addState(new LoadingState());
        	addState(new LogoState());
            addState(new MenuState());
            addState(new GameState());
            addState(new GameOverState());	
            enterState(LoadingState.ID);
        }
        inited = true;
    }

}
