package ld26_kiasaki_dagothig.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			entryX = Math.round(getX() + getW() / 2 - TileBased.TILE_SIZE / 2);
			if (in.getY() < getY())
				entryY = Math.round(getY());
			else
				entryY = Math.round(getY() + getH());
		}
		else if (alignMinY && alignMaxY)
		{
			entryY = Math.round(getY() + getH() / 2 - TileBased.TILE_SIZE / 2);
			if (in.getX() < getX())
				entryX = Math.round(getX());
			else
				entryX = Math.round(getX() + getW());
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
			outX = Math.round(getX() + getW() / 2) - TileBased.TILE_SIZE / 2;
			if (out.getY() < getY())
				outY = Math.round(getY());
			else
				outY = Math.round(getY() + getH());
		}
		else if (alignMinY && alignMaxY)
		{
			outY = Math.round(getY() + getH() / 2) - TileBased.TILE_SIZE / 2;
			if (out.getX() < getX())
				outX = Math.round(getX());
			else
				outX = Math.round(getX() + getW());
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
	
	public Machine in = null;
	public Machine out = null;
	public boolean working = true, deletable = true;
	
	public BlockImage foreGround = null;
	
	public int cost = 0;
	
	public int progressDuration = 1000;
	public Map<Block, Float> progress = new HashMap<Block, Float>();

	@Override
	public void receiveBlock(Block pBlock)
	{
		getProgress().put(pBlock, 0f);
	}
	@Override
	public void sendBlock(Block pBlock) throws SlickException
	{
		getProgress().remove(pBlock);
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
		List<Block> blocksToSend = new ArrayList<Block>();
		for (Block block : progress.keySet())
		{
			getProgress().put(block, getProgress().get(block) + d);

			float progress = (getProgress().get(block) / getProgressDuration());
			if (progress < 0.5)
			{
				progress = Math.max(0, progress * 2f);
				System.out.println(progress);
				block.setX(entryX * (1 - progress) + middleX * progress);
				block.setY(entryY * (1 - progress) + middleY * progress);
			}
			else
			{
				progress = Math.min(1, (progress - 0.5f) * 2f);
				System.out.println(progress);
				block.setX(middleX * (1 - progress) + outX * progress);
				block.setY(middleY * (1 - progress) + outY * progress);
			}
			if (getProgress().get(block) > getProgressDuration())
				blocksToSend.add(block);
		}
		for (Block block : blocksToSend)
			sendBlock(block);
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
}
