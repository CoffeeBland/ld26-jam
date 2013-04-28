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
	
	private Color black = Color.black;
	private Color white = Color.white;
	private UnicodeFont uFont = FontFactory.get().getFont(60, java.awt.Color.WHITE);
	
	private long newLevelMessageFadeStart;
	private long newLevelMessageFadeDuration = 4000;
	
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
		newLevelMessageFadeStart = System.currentTimeMillis();
	}
	
	public void start(){
		paused = false;
		checkIcons();
	}
	public void pause(){
		paused = true;
		checkIcons();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g){
		if (newLevelMessageFadeStart != 0){
			float tPercentFadeCompleted = ((float)System.currentTimeMillis() - (float)newLevelMessageFadeStart) / (float)newLevelMessageFadeDuration;
			tPercentFadeCompleted = ((tPercentFadeCompleted*2f) - 1f);
			if (tPercentFadeCompleted < 0){
				tPercentFadeCompleted *= -1;
			}
			System.out.println(tPercentFadeCompleted);
			black.a = tPercentFadeCompleted;
			white.a = tPercentFadeCompleted;
			g.setColor(black);
			g.fillRoundRect(40, 40, gc.getWidth()-80, gc.getHeight()-80, 20);
			uFont.drawString(gc.getWidth()/2 - uFont.getWidth("Level " + level)/2, gc.getHeight()/2 - 100, "Level " + level, white);
			if (System.currentTimeMillis() - newLevelMessageFadeStart > newLevelMessageFadeDuration){
				newLevelMessageFadeStart = 0;
			}
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
