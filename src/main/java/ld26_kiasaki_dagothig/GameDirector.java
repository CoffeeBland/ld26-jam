package ld26_kiasaki_dagothig;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import ld26_kiasaki_dagothig.entity.Block;
import ld26_kiasaki_dagothig.entity.BlockColor;
import ld26_kiasaki_dagothig.entity.BlockShape;
import ld26_kiasaki_dagothig.entity.Order;
import ld26_kiasaki_dagothig.entity.Processor;
import ld26_kiasaki_dagothig.entity.ProcessorImpl;
import ld26_kiasaki_dagothig.helpers.BlockImage;
import ld26_kiasaki_dagothig.helpers.FontFactory;
import ld26_kiasaki_dagothig.states.GameWinState;

public class GameDirector {

	private int level;
	private World world;
	private boolean paused;
	private int feedTimer = 1000;
	private boolean tutorialOpened = true;
	public boolean getPaused()
	{
		return paused;
	}
	
	private Color black = new Color(0, 0 ,0);
	private Color white = new Color(255, 255, 255);
	private Color translucentBlack = new Color(0, 0, 0, 128);
	private UnicodeFont uFont = FontFactory.get().getFont(60, java.awt.Color.WHITE);
	private UnicodeFont uSmallFont = FontFactory.get().getFont(18, java.awt.Color.WHITE);
	
	private long newLevelMessageFadeStart = 2500;
	private long newLevelMessageFadeDuration = 2500;
	private BlockImage truck;
	private float truckPosition = -160;
	public void resetTruckPosition()
	{
		truckPosition = -160;
	}
	
	private List<GameLevel> levels = new ArrayList<GameLevel>();
	public List<Order> blocksBuilded = new ArrayList<Order>();
	
	public GameDirector(){
		this(1);
	}
	public GameDirector(int pLevel){
		level = pLevel;
		generateLevels();
	}
	
	public void setWorld(World pW){
		world = pW;
		world.getCurrencyBar().addCurrency(500);
		setLevel(level);
	}
	public void setLevel(int pLevel){
		pause();
		level = pLevel;
		newLevelMessageFadeStart = 2500;
		for (Processor mach : getCurrentLevel().getProcessorShop())
			world.buildMenu.addAvailbleMachine(mach);
		blocksBuilded = new ArrayList<Order>();
	}
	
	public void goToNextLevel(){
		setLevel(getCurrentLevel().getLevel() + 1);
		world.save(level);
	}
	public GameLevel getCurrentLevel(){
		return levels.get(level-1);
	}
	
	public void start(){
		paused = false;
		checkIcons();
	}
	public void pause(){
		paused = true;
		checkIcons();
	}
	
