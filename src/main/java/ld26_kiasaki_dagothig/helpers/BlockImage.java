package ld26_kiasaki_dagothig.helpers;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class BlockImage 
{
	public int x, y, w, h;
	public int srcX = 0, srcY = 0;
	public Color color;
	
	private int anim;
	public int animCount = 1;
	public int animDuration;
	public boolean animRepeat;
	
	public Image image;
	
	public void update(int delta)
	{
		if (animDuration <= 0)
			return;
		anim -= delta;
		while (anim < 0)
		{
			anim += animDuration;
			srcX += w;
			if (srcX > (animCount - 1) * w)
				if (animRepeat)
					srcX = 0;
				else
					animDuration = -1;
		}
	}
	public void render(int pScrollX, int pScrollY)
	{
		image.draw(x - pScrollX, y - pScrollY, w, h, srcX, srcY, w, h, color);
	}
}
