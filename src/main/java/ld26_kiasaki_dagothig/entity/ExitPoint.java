package ld26_kiasaki_dagothig.entity;

public class ExitPoint extends MachineImpl
{
	public Factory factory;
	
	@Override
	public void sendBlock(Block pBlock) 
	{
		progress.remove(pBlock);
		factory.getTransformedBlocks().add(pBlock);
	}
}
