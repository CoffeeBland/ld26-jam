package ld26_kiasaki_dagothig.entity;

import java.awt.Graphics;

import ld26_kiasaki_dagothig.helpers.BlockImage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public interface Entity 
{
	public float getX();
	public void setX(float x);
	
	public float getY();
	public void setY(float y);
	
	public float getW();
	public void setW(float w);
	
	public float getH();
	public void setH(float h) ;
	
	public BlockImage getImage();
	public void setImage(BlockImage image);
	
	public BlockColor getColor();
	public void setColor(BlockColor color);
	
	public void update(GameContainer gc, StateBasedGame sbg, int d) throws SlickException;
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int pScrollX, int pScrollY) throws SlickException;
}
