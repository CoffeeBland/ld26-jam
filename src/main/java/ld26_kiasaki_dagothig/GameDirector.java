package ld26_kiasaki_dagothig;

import java.util.ArrayList;
import java.util.List;

import ld26_kiasaki_dagothig.entity.BlockColor;
import ld26_kiasaki_dagothig.entity.BlockShape;

public class GameDirector {

	private int level;
	private World world;
	private boolean paused;
	
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
		world.getCurrencyBar().addCurrency(10);
		level = 1;
	}
	
	public void start(){
		paused = false;
		checkIcons();
	}
	public void pause(){
		paused = true;
		checkIcons();
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
