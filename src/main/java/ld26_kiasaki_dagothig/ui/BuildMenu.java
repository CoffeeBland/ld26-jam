package ld26_kiasaki_dagothig.ui;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import ld26_kiasaki_dagothig.World;
import ld26_kiasaki_dagothig.helpers.Renderable;

public class BuildMenu implements Renderable {

	private boolean activated;
	private World world;
	private List<IconButton> icons = new ArrayList<IconButton>();
	
	public boolean getActivated(){
		return activated;
	}
	public void setActivated(boolean pActivated){
		this.activated = pActivated;
	}
	
	public BuildMenu(World pWorld){
		this.world = pWorld;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		setActivated(false);
		icons.add(new IconButton(gc.getWidth()/2+352, gc.getHeight()/2-324, Color.lightGray, new Color(194,74,56), new Image("res/icons/close.png")));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if (activated){
			g.setColor(new Color(0,0,0,0.75f));
			g.fillRect(0,0,gc.getWidth(),gc.getHeight());
			// Top bar
			g.setColor(Color.lightGray);
			g.fillRect(gc.getWidth()/2-400, gc.getHeight()/2-324, 800, 48);
			// Window body
			g.setColor(new Color(245,245,245));
			g.fillRect(gc.getWidth()/2-400, gc.getHeight()/2-276, 800, 600);
			
			for (IconButton tIB : icons)
				tIB.draw(gc, g);
			
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (activated) {
			float mx = gc.getInput().getMouseX();
			float my = gc.getInput().getMouseY();
			if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && icons.get(0).contains(mx, my)){
				this.setActivated(false);
				world.activateIcons(true);
			}
		}
	}

}
