package uk.co.oumu.gdxgame;

import uk.co.oumu.gdxgame.screens.LevelScreen;
import uk.co.oumu.gdxgame.screens.MenuScreen;
import uk.co.oumu.gdxgame.screens.Screen;

import com.badlogic.gdx.Gdx;

public class Game extends com.badlogic.gdx.Game {
		
	public static final Game GAME = new Game();
	public static MenuScreen MENU;
	public static LevelScreen LEVEL;
	
	public static void changeScreen(Screen screen) {
		GAME.setScreen(screen);
		Gdx.input.setInputProcessor(screen);
	}
	
	@Override
	public void create() {
		MENU = new MenuScreen();
		changeScreen(MENU);
	}
}
