package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.staticvoidgames.topdown.managers.TextureManager;

/**
 * Someone had to do this
 * @author Gaspard__
 *
 */
public class Player implements Entity{
	Circle PlayerCircle;
	float xm;
	float ym;
	
	public Player(float x, float y) {
		PlayerCircle= new Circle(x,y,10);
		xm=0.1f;
		ym=0.1f;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.playerTexture, PlayerCircle.x-PlayerCircle.radius, PlayerCircle.y-PlayerCircle.radius, PlayerCircle.radius*2, PlayerCircle.radius*2);
	}


	@Override
	public void update() {
		PlayerCircle.x+=xm;
		PlayerCircle.y+=ym;
	}

	@Override
	public Circle[] getCircles() {
		return new Circle[]{PlayerCircle};
	}

	@Override
	public void collide(double angle1to2, Entity entity) {
		
	}


	@Override
	public void hit(int damage) {
		
	}
}
