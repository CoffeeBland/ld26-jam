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
	private UnicodeFont uFontSmall = FontFactory.get().getFont(18, java.awt.Color.WHITE);
	private UnicodeFont uFont = FontFactory.get().getFont(26, java.awt.Color.WHITE);
	private UnicodeFont uFontBlack = FontFactory.get().getFont(26, java.awt.Color.DARK_GRAY);
	private UnicodeFont uFontSmallBlack = FontFactory.get().getFont(16, java.awt.Color.BLACK);
	
	public boolean getActivated(){
		return activated;
	}
	public void setActivated(boolean pActivated){
		this.activated = pActivated;
	}
	
	public void addAvailbleMachine(Processor pMachine){
		availableMachines.add(pMachine);
	}
	
	public BuildMenu(World pWorld){
		this.world = pWorld;
		availableMachines.add(new ProcessorImpl());
		availableMachines.get(0).setCost(3);
		availableMachines.get(0).setColor(BlockColor.Red);
		availableMachines.get(0).setTileWidth(2);
		availableMachines.get(0).setTileHeight(2);
		try {
			availableMachines.get(0).setImage(new BlockImage(BlockImage.getImage("Processor_2x2.png")));
			availableMachines.get(0).setForeGround(new BlockImage(BlockImage.getImage("ProcessorForeground_2x2.png")));
		} catch (SlickException e) {
			System.out.println("Failed to load images in BuildMenu");
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		setActivated(false);
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
				tM.renderFull(-ox, -oy);
				// Takes
				uFontSmallBlack.drawString(ox + 96,  oy + i*48 + 4,  "Takes:");
				uFontSmallBlack.drawString(ox + 168, oy + i*48 + 4,  "Anything");
				// Gives
				uFontSmallBlack.drawString(ox + 96,  oy + i*48 + 28, "Gives:");
				uFontSmallBlack.drawString(ox + 168, oy + i*48 + 28, "Reder triangles");
				// Costs
				uFontSmallBlack.drawString(ox + 408, oy + i*48 + 4,  "Costs:");
				uFontSmallBlack.drawString(ox + 480, oy + i*48 + 4,  "3 x");
				g.setColor(new Color(128, 64, 32));
				g.fillRect(ox + 529, oy + i*48+1, 24, 24);
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
					this.setActivated(false);
					world.activateIcons(true);
					world.activateIconsTiedToSelection(false);
				}
				int btnox = gc.getWidth()/2-400 + 664;
				int btnoy = gc.getHeight()/2-276 + 24;
				int i = 0;
				for (Processor tM : availableMachines){
					if ((new Rectangle(btnox + i*48, btnoy + i*48, 94, 46)).contains(mx, my)){
						this.setActivated(false);
						world.activateIcons(true);
						world.enterPlaceMachine(tM);
					}
					i++;
				}
			}// mouse was pressed
			
		}// is activated
	}

}
