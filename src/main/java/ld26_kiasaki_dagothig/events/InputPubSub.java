package ld26_kiasaki_dagothig.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ld26_kiasaki_dagothig.World;
import ld26_kiasaki_dagothig.events.listeners.InputListener;

import org.newdawn.slick.Input;

public class InputPubSub {
	
	private static Map<Integer, List<InputListener>> subscribers = new TreeMap<Integer, List<InputListener>>();
	
	public static void clear()
	{
		subscribers.clear();
	}
	
	public static void subscribe(InputListener pInputListener, Integer pPriority)
	{
		if (subscribers.get(pPriority) == null)
			subscribers.put(pPriority, new ArrayList<InputListener>());
		subscribers.get(pPriority).add(pInputListener);
	}
	
	public static void publishKeyPress(World pWorld, Input pInput)
	{
		if (pInput.isKeyPressed(Input.KEY_ESCAPE))
			notifyKeyPress(pWorld, pInput, Input.KEY_ESCAPE);
		else if (pInput.isKeyPressed(Input.KEY_END))
			notifyKeyPress(pWorld, pInput, Input.KEY_END);
		else if (pInput.isKeyPressed(Input.KEY_DELETE))
			notifyKeyPress(pWorld, pInput, Input.KEY_DELETE);
		else if (pInput.isKeyPressed(Input.KEY_BACK))
			notifyKeyPress(pWorld, pInput, Input.KEY_BACK);
		else if (pInput.isKeyPressed(Input.KEY_ENTER))
			notifyKeyPress(pWorld, pInput, Input.KEY_ENTER);
		else if (pInput.isKeyPressed(Input.KEY_LEFT))
			notifyKeyPress(pWorld, pInput, Input.KEY_LEFT);
		else if (pInput.isKeyPressed(Input.KEY_RIGHT))
			notifyKeyPress(pWorld, pInput, Input.KEY_RIGHT);
		else if (pInput.isKeyPressed(Input.KEY_UP))
			notifyKeyPress(pWorld, pInput, Input.KEY_UP);
		else if (pInput.isKeyPressed(Input.KEY_DOWN))
			notifyKeyPress(pWorld, pInput, Input.KEY_DOWN);
		else if (pInput.isKeyPressed(Input.KEY_A))
			notifyKeyPress(pWorld, pInput, Input.KEY_A);
		else if (pInput.isKeyPressed(Input.KEY_B))
			notifyKeyPress(pWorld, pInput, Input.KEY_B);
		else if (pInput.isKeyPressed(Input.KEY_C))
			notifyKeyPress(pWorld, pInput, Input.KEY_C);
		else if (pInput.isKeyPressed(Input.KEY_D))
			notifyKeyPress(pWorld, pInput, Input.KEY_D);
		else if (pInput.isKeyPressed(Input.KEY_E))
			notifyKeyPress(pWorld, pInput, Input.KEY_E);
		else if (pInput.isKeyPressed(Input.KEY_F))
			notifyKeyPress(pWorld, pInput, Input.KEY_F);
		else if (pInput.isKeyPressed(Input.KEY_G))
			notifyKeyPress(pWorld, pInput, Input.KEY_G);
		else if (pInput.isKeyPressed(Input.KEY_H))
			notifyKeyPress(pWorld, pInput, Input.KEY_H);
		else if (pInput.isKeyPressed(Input.KEY_I))
			notifyKeyPress(pWorld, pInput, Input.KEY_I);
		else if (pInput.isKeyPressed(Input.KEY_J))
			notifyKeyPress(pWorld, pInput, Input.KEY_J);
		else if (pInput.isKeyPressed(Input.KEY_K))
			notifyKeyPress(pWorld, pInput, Input.KEY_K);
		else if (pInput.isKeyPressed(Input.KEY_L))
			notifyKeyPress(pWorld, pInput, Input.KEY_L);
		else if (pInput.isKeyPressed(Input.KEY_M))
			notifyKeyPress(pWorld, pInput, Input.KEY_M);
		else if (pInput.isKeyPressed(Input.KEY_N))
			notifyKeyPress(pWorld, pInput, Input.KEY_N);
		else if (pInput.isKeyPressed(Input.KEY_O))
			notifyKeyPress(pWorld, pInput, Input.KEY_O);
		else if (pInput.isKeyPressed(Input.KEY_P))
			notifyKeyPress(pWorld, pInput, Input.KEY_P);
		else if (pInput.isKeyPressed(Input.KEY_Q))
			notifyKeyPress(pWorld, pInput, Input.KEY_Q);
		else if (pInput.isKeyPressed(Input.KEY_R))
			notifyKeyPress(pWorld, pInput, Input.KEY_R);
		else if (pInput.isKeyPressed(Input.KEY_S))
			notifyKeyPress(pWorld, pInput, Input.KEY_S);
		else if (pInput.isKeyPressed(Input.KEY_T))
			notifyKeyPress(pWorld, pInput, Input.KEY_T);
		else if (pInput.isKeyPressed(Input.KEY_U))
			notifyKeyPress(pWorld, pInput, Input.KEY_U);
		else if (pInput.isKeyPressed(Input.KEY_V))
			notifyKeyPress(pWorld, pInput, Input.KEY_V);
		else if (pInput.isKeyPressed(Input.KEY_W))
			notifyKeyPress(pWorld, pInput, Input.KEY_W);
		else if (pInput.isKeyPressed(Input.KEY_X))
			notifyKeyPress(pWorld, pInput, Input.KEY_X);
		else if (pInput.isKeyPressed(Input.KEY_Y))
			notifyKeyPress(pWorld, pInput, Input.KEY_Y);
		else if (pInput.isKeyPressed(Input.KEY_Z))
			notifyKeyPress(pWorld, pInput, Input.KEY_Z);
	}
	private static void notifyKeyPress(World pWorld, Input pInput, int pKey)
	{
		for (Integer tKey : subscribers.keySet())
		{
			for (InputListener tIL : subscribers.get(tKey))
			{
				tIL.keyPress(pWorld, pInput, pKey);
			}
		}
	}
	
