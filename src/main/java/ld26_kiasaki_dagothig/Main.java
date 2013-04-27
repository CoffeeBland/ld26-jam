package ld26_kiasaki_dagothig;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) {
		System.out.println("Starting v0.1 of ld26Jam by Paul (kiasaki and dagothig)");

        AppGameContainer app;
		try {
			app = new AppGameContainer(new GameStateController("ld26-jam"));
	        //app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), true);
			app.setDisplayMode(800, 600, false);
	        app.setForceExit(true);
	        app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
