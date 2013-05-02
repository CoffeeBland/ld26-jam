package ld26_kiasaki_dagothig.events;

import java.util.ArrayList;
import java.util.List;

import ld26_kiasaki_dagothig.GameLevel;
import ld26_kiasaki_dagothig.World;
import ld26_kiasaki_dagothig.events.listeners.ProgressionListener;

public class ProgressionPubSub {

	private static List<ProgressionListener> subscribers = new ArrayList<ProgressionListener>();
	
	public static void clear()
	{
		subscribers.clear();
	}
	
	public static void subscribe(ProgressionListener pProgressionListener)
	{
		subscribers.add(pProgressionListener);		
	}
	
	public static void publish(World pWorld, GameLevel pGameLevel, ProgressionPubAction pAction)
	{
		for (ProgressionListener tPL : subscribers)
		{
			if (pAction == ProgressionPubAction.LEVEL_SAVE)
				tPL.playerLevelSave(pWorld, pGameLevel);
			else if (pAction == ProgressionPubAction.LEVEL_LOAD)
				tPL.playerLevelLoad(pWorld, pGameLevel);
			else if (pAction == ProgressionPubAction.LEVEL_UP)
				tPL.playerLevelUp(pWorld, pGameLevel);
			else if (pAction == ProgressionPubAction.LEVEL_RESTART)
				tPL.playerLevelRestart(pWorld, pGameLevel);
		}
	}
	
}
