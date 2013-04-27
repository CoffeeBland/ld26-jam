package ld26_kiasaki_dagothig.entity;

import java.util.TreeMap;

import ld26_kiasaki_dagothig.helpers.BlockImage;

public interface Machine extends Entity, TileBased
{
	public Machine getIn();
	public void setIn(Machine in, boolean autoAssignOut);
	
	public Machine getOut();
	public void setOut(Machine out, boolean autoAssignIn);
	
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
	
	public TreeMap<Block, Float> getProgress();
	public void setProgress(TreeMap<Block, Float> progress);

	public void receiveBlock(Block pBlock);
	public void sendBlock(Block pBlock);
	public int repair();
}
