package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.staticvoidgames.topdown.managers.TextureManager;
import com.staticvoidgames.topdown.states.PlayState;

/**
 * Someone had to do this
 * @author Gaspard__
 *
 */
public class Player implements Entity{
	Circle circle;
	float xm;
	float ym;
	private int timer=10;
	private int cooldown=100;
	
	public Player(float x, float y) {
		circle= new Circle(x,y,10);
		PlayState.entities.add(this);
		xm=0.1f;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.playerTexture, circle.x-circle.radius, circle.y-circle.radius, circle.radius*2, circle.radius*2);
	}


	@Override
	public void update() {
		timer--;
		if(timer==0){
			timer+=cooldown;
			new Shot(circle.x, circle.y+17, 0, 3);
		}
		circle.x+=xm;
		circle.y+=ym;
	}

	@Override
	public Circle[] getCircles() {
		return new Circle[]{circle};
	}

	@Override
	public void collide(double angle1to2, Entity entity) {
		
	}


	@Override
	public void hit(int damage) {
		
	}


	@Override
	public boolean isdead() {
		return false;
	}
}
