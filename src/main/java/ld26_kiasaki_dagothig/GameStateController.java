package ld26_kiasaki_dagothig;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import ld26_kiasaki_dagothig.states.*;

public class GameStateController extends StateBasedGame {

	private GameContainer gc;
	
	public GameStateController(String name) {
		super(name);
	}

	@Override
    public void initStatesList(GameContainer gc) throws SlickException {        
    	addState(new LoadingState());
    	addState(new LogoState());
        addState(new MenuState());
        addState(new GameState());
        addState(new GameOverState());
        addState(new GameWinState());	
        enterState(LoadingState.ID);
        this.gc = gc;
    }
	
	public void enterGameState(int pLevelToLoad)
	{
		enterState(GameState.ID, new FadeOutTransition(Color.black, 500), new FadeInTransition(Color.black));
		((GameState)getState(GameState.ID)).getWorld().load(pLevelToLoad);
	}
	
	public void reinit(){
		try {
			init(gc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
