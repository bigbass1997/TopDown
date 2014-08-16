package com.staticvoidgames.topdown.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
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
	private boolean dead;
	
	public Shot(float x, float y,float xm,float ym) {
		polygon= new Polygon(new float[]{
				-2.5f,10f,
				2.5f,10f,
				2.5f,-10f,
				-2.5f,-10f,
		});
		PlayState.entities.add(this);
		polygon.rotate(MathUtils.atan2(ym, xm)*MathUtils.radiansToDegrees-90);
		polygon.translate(x, y);
		this.x=x;
		this.y=y;
		this.xm=xm;
		this.ym=ym;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.draw(new TextureRegion(TextureManager.shotTexture), x-2.5f, y-10f,2.5f,10f, 2.5f*2, 10f*2,1,1,MathUtils.atan2(ym, xm)*MathUtils.radiansToDegrees-90);
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
		entity.hit(2);
		dead=true;
	}


	@Override
	public void hit(int damage) {
		
	}


	@Override
	public boolean isdead() {
		return dead||y>GraphicsMain.SIZE||y<0;
	}
	
}
