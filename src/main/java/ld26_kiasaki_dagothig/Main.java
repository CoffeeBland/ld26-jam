package ld26_kiasaki_dagothig;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) {
		System.out.println("Starting v0.1 of ld26Jam by Paul (kiasaki and dagothig)");

        AppGameContainer app;
		try {
			app = new AppGameContainer(new GameStateController("Ludum Dare 26 - Kiasaki - Dagothig"));
	        //app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), true);
			app.setDisplayMode(1024,768, false);
	        app.setForceExit(true);
	        app.setShowFPS(false);        
	        app.start();
	        
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
