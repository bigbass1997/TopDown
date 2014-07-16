package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {
	public void getX();
	public void render(SpriteBatch batch);
	public void getY();
	public void update();
}