	public void update(int delta) throws SlickException
	{
		if (newLevelMessageFadeStart > 0)
			newLevelMessageFadeStart -= delta;
		if (!paused){
			if (getCurrentLevel() != null && getCurrentLevel().getTruckContent() != null)
				if (truckPosition == world.factory.getX() - 172 && getCurrentLevel().getTruckContent().getQty() > 0)
				{
					if (feedTimer > 0){
						feedTimer -= delta;
					}else if (getCurrentLevel().getTruckContent().getQty() > 0){
						feedTimer = 1000;
						world.factory.receiveBlock(getCurrentLevel().getTruckContent().getBlock());
						getCurrentLevel().getTruckContent().setQty(getCurrentLevel().getTruckContent().getQty() - 1);
					}
				}
				else
				{
					if (getCurrentLevel().getTruckContent().getQty() > 0)
					{
						truckPosition += delta * 0.1f;
						if (truckPosition > world.factory.getX() - 172)
							truckPosition = world.factory.getX() - 172;
					}
					else
					{
						truckPosition -= delta * 0.2f;
						if (truckPosition < -160)
						{
							truckPosition = -160;
							getCurrentLevel().setTruckContent(null);
						}
					}
				}
		}
		if (world.factory.getTransformedBlocks().size() > 0){
			for (Block tB : world.factory.getTransformedBlocks()){
				Order tmpBlo = getCurrentLevel().getNeededByBlock(tB);
				if (tmpBlo != null){
					boolean tFound = false;
					for (Order tOrder : blocksBuilded) {
						if (tOrder.getShape() == tmpBlo.getShape() && tOrder.getColor() == tmpBlo.getColor()){
							tOrder.qty++;
							tFound = true;
						}
					}
					if (tFound == false){
						blocksBuilded.add(new Order(tmpBlo.getShape(), tmpBlo.getColor(), 1, 0));
					}
					tmpBlo.qty--;
					world.getCurrencyBar().addCurrency(tmpBlo.value);
				}
			}
			world.factory.getTransformedBlocks().clear();
			boolean finished = true;
			for (Order tmpBlo : getCurrentLevel().getNeeded())
				if (tmpBlo.getQty() > 0)
					finished = false;
			if (finished)
				if (levels.size() > level)
					goToNextLevel();
				else
					world.sbg.enterState(GameWinState.ID);
		}
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g){
		if (newLevelMessageFadeStart > 0){
			float tPercentFadeCompleted = (float)newLevelMessageFadeStart / (float)newLevelMessageFadeDuration;
			black.a = tPercentFadeCompleted;
			white.a = tPercentFadeCompleted;
			g.setColor(black);
			g.fillRoundRect(300, 300, gc.getWidth()-600, gc.getHeight()-600, 20);
			uFont.drawString(gc.getWidth()/2 - uFont.getWidth("Level " + level)/2, gc.getHeight()/2 - 32, "Level " + level, white);
			uSmallFont.drawString(gc.getWidth()/2 - uSmallFont.getWidth(getCurrentLevel().getName()) / 2, gc.getHeight()/2 + 32, getCurrentLevel().getName(), white);
		}
		
		// Render the truck
		Order tOrder = getCurrentLevel().getTruckContent();
		if (tOrder != null)
			try {
				if (truck == null)
					truck = new BlockImage(BlockImage.getImage("Truck.png"));
				truck.x = Math.round(truckPosition);
				truck.y = gc.getHeight() - 204;
				truck.render(0, 0);
				int xpos = Math.max(6, 28 + Math.round(truckPosition + 48));
				g.setColor(translucentBlack);
				g.fillRect(xpos - 6, truck.y + 42, 78, 28);
				tOrder.getBlock().render(-xpos, -(truck.y + 45));
				uSmallFont.drawString(22 + xpos, truck.y + 48, "x " + tOrder.getQty());
			} catch (SlickException e) {
				e.printStackTrace();
			}
		// Render the needed stuff
		int i = 0;
		for (Order tB : getCurrentLevel().getNeeded()){
			try {
				tB.getBlock().render(-gc.getWidth() + 166, -((gc.getHeight()-459)+i));
				uSmallFont.drawString(gc.getWidth() - 142, (gc.getHeight()-456)+i, "x " + tB.getQty() + " (" + tB.getValue() + "$)");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			i+=22;
		}
		
		// Render the done stuff
		i = 0;
		for (Order tB : blocksBuilded){
			try {
				tB.getBlock().render(-gc.getWidth() + 134, -((gc.getHeight()-287)+i));
				uSmallFont.drawString(gc.getWidth() - 114, (gc.getHeight()-284)+i, "x " + tB.getQty());
			} catch (SlickException e) {
				e.printStackTrace();
			}
			i+=22;
		}
		
		
	}
	public void checkIcons(){
		world.getIcon(0).setActivated(paused);
		world.getIcon(1).setActivated(!paused);
	}
	
	public void generateLevels(){
		levels.clear();
		
		/////////////////////////////////
		// Level 1
		List<Order> tNeeded = new ArrayList<Order>();
		List<Order> tPossibleOrders = new ArrayList<Order>();
		List<Processor> tProcessorShop = new ArrayList<Processor>();
		
		Order tTruckContent = new Order(BlockShape.Circle, BlockColor.Orange, 15, 20);
		tNeeded.add(new Order(BlockShape.Circle, BlockColor.Red, 15, 20));
		
		tProcessorShop.add(generateProcessor(new BlockShape[]{BlockShape.Circle}, BlockShape.Circle, BlockColor.Red, 4, 4));
		
		levels.add(new GameLevel(1, "Starting out!", tNeeded, tPossibleOrders, tTruckContent, tProcessorShop));
		
		
		/////////////////////////////////
		// Level 2
		tNeeded = new ArrayList<Order>();
		tPossibleOrders = new ArrayList<Order>();
		tProcessorShop = new ArrayList<Processor>();
		
		tTruckContent = new Order(BlockShape.Circle, BlockColor.Orange, 20, 10);
		tNeeded.add(new Order(BlockShape.Circle, BlockColor.Purple, 20, 10));
		
		tProcessorShop.add(generateProcessor(new BlockShape[]{BlockShape.Circle}, BlockShape.Circle, BlockColor.Blue, 2, 4));
		
		levels.add(new GameLevel(2, "Colors, think of it!", tNeeded, tPossibleOrders, tTruckContent, tProcessorShop));
		
		
		/////////////////////////////////
		// Level 3
		tNeeded = new ArrayList<Order>();
		tPossibleOrders = new ArrayList<Order>();
		tProcessorShop = new ArrayList<Processor>();
		
		tTruckContent = new Order(BlockShape.Triangle, BlockColor.Red, 15, 25);
		tNeeded.add(new Order(BlockShape.Circle, BlockColor.Blue, 15, 25));
		
		tProcessorShop.add(generateProcessor(new BlockShape[]{BlockShape.Triangle}, BlockShape.Circle, BlockColor.Blue, 3, 3));
		
		levels.add(new GameLevel(3, "Shapes can change!", tNeeded, tPossibleOrders, tTruckContent, tProcessorShop));
		
		
		/////////////////////////////////
		// Level 4
		tNeeded = new ArrayList<Order>();
		tPossibleOrders = new ArrayList<Order>();
		tProcessorShop = new ArrayList<Processor>();
		
		tTruckContent = new Order(BlockShape.Circle, BlockColor.Purple, 15, 30);
		tNeeded.add(new Order(BlockShape.Star, BlockColor.Yellow, 15, 30));
		
		tProcessorShop.add(generateProcessor(new BlockShape[]{BlockShape.Circle, BlockShape.Star}, BlockShape.Triangle, BlockColor.Yellow, 3, 4));
		tProcessorShop.add(generateProcessor(new BlockShape[]{BlockShape.Circle, BlockShape.Triangle, BlockShape.Star}, BlockShape.Star, BlockColor.Yellow, 4, 2));
		
		levels.add(new GameLevel(4, "This game is a puzzle", tNeeded, tPossibleOrders, tTruckContent, tProcessorShop));

		
		/////////////////////////////////
		// Level 5
		tNeeded = new ArrayList<Order>();
		tPossibleOrders = new ArrayList<Order>();
		tProcessorShop = new ArrayList<Processor>();
		
		tTruckContent = null;
		tNeeded.add(new Order(BlockShape.Triangle, BlockColor.Yellow, 1, 150));
		
		tPossibleOrders.add(new Order(BlockShape.Triangle, BlockColor.Blue, 1, 150));
		
		levels.add(new GameLevel(5, "Order blocks!", tNeeded, tPossibleOrders, tTruckContent, tProcessorShop));
		
		
		/////////////////////////////////
		// Level 6
		tNeeded = new ArrayList<Order>();
		tPossibleOrders = new ArrayList<Order>();
		tProcessorShop = new ArrayList<Processor>();
		
		tTruckContent = new Order(BlockShape.Square, BlockColor.Yellow, 15, 35);
		tNeeded.add(new Order(BlockShape.Circle, BlockColor.Green, 15, 35));
		
		tPossibleOrders.add(new Order(BlockShape.Circle, BlockColor.Blue, 20, 20));
		tNeeded.add(new Order(BlockShape.Circle, BlockColor.Red, 20, 20));
		
		tProcessorShop.add(generateProcessor(new BlockShape[]{BlockShape.Square}, BlockShape.Triangle, BlockColor.Yellow, 3, 2));
		
		levels.add(new GameLevel(6, "2 inputs, 2 outputs!", tNeeded, tPossibleOrders, tTruckContent, tProcessorShop));
		
		
		/////////////////////////////////
		// Level 7
		tNeeded = new ArrayList<Order>();
		tPossibleOrders = new ArrayList<Order>();
		tProcessorShop = new ArrayList<Processor>();
		
		tTruckContent = new Order(BlockShape.Star, BlockColor.Green, 6, 45);
		tNeeded.add(new Order(BlockShape.Triangle, BlockColor.Green, 6, 45));
		
		tPossibleOrders.add(new Order(BlockShape.Triangle, BlockColor.Orange, 6, 40));
		tNeeded.add(new Order(BlockShape.Circle, BlockColor.Brown, 6, 40));
		
		tProcessorShop.add(generateProcessor(new BlockShape[]{BlockShape.Star, BlockShape.Square}, BlockShape.Triangle, BlockColor.Blue, 3, 3));
		
		levels.add(new GameLevel(7, "2 -> 2 get used to it", tNeeded, tPossibleOrders, tTruckContent, tProcessorShop));
		
		
		/////////////////////////////////
		// Level 8
		tNeeded = new ArrayList<Order>();
		tPossibleOrders = new ArrayList<Order>();
		tProcessorShop = new ArrayList<Processor>();
		
		tTruckContent = new Order(BlockShape.Square, BlockColor.Brown, 2, 200);
		tNeeded.add(new Order(BlockShape.Circle, BlockColor.Green, 2, 200));
		
		tPossibleOrders.add(new Order(BlockShape.Star, BlockColor.Orange, 4, 50));
		tNeeded.add(new Order(BlockShape.Triangle, BlockColor.Brown, 4, 50));
		
		levels.add(new GameLevel(8, "Moving things around", tNeeded, tPossibleOrders, tTruckContent, tProcessorShop));
		
		
		/////////////////////////////////
		// Level 9
		tNeeded = new ArrayList<Order>();
		tPossibleOrders = new ArrayList<Order>();
		tProcessorShop = new ArrayList<Processor>();
		
		tTruckContent = new Order(BlockShape.Star, BlockColor.Purple, 1, 250);
		tNeeded.add(new Order(BlockShape.Triangle, BlockColor.Yellow, 1, 250));
		
		tProcessorShop.add(generateProcessor(new BlockShape[]{BlockShape.Triangle, BlockShape.Square}, BlockShape.Square, BlockColor.Green, 2, 2));
		
		levels.add(new GameLevel(9, "Lets use all the colors", tNeeded, tPossibleOrders, tTruckContent, tProcessorShop));
		
		
		/////////////////////////////////
		// Level 10
		tNeeded = new ArrayList<Order>();
		tPossibleOrders = new ArrayList<Order>();
		tProcessorShop = new ArrayList<Processor>();
		
		tTruckContent = new Order(BlockShape.Square, BlockColor.Brown, 24, 45);
		tNeeded.add(new Order(BlockShape.Triangle, BlockColor.Yellow, 12, 45));
		tNeeded.add(new Order(BlockShape.Triangle, BlockColor.Blue, 12, 40));
		
		tProcessorShop.add(generateProcessor(new BlockShape[]{BlockShape.Star, BlockShape.Square}, BlockShape.Triangle, BlockColor.Blue, 3, 3));
		
		levels.add(new GameLevel(10, "Machines split output!", tNeeded, tPossibleOrders, tTruckContent, tProcessorShop));
		
		
		/*
		 * Debugging!
		for (GameLevel gl : levels)
		{
			System.out.println(gl.getLevel() + ":");
			for (Processor glp : gl.getProcessorShop())
			{
				System.out.println("    Color: " + glp.getColor().name());
				System.out.println("    Shape: " + glp.getShapeOut().name());
				System.out.print("    Accepts: ");
				for (BlockShape glps : glp.getShapeIns())
				{
					System.out.print(glps.name() + ", ");
				}
				System.out.println();
			}
		}*/
		
	}
	private Processor generateProcessor(BlockShape[] pIns, BlockShape pOut, BlockColor pColor, int pW, int pH)
	{
		Processor tProcessor = new ProcessorImpl(0, pColor, pW, pH);
		for (BlockShape s : pIns)
			tProcessor.getShapeIns().add(s);
		tProcessor.setShapeOut(pOut);
		tProcessor.setCost(tProcessor.getCostFromSize(pW, pH));
		return tProcessor;
	}
	
}