	public static void publishKeyRelease(World pWorld, Input pInput)
	{
		if (pInput.isKeyPressed(Input.KEY_ESCAPE))
			notifyKeyRelease(pWorld, pInput, Input.KEY_ESCAPE);
		else if (pInput.isKeyPressed(Input.KEY_END))
			notifyKeyRelease(pWorld, pInput, Input.KEY_END);
		else if (pInput.isKeyPressed(Input.KEY_DELETE))
			notifyKeyRelease(pWorld, pInput, Input.KEY_DELETE);
		else if (pInput.isKeyPressed(Input.KEY_BACK))
			notifyKeyRelease(pWorld, pInput, Input.KEY_BACK);
		else if (pInput.isKeyPressed(Input.KEY_ENTER))
			notifyKeyRelease(pWorld, pInput, Input.KEY_ENTER);
		else if (pInput.isKeyPressed(Input.KEY_LEFT))
			notifyKeyRelease(pWorld, pInput, Input.KEY_LEFT);
		else if (pInput.isKeyPressed(Input.KEY_RIGHT))
			notifyKeyRelease(pWorld, pInput, Input.KEY_RIGHT);
		else if (pInput.isKeyPressed(Input.KEY_UP))
			notifyKeyRelease(pWorld, pInput, Input.KEY_UP);
		else if (pInput.isKeyPressed(Input.KEY_DOWN))
			notifyKeyRelease(pWorld, pInput, Input.KEY_DOWN);
		else if (pInput.isKeyPressed(Input.KEY_A))
			notifyKeyRelease(pWorld, pInput, Input.KEY_A);
		else if (pInput.isKeyPressed(Input.KEY_B))
			notifyKeyRelease(pWorld, pInput, Input.KEY_B);
		else if (pInput.isKeyPressed(Input.KEY_C))
			notifyKeyRelease(pWorld, pInput, Input.KEY_C);
		else if (pInput.isKeyPressed(Input.KEY_D))
			notifyKeyRelease(pWorld, pInput, Input.KEY_D);
		else if (pInput.isKeyPressed(Input.KEY_E))
			notifyKeyRelease(pWorld, pInput, Input.KEY_E);
		else if (pInput.isKeyPressed(Input.KEY_F))
			notifyKeyRelease(pWorld, pInput, Input.KEY_F);
		else if (pInput.isKeyPressed(Input.KEY_G))
			notifyKeyRelease(pWorld, pInput, Input.KEY_G);
		else if (pInput.isKeyPressed(Input.KEY_H))
			notifyKeyRelease(pWorld, pInput, Input.KEY_H);
		else if (pInput.isKeyPressed(Input.KEY_I))
			notifyKeyRelease(pWorld, pInput, Input.KEY_I);
		else if (pInput.isKeyPressed(Input.KEY_J))
			notifyKeyRelease(pWorld, pInput, Input.KEY_J);
		else if (pInput.isKeyPressed(Input.KEY_K))
			notifyKeyRelease(pWorld, pInput, Input.KEY_K);
		else if (pInput.isKeyPressed(Input.KEY_L))
			notifyKeyRelease(pWorld, pInput, Input.KEY_L);
		else if (pInput.isKeyPressed(Input.KEY_M))
			notifyKeyRelease(pWorld, pInput, Input.KEY_M);
		else if (pInput.isKeyPressed(Input.KEY_N))
			notifyKeyRelease(pWorld, pInput, Input.KEY_N);
		else if (pInput.isKeyPressed(Input.KEY_O))
			notifyKeyRelease(pWorld, pInput, Input.KEY_O);
		else if (pInput.isKeyPressed(Input.KEY_P))
			notifyKeyRelease(pWorld, pInput, Input.KEY_P);
		else if (pInput.isKeyPressed(Input.KEY_Q))
			notifyKeyRelease(pWorld, pInput, Input.KEY_Q);
		else if (pInput.isKeyPressed(Input.KEY_R))
			notifyKeyRelease(pWorld, pInput, Input.KEY_R);
		else if (pInput.isKeyPressed(Input.KEY_S))
			notifyKeyRelease(pWorld, pInput, Input.KEY_S);
		else if (pInput.isKeyPressed(Input.KEY_T))
			notifyKeyRelease(pWorld, pInput, Input.KEY_T);
		else if (pInput.isKeyPressed(Input.KEY_U))
			notifyKeyRelease(pWorld, pInput, Input.KEY_U);
		else if (pInput.isKeyPressed(Input.KEY_V))
			notifyKeyRelease(pWorld, pInput, Input.KEY_V);
		else if (pInput.isKeyPressed(Input.KEY_W))
			notifyKeyRelease(pWorld, pInput, Input.KEY_W);
		else if (pInput.isKeyPressed(Input.KEY_X))
			notifyKeyRelease(pWorld, pInput, Input.KEY_X);
		else if (pInput.isKeyPressed(Input.KEY_Y))
			notifyKeyRelease(pWorld, pInput, Input.KEY_Y);
		else if (pInput.isKeyPressed(Input.KEY_Z))
			notifyKeyRelease(pWorld, pInput, Input.KEY_Z);
	}
	private static void notifyKeyRelease(World pWorld, Input pInput, int pKey)
	{
		for (Integer tKey : subscribers.keySet())
		{
			for (InputListener tIL : subscribers.get(tKey))
			{
				tIL.keyDown(pWorld, pInput, pKey);
			}
		}
	}
	
