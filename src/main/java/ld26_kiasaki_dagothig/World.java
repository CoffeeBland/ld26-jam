package ld26_kiasaki_dagothig;

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
import ld26_kiasaki_dagothig.ui.CurrencyBar;
import ld26_kiasaki_dagothig.ui.IconButton;
import ld26_kiasaki_dagothig.ui.InfoWindow;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class World 
{
	private GameDirector gd;
	
	private UnicodeFont uFontSmall = FontFactory.get().getFont(18, java.awt.Color.WHITE);
	private UnicodeFont uFontRealSmall = FontFactory.get().getFont(14, java.awt.Color.WHITE);
	private Button btnMenu;
	private Button btnNeeded;
	private Button btnDone;
	private List<IconButton> icons;
	
	private int scrollX = 0;
	private int scrollY = 0;
	
	private Machine machineBeingPlaced;
	private Machine lastMachineBeingPlaced;
	private Factory factory;
	private Rectangle currentSelection = new Rectangle(-1, -1, 0, 0);
	
	private final BuildMenu buildMenu = new BuildMenu(this);
	private final CurrencyBar currencybar = new CurrencyBar();
	
	private final Color lightGray = new Color(100,100,100);
	private final Color darkGray = new Color(60,60,60);
	private final Color darkBrick = new Color(194,52,32);
	
	public World(GameDirector pGameDirector){
		this.gd = pGameDirector;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		btnMenu = new Button(gc.getWidth() - 90, 0, 90, 48, uFontSmall, darkGray, "MENU");
		btnNeeded = new Button(gc.getWidth() - 164, 76, 144, 48, uFontSmall, null, "NEEDED");
		btnDone = new Button(gc.getWidth() - 164, 376, 144, 48, uFontSmall, null, "DONE");
		icons = new ArrayList<IconButton>();
		icons.add(new IconButton(300, 0, Color.lightGray, new Color(25,145,47), new Image("res/icons/play.png"), "Play"));
		icons.add(new IconButton(348, 0, Color.lightGray, new Color(247,226,2), new Image("res/icons/pause.png"), "Pause factory"));
		icons.add(new IconButton(396, 0, Color.lightGray, new Color(23,78,217), new Image("res/icons/build.png"), "Build a machine"));
		icons.add(new IconButton(444, 0, Color.lightGray, new Color(25,145,47), new Image("res/icons/pipe_add.png"), "Add a pipe"));
		icons.add(new IconButton(492, 0, Color.lightGray, new Color(161,0,0), new Image("res/icons/trash.png"), "Destroy!"));
		
		factory = new FactoryImpl(24, 24, 224, 100);
		
		currencybar.init(gc, sbg);
		buildMenu.init(gc, sbg);
		
		activateIconsTiedToSelection(false);
		
		gd.setWorld(this);
		gd.start();
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
		if (currentSelection.getX() >= 0 && currentSelection.getY() >= 0){
			int tmpBWFade = (int) Math.abs( (gc.getTime() % 512) - 255 );
			g.setColor(new Color(tmpBWFade, tmpBWFade, tmpBWFade, 128));
			g.fill(rectangleTileToPixel(currentSelection));
			g.draw(rectangleTileToPixel(currentSelection));
		}
		
		for (IconButton tIB : icons)
			tIB.draw(gc, g);
		for (IconButton tIB : icons)
			tIB.drawTooltip(gc, g);
			
		btnMenu.draw(gc, g);
		btnNeeded.draw(gc, g);
		btnDone.draw(gc, g);	
		
		currencybar.render(gc, sbg, g);
		
		if (machineBeingPlaced != null){
			if (machineBeingPlaced instanceof Pipe){
				InfoWindow.renderWindow(g, uFontRealSmall, "Press C to rotate, Press V to switch form,", "Press X to add another pipe.");
			}
			
			float mx = gc.getInput().getMouseX();
			float my = gc.getInput().getMouseY();
			int tileX = clampCursorToTileMapX((int)(mx-machineBeingPlaced.getW()/2), machineBeingPlaced.getTileWidth()),
				tileY = clampCursorToTileMapY((int)(my-machineBeingPlaced.getH()/2), machineBeingPlaced.getTileHeight());
			machineBeingPlaced.renderFull(-tileX * 24-factory.getX(), 
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
				if (!buildMenu.getActivated() && icons.get(2).contains(mx, my)){
					// Build menu
					buildMenu.setActivated(true);
					activateIcons(false);
				}else if (!buildMenu.getActivated() && icons.get(3).getActivated() && icons.get(3).contains(mx, my)){
					// Add pipe
					enterPlacePipe();
				}else if (!buildMenu.getActivated() && icons.get(4).getActivated() && icons.get(4).contains(mx, my)){
					// Destroy!
					factory.destroy( factory.getMachine((int)currentSelection.getX(), (int)currentSelection.getY()) );
					currentSelection = new Rectangle(-1, -1, 0, 0);
				}else if (new Rectangle(factory.getX(), factory.getY(), factory.getTileXAmount() * TileBased.TILE_SIZE, factory.getTileYAmount() * TileBased.TILE_SIZE).contains(mx, my)){
					enterSelectMode((int)mx, (int)my);
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
					lastMachineBeingPlaced = machineBeingPlaced;
					machineBeingPlaced = null;
					activateIcons(true);
					activateIconsTiedToSelection(false);
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
		currencybar.update(gc, sbg, d);
		factory.update(d);
	}
	
	public void activateIcons(boolean pActive){
		for (IconButton tIB : icons)
			tIB.setActivated(pActive);
	}
	public void activateIconsTiedToSelection(boolean pActive){
		icons.get(4).setActivated(pActive);
	}
	public Rectangle rectangleTileToPixel(Rectangle pBase){
		return new Rectangle(pBase.getX()*TileBased.TILE_SIZE + factory.getX(), 
							 pBase.getY()*TileBased.TILE_SIZE + factory.getY(), 
							 pBase.getWidth()*TileBased.TILE_SIZE, 
							 pBase.getHeight()*TileBased.TILE_SIZE);
	}
	
	public void enterPlaceMachine(Machine pMachine){
		machineBeingPlaced = pMachine;
		machineBeingPlaced.setX(0);
		machineBeingPlaced.setY(0);
	}
	public void	enterPlacePipe() throws SlickException{
		Pipe tPipe = new PipeImpl();
		if (lastMachineBeingPlaced instanceof Pipe){
			tPipe.setAngle(lastMachineBeingPlaced.getAngle());
			tPipe.setAngleOut(((Pipe) lastMachineBeingPlaced).getAngleOut());
		}else{
			tPipe.setAngle(0);
			tPipe.setAngleOut(180);
		}
		tPipe.calculateSprite();
		tPipe.setTileHeight(1);
		tPipe.setTileWidth(1);
		enterPlaceMachine(tPipe);
		activateIcons(false);
	}
	public void enterSelectMode(int pMx, int pMy){
		currentSelection.setX(clampCursorToTileMapXCeil(pMx-24, 1));
		currentSelection.setY(clampCursorToTileMapYCeil(pMy-24, 1));
		Machine tmpMach = factory.getMachine((int)currentSelection.getX(), (int)currentSelection.getY());
		if (tmpMach != null) {
			currentSelection.setX(tmpMach.getTileX());
			currentSelection.setY(tmpMach.getTileY());
			currentSelection.setWidth(tmpMach.getTileWidth());
			currentSelection.setHeight(tmpMach.getTileHeight());
			activateIconsTiedToSelection(true);
		}else{
			currentSelection.setX(-1);
			currentSelection.setY(-1);
			activateIconsTiedToSelection(false);
		}
	}
	
	public int clampCursorToTileMapX(int pX, int pMaxOffset)
	{
		return Math.max(0, Math.min(factory.getTileXAmount() - pMaxOffset, Math.round((pX - factory.getX()) / (float)TileBased.TILE_SIZE)));
	}
	public int clampCursorToTileMapY(int pY, int pMaxOffset)
	{
		return Math.max(0, Math.min(factory.getTileYAmount() -pMaxOffset, Math.round((pY - factory.getY()) / (float)TileBased.TILE_SIZE)));	
	}
	public int clampCursorToTileMapXCeil(int pX, int pMaxOffset)
	{
		return (int) Math.max(0, Math.min(factory.getTileXAmount() - pMaxOffset, Math.ceil((pX - factory.getX()) / (float)TileBased.TILE_SIZE)));
	}
	public int clampCursorToTileMapYCeil(int pY, int pMaxOffset)
	{
		return (int) Math.max(0, Math.min(factory.getTileYAmount() -pMaxOffset, Math.ceil((pY - factory.getY()) / (float)TileBased.TILE_SIZE)));	
	}
	
}
