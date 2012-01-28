package uk.co.oumu.brasenose;

import uk.co.oumu.brasenose.screens.LevelScreen;
import uk.co.oumu.brasenose.screens.MenuScreen;
import uk.co.oumu.brasenose.screens.Screen;

import com.badlogic.gdx.Gdx;

public class Game extends com.badlogic.gdx.Game {
		
	public static final Game GAME = new Game();
	public static MenuScreen MENU;
	public static LevelScreen LEVEL;
	public float volumeslider = 0;
	
	public static void changeScreen(Screen screen) {
		GAME.setScreen(screen);
		Gdx.input.setInputProcessor(screen);
	}
	
	@Override
	public void create() {
		Assets.music1.setLooping(true);
		Assets.music2.setLooping(true);
		Assets.music1.setVolume((1f - volumeslider)/4);
		Assets.music2.setVolume(volumeslider/2);
		
		MENU = new MenuScreen();
		changeScreen(MENU);
		
		Assets.music1.play();
		Assets.music2.play();
	}
}
