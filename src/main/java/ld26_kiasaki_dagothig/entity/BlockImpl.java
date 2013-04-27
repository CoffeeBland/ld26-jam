package ld26_kiasaki_dagothig.entity;

import org.newdawn.slick.SlickException;

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
	public void render(int pScrollX, int pScrollY) throws SlickException
	{
		getImage().x = Math.round(getX() + getW() / 2);
		getImage().y = Math.round(getY() + getH() / 2);
		getImage().color = getColor().computeColor();
		getImage().render(pScrollX, pScrollY);
	}
}
