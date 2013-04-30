package ld26_kiasaki_dagothig.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import ld26_kiasaki_dagothig.World;
import ld26_kiasaki_dagothig.helpers.FontFactory;
import ld26_kiasaki_dagothig.helpers.Renderable;
import ld26_kiasaki_dagothig.states.GameOverState;

public class CurrencyBar implements Renderable {

	private UnicodeFont uFont;
	private UnicodeFont uFontBig;
	private Color golden = new Color(247,226,2);
	private int currency = 0;
	private World world;
	
	public int getCurrency() {
		return currency;
	}
	public void setCurrency(int currency) {
		this.currency = currency;
	}
	public void addCurrency(int currency) {
		this.currency += currency;
	}
	public void removeCurrency(int currency) {
		this.currency -= currency;
	}

	public CurrencyBar(World pWorld){
		this.world = pWorld;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		uFont = FontFactory.get().getFont(16, java.awt.Color.WHITE);
		uFontBig = FontFactory.get().getFont(32, java.awt.Color.WHITE);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		uFont.drawString(16, 16, "Your currency:");
		uFontBig.drawString(280-uFontBig.getWidth(Integer.toString(currency)+"$"), 8, Integer.toString(currency)+"$");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if (this.currency < 0){
			sbg.enterState(GameOverState.ID, new FadeOutTransition(Color.white, 700), new FadeInTransition(Color.white));
		}
	}

}
