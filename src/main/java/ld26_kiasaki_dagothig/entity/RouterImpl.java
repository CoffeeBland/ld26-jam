package ld26_kiasaki_dagothig.entity;

import java.util.TreeMap;

import org.newdawn.slick.SlickException;

import ld26_kiasaki_dagothig.helpers.BlockImage;

public class RouterImpl extends PipeImpl implements Router
{
	public TreeMap<Float, Machine> outs = new TreeMap<Float, Machine>();

	@Override
	public Machine getPossibleOut(float pAngle) 
	{
		return outs.get(pAngle);
	}
	@Override
	public void setPossibleOut(float pAngle, Machine pMachine) 
	{
		outs.put(pAngle, pMachine);
	}

	public BlockImage fleche;
	
	@Override
	public void changeDirection() 
	{
		for (int index = 0; index < 4; index++)
		{
			setAngleOut(getAngleOut() + 90);
			System.out.println(getAngleOut());
			if (getPossibleOut(getAngleOut()) != null)
			{
				setOut(getPossibleOut(getAngleOut()), true);
				return;
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
		if (getAngle() != 0 && (mach instanceof Processor || mach instanceof Pipe && ((Pipe)mach).getAngle() == 180))
			setPossibleOut(0, mach);
		else
			setPossibleOut(0, null);
		
		// 90°
		mach = pFactory.getMachine(getTileX() - 1, getTileY());
		if (getAngle() != 90 && (mach instanceof Processor || mach instanceof Pipe && ((Pipe)mach).getAngle() == 270))
			setPossibleOut(90, mach);
		else
			setPossibleOut(90, null);

		// 180°
		mach = pFactory.getMachine(getTileX(), getTileY() + 1);
		if (getAngle() != 180 && (mach instanceof Processor || mach instanceof Pipe && ((Pipe)mach).getAngle() == 0))
			setPossibleOut(180, mach);
		else
			setPossibleOut(180, null);
		
		// 270°
		mach = pFactory.getMachine(getTileX(), getTileY() - 1);
		if (getAngle() != 270 && (mach instanceof Processor || mach instanceof Pipe && ((Pipe)mach).getAngle() == 90))
			setPossibleOut(270, mach);
		else
			setPossibleOut(270, null);
		System.out.println(outs);
	}
	
	@Override
	public void calculateSprite() throws SlickException
	{
		setImage(new BlockImage(BlockImage.getImage("Router.png")));
		setForeGround(new BlockImage(BlockImage.getImage("RouterForeGround.png")));
	}
}
