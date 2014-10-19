package com.staticvoidgames.topdown.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.states.PlayState;

public class ThirdBoss implements Entity {
	private int cooldown = 20;
	int strength=0;
	Polygon polygon;
	final int size=10;
	private float x;
	private float y;
	private int time;
	private int megaAttack;
	private int megaCooldown=1000;
	private int life;
	boolean goright=true;
	private float ym;
	public ThirdBoss() {
		x=200;
		y=500;
		strength=1;
		life=450;
		time=cooldown;
		megaAttack=megaCooldown;
		polygon= new Polygon(new float[]{
				-size,-size,
				-size,size,
				size,size,
				size,-size,
		});
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
		if(megaAttack<-500){
			megaAttack=megaCooldown;
			ym=0;
		}
		if (megaAttack<500) {
			if(megaAttack%30==1){
				for (int i = 0; i < 16; i++) {
					new LShot(x+(i-10)*10, y-(size+12), i*25+(megaAttack/16), 6 , false);
				}
			}
			ym=0;
		}
		else{
			if(time<0){
				if(y<300){
					ym=0.2f;
				}
				else if(y>350){
					ym=-0.2f;
				}
				if(Math.abs(PlayState.player.x-x)>100){
					new LShot(x, y+(size+11), PlayState.player.x, 1 , false);
					new LShot(x, y-(size+12), PlayState.player.x, 2 , false);
				}
				else{
					new Shot(x+5, y-(size+11), 0, -1.2f);
					new Shot(x-5, y-(size+11), 0, -1.2f);
				}
				time=cooldown+250/strength;
				strength++;
			}
			else time--;
		}
		megaAttack--;
		y+=ym;
		polygon.translate(0, ym);
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