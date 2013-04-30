package ld26_kiasaki_dagothig.entity;

import org.newdawn.slick.SlickException;

import ld26_kiasaki_dagothig.helpers.BlockImage;

public class PipeImpl extends MachineImpl implements Pipe
{
	public int angleOut;
	
	@Override
	public int getAngleOut()
	{
		return angleOut;
	}
	@Override
	public void setAngleOut(int pAngle)
	{
		angleOut = pAngle % 360;
	}
	@Override
	public void setAngle(int pAngle)
	{
		super.setAngle(pAngle % 360);
	}
	
	public void calculateSprite() throws SlickException
	{
		int angle = getAngleOut() - getAngle();
		while (angle < 0)
			angle += 360;
		setImage(new BlockImage(BlockImage.getImage("Pipe_" + (angle) + ".png")));
		setForeground(new BlockImage(BlockImage.getImage("PipeForeground_" + (angle) + ".png")));
	}
	
	@Override
	public void renderForeground(int pScrollX, int pScrollY) throws SlickException
	{
		
	}
}
