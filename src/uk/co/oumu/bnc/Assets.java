package uk.co.oumu.bnc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static FileHandle savedir;
	public static FileHandle tempdir;
	
	public static BitmapFont font;
	
	public static Music music1;
	public static Music music2;
	
	public static Texture ui;
	public static Texture npcs;

	public static TextureRegion logo;
	public static TextureRegion textbox;
	
	public static Sound ding;
	public static Sound door;
	public static Sound levelup;
	
	
	public static void loadAssets() {
		
		savedir = Gdx.files.external(".bnc");
		savedir.mkdirs();
		tempdir = savedir.child("tmp");
		tempdir.mkdirs();
		
		// TODO: load a custom font
		font = new BitmapFont();
				
		FileHandle path_music1 = tempdir.child("music1.mp3");
		FileHandle path_music2 = tempdir.child("music2.mp3");
	    Gdx.files.internal("data/snd/music1.mp3").copyTo(path_music1);
	    Gdx.files.internal("data/snd/music2.mp3").copyTo(path_music2);
		music1 = Gdx.audio.newMusic(path_music1);
		music2 = Gdx.audio.newMusic(path_music2);
		
		ui = new Texture(Gdx.files.internal("data/img/ui.png"));
		npcs = new Texture(Gdx.files.internal("data/img/npcs.png"));

		logo = new TextureRegion(ui, 0,0, 324,138);
		textbox = new TextureRegion(ui, 0,138, 16,16);
		
		ding = Gdx.audio.newSound(Gdx.files.internal("data/snd/ding.wav"));
		door = Gdx.audio.newSound(Gdx.files.internal("data/snd/door.wav"));
		levelup = Gdx.audio.newSound(Gdx.files.internal("data/snd/levelup.wav"));
		
	}
	
	public static void disposeAssets() {
		
		tempdir.deleteDirectory();
	}
}
