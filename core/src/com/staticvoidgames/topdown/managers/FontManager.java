package com.staticvoidgames.topdown.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontManager {
	
	//Class used to INIT and store Fonts for use in rendering text.
	
	public BitmapFont fs16;
	public BitmapFont fs32;
	public BitmapFont fs48;
	public BitmapFont fs72;
	
	public FontManager(){
		fs16 = new BitmapFont(Gdx.files.internal("fonts/The-First-FontStruction_16px.fnt"), false);
		fs32 = new BitmapFont(Gdx.files.internal("fonts/The-First-FontStruction_32px.fnt"), false);
		fs48 = new BitmapFont(Gdx.files.internal("fonts/The-First-FontStruction_48px.fnt"), false);
		fs72 = new BitmapFont(Gdx.files.internal("fonts/The-First-FontStruction_72px.fnt"), false);
	}
}
