package ld26_kiasaki_dagothig.entity;

import java.util.TreeMap;

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
		middleX = Math.round(getX() + getW() / 2);
	}
	
	public int getTileY()
	{
		return tileY;
	}
	public void setTileY(int tileY)
	{
		this.tileY = tileY;
		setY(tileY * TileBased.TILE_SIZE);
		middleY = Math.round(getY() + getH() / 2);
	}
	
	public int getTileWidth()
	{
		return tileWidth;
	}
	public void setTileWidth(int tileWidth)
	{
		this.tileWidth = tileWidth;
		setW(tileWidth * TileBased.TILE_SIZE);
		middleX = Math.round(getX() + getW() / 2);
	}
	
	public int getTileHeight() 
	{
		return tileHeight;
	}
	public void setTileHeight(int tileHeight)
	{
		this.tileHeight = tileHeight;
		setH(tileHeight * TileBased.TILE_SIZE);
		middleY = Math.round(getY() + getH() / 2);
	}
	
	public Machine getIn() 
	{
		return in;
	}
	public void setIn(Machine in, boolean autoAssignOut) 
	{
		if (getIn() != null && autoAssignOut)
			getIn().setOut(null, false);
		this.in = in;
		
		if (in == null)
			return;
		
		if (autoAssignOut)
			in.setOut(this, false);
		
		boolean alignMinX = getX() < in.getX() + in.getW(),
				alignMaxX = getX() + getW() > in.getX(),
				alignMinY = getY() < in.getY() + in.getH(),
				alignMaxY = getY() + getH() > in.getY();
		if (alignMinX && alignMaxX)
		{
			entryX = Math.round(getX() + getW() / 2);
			if (in.getY() < getY())
				entryY = Math.round(getY() + TileBased.TILE_SIZE / 2);
			else
				entryY = Math.round(getY() + getH() - TileBased.TILE_SIZE / 2);
		}
		else if (alignMinY && alignMaxY)
		{
			entryY = Math.round(getY() + getH() / 2);
			if (in.getX() < getX())
				entryX = Math.round(getX() + TileBased.TILE_SIZE / 2);
			else
				entryX = Math.round(getX() + getW() - TileBased.TILE_SIZE / 2);
		}
	}
	
	public Machine getOut() 
	{
		return out;
	}
	public void setOut(Machine out, boolean autoAssignIn) 
	{
		if (getOut() != null && autoAssignIn)
			getOut().setIn(null, false);
		this.out = out;
		
		if (out == null)
			return;
		
		if (autoAssignIn)
			out.setIn(this, false);
				
		boolean alignMinX = getX() < out.getX() + out.getW(),
				alignMaxX = getX() + getW() > out.getX(),
				alignMinY = getY() < out.getY() + out.getH(),
				alignMaxY = getY() + getH() > out.getY();
		if (alignMinX && alignMaxX)
		{
			entryX = Math.round(getX() + getW() / 2);
			if (out.getY() < getY())
				entryY = Math.round(getY() + TileBased.TILE_SIZE / 2);
			else
				entryY = Math.round(getY() + getH() - TileBased.TILE_SIZE / 2);
		}
		else if (alignMinY && alignMaxY)
		{
			entryY = Math.round(getY() + getH() / 2);
			if (out.getX() < getX())
				entryX = Math.round(getX() + TileBased.TILE_SIZE / 2);
			else
				entryX = Math.round(getX() + getW() - TileBased.TILE_SIZE / 2);
		}
	}
	
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
		if (getOut() == null)
			return Integer.MAX_VALUE;
		return progressDuration;
	}
	public void setProgressDuration(int progressDuration) 
	{
		this.progressDuration = progressDuration;
	}
	
	public TreeMap<Block, Float> getProgress()
	{
		return progress;
	}
	public void setProgress(TreeMap<Block, Float> progress) 
	{
		this.progress = progress;
	}

	public int tileX = 0;
	public int tileY = 0;
	public int tileWidth = 0;
	public int tileHeight = 0;
	public int entryX = 0, entryY = 0, middleX = 0, middleY = 0, outX = 0, outY = 0;
	
	public Machine in = null;
	public Machine out = null;
	public boolean working = true, deletable = true;
	
	public BlockImage foreGround = null;
	
	public int cost = 0;
	
	public int progressDuration = 1000;
	public TreeMap<Block, Float> progress = new TreeMap<Block, Float>();

	@Override
	public void receiveBlock(Block pBlock)
	{
		progress.put(pBlock, 0f);
	}
	@Override
	public void sendBlock(Block pBlock) throws SlickException
	{
		progress.remove(pBlock);
		out.receiveBlock(pBlock);
	}

	@Override
	public int repair()
	{
		return (int)(cost * 0.1);
	}
	@Override
	public int destroy() 
	{
		setIn(null, false);
		setOut(null, false);
		
		return (int)(cost * 0.5);
	}
	
	@Override
	public void update(int d) throws SlickException 
	{
		super.update(d);
		getForeGround().x = Math.round(getX());
		getForeGround().y = Math.round(getY());
		for (Block block : progress.keySet())
		{
			getProgress().put(block, getProgress().get(block) + d);

			float progress = (getProgress().get(block) / getProgressDuration()) - 0.5f;
			if (progress < 0)
			{
				progress = (progress + 0.5f) * 2;
				block.setX(entryX * (1 - progress) * + middleX * progress);
				block.setY(entryY * (1 - progress) * + middleY * progress);
			}
			else
			{
				progress *= 2f;
				block.setX(middleX * (1 - progress) * + outX * progress);
				block.setY(middleY * (1 - progress) * + outY * progress);
			}
			
			if (getProgress().get(block) > getProgressDuration())
				sendBlock(block);
		}
	}
	@Override
	public void render(int pScrollX, int pScrollY) throws SlickException
	{
		super.render(pScrollX, pScrollY);
		for (Block block : getProgress().keySet())
			block.render(pScrollX, pScrollY);
		if (getAngle() != 0)
		{
			getForeGround().image.setCenterOfRotation(getW() / 2, getH() / 2);
			getForeGround().image.setRotation(getAngle());
		}
		getForeGround().render(pScrollX, pScrollY);
	}
}
