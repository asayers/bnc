package uk.co.oumu.bnc.screens;

import uk.co.oumu.bnc.Assets;
import uk.co.oumu.bnc.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class AboutScreen extends Screen {
	
	private String text = "Brasenose: The Game is a\npokemon-style RPG based on\nBrasenose College.\n\nLead/Programming: Alex Sayers\nPlanning: Jing Ouyang\nMusic: John Forster\nArt: This could be YOU!\n\nPress any key to return.";
	
	public void drawText() {
		int x = (int)-(Assets.font.getMultiLineBounds(text).width)/2;
		int y = (int)(Assets.font.getMultiLineBounds(text).height)/2;
		Assets.font.setColor(0,0,0,1);
		Assets.font.setScale(1);
		Assets.font.drawMultiLine(Game.BATCH, text, x, y);
	}
	
	
	// Screen methods:

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		Game.BATCH.begin();
		drawText();
		Game.BATCH.end();
	}
	
	
	// Input processor methods:

	public boolean keyDown(int keycode) {

		Assets.ding.play();
		Game.changeScreen(Game.MENU);

		return true;
	}
}
