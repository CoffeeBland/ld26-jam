package ld26_kiasaki_dagothig;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import ld26_kiasaki_dagothig.entity.BlockColor;
import ld26_kiasaki_dagothig.entity.BlockShape;
import ld26_kiasaki_dagothig.helpers.FontFactory;

public class GameDirector {

	private int level;
	private World world;
	private boolean paused;
	
	private Color black = new Color(0, 0 ,0);
	private Color white = new Color(255, 255, 255);
	private UnicodeFont uFont = FontFactory.get().getFont(60, java.awt.Color.WHITE);
	
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
	
	public void start(){
		paused = false;
		checkIcons();
	}
	public void pause(){
		paused = true;
		checkIcons();
	}
	
	public void update(int delta)
	{
		if (newLevelMessageFadeStart > 0)
			newLevelMessageFadeStart -= delta;
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
	}
	public void checkIcons(){
		world.getIcon(0).setActivated(paused);
		world.getIcon(1).setActivated(!paused);
	}
	
	private void generateLevels(){
		// Level 1
		List<BlockShape> tOutShapes = new ArrayList<BlockShape>();
		List<BlockColor> tOutColors = new ArrayList<BlockColor>();
		
		levels.add(new GameLevel(1, "Starting out!", tOutShapes, tOutColors));
		
	}
	
}
