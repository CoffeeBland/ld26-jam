package ld26_kiasaki_dagothig;

import ld26_kiasaki_dagothig.ui.Button;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

public class World 
{
	private UnicodeFont uFontSmall;
	private Button btnMenu;
	private Button btnNeeded;
	private Button btnDone;
	
	private final Color lightGray = new Color(100,100,100);
	private final Color darkGray = new Color(60,60,60);
	private final Color darkBrick = new Color(194,74,56);
	
	public World(){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		uFontSmall = new UnicodeFont("res/fonts/slkscr.ttf", 18, false, false);
		uFontSmall.getEffects().add(new ColorEffect());
		uFontSmall.addAsciiGlyphs();
		uFontSmall.loadGlyphs();
		
		btnMenu = new Button(gc.getWidth() - 90, 0, 90, 48, uFontSmall, darkGray, "MENU");
		btnNeeded = new Button(gc.getWidth() - 164, 76, 144, 48, uFontSmall, null, "NEEDED");
		btnDone = new Button(gc.getWidth() - 164, 376, 144, 48, uFontSmall, null, "DONE");
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		// Top bar BG
		g.setColor(lightGray);
		g.fillRect(0, 0, gc.getWidth(), 48);
		g.setColor(darkGray);
		g.fillRect(0, 48, gc.getWidth(), 6);
		
		// Truck
		g.setColor(Color.blue);
		g.fillRect(20, 700-300, 144, 300);
		g.setColor(Color.white);
		g.drawString("Truck", 30, 700-290);
		// Pipe to factory
		g.setColor(darkGray);
		g.fillRect(gc.getWidth()/2-302-44, 630, 44, 24);
		
		// Panel
		g.setColor(darkBrick);
		g.fillRect(gc.getWidth() - 164, 76, 144, 600);
		g.setColor(darkGray);
		g.fillRect(gc.getWidth() - 164 + 22, 676, 100, 24);
		// Pipe to output panel
		g.setColor(darkGray);
		g.fillRect(gc.getWidth()/2+302, 630, 44, 24);
		
		// Border
		g.setColor(darkBrick);
		g.fillRect(gc.getWidth()/2-300, 76, 600, 624);
		
		// Factory square
		g.setColor(Color.blue);
		g.fillRect(gc.getWidth()/2-288, 100, 576, 576);
		g.setColor(Color.white);
		g.drawString("Factory", gc.getWidth()/2-278, 110);
		
		btnMenu.draw(gc, g);
		btnNeeded.draw(gc, g);
		btnDone.draw(gc, g);	
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
	}
	
}
