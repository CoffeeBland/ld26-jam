package ld26_kiasaki_dagothig;

import java.util.ArrayList;
import java.util.List;

import ld26_kiasaki_dagothig.entity.BlockShape;
import ld26_kiasaki_dagothig.entity.Factory;
import ld26_kiasaki_dagothig.entity.FactoryImpl;
import ld26_kiasaki_dagothig.entity.Machine;
import ld26_kiasaki_dagothig.entity.Order;
import ld26_kiasaki_dagothig.entity.Pipe;
import ld26_kiasaki_dagothig.entity.PipeImpl;
import ld26_kiasaki_dagothig.entity.Processor;
import ld26_kiasaki_dagothig.entity.Router;
import ld26_kiasaki_dagothig.entity.RouterImpl;
import ld26_kiasaki_dagothig.entity.TileBased;
import ld26_kiasaki_dagothig.helpers.BlockImage;
import ld26_kiasaki_dagothig.helpers.FontFactory;
import ld26_kiasaki_dagothig.helpers.SavingHelper;
import ld26_kiasaki_dagothig.ui.BuildMenu;
import ld26_kiasaki_dagothig.ui.Button;
import ld26_kiasaki_dagothig.ui.CurrencyBar;
import ld26_kiasaki_dagothig.ui.IconButton;
import ld26_kiasaki_dagothig.ui.InGameMenu;
import ld26_kiasaki_dagothig.ui.InfoWindow;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class World
{
	public GameDirector gd;
	public GameContainer gc;
	public StateBasedGame sbg;
	
	private UnicodeFont uFontSmall;
	private UnicodeFont uFontRealSmall;
	private Button btnMenu;
	private Button btnNeeded;
	private Button btnDone;
	private List<IconButton> icons;
	
	private int scrollX = 0;
	private int scrollY = 0;
	
	private Machine machineBeingPlaced;
	private Machine lastMachineBeingPlaced;
	public Factory factory;
	private Rectangle currentSelection = new Rectangle(-1, -1, 0, 0);
		
	public final BuildMenu buildMenu = new BuildMenu(this);
	public final InGameMenu igMenu = new InGameMenu(this);
	public final CurrencyBar currencybar = new CurrencyBar(this);
	
	private final Color lightGray = new Color(100,100,100);
	private final Color darkGray = new Color(60,60,60);
	private final Color skyColor = new Color(213, 235, 246);
	private BlockImage grass, groundImg, sky, panel;
	
	public World(GameDirector pGameDirector){
		this.gd = pGameDirector;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.gc = gc;
		this.sbg = sbg;
		
		uFontSmall = FontFactory.get().getFont(18, java.awt.Color.WHITE);
		uFontRealSmall = FontFactory.get().getFont(14, java.awt.Color.WHITE);
		
		btnMenu = new Button(gc.getWidth() - 90, 0, 90, 48, uFontSmall, darkGray, "MENU");
		btnNeeded = new Button(gc.getWidth() - 164, gc.getHeight() - 500, 144, 48, uFontSmall, null, "NEEDED");
		btnDone = new Button(gc.getWidth() - 164, gc.getHeight() - 320, 144, 48, uFontSmall, null, "DONE");
		icons = new ArrayList<IconButton>();
		icons.add(new IconButton(300, 0, Color.lightGray, new Color(25,145,47), new Image("res/icons/play.png"), "Play [p]"));
		icons.add(new IconButton(348, 0, Color.lightGray, new Color(247,226,2), new Image("res/icons/pause.png"), "Pause factory [p]"));
		icons.add(new IconButton(396, 0, Color.lightGray, new Color(23,78,217), new Image("res/icons/build.png"), "Build a machine [b]"));
		icons.add(new IconButton(444, 0, Color.lightGray, new Color(25,145,47), new Image("res/icons/pipe_add.png"), "Add a pipe [x]"));
		icons.add(new IconButton(540, 0, Color.lightGray, new Color(255,0,0), new Image("res/icons/trash.png"), "Destroy! [d]"));
		icons.add(new IconButton(492, 0, Color.lightGray, new Color(25,145,47), new Image("res/icons/router_add.png"), "Add a router [r]"));
		
		factory = new FactoryImpl(24, 24, gc.getWidth()/2-288, gc.getHeight() - 24 * 24 - 92);
		
		currencybar.init(gc, sbg);
		buildMenu.init(gc, sbg);
		igMenu.init(gc, sbg);
		
		activateIconsTiedToSelection(false);
		
		gd.setWorld(this);
		gd.pause();
	}
	public void initPositions(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		btnMenu = new Button(gc.getWidth() - 90, 0, 90, 48, uFontSmall, darkGray, "MENU");
		btnNeeded = new Button(gc.getWidth() - 164, gc.getHeight() - 500, 144, 48, uFontSmall, null, "NEEDED");
		btnDone = new Button(gc.getWidth() - 164, gc.getHeight() - 320, 144, 48, uFontSmall, null, "DONE");
		icons = new ArrayList<IconButton>();
		icons.add(new IconButton(300, 0, Color.lightGray, new Color(25,145,47), new Image("res/icons/play.png"), "Play [p]"));
		icons.add(new IconButton(348, 0, Color.lightGray, new Color(247,226,2), new Image("res/icons/pause.png"), "Pause factory [p]"));
		icons.add(new IconButton(396, 0, Color.lightGray, new Color(23,78,217), new Image("res/icons/build.png"), "Build a machine [b]"));
		icons.add(new IconButton(444, 0, Color.lightGray, new Color(25,145,47), new Image("res/icons/pipe_add.png"), "Add a pipe [x]"));
		icons.add(new IconButton(540, 0, Color.lightGray, new Color(255,0,0), new Image("res/icons/trash.png"), "Destroy! [d]"));
		icons.add(new IconButton(492, 0, Color.lightGray, new Color(25,145,47), new Image("res/icons/router_add.png"), "Add a router [r]"));
		
		currencybar.init(gc, sbg);
		buildMenu.init(gc, sbg);
		igMenu.init(gc, sbg);
		
		factory.setX(gc.getWidth()/2-288);
		factory.setY(gc.getHeight() - 24 * 24 - 92);
		
		activateIconsTiedToSelection(false);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		g.setColor(skyColor);
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		if (sky == null)
			sky = new BlockImage(BlockImage.getImage("Sky.png"));
		for (int index = 0; index < gc.getWidth(); index += sky.w)
			sky.render(-index, 0);

		if (groundImg == null)
			groundImg = new BlockImage(BlockImage.getImage("Ground.png"));
		groundImg.y = gc.getHeight() - 86;
		for (int index = 0; index < gc.getWidth(); index += 86)
		{
			groundImg.x = index;
			groundImg.render(0, 0);
		}

		// Panel
		if (panel == null)
			panel = new BlockImage(BlockImage.getImage("Pancarte.png"));
		panel.render(-gc.getWidth() + 180, -gc.getHeight() + 510);
		
		if (grass == null)
			grass = new BlockImage(BlockImage.getImage("Grass.png"));
		grass.y = gc.getHeight() - 92;
		for (int index = -4; index < gc.getWidth(); index += 24)
			if (index < factory.getX() - 24 || index > factory.getX() + factory.getTileXAmount() * TileBased.TILE_SIZE)
			{
				grass.x = index;
				grass.render(0, 0);
			}
		
		factory.render(g, scrollX, scrollY);
		// Selection
		if (currentSelection.getX() >= 0 && currentSelection.getY() >= 0){
			int tmpBWFade = (int) Math.abs( (gc.getTime() % 512) - 255 );
			g.setColor(new Color(tmpBWFade, tmpBWFade, tmpBWFade, 128));
			Rectangle filler = rectangleTileToPixel(currentSelection);
			filler.setHeight(filler.getHeight() + 1);
			filler.setWidth(filler.getWidth() + 1);
			g.fill(filler);
			
			InfoWindow.renderWindow(gc, g, uFontRealSmall, "Press DELETE to delete selected machine/item.", "Press ESCAPE to cancel selection.");
		}
		
		// Top bar BG
		g.setColor(lightGray);
		g.fillRect(0, 0, gc.getWidth(), 48);
		g.setColor(darkGray);
		g.fillRect(0, 48, gc.getWidth(), 6);

		for (IconButton tIB : icons)
			tIB.draw(gc, g);
		for (IconButton tIB : icons)
			tIB.drawTooltip(gc, g);
			
		btnMenu.draw(gc, g);
		btnNeeded.draw(gc, g);
		btnDone.draw(gc, g);	
		
		currencybar.render(gc, sbg, g);
		
		if (machineBeingPlaced != null){
			if (machineBeingPlaced instanceof Pipe && !(machineBeingPlaced instanceof Router)){
				InfoWindow.renderWindow(gc, g, uFontRealSmall, "Press C to rotate, Press V to switch form, Press escape to cancel", "Keep X down to chain place pipes.");
			}
			else
				InfoWindow.renderWindow(gc, g, uFontRealSmall, "Press escape to cancel placement");
			
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
		else
		{
			// Machine tooltip
			int x = gc.getInput().getMouseX();
			int y = gc.getInput().getMouseY();
			if (x > factory.getX() && x < factory.getX() + factory.getTileXAmount() * TileBased.TILE_SIZE &&
				y > factory.getY() && y < factory.getY() + factory.getTileYAmount() * TileBased.TILE_SIZE)
			{
				Machine tmpMach = factory.getMachine(clampCursorToTileMapXCeil(x-24, 1), 
												     clampCursorToTileMapYCeil(y-24, 1));
				if (tmpMach != null && tmpMach instanceof Processor)
				{
					String take = "Takes ";
					for (BlockShape shape : ((Processor)tmpMach).getShapeIns())
						take += shape + ", ";
					String give = "Gives " + ((Processor)tmpMach).getShapeOut();
					g.setColor(InfoWindow.black60);
					g.fillRect(x, y + 16, Math.max(uFontRealSmall.getWidth(take), uFontRealSmall.getWidth(give)) + 2, 32);
					g.setColor(InfoWindow.black75);
					g.drawRect(x, y + 16, Math.max(uFontRealSmall.getWidth(take), uFontRealSmall.getWidth(give)) + 2, 32);
					uFontRealSmall.drawString(x, y + 16, take.substring(0, take.length() - 2));
					uFontRealSmall.drawString(x, y + 32, give);
				}
			}
		}
		
		gd.render(gc, sbg, g);
		
		// Placing orders
		if(gd.getCurrentLevel() != null)
		{
			uFontSmall.drawString(28, 92, "Place order");
			g.setColor(InfoWindow.black60);
			g.fillRect(12, 112, 160, 20 + gd.getCurrentLevel().getPossibleOrders().size() * 32);
			g.setColor(InfoWindow.black75);
			g.drawRect(12, 112, 160, 20 + gd.getCurrentLevel().getPossibleOrders().size() * 32);
			for (int index = 0; index < gd.getCurrentLevel().getPossibleOrders().size(); index++)
			{
				Order order = gd.getCurrentLevel().getPossibleOrders().get(index);
				try
				{
					order.getBlock().render(-28, -124 - index * 32);
					uFontSmall.drawString(50, 128 + index * 32, "x " + order.getQty() + " : " + order.value + "$");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			int orderX = (gc.getInput().getMouseX() - 22);
			int orderY = (int)Math.floor((gc.getInput().getMouseY() - 122) / 32f);
			if (gd.getCurrentLevel().getTruckContent() == null && orderX > 0 && orderX < 140 && orderY >= 0 && orderY < gd.getCurrentLevel().getPossibleOrders().size())
			{
				g.setColor(Color.gray);
				g.drawRect(22, orderY * 32 + 122, 140, 32);
			}
		}
		
		buildMenu.render(gc, sbg, g);
		igMenu.render(gc, sbg, g);
	}
	
	private void updateCheckForMouseInput(GameContainer gc, StateBasedGame sbg, int d) throws SlickException
	{
		float mx = gc.getInput().getMouseX();
		float my = gc.getInput().getMouseY();

		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))
		{
			// Menu button
			if (btnMenu.contains(mx, my))
            {
				igMenu.setActivated(true);
				return;
			}
			// Placing order
            if (gd.getCurrentLevel() != null && gd.getCurrentLevel().getTruckContent() == null)
            {
                int order = (int)Math.floor((gc.getInput().getMouseY() - 120) / 32f);
    			int orderX = (int)(mx - 22);
                if (order >= 0 && order < gd.getCurrentLevel().getPossibleOrders().size() && orderX > 0 && orderX < 140){
                    if (gd.getCurrentLevel() != null && gd.getCurrentLevel().getTruckContent() == null){
                            gd.getCurrentLevel().setTruckContent(gd.getCurrentLevel().getPossibleOrders().get(order));
                            gd.getCurrentLevel().getPossibleOrders().remove(order);
                            getCurrencyBar().addCurrency(-gd.getCurrentLevel().getTruckContent().getValue());
                    }
                }
			}
			// Play pause Btns
			if (!buildMenu.getActivated() && icons.get(0).getActivated() && icons.get(0).contains(mx, my)){
				// Play game director
				gd.start();
			}else if (!buildMenu.getActivated() && icons.get(1).getActivated() && icons.get(1).contains(mx, my)){
				// Pause game director
				gd.pause();
			}
			// Placing a machine ?
			if (machineBeingPlaced == null)
			{
				if (!buildMenu.getActivated() && icons.get(2).contains(mx, my)){
					// Build menu
					buildMenu.setActivated(true);
					activateIcons(false);
				}else if (!buildMenu.getActivated() && icons.get(3).getActivated() && icons.get(3).contains(mx, my)){
					// Add pipe
					enterPlacePipe();
				}else if (!buildMenu.getActivated() && icons.get(4).getActivated() && icons.get(4).contains(mx, my)){
					// Destroy!
					destroySelection();
				}else if (!buildMenu.getActivated() && icons.get(5).getActivated() && icons.get(5).contains(mx, my)){
					// Add router!
					enterPlaceRouter();
				}else if (new Rectangle(factory.getX(), factory.getY(), factory.getTileXAmount() * TileBased.TILE_SIZE, factory.getTileYAmount() * TileBased.TILE_SIZE).contains(mx, my)){
					enterSelectMode((int)mx, (int)my);
				}
			}
			else
			{
				// Get tile X and Y
				int tileX = clampCursorToTileMapX((int)(mx-machineBeingPlaced.getW()/2), machineBeingPlaced.getTileWidth()),
					tileY = clampCursorToTileMapY((int)(my-machineBeingPlaced.getH()/2), machineBeingPlaced.getTileHeight());
				// If hover factory
				if (new Rectangle(factory.getX(), factory.getY(), factory.getTileXAmount() * TileBased.TILE_SIZE, factory.getTileYAmount() * TileBased.TILE_SIZE).contains(mx, my) && 
					factory.spaceAvailable(tileX, tileY, machineBeingPlaced.getTileWidth(), machineBeingPlaced.getTileHeight())){
					// Place machine
					currencybar.removeCurrency(machineBeingPlaced.getCost());
					lastMachineBeingPlaced = machineBeingPlaced;
					// Processor
					if (machineBeingPlaced instanceof Processor)
					{
						Processor machine = (Processor)machineBeingPlaced;
						buildMenu.removeAvailableMachine(machine);
						factory.addProcessor(tileX, tileY, 
											 machineBeingPlaced.getTileWidth(), machineBeingPlaced.getTileHeight(), 
											 machine.getShapeIns(), machine.getShapeOut(), 
											 machineBeingPlaced.getColor());
					}
					// Router
					else if (machineBeingPlaced instanceof Router)
					{
						factory.addRouter(tileX,  tileY, machineBeingPlaced.getAngle());
					}
					// Pipe
					else if (machineBeingPlaced instanceof Pipe)
					{
						Pipe machine = (Pipe)machineBeingPlaced;
						factory.addPipe(tileX, tileY, machine.getAngle(), machine.getAngleOut());
					}
					// Deactivate building mode
					if ((machineBeingPlaced instanceof Router ||
						 machineBeingPlaced instanceof Processor) ||
						 !gc.getInput().isKeyDown(Input.KEY_X))
					{
						machineBeingPlaced = null;
						activateIcons(true);
						activateIconsTiedToSelection(false);
					}
				}
			}
		}
	}
	private void updateCheckForKeyInput(GameContainer gc, StateBasedGame sbg, int d) throws SlickException
	{
		// Keys pressed
		// X //
		if (gc.getInput().isKeyPressed(Input.KEY_X)) {
			if (machineBeingPlaced == null && !buildMenu.getActivated()){
				enterPlacePipe();
			}
		}
		// R //
		if (gc.getInput().isKeyPressed(Input.KEY_R)) {
			if (machineBeingPlaced == null && !buildMenu.getActivated()){
				enterPlaceRouter();
			}
		}
		// C //
		if (gc.getInput().isKeyPressed(Input.KEY_C)) {
			if (machineBeingPlaced instanceof Pipe){
				Pipe tmpPipe = ((Pipe)machineBeingPlaced);
				tmpPipe.setAngle(tmpPipe.getAngle() + 90);
				tmpPipe.setAngleOut(tmpPipe.getAngleOut() + 90);
				tmpPipe.calculateSprite();
			}
		}
		// V //
		if (gc.getInput().isKeyPressed(Input.KEY_V)){
			if (machineBeingPlaced instanceof Pipe){
				Pipe tmpPipe = ((Pipe)machineBeingPlaced);
				tmpPipe.setAngleOut(tmpPipe.getAngleOut() + 90);
				if (tmpPipe.getAngle() == tmpPipe.getAngleOut()){
					tmpPipe.setAngleOut(tmpPipe.getAngleOut()+90);
				}
				tmpPipe.calculateSprite();
			}
		}
		// DELETE && D //
		if ((gc.getInput().isKeyPressed(Input.KEY_DELETE) || gc.getInput().isKeyPressed(Input.KEY_D)) && icons.get(4).getActivated()){
			// Destroy selection
			destroySelection();	
		}
		// B //
		if (gc.getInput().isKeyPressed(Input.KEY_B) && icons.get(2).getActivated()){
			// Open build menu
			buildMenu.setActivated(true);
			activateIcons(false);
		}
		// ESCAPE //
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE) && !buildMenu.getActivated())
		{
			if (currentSelection.getX() >= 0 && currentSelection.getY() >= 0)
			{
				currentSelection = new Rectangle(-1, -1, 0, 0);
				activateIconsTiedToSelection(false);
			}
			else if (machineBeingPlaced != null)
			{
				machineBeingPlaced = null;
				activateIcons(true);
				activateIconsTiedToSelection(false);
			}
			else
			{
				System.out.println("jean");
				igMenu.setActivated(true);
			}
		}
		// P //
		if (gc.getInput().isKeyPressed(Input.KEY_P)){
			if (gd.getPaused())
				gd.start();
			else
				gd.pause();
		}
	
	}
	public void update(GameContainer gc, StateBasedGame sbg, int d) throws SlickException {
		if (!igMenu.getActivated())
		{
			updateCheckForMouseInput(gc, sbg, d);
			updateCheckForKeyInput(gc, sbg, d);
			
			buildMenu.update(gc, sbg, d);
			currencybar.update(gc, sbg, d);
			if (!gd.getPaused())
				factory.update(d);
			gd.update(d);
		}
		else
			igMenu.update(gc, sbg, d);
	}
	
	// Getters and setters
	public IconButton getIcon(int pIndex){
		return icons.get(pIndex);
	}
	public CurrencyBar getCurrencyBar(){
		return currencybar;
	}
	
	// Icon activation
	public void activateIcons(boolean pActive){
		for (IconButton tIB : icons)
			tIB.setActivated(pActive);
		gd.checkIcons();
	}
	public void activateIconsTiedToSelection(boolean pActive){
		icons.get(4).setActivated(pActive);
	}
	public void destroySelection(){
		if (currentSelection.getX() >= 0 && currentSelection.getY() >= 0){
			Machine tMach = factory.getMachine((int)currentSelection.getX(), (int)currentSelection.getY());
			currencybar.addCurrency( factory.destroy( tMach ) );
			currentSelection = new Rectangle(-1, -1, 0, 0);
			icons.get(4).setActivated(false);
			if (tMach instanceof Processor)
				buildMenu.addAvailbleMachine((Processor)tMach);
		}
	}
	public Rectangle rectangleTileToPixel(Rectangle pBase){
		return new Rectangle(pBase.getX()*TileBased.TILE_SIZE + factory.getX(), 
							 pBase.getY()*TileBased.TILE_SIZE + factory.getY(), 
							 pBase.getWidth()*TileBased.TILE_SIZE, 
							 pBase.getHeight()*TileBased.TILE_SIZE);
	}
	
	// Enter specific mode
	public void enterPlaceMachine(Machine pMachine){
		machineBeingPlaced = pMachine;
		machineBeingPlaced.setX(0);
		machineBeingPlaced.setY(0);
	}
	public void	enterPlacePipe() throws SlickException{
		Pipe tPipe = new PipeImpl();
		if (lastMachineBeingPlaced instanceof Pipe && !(lastMachineBeingPlaced instanceof Router)){
			tPipe.setAngle(lastMachineBeingPlaced.getAngle());
			tPipe.setAngleOut(((Pipe) lastMachineBeingPlaced).getAngleOut());
		}else{
			tPipe.setAngle(0);
			tPipe.setAngleOut(180);
		}
		tPipe.setCost(10);
		tPipe.calculateSprite();
		tPipe.setTileHeight(1);
		tPipe.setTileWidth(1);
		enterPlaceMachine(tPipe);
		activateIcons(false);
		currentSelection = new Rectangle(-1, -1, 0, 0);
	}
	public void	enterPlaceRouter() throws SlickException{
		Router tRouter = new RouterImpl();
		tRouter.setCost(25);
		tRouter.calculateSprite();
		tRouter.setTileHeight(1);
		tRouter.setTileWidth(1);
		enterPlaceMachine(tRouter);
		activateIcons(false);
		currentSelection = new Rectangle(-1, -1, 0, 0);
	}
	public void enterSelectMode(int pMx, int pMy){
		currentSelection.setX(clampCursorToTileMapXCeil(pMx-24, 1));
		currentSelection.setY(clampCursorToTileMapYCeil(pMy-24, 1));
		Machine tmpMach = factory.getMachine((int)currentSelection.getX(), (int)currentSelection.getY());
		if (tmpMach != null) {
			if (tmpMach instanceof Router)
				((Router)tmpMach).changeDirection(factory);
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
	
	// Clamping to tile set
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
	
	
	// Saving and loading
	public void save(int pLevel)
	{
		SavingHelper.saveToFile(factory, getCurrencyBar().getCurrency(), buildMenu.getAvailableMachines(), pLevel);
	}
	public void load(int pLevel)
	{
		SavingHelper save = SavingHelper.readWorldFromFile(pLevel);
		
		currentSelection = new Rectangle(-1, -1, 0, 0);
		factory = save.factory;
		gd.resetTruckPosition();
		gd.generateLevels();
		gd.setLevel(save.level);
		buildMenu.setAvailbleMachines(save.buildMenu);
		getCurrencyBar().setCurrency(save.money);
		try { initPositions(gc, sbg); } 
		catch (SlickException e) 
		{ e.printStackTrace(); }
	}
}
