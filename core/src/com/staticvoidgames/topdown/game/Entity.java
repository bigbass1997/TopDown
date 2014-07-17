package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {
	public void render(SpriteBatch batch);
	public void update();
	public Circle[] getCircles();
	public void collide(double angle1to2, Entity entity);
	public void hit(int damage);
	public boolean isdead();
}
