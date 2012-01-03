package uk.co.oumu.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static final BitmapFont font = new BitmapFont();
	
	public static final Texture ui = new Texture(Gdx.files.internal("img/ui.png"));
	public static final Texture tiles = new Texture(Gdx.files.internal("img/tiles.png"));
	public static final Texture npcs = new Texture(Gdx.files.internal("img/npcs.png"));

	public static final TextureRegion logo = new TextureRegion(ui, 0,0, 128,64);
	public static final TextureRegion textbox = new TextureRegion(ui, 0,64, 16,16);
	
	public static final Music music = Gdx.audio.newMusic(Gdx.files.internal("snd/music.mp3"));
	public static final Sound sound = Gdx.audio.newSound(Gdx.files.internal("snd/sfx.wav"));
}
