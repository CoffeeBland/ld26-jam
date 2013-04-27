package ld26_kiasaki_dagothig.entity;

import ld26_kiasaki_dagothig.helpers.BlockImage;

import org.newdawn.slick.SlickException;

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
	
	public int getAngle();
	public void setAngle(int pAngle);
	
	public BlockImage getImage();
	public void setImage(BlockImage image);
	
	public BlockColor getColor();
	public void setColor(BlockColor color);
	
	public void update(int d) throws SlickException;
	public void render(int pScrollX, int pScrollY) throws SlickException;
}
