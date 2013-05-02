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
		for (Integer tKey : subscribers.keySet())
		{
			for (InputListener tIL : subscribers.get(tKey))
			{
				if (pInput.isKeyPressed(Input.KEY_ESCAPE))
					tIL.keyPress(pWorld, pInput, Input.KEY_ESCAPE);
				else if (pInput.isKeyPressed(Input.KEY_END))
					tIL.keyPress(pWorld, pInput, Input.KEY_END);
				else if (pInput.isKeyPressed(Input.KEY_DELETE))
					tIL.keyPress(pWorld, pInput, Input.KEY_DELETE);
				else if (pInput.isKeyPressed(Input.KEY_BACK))
					tIL.keyPress(pWorld, pInput, Input.KEY_BACK);
				else if (pInput.isKeyPressed(Input.KEY_ENTER))
					tIL.keyPress(pWorld, pInput, Input.KEY_ENTER);
				else if (pInput.isKeyPressed(Input.KEY_LEFT))
					tIL.keyPress(pWorld, pInput, Input.KEY_LEFT);
				else if (pInput.isKeyPressed(Input.KEY_RIGHT))
					tIL.keyPress(pWorld, pInput, Input.KEY_RIGHT);
				else if (pInput.isKeyPressed(Input.KEY_UP))
					tIL.keyPress(pWorld, pInput, Input.KEY_UP);
				else if (pInput.isKeyPressed(Input.KEY_DOWN))
					tIL.keyPress(pWorld, pInput, Input.KEY_DOWN);
				else if (pInput.isKeyPressed(Input.KEY_A))
					tIL.keyPress(pWorld, pInput, Input.KEY_A);
				else if (pInput.isKeyPressed(Input.KEY_B))
					tIL.keyPress(pWorld, pInput, Input.KEY_B);
				else if (pInput.isKeyPressed(Input.KEY_C))
					tIL.keyPress(pWorld, pInput, Input.KEY_C);
				else if (pInput.isKeyPressed(Input.KEY_D))
					tIL.keyPress(pWorld, pInput, Input.KEY_D);
				else if (pInput.isKeyPressed(Input.KEY_E))
					tIL.keyPress(pWorld, pInput, Input.KEY_E);
				else if (pInput.isKeyPressed(Input.KEY_F))
					tIL.keyPress(pWorld, pInput, Input.KEY_F);
				else if (pInput.isKeyPressed(Input.KEY_G))
					tIL.keyPress(pWorld, pInput, Input.KEY_G);
				else if (pInput.isKeyPressed(Input.KEY_H))
					tIL.keyPress(pWorld, pInput, Input.KEY_H);
				else if (pInput.isKeyPressed(Input.KEY_I))
					tIL.keyPress(pWorld, pInput, Input.KEY_I);
				else if (pInput.isKeyPressed(Input.KEY_J))
					tIL.keyPress(pWorld, pInput, Input.KEY_J);
				else if (pInput.isKeyPressed(Input.KEY_K))
					tIL.keyPress(pWorld, pInput, Input.KEY_K);
				else if (pInput.isKeyPressed(Input.KEY_L))
					tIL.keyPress(pWorld, pInput, Input.KEY_L);
				else if (pInput.isKeyPressed(Input.KEY_M))
					tIL.keyPress(pWorld, pInput, Input.KEY_M);
				else if (pInput.isKeyPressed(Input.KEY_N))
					tIL.keyPress(pWorld, pInput, Input.KEY_N);
				else if (pInput.isKeyPressed(Input.KEY_O))
					tIL.keyPress(pWorld, pInput, Input.KEY_O);
				else if (pInput.isKeyPressed(Input.KEY_P))
					tIL.keyPress(pWorld, pInput, Input.KEY_P);
				else if (pInput.isKeyPressed(Input.KEY_Q))
					tIL.keyPress(pWorld, pInput, Input.KEY_Q);
				else if (pInput.isKeyPressed(Input.KEY_R))
					tIL.keyPress(pWorld, pInput, Input.KEY_R);
				else if (pInput.isKeyPressed(Input.KEY_S))
					tIL.keyPress(pWorld, pInput, Input.KEY_S);
				else if (pInput.isKeyPressed(Input.KEY_T))
					tIL.keyPress(pWorld, pInput, Input.KEY_T);
				else if (pInput.isKeyPressed(Input.KEY_U))
					tIL.keyPress(pWorld, pInput, Input.KEY_U);
				else if (pInput.isKeyPressed(Input.KEY_V))
					tIL.keyPress(pWorld, pInput, Input.KEY_V);
				else if (pInput.isKeyPressed(Input.KEY_W))
					tIL.keyPress(pWorld, pInput, Input.KEY_W);
				else if (pInput.isKeyPressed(Input.KEY_X))
					tIL.keyPress(pWorld, pInput, Input.KEY_X);
				else if (pInput.isKeyPressed(Input.KEY_Y))
					tIL.keyPress(pWorld, pInput, Input.KEY_Y);
				else if (pInput.isKeyPressed(Input.KEY_Z))
					tIL.keyPress(pWorld, pInput, Input.KEY_Z);
			}
		}
	}
	
	public static void publishKeyDown(World pWorld, Input pInput)
	{
		for (Integer tKey : subscribers.keySet())
		{
			for (InputListener tIL : subscribers.get(tKey))
			{
				if (pInput.isKeyPressed(Input.KEY_ESCAPE))
					tIL.keyDown(pWorld, pInput, Input.KEY_ESCAPE);
				else if (pInput.isKeyPressed(Input.KEY_END))
					tIL.keyDown(pWorld, pInput, Input.KEY_END);
				else if (pInput.isKeyPressed(Input.KEY_DELETE))
					tIL.keyDown(pWorld, pInput, Input.KEY_DELETE);
				else if (pInput.isKeyPressed(Input.KEY_BACK))
					tIL.keyDown(pWorld, pInput, Input.KEY_BACK);
				else if (pInput.isKeyPressed(Input.KEY_ENTER))
					tIL.keyDown(pWorld, pInput, Input.KEY_ENTER);
				else if (pInput.isKeyPressed(Input.KEY_LEFT))
					tIL.keyDown(pWorld, pInput, Input.KEY_LEFT);
				else if (pInput.isKeyPressed(Input.KEY_RIGHT))
					tIL.keyDown(pWorld, pInput, Input.KEY_RIGHT);
				else if (pInput.isKeyPressed(Input.KEY_UP))
					tIL.keyDown(pWorld, pInput, Input.KEY_UP);
				else if (pInput.isKeyPressed(Input.KEY_DOWN))
					tIL.keyDown(pWorld, pInput, Input.KEY_DOWN);
				else if (pInput.isKeyPressed(Input.KEY_A))
					tIL.keyDown(pWorld, pInput, Input.KEY_A);
				else if (pInput.isKeyPressed(Input.KEY_B))
					tIL.keyDown(pWorld, pInput, Input.KEY_B);
				else if (pInput.isKeyPressed(Input.KEY_C))
					tIL.keyDown(pWorld, pInput, Input.KEY_C);
				else if (pInput.isKeyPressed(Input.KEY_D))
					tIL.keyDown(pWorld, pInput, Input.KEY_D);
				else if (pInput.isKeyPressed(Input.KEY_E))
					tIL.keyDown(pWorld, pInput, Input.KEY_E);
				else if (pInput.isKeyPressed(Input.KEY_F))
					tIL.keyDown(pWorld, pInput, Input.KEY_F);
				else if (pInput.isKeyPressed(Input.KEY_G))
					tIL.keyDown(pWorld, pInput, Input.KEY_G);
				else if (pInput.isKeyPressed(Input.KEY_H))
					tIL.keyDown(pWorld, pInput, Input.KEY_H);
				else if (pInput.isKeyPressed(Input.KEY_I))
					tIL.keyDown(pWorld, pInput, Input.KEY_I);
				else if (pInput.isKeyPressed(Input.KEY_J))
					tIL.keyDown(pWorld, pInput, Input.KEY_J);
				else if (pInput.isKeyPressed(Input.KEY_K))
					tIL.keyDown(pWorld, pInput, Input.KEY_K);
				else if (pInput.isKeyPressed(Input.KEY_L))
					tIL.keyDown(pWorld, pInput, Input.KEY_L);
				else if (pInput.isKeyPressed(Input.KEY_M))
					tIL.keyDown(pWorld, pInput, Input.KEY_M);
				else if (pInput.isKeyPressed(Input.KEY_N))
					tIL.keyDown(pWorld, pInput, Input.KEY_N);
				else if (pInput.isKeyPressed(Input.KEY_O))
					tIL.keyDown(pWorld, pInput, Input.KEY_O);
				else if (pInput.isKeyPressed(Input.KEY_P))
					tIL.keyDown(pWorld, pInput, Input.KEY_P);
				else if (pInput.isKeyPressed(Input.KEY_Q))
					tIL.keyDown(pWorld, pInput, Input.KEY_Q);
				else if (pInput.isKeyPressed(Input.KEY_R))
					tIL.keyDown(pWorld, pInput, Input.KEY_R);
				else if (pInput.isKeyPressed(Input.KEY_S))
					tIL.keyDown(pWorld, pInput, Input.KEY_S);
				else if (pInput.isKeyPressed(Input.KEY_T))
					tIL.keyDown(pWorld, pInput, Input.KEY_T);
				else if (pInput.isKeyPressed(Input.KEY_U))
					tIL.keyDown(pWorld, pInput, Input.KEY_U);
				else if (pInput.isKeyPressed(Input.KEY_V))
					tIL.keyDown(pWorld, pInput, Input.KEY_V);
				else if (pInput.isKeyPressed(Input.KEY_W))
					tIL.keyDown(pWorld, pInput, Input.KEY_W);
				else if (pInput.isKeyPressed(Input.KEY_X))
					tIL.keyDown(pWorld, pInput, Input.KEY_X);
				else if (pInput.isKeyPressed(Input.KEY_Y))
					tIL.keyDown(pWorld, pInput, Input.KEY_Y);
				else if (pInput.isKeyPressed(Input.KEY_Z))
					tIL.keyDown(pWorld, pInput, Input.KEY_Z);
			}
		}
	}
	
	public static void publishMousePress(World pWorld, Input pInput)
	{
		for (Integer tKey : subscribers.keySet())
		{
			for (InputListener tIL : subscribers.get(tKey))
			{
				if (pInput.isMousePressed(Input.MOUSE_LEFT_BUTTON))
					tIL.mouseLeftClick(pWorld, pInput, pInput.getMouseX(), pInput.getMouseY());
				else if (pInput.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON))
					tIL.mouseRightClick(pWorld, pInput, pInput.getMouseX(), pInput.getMouseY());
				else if (pInput.isMouseButtonDown(Input.MOUSE_MIDDLE_BUTTON))
					tIL.mouseMiddleClick(pWorld, pInput, pInput.getMouseX(), pInput.getMouseY());
			}
		}
	}
}
