package uk.co.oumu.brasenose;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {
	
	public static void main(String[] args) {
		new LwjglApplication(Game.GAME, "Brasenose: The Game", 480, 320, true);
	}

}
