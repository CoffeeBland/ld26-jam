package ld26_kiasaki_dagothig.entity;

import org.newdawn.slick.SlickException;

public interface Block extends Entity, Comparable<Block>
{
	public BlockShape getShape();
	public void setShape(BlockShape shape) throws SlickException;
}
