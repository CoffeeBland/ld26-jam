package ld26_kiasaki_dagothig.entity;

public interface Router extends Machine
{
	public Machine getPossibleOut(float pAngle);
	public void setPossibleOut(float pAngle, Machine pMachine);
	
	public void changeDirection();
}
