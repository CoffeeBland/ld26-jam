package ld26_kiasaki_dagothig.entity;

public interface Router extends Machine, Pipe
{
	public Machine getPossibleOut(float pAngle);
	public void setPossibleOut(float pAngle, Machine pMachine);
	
	public void updateOuts(Factory pFactory);
	
	public void changeDirection(Factory pFactory);
}
