package ld26_kiasaki_dagothig.entity;

import java.awt.Graphics;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public interface Factory 
{
	public int getTileXAmount();
	public int getTileYAmount();
	
	public boolean spaceAvailable(int pX, int pY, int pW, int pH);
	public Machine getEntryPoint();
	public Machine getExitPoint();
	
	public List<Machine> getMachines();
	
	public void receiveBlock(Block pBlock);
	public List<Block> getTransformedBlocks();
	
	public void update(GameContainer gc, StateBasedGame sbg, int d) throws SlickException;
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int pScrollX, int pScrollY) throws SlickException;

}
