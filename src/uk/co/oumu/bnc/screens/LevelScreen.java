package uk.co.oumu.bnc.screens;

import java.util.ArrayList;
import java.util.HashMap;

import uk.co.oumu.bnc.Assets;
import uk.co.oumu.bnc.Game;
import uk.co.oumu.bnc.entities.NPC;
import uk.co.oumu.bnc.entities.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.graphics.g2d.tiled.TiledObject;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LevelScreen extends Screen {
	
	public String name;
	public int width;
	public int height;
	public Stage stage;
	public Player player;
	public Actor[][] actors;

	private FileHandle tiledir;
	private FileHandle mapfile;
	public TiledMap map;
	private TileAtlas tileAtlas;
	private TileMapRenderer tileMapRenderer;

	
	public LevelScreen(String name) {
		Game.LEVEL = this;
		this.name = name;
		
		// Load the map
		tiledir = Gdx.files.internal("data/world/"+name+"/");
		mapfile = Gdx.files.internal("data/world/"+name+"/level.tmx");
		map = TiledLoader.createMap(mapfile);
		tileAtlas = new TileAtlas(map, tiledir);
		tileMapRenderer = new TileMapRenderer(map, tileAtlas, 16, 16);
		
		// Prepare the camera
		Game.GAME_CAM = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Game.GAME_CAM.position.set(0, 0, 0);
		Game.GAME_CAM.zoom = Game.ZOOM;
		
		width = map.width;
		height = map.height;
		
		
		// Set up the stage
		stage = new Stage(width*32, height*32, false);
		stage.setViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		actors = new Actor[width][height];
		
		// Populate the stage
		ArrayList<TiledObject> npcs = map.objectGroups.get(0).objects;
		int x;
		int y;
		String npcname;
		HashMap<String,String> properties;
		for(int i = 0; i < npcs.size(); i++) {
			x = (int) Math.floor(npcs.get(i).x/32);
			y = (int) (height - 1 - Math.floor(npcs.get(i).y/32));
			npcname = npcs.get(i).name;
			properties = npcs.get(i).properties;
			
			if(npcs.get(i).type.equals("Player") && player == null) {
				player = new Player(npcname, x, y, 15, 2);
				stage.addActor(player);
			}
			if(npcs.get(i).type.equals("NPC")) {
				String dialogue;
				if(properties.get("dialogue") != null) {
					dialogue = properties.get("dialogue");
				} else {
					dialogue = "Default dialogue!";
				}
				stage.addActor(new NPC(npcname, x, y, 0, 0, dialogue));
			}
		}
		
		// TODO: add Door object, create instances from an objectgroup, add collision event.
	}

	@Override
	public void resize(int width, int height) {
		// This is necessary to support window resizing, but it also resets the camera position all the time...
		//stage.setViewport(width, height, false);
	}
	
	@Override
	public void render(float delta) {
		act(delta);
		draw();		
	}
	
	public void act(float delta) {
		if(Game.GAME.volumeslider < 1) {
			Assets.music2.setVolume(Game.GAME.volumeslider/2);
			Assets.music1.setVolume((1f - Game.GAME.volumeslider)/4);
			Game.GAME.volumeslider += 0.01;
		}
		
		// Call act() for all actors
		stage.act(Gdx.graphics.getDeltaTime());
	}
	
	public void draw() {
		// Clear the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Update the camera
		Game.GAME_CAM.update();

		// Render the map
		tileMapRenderer.render(Game.GAME_CAM);
		
		// Draw the actors
		stage.setCamera(Game.GAME_CAM);
		stage.draw();
        
		// Other stuff (UI, debug)
		Game.BATCH.begin();
        Assets.font.draw(Game.BATCH, "x:"+player.x+"; y:"+player.y+"; fps:"+Gdx.graphics.getFramesPerSecond(),-Game.WIDTH/2 + 4,-Game.HEIGHT/2 + 16);
        Game.BATCH.end();
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
		
		if(keycode == Input.Keys.PLUS) {
			Game.ZOOM /= 2;
			Game.updateCameras();
			return true;
		}
		if(keycode == Input.Keys.MINUS) {
			Game.ZOOM *= 2;
			Game.updateCameras();
			return true;
		}
		
		return player.keyDown(keycode);
	}
	
}
