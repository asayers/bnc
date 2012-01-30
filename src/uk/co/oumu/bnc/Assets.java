package uk.co.oumu.bnc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	// TODO: load a custom font
	public static final BitmapFont font = new BitmapFont();
	
	public static final Music music1 = Gdx.audio.newMusic(Gdx.files.internal("data/snd/music1.mp3"));
	public static final Music music2 = Gdx.audio.newMusic(Gdx.files.internal("data/snd/music2.mp3"));
	
	public static final Texture ui = new Texture(Gdx.files.internal("data/img/ui.png"));
	public static final Texture npcs = new Texture(Gdx.files.internal("data/img/npcs.png"));

	public static final TextureRegion logo = new TextureRegion(ui, 0,0, 324,138);
	public static final TextureRegion textbox = new TextureRegion(ui, 0,138, 16,16);
	
	public static final Sound ding = Gdx.audio.newSound(Gdx.files.internal("data/snd/ding.wav"));
	public static final Sound door = Gdx.audio.newSound(Gdx.files.internal("data/snd/door.wav"));
	public static final Sound levelup = Gdx.audio.newSound(Gdx.files.internal("data/snd/levelup.wav"));
}
