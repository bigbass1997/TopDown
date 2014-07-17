package com.staticvoidgames.topdown.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
	public static Texture playerTexture;
	static{
		playerTexture= new Texture(Gdx.files.internal("Smile.png"));
	}
}
