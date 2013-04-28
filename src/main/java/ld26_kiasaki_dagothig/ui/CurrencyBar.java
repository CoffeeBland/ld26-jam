package ld26_kiasaki_dagothig.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import ld26_kiasaki_dagothig.helpers.FontFactory;
import ld26_kiasaki_dagothig.helpers.Renderable;

public class CurrencyBar implements Renderable {

	private UnicodeFont uFont = FontFactory.get().getFont(16, java.awt.Color.WHITE);
	private UnicodeFont uFontBig = FontFactory.get().getFont(32, java.awt.Color.WHITE);
	private Color golden = new Color(247,226,2);
	private int currency = 0;
	
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

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
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
	}

}
