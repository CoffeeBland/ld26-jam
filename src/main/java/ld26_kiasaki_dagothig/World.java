package ld26_kiasaki_dagothig;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import ld26_kiasaki_dagothig.entity.BlockShape;
import ld26_kiasaki_dagothig.entity.Factory;
import ld26_kiasaki_dagothig.entity.FactoryImpl;
import ld26_kiasaki_dagothig.entity.Machine;
import ld26_kiasaki_dagothig.entity.Pipe;
import ld26_kiasaki_dagothig.entity.PipeImpl;
import ld26_kiasaki_dagothig.entity.Processor;
import ld26_kiasaki_dagothig.entity.Router;
import ld26_kiasaki_dagothig.entity.TileBased;
import ld26_kiasaki_dagothig.helpers.FontFactory;
import ld26_kiasaki_dagothig.ui.BuildMenu;
import ld26_kiasaki_dagothig.ui.Button;
import ld26_kiasaki_dagothig.ui.IconButton;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

public class World 
{
	private UnicodeFont uFontSmall = FontFactory.get().getFont(18, java.awt.Color.WHITE);
	private Button btnMenu;
	private Button btnNeeded;
	private Button btnDone;
	private List<IconButton> icons;
	
	private int scrollX = 0;
	private int scrollY = 0;
	
	private Machine machineBeingPlaced;
	private Factory factory;
	
	private final BuildMenu buildMenu = new BuildMenu(this);
	
	private final Color lightGray = new Color(100,100,100);
	private final Color darkGray = new Color(60,60,60);
	private final Color darkBrick = new Color(194,52,32);
	
