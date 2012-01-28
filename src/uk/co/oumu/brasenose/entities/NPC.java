package uk.co.oumu.brasenose.entities;

import uk.co.oumu.brasenose.Assets;
import uk.co.oumu.brasenose.Game;
import uk.co.oumu.brasenose.screens.MessageOverlay;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NPC extends Actor {
	
	private int i;
	private int j;
	private int spr_i;
	private int spr_j;
	private Sprite sprite;
	private int speed = 5;
	private int direction = 2;
	private int walking = 0;
	private String dialogue;
	
	public NPC(String name, int i, int j, int spr_i, int spr_j, String dialogue) {
		super(name);
		
		if(Game.LEVEL.actors[i][j] == null) {
			Game.LEVEL.actors[i][j] = this;
		} else {
			markToRemove(true);
		}
		
		this.i = i;
		this.j = j;
		this.x = i*32;
		this.y = j*32;
		this.direction = 2;
		this.spr_i = spr_i*2;
		this.spr_j = spr_j*4;
		setSprite();
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		this.originX = sprite.getOriginX();
		this.originY = sprite.getOriginY();
		this.dialogue = dialogue;
	}
	
	public void setSprite() {
		if(walking < 0) {
			// TODO: standing still sprite here
			this.sprite = new Sprite(Assets.npcs, 32*(spr_i), 32*(spr_j+direction), 32, 32);
		} else if(walking < 50/speed) {
			// Animation frame 1
			this.sprite = new Sprite(Assets.npcs, 32*(spr_i), 32*(spr_j+direction), 32, 32);
		} else {
			// Animation frame 2
			this.sprite = new Sprite(Assets.npcs, 32*(spr_i+1), 32*(spr_j+direction), 32, 32);
		}
	}
	
	@Override
	public void act(float delta) {

		if(walking > 0) {
			if(x<i*32) x = i*32 - 32*walking*speed/100;
			if(x>i*32) x = i*32 + 32*walking*speed/100;
			if(y<j*32) y = j*32 - 32*walking*speed/100;
			if(y>j*32) y = j*32 + 32*walking*speed/100;
			walking -= delta;
		} else {
			x = i*32;
			y = j*32;
			
			double rand = Math.random()*5/delta;
			if(rand < 0.25) {
				if(checkFree(i,j+1)) {
					Game.LEVEL.actors[i][j] = null;
					j++;
					Game.LEVEL.actors[i][j] = this;
				}
				walking = 100/speed;
				direction = 0;
			} else if(rand < 0.5) {
				if(checkFree(i,j-1)) {
					Game.LEVEL.actors[i][j] = null;
					j--;
					Game.LEVEL.actors[i][j] = this;
				}
				walking = 100/speed;
				direction = 2;
			} else if(rand < 0.75) {
				if(checkFree(i-1,j)) {
					Game.LEVEL.actors[i][j] = null;
					i--;
					Game.LEVEL.actors[i][j] = this;
				}
				walking = 100/speed;
				direction = 3;
			} else if(rand < 1) {
				if(checkFree(i+1,j)) {
					Game.LEVEL.actors[i][j] = null;
					i++;
					Game.LEVEL.actors[i][j] = this;
				}
				walking = 100/speed;
				direction = 1;
			}
		}
		
		setSprite();
	}
	
	public boolean checkFree(int i, int j) {
		if(i<0 || j<0 || i+1>Game.LEVEL.width || j+1>Game.LEVEL.height) {
			return false;
		}
		if(Game.LEVEL.map.layers.get(1).tiles[Game.LEVEL.height-1-j][i] != 0) {
			return false;
		}
		if(Game.LEVEL.actors[i][j] != null) {
			return false;
		}
		
		return true;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		sprite.setPosition(x+16, y+16);
		sprite.draw(batch);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		direction = keycode;
		setSprite();
		Game.LEVEL.draw();
		
		Assets.ding.play();
		Game.changeScreen(new MessageOverlay(dialogue));
		return true;
	}

	@Override
	public Actor hit(float x, float y) {
		return x > 0 && x < width && y > 0 && y < height ? this : null;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer) {
		return false;
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
	}
	
	@Override
	public void touchDragged(float x, float y, int pointer) {
	}
}
