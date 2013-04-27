package ld26_kiasaki_dagothig.entity;

import java.util.ArrayList;
import java.util.List;

import ld26_kiasaki_dagothig.helpers.BlockImage;

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
	public boolean spaceAvailable(int pTileX, int pTileY, int pTileW, int pTileH) 
	{
		for (int tileX = pTileX; tileX < pTileX + pTileW; tileX++)
			for (int tileY = pTileY; tileY < pTileY + pTileH; tileY++)
				if (getMachine(tileX, tileY) != null)
					return false;
		return true;
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
	public Machine getMachine(int pTileX, int pTileY)
	{
		for (Machine machine : getMachines())
			if (pTileX + 1 > machine.getTileX() && pTileX < machine.getTileX() + machine.getTileWidth() &&
				pTileY + 1 > machine.getTileY() && pTileY < machine.getTileY() + machine.getTileHeight())
				return machine;
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
	public void addPipe(int pTileX, int pTileY, int pEntryAngle, int pExitAngle) throws SlickException
	{
		Machine pipe = new MachineImpl();
		int angle = pExitAngle - pEntryAngle;
		while (angle < 0)
			angle += 360;
		pipe.setImage(new BlockImage(BlockImage.getImage("Pipe_" + (angle) + ".png")));
		pipe.setTileX(pTileX);
		pipe.setTileY(pTileY);
		pipe.setTileWidth(1);
		pipe.setTileHeight(1);
		pipe.setAngle(pEntryAngle);
		switch (pEntryAngle)
		{
			case 0:
				pipe.setIn(getMachine(pTileX + 1, pTileY), true);
				break;
			case 90:
				pipe.setIn(getMachine(pTileX, pTileY + 1), true);
				break;
			case 180:
				pipe.setIn(getMachine(pTileX - 1, pTileY), true);
				break;
			case 270:
				pipe.setIn(getMachine(pTileX, pTileY - 1), true);
				break;
		}
		switch (pExitAngle)
		{
			case 0:
				pipe.setOut(getMachine(pTileX + 1, pTileY), true);
				break;
			case 90:
				pipe.setOut(getMachine(pTileX, pTileY + 1), true);
				break;
			case 180:
				pipe.setOut(getMachine(pTileX - 1, pTileY), true);
				break;
			case 270:
				pipe.setOut(getMachine(pTileX, pTileY - 1), true);
				break;
		}
		getMachines().add(pipe);
	}
	@Override
	public void addRouter(int pTileX, int pTileY, int pEntryAngle) throws SlickException 
	{
		Router router = new RouterImpl();
		router.setImage(new BlockImage(BlockImage.getImage("Router.png")));
		router.setTileX(pTileX);
		router.setTileY(pTileY);
		router.setTileWidth(1);
		router.setTileHeight(1);
		router.setAngle(pEntryAngle);
		switch (pEntryAngle)
		{
			case 0:
				router.setIn(getMachine(pTileX + 1, pTileY), true);
				break;
			case 90:
				router.setIn(getMachine(pTileX, pTileY + 1), true);
				break;
			case 180:
				router.setIn(getMachine(pTileX - 1, pTileY), true);
				break;
			case 270:
				router.setIn(getMachine(pTileX, pTileY - 1), true);
				break;
		}
		if (pEntryAngle != 0)
			router.setPossibleOut(0, getMachine(pTileX + 1, pTileY));
		if (pEntryAngle != 90)
			router.setPossibleOut(0, getMachine(pTileX, pTileY + 1));
		if (pEntryAngle != 180)
			router.setPossibleOut(0, getMachine(pTileX - 1, pTileY));
		if (pEntryAngle != 270)
			router.setPossibleOut(0, getMachine(pTileX, pTileY - 1));
		router.changeDirection();
		getMachines().add(router);

	}
	@Override
	public void addProcessor(int pTileX, int pTileY, int pTileW, int pTileH, List<BlockShape> pAcceptedShapes, BlockShape pResultShape,	BlockColor pColor) throws SlickException 
	{
		Processor processor = new ProcessorImpl();
		processor.setImage(new BlockImage(BlockImage.getImage("Processor_" + pTileW + "x" + pTileH + ".png")));
		processor.setTileX(pTileX);
		processor.setTileY(pTileY);
		processor.setTileWidth(pTileW);
		processor.setTileHeight(pTileH);
		processor.setShapeIns(pAcceptedShapes);
		processor.setShapeOut(pResultShape);
		processor.setColor(pColor);
	}
	@Override
	public int destroy(Machine pMachine)
	{
		getMachines().remove(pMachine);
		return pMachine.destroy();
	}
}
