package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.managers.TextureManager;
import com.staticvoidgames.topdown.states.PlayState;

public class Rock implements Entity{
	Polygon polygon;
	private float x, y;
	private int life;
	private float xm;
	private float ym;
	
	public Rock(float x, float y,float xm,float ym) {
		life=100;
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
		this.xm = xm;
		this.ym = ym;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.rockTexture, x-20, y-20, 20*2, 20*2);
	}


	@Override
	public void update() {
		x+=xm;
		y+=ym;
		polygon.translate(xm, ym);
		if(x<0)xm*=-1;
		if(x+50>GraphicsMain.SIZE)xm*=-1;
	}

	@Override
	public Polygon[] getPolygons() {
		return new Polygon[]{polygon};
	}

	@Override
	public void collide(Entity entity) {
		entity.hit(1);
	}


	@Override
	public void hit(int damage) {
		xm*=-1;
		life-=damage;
		if(life<0)new PowerUp(x, y, ((int) y)%3);
	}


	@Override
	public boolean isdead() {
		return life<0||y>GraphicsMain.SIZE+50;
	}

}
