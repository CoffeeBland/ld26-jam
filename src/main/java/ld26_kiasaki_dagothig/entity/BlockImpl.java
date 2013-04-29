package ld26_kiasaki_dagothig.entity;

import ld26_kiasaki_dagothig.helpers.BlockImage;

import org.newdawn.slick.SlickException;

public class BlockImpl extends EntityImpl implements Block
{
	@Override
	public BlockShape getShape() 
	{
		return shape;
	}
	@Override
	public void setShape(BlockShape shape) throws SlickException
	{
		this.shape = shape;
		this.image = new BlockImage(BlockImage.getImage("Shape_" + shape.name() + ".png"));
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
	
	@Override
	public boolean equals(Object o){
		if (o instanceof Block){
			if (((Block) o).getColor().equals(this.color) && ((Block) o).getShape().equals(this.shape)){
				return true;
			}
		}
		return false;
	}
	@Override
	public int compareTo(Block o) {
		return -1;
	}
	
}
