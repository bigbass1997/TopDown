package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;

public interface Entity {
	public void render(SpriteBatch batch);
	public void update();
	public Polygon[] getPolygons();
	public void collide(Entity entity);
	public void hit(int damage);
	public boolean isdead();
}
