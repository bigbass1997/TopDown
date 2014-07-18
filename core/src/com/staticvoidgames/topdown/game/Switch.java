
package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.states.PlayState;

public class Switch implements Entity{
	
	Polygon polygon;
	private float x, y;
	boolean taken;
	private int color;
	private int immune;
	
	public Switch(float x, float y, int color) {
		immune=0;
		this.color=color;
		polygon= new Polygon(new float[]{
				-10,-10,
				-10,10,
				10,10,
				10,-10,
		});
		polygon.translate(x, y);
		PlayState.entities.add(this);
		this.x = x;
		this.y = y;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.end();
		if(immune<0)GraphicsMain.shaperenderer.setColor(PlayState.colors[color]);
		else GraphicsMain.shaperenderer.setColor(Color.WHITE);
		GraphicsMain.shaperenderer.begin(ShapeType.Filled);
		GraphicsMain.shaperenderer.rect(x-10, y-10,20,20);
		GraphicsMain.shaperenderer.end();
		batch.begin();
	}


	@Override
	public void update() {
		immune--;
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
		if(immune<0){PlayState.Active[color]=!PlayState.Active[color];
			immune=100;
		}
	}


	@Override
	public boolean isdead() {
		return y<0||taken;
	}


}