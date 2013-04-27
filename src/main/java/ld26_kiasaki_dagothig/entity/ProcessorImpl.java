package ld26_kiasaki_dagothig.entity;

import java.util.List;

import org.newdawn.slick.SlickException;

public class ProcessorImpl extends MachineImpl implements Processor
{
	public List<BlockShape> getShapeIns() 
	{
		return shapeIns;
	}
	public void setShapeIns(List<BlockShape> shapeIns)
	{
		this.shapeIns = shapeIns;
	}

	public BlockShape getShapeOut()
	{
		return shapeOut;
	}
	public void setShapeOut(BlockShape shapeOut) 
	{
		this.shapeOut = shapeOut;
	}

	public List<BlockShape> shapeIns = null;
	public BlockShape shapeOut = null;
	public BlockColor transformationColor = null;
	
	@Override
	public BlockColor transform(BlockColor pColor) 
	{
		switch (getColor())
		{
			case Red:
				switch (pColor)
				{
					case Red:
					case Purple:
					case Orange:
					case Brown:
						return BlockColor.Red;
					case Blue:
						return BlockColor.Purple;
					case Yellow:
						return BlockColor.Orange;
					default:
						return BlockColor.Brown;
				}
			case Purple:
				switch (pColor)
				{
					case Red:
					case Purple:
					case Blue:
					case Brown:
						return BlockColor.Purple;
					default:
						return BlockColor.Brown;
				}
			case Blue:
				switch(pColor)
				{
					case Blue:
					case Purple:
					case Green:
					case Brown:
						return BlockColor.Blue;
					case Red:
						return BlockColor.Purple;
					case Yellow:
						return BlockColor.Green;
					default:
						return BlockColor.Brown;
				}
			case Green:
				switch (pColor)
				{
					case Green:
					case Yellow:
					case Blue:
					case Brown:
						return BlockColor.Green;
					default:
						return BlockColor.Brown;
				}
			case Yellow:
				switch(pColor)
				{
					case Yellow:
					case Green:
					case Orange:
					case Brown:
						return BlockColor.Yellow;
					case Red:
						return BlockColor.Orange;
					case Blue:
						return BlockColor.Green;
					default:
						return BlockColor.Brown;
				}
			case Orange:
				switch (pColor)
				{
					case Yellow:
					case Orange:
					case Red:
					case Brown:
						return BlockColor.Orange;
					default:
						return BlockColor.Brown;
				}
			default:
				return BlockColor.Brown;
		}
	}

	@Override
	public boolean processBlock(Block pBlock) throws SlickException
	{
		pBlock.setColor(transform(pBlock.getColor()));
		if (getShapeIns().contains(pBlock.getShape()))
		{
			pBlock.setShape(getShapeOut());
			return true;
		}
		return false;
	}

	@Override
	public void sendBlock(Block pBlock) throws SlickException
	{
		if(processBlock(pBlock))
			super.sendBlock(pBlock);
		else
			setWorking(false);
	}
}
