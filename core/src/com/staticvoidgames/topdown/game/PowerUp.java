package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.managers.TextureManager;
import com.staticvoidgames.topdown.states.PlayState;

public class PowerUp implements Entity{
	
	Polygon polygon;
	private float x, y;
	boolean taken;
	public ShotType shottype;
	
	public PowerUp(float x, float y) {
		polygon= new Polygon(new float[]{
				-10,-10,
				-10,10,
				10,10,
				10,-10,
		});
		polygon.translate(x, y);
		PlayState.entities.add(this);
		shottype=ShotType.DOUBLE;
		this.x = x;
		this.y = y;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.playerTexture, x-10, y-10, 10*2, 10*2);
	}


	@Override
	public void update() {
		y-=0.1f;
		polygon.translate(0, -0.1f);
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
		
	}


	@Override
	public boolean isdead() {
		return y<0||taken;
	}


}
