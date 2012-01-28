package uk.co.oumu.brasenose.screens;

import java.util.Random;

import uk.co.oumu.brasenose.Assets;
import uk.co.oumu.brasenose.Game;
import uk.co.oumu.brasenose.entities.NPC;
import uk.co.oumu.brasenose.entities.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LevelScreen extends Screen {
	
	public String name;
	public int width;
	public int height;
	public OrthographicCamera camera;
	public Stage stage;
	public Player player;
	public Actor[][] actors;

	private SpriteBatch batch = new SpriteBatch();
	private FileHandle tiledir;
	private FileHandle mapfile;
	public TiledMap map;
	private TileAtlas tileAtlas;
	private TileMapRenderer tileMapRenderer;

	
	public LevelScreen() {
		Game.LEVEL = this;
		name = "college";
		
		// Load the map
		tiledir = Gdx.files.internal("data/world/"+name+"/");
		mapfile = Gdx.files.internal("data/world/"+name+"/level.tmx");
		map = TiledLoader.createMap(mapfile);
		tileAtlas = new TileAtlas(map, tiledir);
		tileMapRenderer = new TileMapRenderer(map, tileAtlas, 16, 16);
		
		// Prepare the camera
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0, 0, 0);
		
		width = map.width;
		height = map.height;
		
		
		// Set up the stage
		stage = new Stage(width*32, height*32, false);
		stage.setViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		actors = new Actor[width][height];
		
		// Create the player
		player = new Player("Player", 31, 1, 15, 2);
		stage.addActor(player);
		
		// Populate the stage
		Random rand = new Random();
		int x = (int) width/2;
		int y = (int) height/2;
		for(int i=0; i<20; i++) {
			x = (int) Math.floor(rand.nextFloat()*width);
			y = (int) Math.floor(rand.nextFloat()*height);
			stage.addActor(new NPC("NPC", x, y, 0, 0, "Default dialogue!"));
		}
	}

	@Override
	public void resize(int width, int height) {
		// This is necessary to support window resizing, but it also resets the camera position all the time...
		//stage.setViewport(width, height, false);
	}
	
	@Override
	public void render(float delta) {
		if(Game.GAME.volumeslider < 1) {
			Assets.music2.setVolume(Game.GAME.volumeslider/2);
			Assets.music1.setVolume((1f - Game.GAME.volumeslider)/4);
			Game.GAME.volumeslider += 0.01;
		}
		
		// Call act() for all actors
		stage.act(Gdx.graphics.getDeltaTime());
		
		// Sync the map and stage cameras
		Vector3 translation = new Vector3(camera.position.x+16 - stage.getCamera().position.x, camera.position.y+16 - stage.getCamera().position.y, 0);
		stage.getCamera().translate(translation.x, translation.y, translation.z);
		
		// Clear the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Render the map
		camera.update();
		tileMapRenderer.getProjectionMatrix().set(camera.combined);
		tileMapRenderer.render(camera);
		
		// Draw the actors
		stage.draw();
        
		// Other stuff (UI, debug)
        batch.begin();
        Assets.font.draw(batch, "x:"+player.x+"; y:"+player.y,0,16);
        batch.end();
	}
	
	@Override
	public void show() {
		Game.LEVEL = this;
	}
	
	@Override
	public void dispose() {
        stage.dispose();
        tileAtlas.dispose();
		tileMapRenderer.dispose();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.ESCAPE) {
			Assets.ding.play();
			Game.changeScreen(Game.MENU);
			return true;
		}
		
		return player.keyDown(keycode);
	}
	
}
