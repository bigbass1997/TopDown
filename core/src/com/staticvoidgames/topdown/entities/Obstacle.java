package com.staticvoidgames.topdown.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.states.PlayState;

public class Obstacle implements Entity {
	Polygon polygon;
	boolean activated;
	int color;
	private boolean ReactTo;
	private float x;
	private float y;
	private float w;
	private float h;
	public Obstacle(float x, float y, float w, float h, int color,boolean Reacto) {
		this.ReactTo=Reacto;
		polygon= new Polygon(new float[]{
				0,0,
				0,h,
				w,h,
				w,0,
		});
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		polygon.translate(x, y);
		this.color=color;
		PlayState.entities.add(this);
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.end();
		GraphicsMain.shaperenderer.setColor(PlayState.colors[color]);
		if(PlayState.Active[color]==ReactTo)GraphicsMain.shaperenderer.begin(ShapeType.Filled);
		else GraphicsMain.shaperenderer.begin(ShapeType.Line);
		GraphicsMain.shaperenderer.rect(x, y, w, h);
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
		if(PlayState.Active[color]==ReactTo)return new Polygon[]{polygon};
		else return new Polygon[]{};
	}

	@Override
	public void collide( Entity entity) {
		entity.hit(1);
	}


	@Override
	public void hit(int damage) {
		
	}


	@Override
	public boolean isdead() {
		return y+h<0;
	}

}
