package ld26_kiasaki_dagothig.entity;

public class ExitPoint extends PipeImpl
{
	public Factory factory;
	
	@Override
	public int getProgressDuration()
	{
		return progressDuration;
	}
	
	@Override
	public void sendBlock(Block pBlock) 
	{
		progress.remove(pBlock);
		factory.getTransformedBlocks().add(pBlock);
	}
	
	public ExitPoint(Factory pFactory)
	{
		factory = pFactory;
	}
}
