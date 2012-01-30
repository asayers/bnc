package uk.co.oumu.bnc.screens;

import uk.co.oumu.bnc.Assets;
import uk.co.oumu.bnc.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MessageOverlay extends Screen {
	
	private SpriteBatch batch = Game.BATCH;
	private String text;
	
	public MessageOverlay(String message) {
		// TODO: convert to a string array for multiple screens
		text = message;
	}
	
	public void drawText() {
//		float w = Assets.font.getMultiLineBounds(text).width;
//		float h = Assets.font.getMultiLineBounds(text).height;
		
		batch.draw(Assets.textbox, -Game.WIDTH/2, -Game.HEIGHT/2, Game.WIDTH, Game.HEIGHT/3);
		
		// TODO: Get the text to wrap
		Assets.font.setColor(0,0,0,1);
		Assets.font.setScale(1);
		Assets.font.drawMultiLine(batch, text, -Game.WIDTH/2 + 16, -Game.HEIGHT/6 - 16);
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
		
		// TODO: remove a string from the message array, change the screen to another MessageOverlay with the new array; back to LEVEL is the limiting case.
		
		Assets.ding.play();
		Game.changeScreen(Game.LEVEL);

		return true;
	}

}
