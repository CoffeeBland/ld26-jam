package ld26_kiasaki_dagothig.entity;

import org.newdawn.slick.SlickException;

public interface Pipe extends Machine
{
	public int getAngleOut();
	public void setAngleOut(int pAngle);
	
	public void calculateSprite() throws SlickException;
}
