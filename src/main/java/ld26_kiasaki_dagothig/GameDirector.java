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
import ld26_kiasaki_dagothig.helpers.FontFactory;

public class GameDirector {

	private int level;
	private World world;
	private boolean paused;
	private int feedTimer = 1000;
	public boolean getPaused()
	{
		return paused;
	}
	
	private Color black = new Color(0, 0 ,0);
	private Color white = new Color(255, 255, 255);
	private UnicodeFont uFont = FontFactory.get().getFont(60, java.awt.Color.WHITE);
	private UnicodeFont uSmallFont = FontFactory.get().getFont(18, java.awt.Color.WHITE);
	
	private long newLevelMessageFadeStart = 2500;
	private long newLevelMessageFadeDuration = 2500;
	
	private List<GameLevel> levels = new ArrayList<GameLevel>();
	
	public GameDirector(){
		this(1);
	}
	public GameDirector(int pLevel){
		level = pLevel;
		generateLevels();
	}
	
	public void setWorld(World pW){
		world = pW;
		world.getCurrencyBar().addCurrency(100);
		setLevel(1);
	}
	public void setLevel(int pLevel){
		level = pLevel;
		newLevelMessageFadeStart = 2500;
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
			if (feedTimer > 0){
				feedTimer -= delta;
			}else if (getCurrentLevel().getTruckContent().getQty() > 0){
				feedTimer = 1000;
				world.factory.receiveBlock(getCurrentLevel().getTruckContent().getBlock());
				getCurrentLevel().getTruckContent().setQty(getCurrentLevel().getTruckContent().getQty() - 1);
			}
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
		}
		
		// Render the needed stuff
		Order tOrder = getCurrentLevel().getTruckContent();
		try {
			tOrder.getBlock().render(-30, -(433));
			uSmallFont.drawString(50, 436, "x " + tOrder.getQty());
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		// Render the needed stuff
		int i = 0;
		for (Order tB : getCurrentLevel().getNeeded()){
			try {
				tB.getBlock().render(-gc.getWidth() + 154, -(303+i));
				uSmallFont.drawString(gc.getWidth() - 134, 306+i, "x " + tB.getQty() + " (" + tB.getValue() + "$)");
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
	
	private void generateLevels(){
		// Level 1
		List<Order> tNeeded = new ArrayList<Order>();
		List<Order> tPossibleOrders = new ArrayList<Order>();
		Order tTruckContent = new Order(BlockShape.Circle, BlockColor.Orange, 5, 10);
		
		tNeeded.add(new Order(BlockShape.Square, BlockColor.Red, 5, 10));
		tNeeded.add(new Order(BlockShape.Circle, BlockColor.Green, 5, 10));
		
		levels.add(new GameLevel(1, "Starting out!", tNeeded, tPossibleOrders, tTruckContent));
		
	}
	
}
