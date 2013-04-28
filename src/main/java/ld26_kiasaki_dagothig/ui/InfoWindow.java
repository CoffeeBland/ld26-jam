package ld26_kiasaki_dagothig.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;

public class InfoWindow {

	public static Color black60  = new Color(0,0,0,0.6f);
	public static Color black75  = new Color(0,0,0,0.75f);
	
	public static void renderWindow(Graphics g, UnicodeFont pFont, String pText){
		renderWindow(g, pFont, pText, null);
	}
	
	public static void renderWindow(Graphics g, UnicodeFont pFont, String pText, String pTextLine2){
		g.setColor(black60);
		g.fillRect(212, 710, 600, 52);
		g.setColor(black75);
		g.drawRect(212, 710, 600, 52);
		pFont.drawString(222, 720, pText);
		if (pTextLine2 != null){
			pFont.drawString(222, 720+pFont.getLineHeight(), pTextLine2);
		}
	}
	
}
