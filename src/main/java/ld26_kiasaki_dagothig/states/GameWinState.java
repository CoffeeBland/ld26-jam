package ld26_kiasaki_dagothig.states;

import java.util.Random;

import ld26_kiasaki_dagothig.entity.Block;
import ld26_kiasaki_dagothig.entity.BlockColor;
import ld26_kiasaki_dagothig.entity.BlockImpl;
import ld26_kiasaki_dagothig.entity.BlockShape;
import ld26_kiasaki_dagothig.helpers.FontFactory;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameWinState extends BasicGameState {

	public static final int ID = 5;
	private GameContainer gc;
	private StateBasedGame sbg;
	
	private long started;
	private UnicodeFont uFont = FontFactory.get().getFont(60, java.awt.Color.WHITE);
	private UnicodeFont uFontSmall = FontFactory.get().getFont(18, java.awt.Color.WHITE);
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.gc = gc;
		this.sbg = sbg;
		this.started = System.currentTimeMillis();
	}

	@Override
	public void keyReleased(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			exitGame();					
		}
	} 
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// Block
		Random r = new Random(gc.getTime()/100);
		BlockColor[] colors = new BlockColor[(int)Math.ceil(gc.getWidth()/16)];
		for (int x = 0; x < (int)Math.ceil(gc.getWidth()/16); x++)
			colors[x] = BlockColor.values()[r.nextInt(BlockColor.values().length)];
		for (int x = 0; x < (int)Math.ceil(gc.getWidth()/16); x++)
		{
			for (int y = 0; y < (gc.getHeight()/16) + 1; y++)
			{
				Block tmpB = new BlockImpl();
				tmpB.setColor(colors[(x + y) % colors.length]);
				tmpB.setShape(BlockShape.values()[r.nextInt(BlockShape.values().length)]);
				tmpB.render(-(x*16), -(y*16));
			}
		}
		
		g.setColor(new Color(0,0,0,0.75f));
		g.fillOval(gc.getWidth()/2-400, gc.getHeight()/2-250, 800, 500);
		
		// Game over
		uFont.drawString(gc.getWidth()/2-(uFont.getWidth("CONGRATULATION")/2), gc.getHeight()/2-125, "CONGRATULATION");
		uFont.drawString(gc.getWidth()/2-(uFont.getWidth("YOU WIN!")/2), gc.getHeight()/2, "YOU WIN!");
		// Info
		uFontSmall.drawString(gc.getWidth()/2-(uFontSmall.getWidth("Press escape to continue...")/2), gc.getHeight()/2+125, "Press escape to continue...");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
	}

	public void exitGame(){
		if (System.currentTimeMillis() - this.started > 2000){
			sbg.enterState(MenuState.ID, new FadeOutTransition(Color.white, 700), new FadeInTransition(Color.white));
		}
	}

	@Override
	public int getID() {
		return ID;
	}
	
}
