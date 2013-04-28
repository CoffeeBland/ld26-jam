package ld26_kiasaki_dagothig.entity;

public class PipeImpl extends MachineImpl implements Pipe
{
	public int angleOut;
	
	@Override
	public int getAngleOut()
	{
		return angleOut;
	}
	@Override
	public void setAngleOut(int pAngle)
	{
		angleOut = pAngle;
	}
}
