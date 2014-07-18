package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.states.PlayState;

public class EnemiShip implements Entity {
	private int cooldown = 100;
	Polygon polygon;
	boolean activated;
	final int size=10;
	private float x;
	private float y;
	private int time;
	private float xm;
	private int life;
	public EnemiShip(float x, float y) {
		life=20;
		time=cooldown;
		polygon= new Polygon(new float[]{
				-size,-size,
				-size,size,
				size,size,
				size,-size,
		});
		this.x=x;
		this.y=y;
		polygon.translate(x, y);
		PlayState.entities.add(this);
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.end();
		GraphicsMain.shaperenderer.setColor(Color.LIGHT_GRAY);
		GraphicsMain.shaperenderer.begin(ShapeType.Filled);
		GraphicsMain.shaperenderer.rect(x-size, y-size, size*2, size*2);
		GraphicsMain.shaperenderer.end();
		batch.begin();
	}


	@Override
	public void update() {
		if(time==0){
			if(x>PlayState.player.x)xm=-0.2f;
			else xm=0.2f;
			new Shot(x, y-(size+3), 0, -1f);
			time=cooldown;
		}
		else time--;
		x+=xm;
		polygon.translate(xm, 0);
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
		life-=damage;
	}


	@Override
	public boolean isdead() {
		return life<0;
	}

}