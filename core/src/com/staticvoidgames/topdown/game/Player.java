package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.managers.TextureManager;
import com.staticvoidgames.topdown.states.PlayState;

/**
 * Someone had to do this
 * @author Gaspard__
 *
 */
public class Player implements Entity{
	Polygon polygon;
	float xm;
	float ym;
	float x;
	float y;
	private int timer=10;
	private int cooldown=100;
	
	public Player(float x, float y) {
		polygon= new Polygon(new float[]{
				-10,10,
				10,10,
				10,-10,
				-10,-10,
		});
		polygon.translate(x, y);
		PlayState.entities.add(this);
		this.x=x;
		this.y=y;
		xm=0.1f;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.playerTexture, x-10, y-10, 10*2, 10*2);
	}


	@Override
	public void update() {
		timer--;
		if(timer==0){
			timer+=cooldown;
			new Shot(x, y+17, 0, 3);
		}
		
		x+=xm;
		y+=ym;
		polygon.translate(xm, ym);
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
		return false;
	}
}
