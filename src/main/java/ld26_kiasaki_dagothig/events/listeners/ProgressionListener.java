package ld26_kiasaki_dagothig.events.listeners;

import ld26_kiasaki_dagothig.GameLevel;
import ld26_kiasaki_dagothig.World;

public interface ProgressionListener {

	public void playerLevelSave(World world, GameLevel level);
	public void playerLevelLoad(World world, GameLevel newLevel);
	public void playerLevelUp(World world, GameLevel newLevel);
	public void playerLevelRestart(World world, GameLevel newLevel);
	
}
