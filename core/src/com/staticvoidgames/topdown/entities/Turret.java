package com.staticvoidgames.topdown.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.states.PlayState;

public class Turret implements Entity {
	private int cooldown = 20;
	boolean activated;
	int color;
	private boolean ReactTo;
	final int size=10;
	private float x;
	private float y;
	private int time;
	public Turret(float x, float y, int color,boolean Reacto) {
		time=cooldown;
		this.ReactTo=Reacto;
		this.x=x;
		this.y=y;
		this.color=color;
		PlayState.entities.add(this);
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.end();
		GraphicsMain.shaperenderer.setColor(PlayState.getcolor(color));
		if(PlayState.Active[color]==ReactTo)GraphicsMain.shaperenderer.begin(ShapeType.Filled);
		else GraphicsMain.shaperenderer.begin(ShapeType.Line);
		GraphicsMain.shaperenderer.rect(x-size, y-size, size*2, size*2);
		GraphicsMain.shaperenderer.end();
		GraphicsMain.shaperenderer.setColor(Color.WHITE);
		if(0<-(PlayState.player.y-y))GraphicsMain.shaperenderer.begin(ShapeType.Filled);
		else GraphicsMain.shaperenderer.begin(ShapeType.Line);
		GraphicsMain.shaperenderer.circle(x, y, size);
		GraphicsMain.shaperenderer.end();
		batch.begin();
	}


	@Override
	public void update() {
		if(ReactTo==PlayState.Active[color]){
			if(time==0){
				float Dx= PlayState.player.x-x;
				float Dy= PlayState.player.y-y;
				float d=(float) Math.hypot(Dx, Dy);
				Dx/=d;
				Dy/=d;
				if(Dy<0)new Shot(x, y-(size+10), Dx*4, Dy*4);
				time=cooldown;
			}
			else time--;
		}
		y-=PlayState.ScrollSpeed;
	}

	@Override
	public Polygon[] getPolygons() {
		return new Polygon[]{};
	}

	@Override
	public void collide( Entity entity) {
		
	}


	@Override
	public void hit(int damage) {
		
	}


	@Override
	public boolean isdead() {
		return y+size<0;
	}

}