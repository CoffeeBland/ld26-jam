package ld26_kiasaki_dagothig.entity;

import java.util.TreeMap;

import org.newdawn.slick.SlickException;

import ld26_kiasaki_dagothig.helpers.BlockImage;

public class RouterImpl extends MachineImpl implements Router
{
	public TreeMap<Float, Machine> outs = new TreeMap<Float, Machine>();

	@Override
	public Machine getPossibleOut(float pAngle) 
	{
		return outs.get(pAngle);
	}
	@Override
	public void setPossibleOut(float pAngle, Machine pMachine) 
	{
		outs.put(pAngle, pMachine);
	}

	public BlockImage fleche;
	
	@Override
	public void changeDirection() 
	{
		for (int index = 0; index < 4; index++)
		{
			angleOut += 90;
			if (getPossibleOut(angleOut) != null)
				return;
		}
		setOut(getPossibleOut(angleOut), true);
	}
	
	@Override
	public void renderForeground(int pScrollX, int pScrollY) throws SlickException
	{
		super.render(pScrollX, pScrollY);
		if (fleche == null)
			fleche = new BlockImage(BlockImage.getImage("Fleche.png"));
		fleche.image.setCenterOfRotation(fleche.w / 2, fleche.h / 2);
		fleche.image.setRotation(angleOut);
		fleche.x = Math.round(getX());
		fleche.y = Math.round(getY());
		fleche.render(pScrollX, pScrollY);
	}
	
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
	@Override
	public void calculateSprite() throws SlickException
	{
		setImage(new BlockImage(BlockImage.getImage("Router.png")));
		setForeGround(new BlockImage(BlockImage.getImage("RouterForeGround.png")));
	}
}
