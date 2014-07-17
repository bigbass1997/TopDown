package com.staticvoidgames.topdown.game;

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
	public Obstacle(float x1, float y1, float x2, float y2, float x3, float y3, int color) {
		polygon= new Polygon(new float[]{
				x1,y1,
				x2,y2,
				x2,y3,
		});
		this.color=color;
		PlayState.entities.add(this);
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.end();
		GraphicsMain.shaperenderer.setColor(PlayState.colors[color]);
		if(PlayState.Active[color]==ReactTo)GraphicsMain.shaperenderer.begin(ShapeType.Filled);
		else GraphicsMain.shaperenderer.begin(ShapeType.Line);
		GraphicsMain.shaperenderer.triangle(polygon.getTransformedVertices()[0],polygon.getTransformedVertices()[1],polygon.getTransformedVertices()[2],polygon.getTransformedVertices()[3],polygon.getTransformedVertices()[4],polygon.getTransformedVertices()[5]);
		GraphicsMain.shaperenderer.end();
		batch.begin();
	}


	@Override
	public void update() {
		polygon.translate(0, -0.2f);
	}

	@Override
	public Polygon[] getPolygons() {
		if(PlayState.Active[color]==ReactTo)return new Polygon[]{polygon};
		else return new Polygon[]{};
	}

	@Override
	public void collide( Entity entity) {
		
	}


	@Override
	public void hit(int damage) {
		
	}


	@Override
	public boolean isdead() {
		return false;
	}

}
