package ld26_kiasaki_dagothig.entity;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

public class FactoryImpl implements Factory
{
	public int tileXAmount = 24, tileYAmount = 24;
	@Override
	public int getTileXAmount()
	{
		return 0;
	}

	@Override
	public int getTileYAmount() 
	{
		return 0;
	}

	@Override
	public boolean spaceAvailable(int pX, int pY, int pW, int pH) 
	{
		return false;
	}
	
	public Machine entryPoint, exitPoint;
	@Override
	public Machine getEntryPoint() 
	{
		return entryPoint;
	}
	@Override
	public Machine getExitPoint()
	{
		return exitPoint;
	}
	
	private List<Machine> machines = new ArrayList<Machine>();
	@Override
	public List<Machine> getMachines() 
	{
		return machines;
	}

	@Override
	public Machine getMachine(int pX, int pY)
	{
		return null;
	}
	
	@Override
	public void receiveBlock(Block pBlock)
	{
		getEntryPoint().sendBlock(pBlock);
	}
	private List<Block> transformedBlocks = new ArrayList<Block>();
	@Override
	public List<Block> getTransformedBlocks()
	{
		return transformedBlocks;
	}

	@Override
	public void update(int d) throws SlickException 
	{
		for (Machine machine : getMachines())
			machine.update(d);
	}
	@Override
	public void render(int pScrollX, int pScrollY) throws SlickException 
	{
		for (Machine machine : getMachines())
			machine.render(pScrollX, pScrollY);
	}

	public FactoryImpl(int pTileXAmount, int pTileYAmount)
	{
		tileXAmount = pTileXAmount;
		tileYAmount = pTileYAmount;
	}

	@Override
	public void addPipe(int pX, int pY, int pAngle1, int pAngle2)
	{
	}
	@Override
	public void addRouter(int pX, int pY, int pEntryAngle) 
	{
	}
	@Override
	public void addProcessor(int pX, int pY, int pW, int pH, List<BlockShape> pAcceptedShapes, BlockShape pResultShape,	BlockColor pColor) 
	{
	}
	@Override
	public int destroy(Machine pMachine)
	{
		getMachines().remove(pMachine);
		return pMachine.destroy();
	}
}