	public static void publishMousePress(World pWorld, Input pInput)
	{
		if (pInput.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			notifyMouseLeftClick(pWorld, pInput);
		else if (pInput.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON))
			notifyMouseRightClick(pWorld, pInput);
		else if (pInput.isMouseButtonDown(Input.MOUSE_MIDDLE_BUTTON))
			notifyMouseMiddleClick(pWorld, pInput);
	}
	private static void notifyMouseLeftClick(World pWorld, Input pInput)
	{
		for (Integer tKey : subscribers.keySet())
		{
			for (InputListener tIL : subscribers.get(tKey))
			{
				tIL.mouseLeftClick(pWorld, pInput, pInput.getMouseX(), pInput.getMouseY());
			}
		}
	}
	private static void notifyMouseRightClick(World pWorld, Input pInput)
	{
		for (Integer tKey : subscribers.keySet())
		{
			for (InputListener tIL : subscribers.get(tKey))
			{
				tIL.mouseRightClick(pWorld, pInput, pInput.getMouseX(), pInput.getMouseY());
			}
		}
	}
	private static void notifyMouseMiddleClick(World pWorld, Input pInput)
	{
		for (Integer tKey : subscribers.keySet())
		{
			for (InputListener tIL : subscribers.get(tKey))
			{
				tIL.mouseMiddleClick(pWorld, pInput, pInput.getMouseX(), pInput.getMouseY());
			}
		}
	}

}
