package ld26_kiasaki_dagothig.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.StateBasedGame;

public class Button extends Rectangle {

	public Color bghover = Color.white;
	public String text = "";
	private UnicodeFont font;
	
	public Button(float x, float y, float width, float height, UnicodeFont font, Color bghover, String text) {
		super(x, y, width, height);
		this.font = font;
		this.bghover = bghover;
		this.text = text;
	}
	
	public void setFont(UnicodeFont pFont){
		this.font = pFont;
	}
	public void setText(String pText){
		this.text = pText;
	}
	
	public void draw(GameContainer gc, Graphics g){
		
		if (bghover != null && this.contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())){
			g.setColor(bghover);
			g.fill(this);
		}

		font.drawString(x + width/2 - font.getWidth(text)/2, y + height/2 - font.getLineHeight()/2, text);
	}
	
	
}
