package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.managers.TextureManager;
import com.staticvoidgames.topdown.states.PlayState;

public class Shot implements Entity {
	Polygon polygon;
	float xm;
	float ym;
	float x;
	float y;
	
	public Shot(float x, float y,float xm,float ym) {
		polygon= new Polygon(new float[]{
				-1,1,
				1,1,
				1,-1,
				-1,-1,
		});
		PlayState.entities.add(this);
		polygon.translate(x, y);
		this.x=x;
		this.y=y;
		this.xm=xm;
		this.ym=ym;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.shotTexture, x-2.5f, y-2.5f, 2.5f*2, 2.5f*2);
	}


	@Override
	public void update() {
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
		return y>GraphicsMain.SIZE;
	}
	
}
