package ld26_kiasaki_dagothig.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ld26_kiasaki_dagothig.helpers.BlockImage;

import org.newdawn.slick.SlickException;

public class MachineImpl extends EntityImpl implements Machine
{
	public int getTileX()
	{
		return tileX;
	}
	public void setTileX(int tileX) 
	{
		this.tileX = tileX;
		setX(tileX * TileBased.TILE_SIZE);
		middleX = Math.round(getX() + getW() / 2) - TileBased.TILE_SIZE / 2;
	}
	
	public int getTileY()
	{
		return tileY;
	}
	public void setTileY(int tileY)
	{
		this.tileY = tileY;
		setY(tileY * TileBased.TILE_SIZE);
		middleY = Math.round(getY() + getH() / 2) - TileBased.TILE_SIZE / 2;
	}
	
	public int getTileWidth()
	{
		return tileWidth;
	}
	public void setTileWidth(int tileWidth)
	{
		this.tileWidth = tileWidth;
		setW(tileWidth * TileBased.TILE_SIZE);
		middleX = Math.round(getX() + getW() / 2) - TileBased.TILE_SIZE / 2;
		
	}
	
	public int getTileHeight() 
	{
		return tileHeight;
	}
	public void setTileHeight(int tileHeight)
	{
		this.tileHeight = tileHeight;
		setH(tileHeight * TileBased.TILE_SIZE);
		middleY = Math.round(getY() + getH() / 2) - TileBased.TILE_SIZE / 2;
	}
	
	public List<Machine> getIns(){return ins;}
	public List<Machine> getOuts(){return outs;}

	public void addIn(Machine in, boolean autoAssignOut)
	{
		if (in == null)
			return;
		
		getIns().add(in);
		if (autoAssignOut)
			if (!in.getOuts().contains(this))
				in.addOut(this, false);
		
		if (!(this instanceof Pipe))
		{
			entryX = (int)(in.getX() + in.getW() / 2 - 12);
			entryY = (int)(in.getY() + in.getH() / 2 - 12);
		}
	}
	public void removeIn(Machine in, boolean autoAssignOut)
	{
		if (in == null)
			return;
		
		getIns().remove(in);
		if (autoAssignOut)
			in.removeOut(this, false);
		
		if (getIns().size() > 0)
		{
			Machine mach = getIns().get(rnd.nextInt(getIns().size()));
			entryX = (int)(mach.getX() + mach.getW() / 2 - 12);
			entryY = (int)(mach.getY() + mach.getH() / 2 - 12);
		}
	}
	
	public void addOut(Machine out, boolean autoAssignIn)
	{
		if (out == null)
			return;
		
		getOuts().add(out);
		if (autoAssignIn)
			if (!out.getIns().contains(this))
				out.addIn(this, false);
		
		if (!(this instanceof Pipe))
		{
			outX = (int)(out.getX() + out.getW() / 2 - 12);
			outY = (int)(out.getY() + out.getH() / 2 - 12);
		}
	}
	public void removeOut(Machine out, boolean autoAssignIn)
	{
		if (out == null)
			return;
		
		getOuts().remove(out);
		if (autoAssignIn)
			out.removeIn(this, false);
		
		if (getOuts().size() > 0)
		{
			Machine mach = getOuts().get(rnd.nextInt(getOuts().size()));
			outX = (int)(mach.getX() + mach.getW() / 2 - 12);
			outY = (int)(mach.getY() + mach.getH() / 2 - 12);
		}
	}
	
	public int getEntryX(){return entryX;}
	public int getEntryY(){return entryY;}
	public int getMiddleX(){return middleX;}
	public int getMiddleY(){return middleY;}
	public int getOutX(){return outX;}
	public int getOutY(){return outY;}
	
	public boolean isWorking() 
	{
		return working;
	}
	public void setWorking(boolean working)
	{
		this.working = working;
	}
	
	public boolean isDeletable() 
	{
		return deletable;
	}
	public void setDeletable(boolean deletable) 
	{
		this.deletable = deletable;
	}
	
	public BlockImage getForeGround()
	{
		return foreGround;
	}
	public void setForeGround(BlockImage foreGround)
	{
		this.foreGround = foreGround;
	}
	
	public int getCost()
	{
		return cost;
	}
	public void setCost(int cost)
	{
		this.cost = cost;
	}
	
