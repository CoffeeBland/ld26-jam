package ld26_kiasaki_dagothig;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	private static boolean fullscreen;
	private static AppGameContainer app;
	private static GameStateController gsc;
	
	public static void main(String[] args) {
		System.out.println("Starting v0.5 of ld26Jam by Paul (kiasaki and dagothig)");
		fullscreen = false;
		try {
			gsc = new GameStateController("Ludum Dare 26 - Kiasaki - Dagothig");
			app = new AppGameContainer(gsc);
			if (!fullscreen){
				app.setDisplayMode(1024,768, false);
			}else{
				app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), true);				
			}
			app.setForceExit(true);
	        app.setShowFPS(false);        
	        app.start();
	    } catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static void toggleFullscreen(){
		try {
			if (fullscreen){
				app.setDisplayMode(1024,768, false);
				fullscreen = false;
			}else{
				app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), true);
				fullscreen = true;
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
