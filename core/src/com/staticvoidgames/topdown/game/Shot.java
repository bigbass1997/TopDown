package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.staticvoidgames.topdown.managers.TextureManager;
import com.staticvoidgames.topdown.states.PlayState;

public class Shot implements Entity {
	Circle circle;
	private float xm;
	private float ym;
	public Shot(float x, float y, float xm, float ym) {
		this.xm=xm;
		this.ym=ym;
		circle= new Circle(x, y, 3);
		PlayState.entities.add(this);
		
	}
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.shotTexture, circle.x-circle.radius, circle.y-circle.radius, circle.radius*2, circle.radius*2);
	}

	@Override
	public void update() {
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
	
}
