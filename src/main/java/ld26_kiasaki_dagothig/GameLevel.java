package ld26_kiasaki_dagothig;

import java.util.List;

import ld26_kiasaki_dagothig.entity.BlockColor;
import ld26_kiasaki_dagothig.entity.BlockShape;

public class GameLevel {

	private int level;
	private String name;
	private List<BlockShape> outShapes;
	private List<BlockColor> outColors;
	
	public GameLevel(int pLevel, String pName, List<BlockShape> pOutShapes, List<BlockColor> pOutColors){
		level = pLevel;
		name = pName;
		outShapes = pOutShapes;
		outColors = pOutColors;
	}
	
}
