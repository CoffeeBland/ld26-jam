package ld26_kiasaki_dagothig.entity;

import java.util.TreeMap;

import org.newdawn.slick.SlickException;

import ld26_kiasaki_dagothig.helpers.BlockImage;

public class RouterImpl extends PipeImpl implements Router
{
	public TreeMap<Float, Machine> outsByAngle = new TreeMap<Float, Machine>();
	
	@Override
	public Machine getPossibleOut(float pAngle) 
	{
		return outsByAngle.get(pAngle);
	}
	@Override
	public void setPossibleOut(float pAngle, Machine pMachine) 
	{
		outsByAngle.put(pAngle, pMachine);
	}
	
	public BlockImage fleche;
	
	@Override
	public void changeDirection(Factory pFactory) 
	{
		updateOuts(pFactory);
		
		setAngleOut(getAngleOut() + 90);
		destroy();
		addOut(getPossibleOut(getAngleOut()), true);
		
		ins.clear();
		
		Machine mach;
		for (int angleIn = 0; angleIn < 360; angleIn += 90)
		{
			if (getAngleOut() != angleIn)
			{
				switch (angleIn)
				{
					case 0:
						mach = pFactory.getMachine(getTileX() + 1, getTileY());
						if (mach != null && (mach instanceof Processor || (mach instanceof Pipe && ((Pipe)mach).getAngleOut() == 180)))
							addIn(mach, true);
						break;
					case 90:
						mach = pFactory.getMachine(getTileX(), getTileY() + 1);
						if (mach != null && (mach instanceof Processor || (mach instanceof Pipe && ((Pipe)mach).getAngleOut() == 270)))
							addIn(mach, true);
						break;
					case 180:
						mach = pFactory.getMachine(getTileX() - 1, getTileY());
						if (mach != null && (mach instanceof Processor || (mach instanceof Pipe && ((Pipe)mach).getAngleOut() == 0)))
							addIn(mach, true);
						break;
					case 270:
						mach = pFactory.getMachine(getTileX(), getTileY() - 1);
						if (mach != null && (mach instanceof Processor || (mach instanceof Pipe && ((Pipe)mach).getAngleOut() == 90)))
							addIn(mach, true);
						break;
				}
			}
		}
	}
	
	@Override
	public void renderForeground(int pScrollX, int pScrollY) throws SlickException
	{
		super.renderForeground(pScrollX, pScrollY);
		if (fleche == null)
			fleche = new BlockImage(BlockImage.getImage("Fleche.png"));
		fleche.image.setCenterOfRotation(fleche.w / 2, fleche.h / 2);
		fleche.image.setRotation(getAngleOut());
		fleche.x = Math.round(getX());
		fleche.y = Math.round(getY());
		fleche.render(pScrollX, pScrollY);
	}

	@Override
	public void updateOuts(Factory pFactory)
	{
		Machine mach;
		// 0°
		mach = pFactory.getMachine(getTileX() + 1, getTileY());
		if ((mach instanceof Processor || mach instanceof Pipe && ((Pipe)mach).getAngle() == 180) || mach instanceof Router)
			setPossibleOut(0, mach);
		else
			setPossibleOut(0, null);
		
		// 90°
		mach = pFactory.getMachine(getTileX(), getTileY() + 1);
		if ((mach instanceof Processor || mach instanceof Pipe && ((Pipe)mach).getAngle() == 270) || mach instanceof Router)
			setPossibleOut(90, mach);
		else
			setPossibleOut(90, null);

		// 180°
		mach = pFactory.getMachine(getTileX() - 1, getTileY());
		if ((mach instanceof Processor || mach instanceof Pipe && ((Pipe)mach).getAngle() == 0) || mach instanceof Router)
			setPossibleOut(180, mach);
		else
			setPossibleOut(180, null);
		
		// 270°
		mach = pFactory.getMachine(getTileX(), getTileY() - 1);
		if ((mach instanceof Processor || mach instanceof Pipe && ((Pipe)mach).getAngle() == 90) || mach instanceof Router)
			setPossibleOut(270, mach);
		else
			setPossibleOut(270, null);
	}
	
	@Override
	public void calculateSprite() throws SlickException
	{
		setImage(new BlockImage(BlockImage.getImage("Router.png")));
		setForeground(new BlockImage(BlockImage.getImage("RouterForeground.png")));
	}
}
