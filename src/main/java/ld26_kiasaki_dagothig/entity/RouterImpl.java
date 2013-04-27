package ld26_kiasaki_dagothig.entity;

import java.awt.Graphics;
import java.util.TreeMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

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
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int pScrollX, int pScrollY) throws SlickException
	{
		super.render(gc, sbg, g, pScrollX, pScrollY);
		fleche.image.setCenterOfRotation(fleche.w, fleche.h);
		fleche.image.setRotation(rotation);
		fleche.render(pScrollX, pScrollY);
	}
}
