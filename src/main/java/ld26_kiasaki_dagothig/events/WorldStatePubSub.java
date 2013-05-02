package ld26_kiasaki_dagothig.events;

import java.util.ArrayList;
import java.util.List;

import ld26_kiasaki_dagothig.World;
import ld26_kiasaki_dagothig.events.listeners.WorldStateListener;

public class WorldStatePubSub {

private static List<WorldStateListener> subscribers = new ArrayList<WorldStateListener>();
	
	public static void clear()
	{
		subscribers.clear();
	}
	
	public static void subscribe(WorldStateListener pWSL)
	{
		subscribers.add(pWSL);		
	}
	
	public static void publish(World pWorld, WorldStatePubAction pAction)
	{
		for (WorldStateListener tWSL : subscribers)
		{
			if (pAction == WorldStatePubAction.PAUSE)
				tWSL.pause(pWorld);
			else if (pAction == WorldStatePubAction.PLAY)
				tWSL.play(pWorld);
		}
	}
}
