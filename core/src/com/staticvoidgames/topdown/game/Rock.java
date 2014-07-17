package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.managers.TextureManager;
import com.staticvoidgames.topdown.states.PlayState;

public class Rock implements Entity{
	int life=10;
	private Polygon polygon;
	private float y;
	private float x;
	public Rock(float x,float y) {
		polygon= new Polygon(new float[]{
				0,16,
				14,0,
				34,7,
				39,24,
				25,39,
				5,38
		});
		PlayState.entities.add(this);
		this.x=x;
		this.y=y;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.rockTexture, x-20, y-20, 20*2, 20*2);
	}


	@Override
	public void update() {
		y-=0.1f;
	}

	@Override
	public Polygon[] getPolygons() {
		polygon.setPosition(x, y);
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
		return false;
	}
}
