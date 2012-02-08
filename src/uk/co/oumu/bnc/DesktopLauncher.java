package uk.co.oumu.bnc;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {
	
	public static void main(String[] args) {
		Game.ZOOM = 1;
		
		// FIXME: wuh? (returns zero)
		System.out.println(""+(float)(1/2));
		
		new LwjglApplication(Game.GAME, "Brasenose: The Game", 480, 320, true);
//		new LwjglApplication(Game.GAME, "Brasenose: The Game", 800, 500, true);

//		new LwjglApplication(Game.GAME, "Brasenose: The Game", Gdx.graphics.getDisplayModes()[0].width, Gdx.graphics.getDisplayModes()[0].height, true);
	}
}
