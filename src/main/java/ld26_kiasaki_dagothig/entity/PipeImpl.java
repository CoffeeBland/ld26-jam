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
		switch (getAngleOut())
		{
			case 0:
				outX = (int)(getX() + getW() - TileBased.TILE_SIZE / 2);
				outY = (int)(getY() + getH() / 2 - TileBased.TILE_SIZE / 2);
				break;
			case 90:
				outX = (int)(getX() + getW() / 2 - TileBased.TILE_SIZE / 2);
				outY = (int)(getY() + getH() - TileBased.TILE_SIZE / 2);
				break;
			case 180:
				outX = (int)(getX() - TileBased.TILE_SIZE / 2);
				outY = (int)(getY() + getH() / 2 - TileBased.TILE_SIZE / 2);
				break;
			case 270:
				outX = (int)(getX() + getW() / 2 - TileBased.TILE_SIZE / 2);
				outY = (int)(getY() - TileBased.TILE_SIZE / 2);
				break;
		}
	}
	@Override
	public void setAngle(int pAngle)
	{
		super.setAngle(pAngle % 360);
		switch (getAngle())
		{
			case 0:
				entryX = (int)(getX() + getW() - TileBased.TILE_SIZE / 2);
				entryY = (int)(getY() + getH() / 2 - TileBased.TILE_SIZE / 2);
				break;
			case 90:
				entryX = (int)(getX() + getW() / 2 - TileBased.TILE_SIZE / 2);
				entryY = (int)(getY() + getH() - TileBased.TILE_SIZE / 2);
				break;
			case 180:
				entryX = (int)(getX() - TileBased.TILE_SIZE / 2);
				entryY = (int)(getY() + getH() / 2 - TileBased.TILE_SIZE / 2);
				break;
			case 270:
				entryX = (int)(getX() + getW() / 2 - TileBased.TILE_SIZE / 2);
				entryY = (int)(getY() - TileBased.TILE_SIZE / 2);
				break;
		}
	}
	
	public void calculateSprite() throws SlickException
	{
		int angle = getAngleOut() - getAngle();
		while (angle < 0)
			angle += 360;
		setImage(new BlockImage(BlockImage.getImage("Pipe_" + (angle) + ".png")));
		setForeGround(new BlockImage(BlockImage.getImage("PipeForeground_" + (angle) + ".png")));
	}
	
	@Override
	public void renderForeground(int pScrollX, int pScrollY) throws SlickException
	{
		
	}
}
