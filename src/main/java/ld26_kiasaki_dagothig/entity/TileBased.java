package ld26_kiasaki_dagothig.entity;

public interface TileBased 
{
	public final static int TILE_SIZE = 24;
	
	public int getTileX();
	public void setTileX(int tileX);
	
	public int getTileY();
	public void setTileY(int tileY);
	
	public int getTileWidth();
	public void setTileWidth(int tileWidth);
	
	public int getTileHeight();
	public void setTileHeight(int tileHeight);
	
}
