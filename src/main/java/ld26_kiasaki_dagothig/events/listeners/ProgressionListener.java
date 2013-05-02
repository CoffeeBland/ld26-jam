package ld26_kiasaki_dagothig.events.listeners;

import ld26_kiasaki_dagothig.GameLevel;
import ld26_kiasaki_dagothig.World;

public interface ProgressionListener {

	public void playerLevelPreSave(World world, GameLevel level);
	public void playerLevelPreLoad(World world, GameLevel newLevel);
	public void playerLevelPostSave(World world, GameLevel level);
	public void playerLevelPostLoad(World world, GameLevel newLevel);
	public void playerLevelUp(World world, GameLevel newLevel);
	public void playerLevelRestart(World world, GameLevel newLevel);
	
}
