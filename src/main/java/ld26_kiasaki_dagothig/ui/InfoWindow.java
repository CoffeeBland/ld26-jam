package ld26_kiasaki_dagothig.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;

public class InfoWindow {

	public static Color black60  = new Color(0,0,0,0.6f);
	public static Color black75  = new Color(0,0,0,0.75f);
	
	public static void renderWindow(GameContainer gc, Graphics g, UnicodeFont pFont, String pText){
		renderWindow(gc, g, pFont, pText, null);
	}
	
	public static void renderWindow(GameContainer gc, Graphics g, UnicodeFont pFont, String pText, String pTextLine2){
		g.setColor(black60);
		g.fillRect(gc.getWidth() / 2 - 298, gc.getHeight()-56, 600, 52);
		g.setColor(black75);
		g.drawRect(gc.getWidth() / 2 - 298, gc.getHeight()-56, 600, 52);
		pFont.drawString(gc.getWidth() / 2 - 288, gc.getHeight()-46, pText);
		if (pTextLine2 != null){
			pFont.drawString(gc.getWidth() / 2 - 288, gc.getHeight()-46+pFont.getLineHeight(), pTextLine2);
		}
	}
	
}
