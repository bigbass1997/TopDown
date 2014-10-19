package com.staticvoidgames.topdown.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.states.PlayState;

public class FirstBoss implements Entity {
	private int cooldown = 50;
	int strength=0;
	Polygon polygon;
	boolean activated;
	final int size=10;
	private float x;
	private float tx;
	private float y;
	private int time;
	private float xm;
	private int life;
	boolean goright=true;
	private boolean dead;
	public FirstBoss(float x, float y) {
		strength=1;
		life=250;
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
				xm=0.6f;
				if(x>tx)goright=false;
			}
			else{
				xm=-0.6f;
				if(x<tx)goright=true;
			}
			tx = PlayState.player.x;
			new Shot(x, y-(size+11), 0, -3f);
			time=cooldown+200/strength;
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
		if(!dead&&life<0){
			dead=true;
			for (int i = 0; i < 10; i++) {
				new PowerUp(x, y, (i-5)/10f, -i/10f, i%2);
			}
		}
	}


	@Override
	public boolean isdead() {
		return dead;
	}

}