package ld26_kiasaki_dagothig.helpers;

import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BlockImage 
{
	private static Map<String, Image> images = new TreeMap<String, Image>();
	public static Image getImage(String pRef) throws SlickException
	{
		Image img = images.get(pRef);
		if (img == null)
		{
			img = new Image("/res/sprites/" + pRef);
			images.put(pRef, img);
		}
		return img;
	}
	
	public int x, y, w, h;
	public int srcX = 0, srcY = 0;
	public Color color = Color.white;
	
	private int anim;
	public int animCount = 1;
	public int animDuration;
	public boolean animRepeat;
	
	public Image image;
	
	public void update(int delta)
	{
		if (animCount <= 0)
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
					animCount = -1;
		}
	}
	public void render(int pScrollX, int pScrollY)
	{
		image.draw(x - pScrollX, y - pScrollY, x - pScrollX + w, y - pScrollY + h, 
				   srcX, srcY, srcX + w, srcY + h,
				   color);
	}

	public BlockImage(Image pImage)
	{
		w = pImage.getWidth();
		h = pImage.getHeight();
		image = pImage;
		
		animCount = -1;
	}
	public BlockImage(Image pImage, int pAnimCount, int pAnimDuration, boolean pAnimRepeat)
	{
		w = pImage.getWidth() / pAnimCount;
		h = pImage.getHeight();
		image = pImage;
		
		animCount = pAnimCount;
		animDuration = pAnimDuration;
		anim = animDuration;
		animRepeat = pAnimRepeat;
	}
}
