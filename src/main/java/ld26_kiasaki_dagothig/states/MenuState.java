package ld26_kiasaki_dagothig.states;

import java.io.File;

import ld26_kiasaki_dagothig.GameStateController;
import ld26_kiasaki_dagothig.helpers.FontFactory;
import ld26_kiasaki_dagothig.helpers.KeyListenerImpl;
import ld26_kiasaki_dagothig.helpers.MouseListenerImpl;
import ld26_kiasaki_dagothig.helpers.SavingHelper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	UnicodeFont uFont = FontFactory.get().getFont(32, java.awt.Color.BLACK);
	UnicodeFont uFontSmall = FontFactory.get().getFont(18, java.awt.Color.WHITE);
	
	private Image gameLogo;
	private File[] saves;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.gc = gc;
		this.sbg = sbg;
		
		
		gameLogo = new Image("res/game_logo.png");
		
		final StateBasedGame tSbg = sbg;  
		
		Input tI = gc.getInput();
		tI.addMouseListener(new MouseListenerImpl(){
			public void mousePressed(int button, int x, int y) 
			{
				if(tSbg.getCurrentStateID() == ID)
				{
					if (new Rectangle(getGameContainer().getWidth()/2-125, getGameContainer().getHeight()/2-30, 250, 60).contains(x, y))
						getStateBasedGame().enterState(GameState.ID, new FadeOutTransition(Color.black, 500), new FadeInTransition(Color.black));
					int decal = getGameContainer().getWidth() / 2 - saves.length * 16 - 4;
					for (File file : saves)
					{
						if (new Rectangle(decal, getGameContainer().getHeight()/2 + 60, 28, 28).contains(x, y))
							((GameStateController)getStateBasedGame()).enterGameState(Integer.parseInt(file.getName().replace("level", "").replace(".ini", "")));
						decal += 32;
					}
				}
			}
		});
		
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
		gameLogo.draw(gc.getWidth()/2-330, 80);
		g.drawRect(gc.getWidth()/2-350, 80, 720, 480);
		// Rect Start game
		g.setColor(Color.white);
		g.fillRect(gc.getWidth()/2-125, gc.getHeight()/2-30, 250, 60);
		// Text Start Game
		uFont.drawString(gc.getWidth()/2-105, gc.getHeight()/2-15, "Start Game");
		// Text load game
		uFontSmall.drawString(gc.getWidth()/2-30, gc.getHeight()/2+40, "Load");
		int decal = gc.getWidth() / 2 - saves.length * 16 - 4;
		for (File file : saves)
		{
			g.fillRect(decal, gc.getHeight()/2 + 60, 28, 28);
			uFont.drawString(decal + 2, gc.getHeight()/2 + 60, file.getName().replace("level", "").replace(".ini", ""));
			decal += 32;
		}
		// available saves
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
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		File tFile = new File("saves");
		tFile.mkdir();
		saves = SavingHelper.getSaveFiles();
	}

	@Override
	public int getID() {
		return ID;
	}

}
