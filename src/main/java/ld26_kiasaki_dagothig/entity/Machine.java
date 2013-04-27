package ld26_kiasaki_dagothig.entity;

import java.util.TreeMap;

public interface Machine extends Entity, TileBased
{
	public Machine in = null;
	public Machine out = null;
	public boolean working = true;
	
	public int cost = 0;
	
	public TreeMap<Block, Float> progress = new TreeMap<Block, Float>();
	
	public void receiveBlock(Block pBlock);
	public void sendBlock(Block pBlock);
	public int repair();
}
