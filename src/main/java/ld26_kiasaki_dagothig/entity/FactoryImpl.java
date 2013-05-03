package ld26_kiasaki_dagothig.entity;

import java.util.ArrayList;
import java.util.List;

import ld26_kiasaki_dagothig.helpers.BlockImage;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class FactoryImpl implements Factory
{
	public Color background = new Color(12, 18, 24), grid = new Color(24, 32, 48);
	public int tileXAmount = 24, tileYAmount = 24, x, y;
	@Override
	public int getTileXAmount()
	{
		return tileXAmount;
	}
	@Override
	public int getTileYAmount() 
	{
		return tileYAmount;
	}
	public int getX()
	{
		return x;
	}
	public void setX(int pX)
	{
		x = pX;
	}
	public int getY()
	{
		return y;
	}
	public void setY(int pY)
	{
		y = pY;
	}
	
	public int shake = 0;
	
	@Override
	public boolean spaceAvailable(int pTileX, int pTileY, int pTileW, int pTileH) 
	{
		for (int tileX = pTileX; tileX < pTileX + pTileW; tileX++)
			for (int tileY = pTileY; tileY < pTileY + pTileH; tileY++)
				if (getMachine(tileX, tileY) != null)
					return false;
		return true;
	}
	
	public Pipe entryPoint, exitPoint;
	@Override
	public Pipe getEntryPoint() 
	{
		return entryPoint;
	}
	@Override
	public Pipe getExitPoint()
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
	public void receiveBlock(Block pBlock) throws SlickException
	{
		pBlock.setX(getEntryPoint().getX() - 12);
		pBlock.setY(getEntryPoint().getMiddleY());
		getEntryPoint().receiveBlock(pBlock);
	}
	private List<Block> transformedBlocks = new ArrayList<Block>();
	@Override
	public List<Block> getTransformedBlocks()
	{
		return transformedBlocks;
	}

	public List<BlockImage> sfxs = new ArrayList<BlockImage>();
	public void addSFX(BlockImage pImage)
	{
		sfxs.add(pImage);
	}
	
	@Override
	public void update(int d) throws SlickException 
	{
		if (shake > 0)
			shake-=d;
		
		for (Machine machine : getMachines())
		{
			boolean tmpWorking = machine.isWorking();
			machine.update(d);
			if (!machine.isWorking())
			{
				int repeat = 1;
				if (tmpWorking)
				{
					shake = 400;
					repeat = 500;
				}
				for (int index = 0; index < repeat; index++)
					if (Math.random() < 0.025)
					{
						BlockImage img = new BlockImage(BlockImage.getImage("Explosion.png"), 5, 200, false);
						img.x = (int)(machine.getX() - 16 + ( machine.getW()) * Math.random()) + getX();
						img.y = (int)(machine.getY() - 16 + (machine.getH()) * Math.random()) + getY();
						addSFX(img);
					}
			}
		}
		
		for (int index = sfxs.size() - 1; index >= 0; index--)
			if (sfxs.get(index).animCount < 0)
				sfxs.remove(index);
			else
				sfxs.get(index).update(d);
	}
	@Override
	public void render(Graphics g, int pScrollX, int pScrollY) throws SlickException 
	{
		int scrollX = pScrollX, scrollY = pScrollY;
		if (shake > 0)
		{
			scrollX += (Math.random() - 0.5) * 5;
			scrollY += (Math.random() - 0.5) * 5;
		}
		g.setColor(background);
		g.fillRect(x - scrollX, y - scrollY, getTileXAmount() * TileBased.TILE_SIZE, getTileYAmount() * TileBased.TILE_SIZE);
		g.setColor(grid);
		g.setLineWidth(2);
		for (int tileX = 0; tileX <= getTileXAmount(); tileX++)
			g.drawLine(x + tileX * TileBased.TILE_SIZE - scrollX, y - scrollY,
					   x + tileX * TileBased.TILE_SIZE - scrollX, y - scrollY + getTileYAmount() * TileBased.TILE_SIZE);
		for (int tileY = 0; tileY <= getTileYAmount(); tileY++)
			g.drawLine(x - scrollX, y + tileY * TileBased.TILE_SIZE - scrollY, 
					   x - scrollX + getTileXAmount() * TileBased.TILE_SIZE, y + tileY * TileBased.TILE_SIZE - scrollY);
		
		for (Machine machine : getMachines())
			machine.render(scrollX - x, scrollY - y);
		for (Machine machine : getMachines())
			machine.renderBlock(scrollX - x, scrollY - y);
		for (Machine machine : getMachines())
			machine.renderForeground(scrollX - x, scrollY - y);
		
		for (BlockImage sfx : sfxs)
			sfx.render(scrollX, scrollY);

		for (int tileX = -1; tileX <= getTileXAmount(); tileX++)
		{
			roof.x = tileX * TileBased.TILE_SIZE + getX();
			roof.y = getY() - 32;
			roof.render(pScrollX, pScrollY);
			floor.x = tileX * TileBased.TILE_SIZE + getX();
			floor.y = getY() + getTileYAmount() * TileBased.TILE_SIZE;
			floor.render(pScrollX, pScrollY);
		}
		for (int tileY = 0; tileY < getTileYAmount(); tileY++)
		{
			wall.x = getX() -18;
			wall.y = tileY * TileBased.TILE_SIZE + getY();
			wall.render(pScrollX, pScrollY);
			wall.x = getX() + getTileXAmount() * TileBased.TILE_SIZE;
			wall.render(pScrollX, pScrollY);
		}
	}
	BlockImage roof = new BlockImage(BlockImage.getImage("Roof.png"));
	BlockImage floor = new BlockImage(BlockImage.getImage("Floor.png"));
	BlockImage wall = new BlockImage(BlockImage.getImage("Brickwall.png"));

	public FactoryImpl(int pTileXAmount, int pTileYAmount, int pX, int pY) throws SlickException
	{
		tileXAmount = pTileXAmount;
		tileYAmount = pTileYAmount;
		x = pX;
		y = pY;
		
		entryPoint = new PipeImpl();
		entryPoint.setDeletable(false);
		getEntryPoint().setTileX(0);
		getEntryPoint().setTileY(getTileYAmount() - 2);
		getEntryPoint().setTileWidth(1);
		getEntryPoint().setTileHeight(1);
		getEntryPoint().setAngle(180);
		getEntryPoint().setAngleOut(0);
		getEntryPoint().calculateSprite();
		getMachines().add(getEntryPoint());
		
		exitPoint = new ExitPoint(this);
		exitPoint.setDeletable(false);
		getExitPoint().setTileX(getTileXAmount() - 1);
		getExitPoint().setTileY(getTileYAmount() - 2);
		getExitPoint().setTileWidth(1);
		getExitPoint().setTileHeight(1);
		getExitPoint().setAngle(180);
		getExitPoint().setAngleOut(0);
		getExitPoint().calculateSprite();
		getMachines().add(getExitPoint());
	}

	@Override
	public void addPipe(int pTileX, int pTileY, int pEntryAngle, int pExitAngle) throws SlickException
	{
		Pipe pipe = new PipeImpl();
		pipe.setCost(10);
		pipe.setTileX(pTileX);
		pipe.setTileY(pTileY);
		pipe.setTileWidth(1);
		pipe.setTileHeight(1);
		pipe.setAngle(pEntryAngle);
		pipe.setAngleOut(pExitAngle);
		int angle = pExitAngle - pEntryAngle;
		while (angle < 0)
			angle += 360;
		pipe.calculateSprite();
		Machine mach;
		switch (pEntryAngle)
		{
			case 0:
				mach = getMachine(pTileX + 1, pTileY);
				break;
			case 90:
				mach = getMachine(pTileX, pTileY + 1);
				break;
			case 180:
				mach = getMachine(pTileX - 1, pTileY);
				break;
			case 270:
				mach = getMachine(pTileX, pTileY - 1);
				break;
			default:
				mach = getMachine(pTileX + 1, pTileY);
				break;
		}
		if (mach != null && (mach instanceof Processor || 
							 mach instanceof Pipe && Math.abs(((Pipe)mach).getAngleOut() - pipe.getAngle()) == 180 || 
							 mach instanceof Router && ((Router)mach).getAngleOut() == (pipe.getAngle() + 180) % 360))
			pipe.addIn(mach, true);
		
		switch (pExitAngle)
		{
			case 0:
				mach = getMachine(pTileX + 1, pTileY);
				break;
			case 90:
				mach = getMachine(pTileX, pTileY + 1);
				break;
			case 180:
				mach = getMachine(pTileX - 1, pTileY);
				break;
			case 270:
				mach = getMachine(pTileX, pTileY - 1);
				break;
			default:
				mach = getMachine(pTileX - 1, pTileY);
				break;
		}
		if (mach != null && (mach instanceof Processor || 
							 mach instanceof Pipe &&  Math.abs(mach.getAngle() - pipe.getAngleOut()) == 180 || 
							 mach instanceof Router && ((Router)mach).getAngleOut() != (pipe.getAngleOut() + 180) % 360))
			pipe.addOut(mach, true);
		
		getMachines().add(pipe);
	}
	@Override
	public void addRouter(int pTileX, int pTileY, int pEntryAngle) throws SlickException 
	{
		Router router = new RouterImpl();
		router.setCost(25);
		router.setImage(new BlockImage(BlockImage.getImage("Router.png")));
		router.setForeground(new BlockImage(BlockImage.getImage("RouterForeground.png")));
		router.setTileX(pTileX);
		router.setTileY(pTileY);
		router.setTileWidth(1);
		router.setTileHeight(1);
		router.setAngle(pEntryAngle);
		Machine mach;
		switch (pEntryAngle)
		{
			case 0:
				mach = getMachine(pTileX + 1, pTileY);
				break;
			case 90:
				mach = getMachine(pTileX, pTileY + 1);
				break;
			case 180:
				mach = getMachine(pTileX - 1, pTileY);
				break;
			case 270:
				mach = getMachine(pTileX, pTileY - 1);
				break;
			default:
				mach = getMachine(pTileX + 1, pTileY);
				break;
		}
		if (mach != null && (mach instanceof Processor || mach instanceof Pipe && Math.abs(((Pipe)mach).getAngleOut() - router.getAngle()) == 180))
			router.addIn(mach, true);
		
		router.updateOuts(this);
		
		router.changeDirection(this);
		getMachines().add(router);

	}
	@Override
	public void addProcessor(int pTileX, int pTileY, int pTileW, int pTileH, List<BlockShape> pAcceptedShapes, BlockShape pResultShape,	BlockColor pColor) throws SlickException 
	{
		Processor processor = new ProcessorImpl();
		processor.setCost(processor.getCostFromSize(pTileW, pTileH));
		processor.setImage(new BlockImage(BlockImage.getImage("Processor_" + pTileW + "x" + pTileH + ".png")));
		processor.setForeground(new BlockImage(BlockImage.getImage("ProcessorForeground_" + pTileW + "x" + pTileH + ".png")));
		processor.setTileX(pTileX);
		processor.setTileY(pTileY);
		processor.setTileWidth(pTileW);
		processor.setTileHeight(pTileH);
		processor.setShapeIns(pAcceptedShapes);
		processor.setShapeOut(pResultShape);
		processor.setColor(pColor);
		
		Machine mach;
		for (int tileX = pTileX; tileX < pTileX + pTileW; tileX++)
		{
			mach = getMachine(tileX, pTileY - 1);
			if (mach instanceof Pipe)
			{
				if (((Pipe)mach).getAngleOut() == 90)
					processor.addIn(mach, true);
				else if (((Pipe)mach).getAngle() == 90 || mach instanceof Router)
					processor.addOut(mach, true);
			}
			mach = getMachine(tileX, pTileY + pTileH);
			if (mach instanceof Pipe)
			{
				if (((Pipe)mach).getAngleOut() == 270)
					processor.addIn(mach, true);
				else if (((Pipe)mach).getAngle() == 270 || mach instanceof Router)
					processor.addOut(mach, true);
			}
		}
		for (int tileY = pTileY; tileY < pTileY + pTileH; tileY++)
		{
			mach = getMachine(pTileX - 1, tileY);
			if (mach instanceof Pipe)
			{
				if (((Pipe)mach).getAngleOut() == 0)
					processor.addIn(mach, true);
				else if (((Pipe)mach).getAngle() == 0 || mach instanceof Router)
					processor.addOut(mach, true);
			}
			mach = getMachine(pTileX + pTileW, tileY);
			if (mach instanceof Pipe)
			{
				if (((Pipe)mach).getAngleOut() == 180)
					processor.addIn(mach, true);
				else if (((Pipe)mach).getAngle() == 180 || mach instanceof Router)
					processor.addOut(mach, true);
			}
		}
		getMachines().add(processor);
	}
	@Override
	public int destroy(Machine pMachine)
	{
		if (pMachine.isDeletable())
		{
			getMachines().remove(pMachine);
			return pMachine.destroy();
		}
		else
			return 0;
	}
}
