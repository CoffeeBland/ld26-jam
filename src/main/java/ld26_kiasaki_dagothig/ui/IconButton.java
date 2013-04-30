package ld26_kiasaki_dagothig.ui;

import ld26_kiasaki_dagothig.helpers.FontFactory;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;

public class IconButton extends Rectangle {
	
	public Color bghover = Color.white;
	public Color fg = Color.black;
	public Image sprite;
	public String tooltipText;
	
	private UnicodeFont uFont;	
	private final Color gray = new Color(150, 150, 150);
	private boolean activated = true;
	
	public IconButton(float x, float y, Color bghover, Color fg, Image pSprite, String pTooltipText) {
		super(x, y, 48, 48);
		uFont = FontFactory.get().getFont(16, java.awt.Color.WHITE);
		this.bghover = bghover;
		this.fg = fg;
		this.sprite = pSprite;
		this.tooltipText = pTooltipText;
	}
	
	public boolean getActivated(){
		return activated;
	}
	public void setActivated(boolean pActivated){
		this.activated = pActivated;
	}
	
	public void draw(GameContainer gc, Graphics g){
		int mx = gc.getInput().getMouseX(), my = gc.getInput().getMouseY();
		// Background
		if (bghover != null && activated && this.contains(mx,my)){
			g.setColor(Color.white);
			g.fill(this);
		}else{
			g.setColor(bghover);
			g.fill(this);
		}
		//Sprite
		if (activated){
			sprite.draw(x, y, fg);
		}else{
			sprite.draw(x, y, gray);
		}		
	}
	public void drawTooltip(GameContainer gc, Graphics g){
		// Tooltip
		int mx = gc.getInput().getMouseX(), my = gc.getInput().getMouseY();
		if (tooltipText != null && tooltipText.length() != 0 && this.contains(mx,my)){
			g.setColor(new Color(255,255,255,0.5f));
			g.fillRect(mx, my, uFont.getWidth(tooltipText) + 16, uFont.getLineHeight() + 16);
			g.setColor(new Color(0,0,0,0.5f));
			g.fillRect(mx+2, my+2, uFont.getWidth(tooltipText) + 12, uFont.getLineHeight() + 12);
			uFont.drawString(mx+8, my+8, tooltipText);
		}
	}
	
}
