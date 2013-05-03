package ld26_kiasaki_dagothig.ui;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import ld26_kiasaki_dagothig.World;
import ld26_kiasaki_dagothig.entity.BlockColor;
import ld26_kiasaki_dagothig.entity.BlockShape;
import ld26_kiasaki_dagothig.entity.Processor;
import ld26_kiasaki_dagothig.entity.ProcessorImpl;
import ld26_kiasaki_dagothig.helpers.BlockImage;
import ld26_kiasaki_dagothig.helpers.FontFactory;
import ld26_kiasaki_dagothig.helpers.Renderable;

public class BuildMenu implements Renderable {

	private boolean activated;
	private World world;
	private List<IconButton> icons = new ArrayList<IconButton>();
	private List<Processor> availableMachines = new ArrayList<Processor>();
	private UnicodeFont uFont;
	private UnicodeFont uFontSmall;
	private UnicodeFont uFontSmallBlack;
	
	public boolean getActivated(){
		return activated;
	}
	public void setActivated(boolean pActivated){
		this.activated = pActivated;
	}
	public void addAvailbleMachine(Processor pMachine){
		pMachine.setX(0);
		pMachine.setY(0);
		availableMachines.add(pMachine);
	}
	public void removeAvailableMachine(Processor pMachine)
	{
		availableMachines.remove(pMachine);
	}
	public void setAvailbleMachines(List<Processor> pMachines){
		availableMachines = pMachines;
	}
	public List<Processor> getAvailableMachines()
	{
		return availableMachines;
	}
	
	public BuildMenu(World pWorld){
		this.world = pWorld;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		uFont = FontFactory.get().getFont(26, java.awt.Color.WHITE);
		uFontSmall = FontFactory.get().getFont(18, java.awt.Color.WHITE);
		uFontSmallBlack = FontFactory.get().getFont(16, java.awt.Color.BLACK);
		setActivated(false);
		icons = new ArrayList<IconButton>();
		icons.add(new IconButton(gc.getWidth()/2+352, gc.getHeight()/2-324, Color.lightGray, new Color(194,74,56), new Image("res/icons/close.png"), "Close"));
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
			
			// Icons
			for (IconButton tIB : icons)
				tIB.draw(gc, g);
			for (IconButton tIB : icons)
				tIB.drawTooltip(gc, g);
			
			// Title
			uFontSmall.drawString(gc.getWidth()/2-380, gc.getHeight()/2-300-uFontSmall.getLineHeight()/2, "Build a machine");
			
			g.setColor(Color.black);
			int ox = gc.getWidth()/2-400 + 40;
			int oy = gc.getHeight()/2-276 + 24;
			for (int j = 0; j < 31; j++){
				for (int k = 0; k < 24; k++){
					g.drawLine(j*24 + ox, k*24 + oy, j*24 + ox, k*24 + oy);
				}				
			}
			
			int i = 0;
			for (Processor tM : availableMachines){
				uFontSmallBlack.drawString(ox,  oy + i*48 + 4,  "Machine");
				uFontSmallBlack.drawString(ox,  oy + i*48 + 28,  tM.getTileWidth() +"x" + tM.getTileHeight());
				// Takes
				uFontSmallBlack.drawString(ox + 120,  oy + i*48 + 4,  "Takes:");
				String tTakes = "";
				for (BlockShape tBS : tM.getShapeIns()){
					tTakes += tBS.name().toString() + ", ";
				}
				uFontSmallBlack.drawString(ox + 192, oy + i*48 + 4,  tTakes.substring(0, tTakes.length()-2));
				// Gives
				uFontSmallBlack.drawString(ox + 120,  oy + i*48 + 28, "Gives:");
				uFontSmallBlack.drawString(ox + 192, oy + i*48 + 28, tM.getColor().name()+"er "+tM.getShapeOut().name().toLowerCase());
				// Costs
				uFontSmallBlack.drawString(ox + 408, oy + i*48 + 4,  "Costs:");
				uFontSmallBlack.drawString(ox + 480, oy + i*48 + 4,  tM.getCost() + " $");
				// Build button
				int tmpBtnX = ox + 624;
				int tmpBtnY = oy + i*48;
				g.setColor(new Color(41,125,44));
				g.fillRect(tmpBtnX+1, tmpBtnY+1, 95, 47);
				g.setColor(new Color(46,173,52));
				g.fillRect(tmpBtnX+3, tmpBtnY+3, 91, 43);
				g.setColor(new Color(36,199,44));
				g.fillRect(tmpBtnX+1, tmpBtnY+2, 2, 44);
				g.fillRect(tmpBtnX+1, tmpBtnY+3 + 43, 94, 2);
				//uFontBlack.drawString(tmpBtnX+47-uFontBlack.getWidth("BUILD")/2, tmpBtnY+10, "BUILD");
				uFont.drawString(tmpBtnX+48-uFont.getWidth("BUILD")/2, tmpBtnY+11, "BUILD");
				i++;
			}
			
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (activated) {
			float mx = gc.getInput().getMouseX();
			float my = gc.getInput().getMouseY();
			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				if(icons.get(0).contains(mx, my)){
					close();
				}
				int btnox = gc.getWidth()/2-400 + 664;
				int btnoy = gc.getHeight()/2-276 + 24;
				int i = 0;
				for (Processor tM : availableMachines){
					if ((new Rectangle(btnox, btnoy + i*48, 94, 46)).contains(mx, my)){
						close();
						world.enterPlaceMachine(tM);
					}
					i++;
				}
			}// mouse was pressed
			if (gc.getInput().isKeyDown(Input.KEY_ESCAPE)){
				close();
			}
		}// is activated
	}

	public void close(){
		this.setActivated(false);
		world.activateIcons(true);
		world.activateIconsTiedToSelection(false);
	}
	
}
