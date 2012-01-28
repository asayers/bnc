package uk.co.oumu.brasenose.screens;

import uk.co.oumu.brasenose.Assets;
import uk.co.oumu.brasenose.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MessageOverlay extends Screen {
	
	private SpriteBatch batch = new SpriteBatch();
	private String text;
	
	public MessageOverlay(String message) {
		text = message;
	}
	
	public void drawText() {
//		float w = Assets.font.getMultiLineBounds(text).width;
//		float h = Assets.font.getMultiLineBounds(text).height;
		
		batch.draw(Assets.textbox, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/3);
		
		Assets.font.setColor(0,0,0,1);
		Assets.font.setScale(1);
		Assets.font.drawMultiLine(batch, text, 16, Gdx.graphics.getHeight()/3 - 16);
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

		Assets.ding.play();
		Game.changeScreen(Game.LEVEL);

		return true;
	}

}
