package com.staticvoidgames.topdown.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.states.PlayState;

public class SecondBoss implements Entity {
	private int cooldown = 25;
	int strength=0;
	Polygon polygon;
	final int size=10;
	private float x;
	private float y;
	private int time;
	private float xm;
	private int life;
	boolean goright=true;
	public SecondBoss(float x, float y) {
		strength=1;
		life=45;
		time=cooldown;
		polygon= new Polygon(new float[]{
				-size,-size,
				-size,size,
				size,size,
				size,-size,
		});
		this.x=x;
		this.y=y;
		polygon.translate(x, y);
		PlayState.entities.add(this);
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.end();
		GraphicsMain.shaperenderer.setColor(Color.LIGHT_GRAY);
		GraphicsMain.shaperenderer.begin(ShapeType.Filled);
		GraphicsMain.shaperenderer.rect(x-size, y-size, size*2, size*2);
		GraphicsMain.shaperenderer.end();
		batch.begin();
	}


	@Override
	public void update() {
		if(time<0){
			if(goright){
				xm=0.2f;
				if(x>300)goright=false;
			}
			else{
				xm=-0.2f;
				if(x<100)goright=true;
			}
			if(Math.abs(PlayState.player.x-x)>100){
				new LShot(x, y+(size+11), PlayState.player.x, 2 , false);
				new LShot(x, y-(size+11), PlayState.player.x, 2 , false);
			}
			else{
				new Shot(x+5, y-(size+11), 0, -1.2f);
				new Shot(x-5, y-(size+11), 0, -1.2f);
			}
			time=cooldown+500/strength;
			strength++;
		}
		else time--;
		x+=xm;
		polygon.translate(xm, 0);
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
		life-=damage;
	}


	@Override
	public boolean isdead() {
		return life<0;
	}

}