	public int getProgressDuration() 
	{
		if (getOuts().size() <= 0)
			return Integer.MAX_VALUE;
		return progressDuration;
	}
	public void setProgressDuration(int progressDuration) 
	{
		this.progressDuration = progressDuration;
	}
	
	public Map<Block, Float> getProgress()
	{
		return progress;
	}
	public void setProgress(Map<Block, Float> progress) 
	{
		this.progress = progress;
	}

	public int tileX = 0;
	public int tileY = 0;
	public int tileWidth = 0;
	public int tileHeight = 0;
	public int entryX = 0, entryY = 0, middleX = 0, middleY = 0, outX = 0, outY = 0;
	
	public List<Machine> ins = new ArrayList<Machine>();
	public List<Machine> outs = new ArrayList<Machine>();
	public boolean working = true, deletable = true;
	
	public BlockImage foreGround = null;
	
	public int cost = 0;
	
	public int progressDuration = 250;
	public Map<Block, Float> progress = new HashMap<Block, Float>();
	
	private Random rnd = new Random();
	@Override
	public void receiveBlock(Block pBlock)
	{
		getProgress().put(pBlock, 0f);
		/*pBlock.setX(getEntryX());
		pBlock.setY(getEntryY());*/
	}
	@Override
	public void sendBlock(Block pBlock) throws SlickException
	{
		getProgress().remove(pBlock);
		getOuts().get(rnd.nextInt(getOuts().size())).receiveBlock(pBlock);
	}
	
	@Override
	public int repairCost() 
	{
		return (int)(cost * 0.1);
	}
	@Override
	public int repair()
	{
		setWorking(true);
		return repairCost();
	}
	@Override
	public int destroy() 
	{
		for (int index = getIns().size() - 1; index >= 0; index--)
			removeIn(getIns().get(index), true);
		for (int index = getOuts().size() - 1; index >= 0; index--)
			removeOut(getOuts().get(index), true);
		
		return (int)(cost * 0.5);
	}
		
	public int nextSend = 0;
	@Override
	public void update(int d) throws SlickException 
	{
		super.update(d);
		List<Block> blocksToSend = new ArrayList<Block>();
		{
			for (Block block : progress.keySet())
			{
				if (getProgressDuration() != Integer.MAX_VALUE)
					getProgress().put(block, getProgress().get(block) + d);
				
				for (int index = 0; index < d; index++)
				{
					block.setX((block.getX() * 0.99f + middleX * 0.01f));
					block.setY((block.getY() * 0.99f + middleY * 0.01f));
				}
				if (getProgress().get(block) > getProgressDuration())
					blocksToSend.add(block);
			}
			for (Block block : blocksToSend)
			{
				if (nextSend <= 0)
				{
					sendBlock(block);
					nextSend = 200;
				}
			}
		}
		if (nextSend > 0)
			nextSend -= d;
			
	}
	@Override
	public void renderFull(int pScrollX, int pScrollY) throws SlickException
	{
		render(pScrollX, pScrollY);
		renderForeground(pScrollX, pScrollY);
	}
	@Override
	public void renderBlock(int pScrollX, int pScrollY) throws SlickException
	{
		for (Block block : getProgress().keySet())
			block.render(pScrollX, pScrollY);
	}
	@Override
	public void renderForeground(int pScrollX, int pScrollY) throws SlickException
	{
		getForeGround().x = Math.round(getX());
		getForeGround().y = Math.round(getY());
		getForeGround().image.setCenterOfRotation(getW() / 2, getH() / 2);
		getForeGround().image.setRotation(getAngle());
		
		getForeGround().render(pScrollX, pScrollY);
	}
	
	public MachineImpl(){}
	public MachineImpl(int pCost, BlockColor pColor, String pBack, String pFore, int pX, int pY, int pTileWidth, int pTileHeight) throws SlickException
	{
		setCost(pCost);
		setColor(pColor);
		setImage(new BlockImage(BlockImage.getImage(pBack)));
		setForeGround(new BlockImage(BlockImage.getImage(pFore)));
		setX(pX);
		setY(pY);
		setTileWidth(pTileWidth);
		setTileHeight(pTileHeight);
	}
	
	@Override
	public int getCostFromSize(int pTileW, int pTileH) 
	{
		return (20 - (pTileW * pTileH)) * 10;
	}
}
