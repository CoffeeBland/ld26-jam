package ld26_kiasaki_dagothig.entity;

import org.newdawn.slick.Color;

public enum BlockColor 
{
	Red(255, 0, 0),
	Purple(255, 0, 255),
	Blue(0, 0, 255),
	Green(0, 255, 0),
	Yellow(255, 255, 0),
	Orange(255, 128, 0);
	
	public int r,g,b;
	
	private BlockColor(int pR, int pG, int pB)
	{
		r = pR;
		g = pG;
		b = pB;
	}
	public Color computeColor()
	{
		return new Color(r, g, b);
	}
}
