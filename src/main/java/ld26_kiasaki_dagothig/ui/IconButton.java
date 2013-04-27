package ld26_kiasaki_dagothig.ui;

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
	
	private final Color gray = new Color(150, 150, 150);
	private boolean activated = true;
	
	public IconButton(float x, float y, Color bghover, Color fg, Image pSprite) {
		super(x, y, 48, 48);
		this.bghover = bghover;
		this.fg = fg;
		this.sprite = pSprite;
	}
	
	public boolean getActivated(){
		return activated;
	}
	public void setActivated(boolean pActivated){
		this.activated = pActivated;
	}
	
	public void draw(GameContainer gc, Graphics g){
		
		if (bghover != null && this.contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())){
			g.setColor(Color.white);
			g.fill(this);
		}else{
			g.setColor(bghover);
			g.fill(this);
		}
		
		if (activated){
			sprite.draw(x, y, fg);
		}else{
			sprite.draw(x, y, gray);
		}
		
	}
	
}
