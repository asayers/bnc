package uk.co.oumu.bnc.entities;

import uk.co.oumu.bnc.Assets;
import uk.co.oumu.bnc.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
	// TODO: make player extend NPC, offload some code to NPC class
	
	public int i;
	public int j;
	private int spr_i;
	private int spr_j;
	private Sprite sprite;
	public int direction = 2;
	private int speed = 5;
	private int walking = 0;
	
	public Player(String name, int i, int j, int spr_i, int spr_j) {
		super(name);
		
		if(Game.LEVEL.actors[i][j] == null) {
			Game.LEVEL.actors[i][j] = this;
		} else {
			Game.LEVEL.actors[i][j].markToRemove(true);
			Game.LEVEL.actors[i][j] = this;
		}
		
		this.i = i;
		this.j = j;
		this.x = i*32;
		this.y = j*32;
		this.spr_i = spr_i*2;
		this.spr_j = spr_j*4;
		setSprite();
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		this.originX = sprite.getOriginX();
		this.originY = sprite.getOriginY();
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
			// This code gets called every frame while the player is moving between grid squares
			if(x<i*32) x = i*32 - 32*walking*speed/100;
			if(x>i*32) x = i*32 + 32*walking*speed/100;
			if(y<j*32) y = j*32 - 32*walking*speed/100;
			if(y>j*32) y = j*32 + 32*walking*speed/100;
			walking -= delta;
			
			if(walking <= 0) {
				// This code gets called once, when the player arrives in the grid square
				if(Game.LEVEL.interactors[i][j] != null) {
					Game.LEVEL.interactors[i][j].act();
				}
			}
		} else {
			// This code is called every frame while the player is standing still
			x = i*32;
			y = j*32;
			
			if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
				if(checkFree(i,j+1)) {
					Game.LEVEL.actors[i][j] = null;
					j++;
					Game.LEVEL.actors[i][j] = this;
				}
				walking = 100/speed;
				direction = 0;
			} else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				if(checkFree(i,j-1)) {
					Game.LEVEL.actors[i][j] = null;
					j--;
					Game.LEVEL.actors[i][j] = this;
				}
				walking = 100/speed;
				direction = 2;
			} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				if(checkFree(i-1,j)) {
					Game.LEVEL.actors[i][j] = null;
					i--;
					Game.LEVEL.actors[i][j] = this;
				}
				walking = 100/speed;
				direction = 3;
			} else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
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
		
		Vector3 translation = new Vector3(x+16 - (Game.LEVEL.stage.getCamera().position.x), y+16 - (Game.LEVEL.stage.getCamera().position.y), 0);
		Game.GAME_CAM.translate(translation.x, translation.y, translation.z);
	}
	
	public boolean checkFree(int i, int j) {
		if(i<0 || j<0 || i+1>Game.LEVEL.width || j+1>Game.LEVEL.height) {
			return false;
		}
		if(Game.LEVEL.map.layers.get(1).tiles[Game.LEVEL.height-1-j][i] != 0) {
			return false;
		}
		if(Game.LEVEL.map.layers.get(0).tiles[Game.LEVEL.height-1-j][i] == 0) {
			return false;
		}
		if(Game.LEVEL.actors[i][j] != null) {
			return false;
		}
		
		return true;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		sprite.setPosition(x, y);
		sprite.draw(batch);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.SPACE) {
			int ti = i;
			int tj = j;
			
			if(direction == 0) {
				ti=i;
				tj=j+1;
			}
			if(direction == 1) {
				ti=i+1;
				tj=j;
			}
			if(direction == 2) {
				ti=i;
				tj=j-1;
			}
			if(direction == 3) {
				ti=i-1;
				tj=j;
			}
			
			// If the target grid square contains an actor, perform actor.keyDown()
			if(!(ti<0) && !(tj<0) && !(ti+1>Game.LEVEL.width) && !(tj+1>Game.LEVEL.height)) {
				if(Game.LEVEL.actors[ti][tj] != null) {
					return Game.LEVEL.actors[ti][tj].keyDown((direction+2)%4);
				}
			}
		}
		
		return false;
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
