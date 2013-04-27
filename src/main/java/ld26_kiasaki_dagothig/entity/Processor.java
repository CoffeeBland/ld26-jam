package ld26_kiasaki_dagothig.entity;

import java.util.List;

public interface Processor extends Machine
{
	public List<BlockShape> shapeIns = null;
	public BlockShape shapeOut = null;
	public BlockColor transformationColor = null;
	
	public BlockColor transform(BlockColor pColor);
	public void processBlock(Block pBlock);
}
