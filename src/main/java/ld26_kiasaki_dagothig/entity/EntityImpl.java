package ld26_kiasaki_dagothig.entity;

import ld26_kiasaki_dagothig.helpers.BlockImage;

import org.newdawn.slick.SlickException;

public abstract class EntityImpl implements Entity 
{
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public float getW() {
		return w;
	}
	public void setW(float w) {
		this.w = w;
	}
	
	public float getH() {
		return h;
	}
	public void setH(float h) {
		this.h = h;
	}
	
	public BlockImage getImage() {
		return image;
	}
	public void setImage(BlockImage image) {
		this.image = image;
	}
	
	public BlockColor getColor()
	{
		return color;
	}
	public void setColor(BlockColor color)
	{
		this.color = color;
	}
	
	public float x = 0;
	public float y = 0;
	public float w = 0;
	public float h = 0;
	
	public BlockImage image = null;
	public BlockColor color = null;
	
	@Override
	public void update(int d) throws SlickException
	{
		getImage().update(d);
	}
	@Override
	public void render(int pScrollX, int pScrollY) throws SlickException
	{
		getImage().x = Math.round(x);
		getImage().y = Math.round(y);
		getImage().color = getColor().computeColor();
		getImage().render(pScrollX, pScrollY);
	}
}
