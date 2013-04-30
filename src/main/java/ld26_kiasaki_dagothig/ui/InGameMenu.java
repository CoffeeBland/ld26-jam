package ld26_kiasaki_dagothig.ui;

import java.awt.Menu;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import ld26_kiasaki_dagothig.Main;
import ld26_kiasaki_dagothig.World;
import ld26_kiasaki_dagothig.helpers.FontFactory;
import ld26_kiasaki_dagothig.helpers.Renderable;
import ld26_kiasaki_dagothig.states.GameState;
import ld26_kiasaki_dagothig.states.MenuState;

public class InGameMenu implements Renderable {

	private World world;
	private boolean activated;
	private UnicodeFont uFontSmall;
	
	private List<String> btns;
	private int topX;
	private int topCenterX;
	private int topY;
	private int width;
	private int height;
	
	public InGameMenu(World pWorld){
		world = pWorld;
		activated = false;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		uFontSmall = FontFactory.get().getFont(18, java.awt.Color.WHITE);
		
		btns = new ArrayList<String>();
		btns.add("Resume game");
		btns.add("Restart level");
		btns.add("Toggle fullscreen");
		btns.add("Main menu");
		btns.add("Quit game");
		
		width = 200;
		height = btns.size()*48;
		topCenterX = gc.getWidth()/2;		
		topX = gc.getWidth()/2-width/2;
		topY = gc.getHeight()/2-24-btns.size()*24;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		if (activated){
			// Overlay
			g.setColor(new Color(0,0,0,0.75f));
			g.fillRect(0,0,gc.getWidth(),gc.getHeight());
			// Top bar
			g.setColor(Color.lightGray);
			g.fillRect(topX, topY, width, 48);
			// Window body
			g.setColor(new Color(245,245,245));
			g.fillRect(topX, topY+48, width, height);
			// Title
			uFontSmall.drawString(topCenterX - uFontSmall.getWidth("Menu")/2, topY + (24 - uFontSmall.getLineHeight()/2), "Menu");
			
			float mx = gc.getInput().getMouseX();
			float my = gc.getInput().getMouseY();
			int i = 0;
			for (String tBtnText : btns){
				if (new Rectangle(topX, topY + 48 + i, width, 48).contains(mx, my)) {
					g.setColor(Color.lightGray);
					g.fillRect(topX, topY + 48 + i, width, 48);
				}
				i += 48;
			}
			
			i = -96;
			g.setColor(Color.black);
			g.drawLine(topX+1, topY + 96 + i, topX+width-1, topY + 96 + i);
			i = -48;
			g.setColor(Color.black);
			g.drawLine(topX+1, topY + 96 + i, topX+width-1, topY + 96 + i);
			i = 0;
			for (String tBtnText : btns){
				uFontSmall.drawString(topCenterX - uFontSmall.getWidth(tBtnText)/2, topY+76-uFontSmall.getLineHeight()/2+i, tBtnText, Color.black);
				g.setColor(Color.darkGray);
				g.drawLine(topX+1, topY + 96 + i, topX+width-1, topY + 96 + i);
				i += 48;
			}
			
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		float mx = gc.getInput().getMouseX();
		float my = gc.getInput().getMouseY();
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			this.activated = false;
		}
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if (new Rectangle(topX, topY, width, height+48).contains(mx, my)){
				int i = 0;
				for (String tBtnText : btns){
					if (new Rectangle(topX, topY + 48 + i, width, 48).contains(mx, my)) {
						switch (i/48) {
							case 0:
								this.activated = false;
								break;
							case 1:
								if (world.gd.getCurrentLevel().getLevel() == 1)
									sbg.enterState(GameState.ID);
								else
								{
									world.load(world.gd.getCurrentLevel().getLevel());
									this.activated = false;
								}
								break;
							case 2:
								Main.toggleFullscreen();
								world.initPositions(gc, sbg);
								break;
							case 3:
								sbg.enterState(MenuState.ID);
								break;
							case 4:
								gc.exit();
								break;
						}
					}
					i += 48;
				}
			}else{
				this.activated = false;
			}
		}
	}
	
	public boolean getActivated(){
		return activated;
	}
	public void setActivated(boolean pActivated){
		this.activated = pActivated;
	}

}
