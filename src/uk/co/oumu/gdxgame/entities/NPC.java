package uk.co.oumu.gdxgame.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NPC extends Actor {
	
	private Sprite sprite;
	
	public NPC(String name, int x, int y, Sprite sprite) {
		super(name);
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		this.originX = sprite.getOriginX();
		this.originY = sprite.getOriginY();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		sprite.setPosition(x, y);
		sprite.draw(batch);
	}
	
	@Override
	public boolean keyDown(int keycode) {
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
