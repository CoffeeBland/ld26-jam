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

public class LogoState extends BasicGameState {

	public static final int ID = 1;
	
	Image logoImage;
    final long logoDuration = 2000;
    long startTime;
    Color colMult = new Color(Color.white);

    public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
        logoImage = new Image("res/logo.png");
        startTime = container.getTime();
        colMult.a = 0;
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.fillRect(0, 0, container.getWidth(), container.getHeight());
        logoImage.setColor(0, colMult.r, colMult.g, colMult.b, colMult.a);
        logoImage.setColor(1, colMult.r, colMult.g, colMult.b, colMult.a);
        logoImage.setColor(2, colMult.r, colMult.g, colMult.b, colMult.a);
        logoImage.setColor(3, colMult.r, colMult.g, colMult.b, colMult.a);
        logoImage.draw(container.getWidth() / 2 - logoImage.getWidth() / 2, container.getHeight() / 2 - logoImage.getHeight() / 2);
    }

    public void update(GameContainer container, StateBasedGame sbg, int deltaMS) throws SlickException {
    	ColorTools.visualSeekAlpha(colMult, 1.0f, 0.02f);
    	Input input = container.getInput();
        boolean skipToStart = input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || input.isKeyDown(Input.KEY_SPACE) || input.isKeyDown(Input.KEY_ESCAPE);
        boolean goToStartScreen = startTime + logoDuration < container.getTime();
        if (skipToStart || goToStartScreen) {
            sbg.enterState(MenuState.ID, new FadeOutTransition(Color.white, 700), new FadeInTransition(Color.white));
        }
    }
    
    @Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	startTime = gc.getTime();
	}

	public int getID() {
		return ID;
	}

}
