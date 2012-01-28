package uk.co.oumu.brasenose.screens;

import uk.co.oumu.brasenose.Assets;
import uk.co.oumu.brasenose.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AboutScreen extends Screen {
	
	private SpriteBatch batch = new SpriteBatch();
	private String text = "About GdxGame\n\nThis is a template for a game\nwritten using LibGDX.\n\nLorem ipsum dolor sunt, etc.\nPress any key to return.\n\n -Alex";
	
	public void drawText() {
		int x = (int)(Gdx.graphics.getWidth()-Assets.font.getMultiLineBounds(text).width)/2;
		int y = (int)(Gdx.graphics.getHeight()+Assets.font.getMultiLineBounds(text).height)/2;
		Assets.font.setColor(0,0,0,1);
		Assets.font.setScale(1);
		Assets.font.drawMultiLine(batch, text, x, y);
	}
	
	
	// Screen methods:

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		drawText();
		batch.end();
	}
	
	
	// Input processor methods:

	public boolean keyDown(int keycode) {

		Assets.ding.play();
		Game.changeScreen(Game.MENU);

		return true;
	}
}
