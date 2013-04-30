package ld26_kiasaki_dagothig.entity;

import java.util.List;

import ld26_kiasaki_dagothig.helpers.BlockImage;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Factory 
{
	public int getTileXAmount();
	public int getTileYAmount();
	public int getX();
	public void setX(int pX);
	public int getY();
	public void setY(int pY);
	
	public boolean spaceAvailable(int pTileX, int pTileY, int pTileW, int pTileH);
	public Pipe getEntryPoint();
	public Pipe getExitPoint();
	
	public List<Machine> getMachines();
	
	public Machine getMachine(int pTileX, int pTileY);
	
	public void receiveBlock(Block pBlock) throws SlickException;
	public List<Block> getTransformedBlocks();
	
	public void addSFX(BlockImage pImage);
	
	public void update(int d) throws SlickException;
	public void render(Graphics g, int pScrollX, int pScrollY) throws SlickException;

	public void addPipe(int pTileX, int pTileY, int pEntryAngle, int pExitAngle) throws SlickException;
	public void addRouter(int pTileX, int pTileY, int pEntryAngle) throws SlickException;
	public void addProcessor(int pTileX, int pTileY, int pTileW, int pTileH, List<BlockShape> pAcceptedShapes, BlockShape pResultShape, BlockColor pColor) throws SlickException;
	public int destroy(Machine pMachine);
}
