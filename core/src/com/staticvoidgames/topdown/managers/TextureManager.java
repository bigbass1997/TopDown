package com.staticvoidgames.topdown.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
	public static Texture playerTexture;
	public static Texture shotTexture;
	public static Texture rockTexture;
	public static Texture powerupTexture;
	static{
		playerTexture= new Texture(Gdx.files.internal("player.png"));
		powerupTexture = new Texture(Gdx.files.internal("powerup.png"));
		shotTexture= new Texture(Gdx.files.internal("Shot.png"));
		rockTexture= new Texture(Gdx.files.internal("Rock.png"));
	}
}
