package uk.co.oumu.gdxgame.screens;

import java.util.Random;

import uk.co.oumu.gdxgame.Assets;
import uk.co.oumu.gdxgame.Game;
import uk.co.oumu.gdxgame.entities.NPC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LevelScreen extends Screen {
	
	public Stage stage;
	
	public LevelScreen() {
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		
		Assets.music.setLooping(true);
		Assets.music.setVolume(0.5f);
		
		Random rand = new Random();
		stage.addActor(new NPC("Alex",(int)(rand.nextFloat()*Gdx.graphics.getWidth()),(int)(rand.nextFloat()*Gdx.graphics.getHeight()),new Sprite(Assets.npcs,0,16,32,32)));
		stage.addActor(new NPC("Ned",(int)(rand.nextFloat()*Gdx.graphics.getWidth()),(int)(rand.nextFloat()*Gdx.graphics.getHeight()),new Sprite(Assets.npcs,0,16,32,32)));
		stage.addActor(new NPC("Franko",(int)(rand.nextFloat()*Gdx.graphics.getWidth()),(int)(rand.nextFloat()*Gdx.graphics.getHeight()),new Sprite(Assets.npcs,0,16,32,32)));
		stage.addActor(new NPC("Dan",(int)(rand.nextFloat()*Gdx.graphics.getWidth()),(int)(rand.nextFloat()*Gdx.graphics.getHeight()),new Sprite(Assets.npcs,0,16,32,32)));
		stage.addActor(new NPC("Benj",(int)(rand.nextFloat()*Gdx.graphics.getWidth()),(int)(rand.nextFloat()*Gdx.graphics.getHeight()),new Sprite(Assets.npcs,0,16,32,32)));
	}

	@Override
	public void resize(int width, int height) {
		// This is necessary to support window resizing, but it also resets the camera position all the time...
		//stage.setViewport(width, height, false);
	}
	
	@Override
	public void render(float delta) {
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			stage.getCamera().translate(0, 2, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			stage.getCamera().translate(0, -2, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			stage.getCamera().translate(-2, 0, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			stage.getCamera().translate(2, 0, 0);
		}
		
		stage.act(Gdx.graphics.getDeltaTime());
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
        stage.draw();
	}
	
	@Override
	public void show() {
		if(!Assets.music.isPlaying()) {
			Assets.music.play();
		}
	}
	
	@Override
	public void dispose() {
        stage.dispose();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.ESCAPE) {
			Assets.sound.play();
			Game.changeScreen(Game.MENU);
			return true;
		}
		if(keycode == Input.Keys.SPACE) {
			Assets.sound.play();
			Game.changeScreen(new InfoScreen());
			return true;
		}
		
		return false;
	}
}
