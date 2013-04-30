package ld26_kiasaki_dagothig.helpers;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class FontFactory {

	private static FontFactory instance = new FontFactory();
	public static FontFactory get(){
		return instance;
	}
	
	private Map<Integer, Map<Color, UnicodeFont>> fonts = new HashMap<Integer, Map<Color, UnicodeFont>>();
	
	private FontFactory(){
	}
	
	@SuppressWarnings("unchecked")
	public UnicodeFont getFont(int pSize, Color pColor){
		Map<Color, UnicodeFont> tFontArr = fonts.get(pSize);
		if (tFontArr == null){
			fonts.put(pSize, new HashMap<Color, UnicodeFont>());
			tFontArr = fonts.get(pSize);
		}
		
		UnicodeFont tFont = null;
		tFont = tFontArr.get(pColor);
		try {
			if (tFont == null){
				tFontArr.put(pColor, new UnicodeFont("res/fonts/slkscr.ttf", pSize, false, false));
				tFont = tFontArr.get(pColor);
				tFont.getEffects().add(new ColorEffect(pColor));
				tFont.addAsciiGlyphs();
				tFont.loadGlyphs();
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return tFont;
	}
	
}
