package ld26_kiasaki_dagothig.entity;

import java.util.List;

import org.newdawn.slick.SlickException;

public interface Factory 
{
	public int getTileXAmount();
	public int getTileYAmount();
	
	public boolean spaceAvailable(int pTileX, int pTileY, int pTileW, int pTileH);
	public Machine getEntryPoint();
	public Machine getExitPoint();
	
	public List<Machine> getMachines();
	
	public Machine getMachine(int pTileX, int pTileY);
	
	public void receiveBlock(Block pBlock);
	public List<Block> getTransformedBlocks();
	
	public void update(int d) throws SlickException;
	public void render(int pScrollX, int pScrollY) throws SlickException;

	public void addPipe(int pTileX, int pTileY, int pEntryAngle, int pExitAngle);
	public void addRouter(int pTileX, int pTileY, int pEntryAngle);
	public void addProcessor(int pTileX, int pTileY, int pTileW, int pTileH, List<BlockShape> pAcceptedShapes, BlockShape pResultShape, BlockColor pColor);
	public int destroy(Machine pMachine);
}
