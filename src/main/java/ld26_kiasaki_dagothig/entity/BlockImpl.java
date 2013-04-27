package ld26_kiasaki_dagothig.entity;

import java.awt.Graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BlockImpl extends EntityImpl implements Block
{
	@Override
	public BlockShape getShape() 
	{
		return shape;
	}
	@Override
	public void setShape(BlockShape shape) 
	{
		this.shape = shape;
	}
	
	public BlockShape shape;

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int pScrollX, int pScrollY) throws SlickException
	{
		getImage().x = Math.round(getX() + getW() / 2);
		getImage().y = Math.round(getY() + getH() / 2);
		getImage().color = getColor().computeColor();
		getImage().render(pScrollX, pScrollY);
	}
}
