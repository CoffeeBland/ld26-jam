package ld26_kiasaki_dagothig.entity;

import java.util.List;

import org.newdawn.slick.SlickException;

public interface Processor extends Machine
{
	public List<BlockShape> getShapeIns();
	public void setShapeIns(List<BlockShape> shapeIns);

	public BlockShape getShapeOut();
	public void setShapeOut(BlockShape shapeOut);

	public BlockColor transform(BlockColor pColor);
	public boolean processBlock(Block pBlock) throws SlickException;
}
