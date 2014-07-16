package com.staticvoidgames.topdown.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.managers.GameStateManager;

public class MenuState extends GameState {
	
	private Texture texture;
	private Sprite sprite;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		
		//Testing for rendering and positioning of coords.
		texture = new Texture(Gdx.files.internal("test.png"));
		sprite = new Sprite(texture);
		sprite.setPosition(0, 0);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void draw() {
		//Testing Image being drawn.
		GraphicsMain.batch.begin();
		GraphicsMain.batch.draw(sprite, 0, 0, 1, 1);
		GraphicsMain.batch.end();
	}

	@Override
	public void dispose() {
		
	}
}
