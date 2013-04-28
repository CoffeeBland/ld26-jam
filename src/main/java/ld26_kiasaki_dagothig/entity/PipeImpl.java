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
		angleOut = pAngle;
	}
	
	public void calculateSprite() throws SlickException
	{
		int angle = getAngleOut() - getAngle();
		while (angle < 0)
			angle += 360;
		setImage(new BlockImage(BlockImage.getImage("Pipe_" + (angle) + ".png")));
		setForeGround(new BlockImage(BlockImage.getImage("PipeForeground_" + (angle) + ".png")));
	}
}
