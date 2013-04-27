package ld26_kiasaki_dagothig.states;

import ld26_kiasaki_dagothig.helpers.KeyListenerImpl;
import ld26_kiasaki_dagothig.helpers.MouseListenerImpl;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MenuState extends BasicGameState {

	public static final int ID = 2;
	private GameContainer gc;
	private StateBasedGame sbg;
	UnicodeFont uFont;
	UnicodeFont uFontSmall;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.gc = gc;
		this.sbg = sbg;
		uFont = new UnicodeFont("res/fonts/slkscr.ttf", 32, false, false);
		uFont.getEffects().add(new ColorEffect(java.awt.Color.black));
		uFont.addAsciiGlyphs();
		uFont.loadGlyphs();
		uFontSmall = new UnicodeFont("res/fonts/slkscr.ttf", 18, false, false);
		uFontSmall.getEffects().add(new ColorEffect());
		uFontSmall.addAsciiGlyphs();
		uFontSmall.loadGlyphs();
		
		Input tI = gc.getInput();
		tI.addKeyListener(new KeyListenerImpl(){
			@Override
			public void keyPressed(int key, char c) {
				if (key == Input.KEY_ESCAPE) {
					exitGame();					
				}
			} 
		});
		tI.addMouseListener(new MouseListenerImpl(){
			public void mouseReleased(int button, int x, int y) {
				System.out.println(x + ":" + y);
				if (new Rectangle(getGameContainer().getWidth()/2-125, getGameContainer().getHeight()/2-30, 250, 60).contains(x, y)){
					getStateBasedGame().enterState(GameState.ID);
				}
			}
		});
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		// Rect Start game
		g.setColor(Color.white);
		g.fillRect(gc.getWidth()/2-125, gc.getHeight()/2-30, 250, 60);
		// Text Start Game
		uFont.drawString(gc.getWidth()/2-105, gc.getHeight()/2-15, "Start Game");
		// Text credits
		uFontSmall.drawString(gc.getWidth()/2-300, gc.getHeight()-45, "Ludum Dare 26 - April 2013 - By Kiasaki and Dagothig");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int d)
			throws SlickException {

	}
	
	private void exitGame(){
		gc.exit();
	}
	
	private GameContainer getGameContainer(){
		return gc;
	}
	
	private StateBasedGame getStateBasedGame(){
		return sbg;
	}

	@Override
	public int getID() {
		return ID;
	}

}
