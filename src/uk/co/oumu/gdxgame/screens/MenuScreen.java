package uk.co.oumu.gdxgame.screens;

import uk.co.oumu.gdxgame.Assets;
import uk.co.oumu.gdxgame.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen extends Screen {
	
	private SpriteBatch batch = new SpriteBatch();
	private String[] menuitems = {"Continue", "New game","About","Exit"};
	private int selection = 1;
	
	public void drawTitle() {
		int x = (int)(Gdx.graphics.getWidth()-Assets.logo.getRegionWidth())/2;
		int y = (int)(Gdx.graphics.getHeight()-Assets.logo.getRegionHeight())*2/3;
		batch.draw(Assets.logo, x, y);
	}

	public void drawMenu() {
		String menutext = "";
		for(int i=0; i<menuitems.length; i++) {
			if(i == selection) {
				menutext = menutext.concat("> ");
			} else {
				menutext = menutext.concat("    ");
			}
			menutext = menutext.concat(menuitems[i]+"\n");
		}
		
		int x = (int)(Gdx.graphics.getWidth()-Assets.font.getMultiLineBounds(menutext).width)/2;
		int y = (int)(Gdx.graphics.getHeight()-Assets.font.getMultiLineBounds(menutext).height)/2;
		Assets.font.setColor(0,0,0,1);
		Assets.font.setScale(1);
		Assets.font.drawMultiLine(batch, menutext, x, y);
	}
	
	public void selectionUp() {
		int i;
		if(Game.LEVEL!=null) {
			i = 0;
		} else {
			i = 1;
		}
		
		selection--;
		if(selection < i) {
			selection = menuitems.length-1;
		}
	}
	
	public void selectionDown() {
		int i;
		if(Game.LEVEL!=null) {
			i = 0;
		} else {
			i = 1;
		}
		
		selection++;
		if(selection > menuitems.length-1) {
			selection = i;
		}
	}
	
	// Screen methods:
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		drawTitle();
		drawMenu();
		Assets.font.draw(batch, "s:"+selection+";l:"+Game.LEVEL,0,16);
		batch.end();
	}

	@Override
	public void show() {
		if(Game.LEVEL == null) {
			selection = 1;
		} else {
			selection = 0;
		}
	}

	@Override
	public void hide() {
	}
	
	
	// Input processor methods:

	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.SPACE) {
			if(selection == 0) {
				if(Game.LEVEL!=null) {
					Assets.sound.play();
					Game.changeScreen(Game.LEVEL);
				}
			}
			if(selection == 1) {
				Assets.sound.play();
				if(Game.LEVEL!=null) {
					Game.LEVEL.dispose();
				}
				Game.LEVEL = new LevelScreen();
				Game.changeScreen(Game.LEVEL);
			}
			if(selection == 2) {
				Assets.sound.play();
				Game.changeScreen(new AboutScreen());
			}
			if(selection == 3) {
				Assets.sound.play();
				Gdx.app.exit();
			}
			return true;
		}
		if(keycode == Input.Keys.ESCAPE) {
			Gdx.app.exit();
			return true;
		}
		
		if(keycode == Input.Keys.UP) {
			selectionUp();
			return true;
		}
		if(keycode == Input.Keys.DOWN) {
			selectionDown();
			return true;
		}
		return false;
	}
}
