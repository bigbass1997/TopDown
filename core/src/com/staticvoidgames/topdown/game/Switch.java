
package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.states.PlayState;

public class Switch implements Entity{
	
	Polygon polygon;
	private float x, y;
	boolean taken;
	public PowerUpType powerUpType;
	private int color;
	
	public Switch(float x, float y, int color) {
		polygon= new Polygon(new float[]{
				-10,-10,
				-10,10,
				10,10,
				10,-10,
		});
		polygon.translate(x, y);
		PlayState.entities.add(this);
		powerUpType=PowerUpType.DOUBLE;
		this.x = x;
		this.y = y;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.end();
		GraphicsMain.shaperenderer.setColor(PlayState.colors[color]);
		GraphicsMain.shaperenderer.begin(ShapeType.Filled);
		GraphicsMain.shaperenderer.rect(x-10, y-10,20,20);
		GraphicsMain.shaperenderer.end();
		batch.begin();
	}


	@Override
	public void update() {
		y-=0.2f;
		polygon.translate(0, -0.2f);
	}

	@Override
	public Polygon[] getPolygons() {
		return new Polygon[]{polygon};
	}

	@Override
	public void collide( Entity entity) {
		
	}


	@Override
	public void hit(int damage) {
		PlayState.Active[color]=!PlayState.Active[color];
	}


	@Override
	public boolean isdead() {
		return y<0||taken;
	}


}