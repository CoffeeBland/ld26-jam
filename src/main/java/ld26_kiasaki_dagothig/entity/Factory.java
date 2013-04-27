package ld26_kiasaki_dagothig.entity;

import java.util.List;

import org.newdawn.slick.SlickException;

public interface Factory 
{
	public int getTileXAmount();
	public int getTileYAmount();
	
	public boolean spaceAvailable(int pX, int pY, int pW, int pH);
	public Machine getEntryPoint();
	public Machine getExitPoint();
	
	public List<Machine> getMachines();
	
	public Machine getMachine(int pX, int pY);
	
	public void receiveBlock(Block pBlock);
	public List<Block> getTransformedBlocks();
	
	public void update(int d) throws SlickException;
	public void render(int pScrollX, int pScrollY) throws SlickException;

	public void addPipe(int pX, int pY, int pAngle1, int pAngle2);
	public void addRouter(int pX, int pY, int pEntryAngle);
	public void addProcessor(int pX, int pY, int pW, int pH, List<BlockShape> pAcceptedShapes, BlockShape pResultShape, BlockColor pColor);
	public int destroy(Machine pMachine);
}
