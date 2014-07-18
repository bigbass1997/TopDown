package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.managers.TextureManager;
import com.staticvoidgames.topdown.states.PlayState;

public class Rock implements Entity{
	Polygon polygon;
	private float x, y;
	private int life;
	
	public Rock(float x, float y) {
		life=10;
		polygon= new Polygon(new float[]{
				-20,-4,
				-6,-20,
				14,-13,
				19,4,
				5,19,
				-15,18,
		});
		polygon.translate(x, y);
		PlayState.entities.add(this);
		this.x = x;
		this.y = y;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.rockTexture, x-20, y-20, 20*2, 20*2);
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
	public void collide(Entity entity) {
		
	}


	@Override
	public void hit(int damage) {
		life-=damage;
		if(life<0)new PowerUp(x, y, ((int) y)%3);
	}


	@Override
	public boolean isdead() {
		return life<0||y<-50;
	}

}
