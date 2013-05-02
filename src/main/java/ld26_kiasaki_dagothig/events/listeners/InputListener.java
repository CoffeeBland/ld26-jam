package ld26_kiasaki_dagothig.events.listeners;

import ld26_kiasaki_dagothig.World;

import org.newdawn.slick.Input;

public interface InputListener {
	
	public void keyPress(World pWorld, Input pInput, int pKey);
	public void keyDown(World pWorld, Input pInput, int pKey);
	public void mouseLeftClick(World pWorld, Input pInput, int mx, int my);
	public void mouseRightClick(World pWorld, Input pInput, int mx, int my);
	public void mouseMiddleClick(World pWorld, Input pInput, int mx, int my);
	
}
