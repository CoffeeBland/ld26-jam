package ld26_kiasaki_dagothig.entity;

import java.util.Map;

import org.newdawn.slick.SlickException;

import ld26_kiasaki_dagothig.helpers.BlockImage;

public interface Machine extends Entity, TileBased
{
	public Machine getIn();
	public void setIn(Machine in, boolean autoAssignOut);
	
	public Machine getOut();
	public void setOut(Machine out, boolean autoAssignIn);
	
	public int getEntryX();
	public int getEntryY();
	public int getMiddleX();
	public int getMiddleY();
	public int getOutX();
	public int getOutY();
	
	public boolean isWorking();
	public void setWorking(boolean working);
	
	public boolean isDeletable();
	public void setDeletable(boolean deletable);
	
	public BlockImage getForeGround();
	public void setForeGround(BlockImage foreGround);
	
	public int getCost();
	public void setCost(int cost);
	
	public int getProgressDuration();
	public void setProgressDuration(int progressDuration);
	
	public Map<Block, Float> getProgress();
	public void setProgress(Map<Block, Float> progress);

	public void receiveBlock(Block pBlock);
	public void sendBlock(Block pBlock) throws SlickException;
	public int repair();
	public int repairCost();
	public int destroy();
	
	public int getCostFromSize(int pTileW, int pTileH);
	
	public void renderFull(int pScrollX, int pScrollY) throws SlickException;
	public void renderBlock(int pScrollX, int pScrollY) throws SlickException;
	public void renderForeground(int pScrollX, int pScrollY) throws SlickException;
}
