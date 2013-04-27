package ld26_kiasaki_dagothig.helpers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public interface Renderable {

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException;
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException;
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException;
	
}
