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
	public float rotation;
	
	@Override
	public void changeDirection() 
	{
		for (int index = 0; index < 4; index++)
		{
			rotation += 90;
			if (getPossibleOut(rotation) != null)
				return;
		}
		setOut(getPossibleOut(rotation), true);
	}
	
	@Override
	public void renderForeground(int pScrollX, int pScrollY) throws SlickException
	{
		super.render(pScrollX, pScrollY);
		if (fleche == null)
			fleche = new BlockImage(BlockImage.getImage("Fleche.png"));
		fleche.image.setCenterOfRotation(fleche.w / 2, fleche.h / 2);
		fleche.image.setRotation(rotation);
		fleche.x = Math.round(getX());
		fleche.y = Math.round(getY());
		fleche.render(pScrollX, pScrollY);
	}
}
