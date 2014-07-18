package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.managers.TextureManager;
import com.staticvoidgames.topdown.states.PlayState;

public class PowerUp implements Entity{
	
	Polygon polygon;
	private float x, y;
	boolean taken;
	/**
	 * 0-->increase shot amount.
	 * 1-->increase speed.
	 * 2-->increase fire-rate.
	 */
	public int powerUpType;
	/**
	 * @param powerUpType
	 * 0-->increase shot amount.
	 * 1-->increase speed.
	 * 2-->increase fire-rate.
	 */
	public PowerUp(float x, float y,int powerUpType) {
		polygon= new Polygon(new float[]{
				-10,-10,
				-10,10,
				10,10,
				10,-10,
		});
		polygon.translate(x, y);
		PlayState.entities.add(this);
		this.powerUpType=powerUpType;
		this.x = x;
		this.y = y;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.powerupTexture, x-10, y-10, 10*2, 10*2);
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
		if(entity.getClass()==Player.class){
			Player player = (Player)entity;
			switch (powerUpType) {
			case 0:
				player.shotamount+=2.0f;
				break;
			case 1:
				player.speed+=0.5f;
				break;
			case 2:
				player.cooldown=player.cooldown/2+10;
				break;
			default:
				break;
			}
			taken=true;
		}
	}


	@Override
	public void hit(int damage) {
		
	}


	@Override
	public boolean isdead() {
		return y<0||taken;
	}


}
