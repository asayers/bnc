package uk.co.oumu.bnc;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {
	
	public static final int TILES_H = 11;
	public static final int TILES_V = 7;
	public static final float ZOOM = 0.5f;
	
	public static void main(String[] args) {
		Game.ZOOM = ZOOM;
		
		// FIXME: wuh? (returns zero)
		System.out.println(""+(float)(1/2));
		
//		new LwjglApplication(Game.GAME, "Brasenose: The Game", 480, 320, true);
		new LwjglApplication(Game.GAME, "Brasenose: The Game", (int)(TILES_H*32/ZOOM), (int)(TILES_V*32/ZOOM), true);

//		new LwjglApplication(Game.GAME, "Brasenose: The Game", Gdx.graphics.getDisplayModes()[0].width, Gdx.graphics.getDisplayModes()[0].height, true);
	}
}