	public World(){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		btnMenu = new Button(gc.getWidth() - 90, 0, 90, 48, uFontSmall, darkGray, "MENU");
		btnNeeded = new Button(gc.getWidth() - 164, 76, 144, 48, uFontSmall, null, "NEEDED");
		btnDone = new Button(gc.getWidth() - 164, 376, 144, 48, uFontSmall, null, "DONE");
		icons = new ArrayList<IconButton>();
		icons.add(new IconButton(300, 0, Color.lightGray, new Color(25,145,47), new Image("res/icons/play.png")));
		icons.add(new IconButton(348, 0, Color.lightGray, new Color(247,226,2), new Image("res/icons/pause.png")));
		icons.add(new IconButton(396, 0, Color.lightGray, new Color(194,7,7), new Image("res/icons/build.png")));
		icons.add(new IconButton(444, 0, Color.lightGray, new Color(25,145,47), new Image("res/icons/pipe_add.png")));
		
		factory = new FactoryImpl(24, 24, 224, 100);
		factory.addPipe(3,  3,  0,  90);
		
		buildMenu.init(gc, sbg);
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
		
		factory.render(g, scrollX, scrollY);
		
		for (IconButton tIB : icons)
			tIB.draw(gc, g);
			
		btnMenu.draw(gc, g);
		btnNeeded.draw(gc, g);
		btnDone.draw(gc, g);	
		
		if (machineBeingPlaced != null){
			float mx = gc.getInput().getMouseX();
			float my = gc.getInput().getMouseY();
			int tileX = clampCursorToTileMapX((int)(mx-machineBeingPlaced.getW()/2), machineBeingPlaced.getTileWidth()),
				tileY = clampCursorToTileMapY((int)(my-machineBeingPlaced.getH()/2), machineBeingPlaced.getTileHeight());
			machineBeingPlaced.render(-tileX * 24-factory.getX(), 
									  -tileY * 24-factory.getY());
			if (factory.spaceAvailable(tileX, tileY, machineBeingPlaced.getTileWidth(), machineBeingPlaced.getTileHeight()))
				g.setColor(new Color(0,255,0, 0.5f));
			else
				g.setColor(new Color(255,0,0, 0.5f));
			g.fillRect(tileX * 24+factory.getX(), 
					tileY * 24+factory.getY(), 
					machineBeingPlaced.getW(), machineBeingPlaced.getH());
		}
		
		buildMenu.render(gc, sbg, g);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int d) throws SlickException {
		float mx = gc.getInput().getMouseX();
		float my = gc.getInput().getMouseY();
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if (machineBeingPlaced == null){
				if (!buildMenu.getActivated() && icons.get(2).contains(mx, my))
				{
					// Build menu
					buildMenu.setActivated(true);
					activateIcons(false);
				}
				else if (!buildMenu.getActivated() && icons.get(3).contains(mx, my))
				{
					// Add pipe
					enterPlacePipe();
				}
				buildMenu.update(gc, sbg, d);
			}else{
				int tileX = clampCursorToTileMapX((int)(mx-machineBeingPlaced.getW()/2), machineBeingPlaced.getTileWidth()),
					tileY = clampCursorToTileMapY((int)(my-machineBeingPlaced.getH()/2), machineBeingPlaced.getTileHeight());
				if (new Rectangle(factory.getX(), factory.getY(), factory.getTileXAmount() * TileBased.TILE_SIZE, factory.getTileYAmount() * TileBased.TILE_SIZE).contains(mx, my) && 
					factory.spaceAvailable(tileX, tileY, machineBeingPlaced.getTileWidth(), machineBeingPlaced.getTileHeight())){
					if (machineBeingPlaced instanceof Processor)
					{
						Processor machine = (Processor)machineBeingPlaced;
						factory.addProcessor(tileX, tileY, machineBeingPlaced.getTileWidth(), machineBeingPlaced.getTileHeight(), machine.getShapeIns(), machine.getShapeOut(), machineBeingPlaced.getColor());
					}
					else if (machineBeingPlaced instanceof Router)
						factory.addRouter(tileX,  tileY, machineBeingPlaced.getAngle());
					else if (machineBeingPlaced instanceof Pipe)
					{
						Pipe machine = (Pipe)machineBeingPlaced;
						factory.addPipe(tileX, tileY, machine.getAngle(), machine.getAngleOut());
					}
					machineBeingPlaced = null;
					activateIcons(true);
				}
			}// We are add an item to the factory
		}// Mouse press
		if (gc.getInput().isKeyPressed(Input.KEY_X)) 
		{
			if (machineBeingPlaced == null && !buildMenu.getActivated()){
				enterPlacePipe();
			}
		}
		else if (gc.getInput().isKeyPressed(Input.KEY_C)) 
		{
			if (machineBeingPlaced instanceof Pipe){
				Pipe tmpPipe = ((Pipe)machineBeingPlaced);
				tmpPipe.setAngle((tmpPipe.getAngle() + 90) % 360);
				tmpPipe.setAngleOut((tmpPipe.getAngleOut() + 90) % 360);
				tmpPipe.calculateSprite();
			}
		}
		else if (gc.getInput().isKeyPressed(Input.KEY_V))
		{
			if (machineBeingPlaced instanceof Pipe){
				Pipe tmpPipe = ((Pipe)machineBeingPlaced);
				tmpPipe.setAngleOut((tmpPipe.getAngleOut() + 90) % 360);
				if (tmpPipe.getAngle() == tmpPipe.getAngleOut()){
					tmpPipe.setAngleOut((tmpPipe.getAngleOut()+90)%360);
				}
				tmpPipe.calculateSprite();
			}
		}
		factory.update(d);
	}
	
	public void activateIcons(boolean pActive){
		for (IconButton tIB : icons)
			tIB.setActivated(pActive);
	}
	
	public void enterPlaceMachine(Machine pMachine){
		machineBeingPlaced = pMachine;
		machineBeingPlaced.setX(0);
		machineBeingPlaced.setY(0);
	}
	public void	enterPlacePipe() throws SlickException{
		Pipe tPipe = new PipeImpl();
		tPipe.setAngle(0);
		tPipe.setAngleOut(180);
		tPipe.calculateSprite();
		tPipe.setTileHeight(1);
		tPipe.setTileWidth(1);
		enterPlaceMachine(tPipe);
		activateIcons(false);
	}
	
	public int clampCursorToTileMapX(int pX, int pMaxOffset)
	{
		return Math.max(0, Math.min(factory.getTileXAmount() - pMaxOffset, Math.round((pX - factory.getX()) / (float)TileBased.TILE_SIZE)));
	}
	public int clampCursorToTileMapY(int pY, int pMaxOffset)
	{
		return Math.max(0, Math.min(factory.getTileYAmount() -pMaxOffset, Math.round((pY - factory.getY()) / (float)TileBased.TILE_SIZE)));	
	}
	
}
