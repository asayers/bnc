package uk.co.oumu.bnc;

import uk.co.oumu.bnc.screens.LevelScreen;
import uk.co.oumu.bnc.screens.MenuScreen;
import uk.co.oumu.bnc.screens.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {
		
	public static final Game GAME = new Game();
	public static MenuScreen MENU;
	public static LevelScreen LEVEL;
	public static OrthographicCamera UI_CAM;
	public static OrthographicCamera GAME_CAM;
	public static SpriteBatch BATCH;
	public static float ZOOM = 1f;
	public static int WIDTH;
	public static int HEIGHT;
	public float volumeslider = 0;
	
	public static void changeScreen(Screen screen) {
		GAME.setScreen(screen);
		Gdx.input.setInputProcessor(screen);
	}
	
	public static void updateCameras() {
		WIDTH = (int) (Gdx.graphics.getWidth()*ZOOM);
		HEIGHT = (int) (Gdx.graphics.getHeight()*ZOOM);
		
		UI_CAM.zoom = ZOOM;
		UI_CAM.update();
		GAME_CAM.zoom = ZOOM;
		GAME_CAM.update();
		BATCH.getProjectionMatrix().set(UI_CAM.combined);
	}
	
	@Override
	public void create() {

		UI_CAM = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		GAME_CAM = UI_CAM;
		BATCH = new SpriteBatch();
		updateCameras();
		
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
