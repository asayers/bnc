package uk.co.oumu.bnc.screens;

import uk.co.oumu.bnc.Assets;
import uk.co.oumu.bnc.Game;
import uk.co.oumu.bnc.levels.CollegeLevel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen extends Screen {
	
	private SpriteBatch batch = Game.BATCH;
	private String[] menuitems = {"Continue", "New game","About","Exit"};
	private int selection = 1;
	
	public void drawTitle() {
		int x = (int)-(Assets.logo.getRegionWidth()/2);
//		int y = (int)(Assets.logo.getRegionHeight()/8);
		int y = -16;
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
		
		int x = (int)-(Assets.font.getMultiLineBounds(menutext).width/2);
		int y = (int)-(Assets.font.getMultiLineBounds(menutext).height*2/3);
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
		if(Game.GAME.volumeslider > 0) {
			Assets.music1.setVolume((1f - Game.GAME.volumeslider)/4);
			Assets.music2.setVolume(Game.GAME.volumeslider/2);
			Game.GAME.volumeslider -= 0.01;
		}
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		drawTitle();
		drawMenu();
		Assets.font.draw(batch, "s:"+selection+";l:"+Game.LEVEL,-Game.WIDTH/2 + 4,-Game.HEIGHT/2 + 16);
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
					Assets.ding.play();
					Game.changeScreen(Game.LEVEL);
				}
			}
			if(selection == 1) {
				Assets.ding.play();
				if(Game.LEVEL!=null) {
					Game.LEVEL.dispose();
				}
				Game.LEVEL = new CollegeLevel();
				Game.changeScreen(Game.LEVEL);
			}
			if(selection == 2) {
				Assets.ding.play();
				Game.changeScreen(new AboutScreen());
			}
			if(selection == 3) {
				Assets.ding.play();
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
		
		return false;
	}
}
