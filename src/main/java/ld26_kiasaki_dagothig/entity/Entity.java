package ld26_kiasaki_dagothig.entity;

import java.awt.Graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public interface Entity {

	public float x = 0;
	public float y = 0;
	public float w = 0;
	public float h = 0;
	
	public void update(GameContainer gc, StateBasedGame sbg, int d) throws SlickException;
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException;
}
