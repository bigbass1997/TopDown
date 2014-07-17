package com.staticvoidgames.topdown.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.staticvoidgames.topdown.managers.GameStateManager;

public class MenuState extends GameState {
	
	private Texture texture;
	//private Sprite sprite;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		
		//Testing for rendering and positioning of coords.
		texture = new Texture(Gdx.files.internal("test.png"));
		/*sprite = new Sprite(texture);
		sprite.setPosition(0, 0);*/
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update(float delta) {
		
	}
	/**
	 * You need to end and restart the batch if you wish to use another batch.
	 */
	@Override
	public void draw(SpriteBatch batch) {
		//Testing Image being drawn.
		batch.draw(texture, 0, 0, 100, 100);
	}

	@Override
	public void dispose() {
		
	}
}
