package uk.co.oumu.gdxgame.screens;

import uk.co.oumu.gdxgame.Assets;
import uk.co.oumu.gdxgame.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InfoScreen extends Screen {
	
	private SpriteBatch batch = new SpriteBatch();
	private String text = "About GdxGame\n\nThis is a template for a game\nwritten using LibGDX.\n\nLorem ipsum dolor sunt, etc.\nPress any key to return.\n\n -Alex";
	
	
	public void drawText() {
		float w = Assets.font.getMultiLineBounds(text).width;
		float h = Assets.font.getMultiLineBounds(text).height;
		int x = (int)(Gdx.graphics.getWidth()-w)/2;
		int y = (int)(Gdx.graphics.getHeight()+h)/2;
		
		batch.draw(Assets.textbox, x-16, y-h-16, w+32, h+32);
		
		Assets.font.setColor(0,0,0,1);
		Assets.font.setScale(1);
		Assets.font.drawMultiLine(batch, text, x, y);
	}
	
	
	// Screen methods:
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		batch.begin();
		drawText();
		batch.end();
	}
	
	@Override
	public void hide() {
		
	}
	
	
	// Input processor methods:

	public boolean keyDown(int keycode) {

		Assets.sound.play();
		Game.changeScreen(Game.LEVEL);

		return true;
	}

}
