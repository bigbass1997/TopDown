package com.staticvoidgames.topdown.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.managers.TextureManager;
import com.staticvoidgames.topdown.states.PlayState;

/**
 * Someone had to do this
 * @author Gaspard__
 *
 */
public class Player implements Entity{

	public float shotamount=1;
	Polygon polygon;
	float x;
	float y;
	private float xm;
	private float ym;
	
	private int timer = 10;
	int cooldown = 1000;
	
	float speed = 0.5f;
	public int life;
	
	public Player(float x, float y) {
		life=600;
		polygon= new Polygon(new float[]{
				0,9,
				-8,-6,
				8,-6,
		});
		polygon.translate(x, y);
		PlayState.entities.add(this);
		this.x = x;
		this.y = y;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.playerTexture, x-9, y-10, 19, 10*2);
	}


	@Override
	public void update() {
		timer--;
		if(speed>0.5f)speed-=0.0005f;
		if(cooldown<1000)cooldown++;
		if(shotamount>1.002f)shotamount-=0.0005f;
		if(timer==0){
			int s=(int) Math.floor(shotamount);
			if(s%2==0){
					for (int i = 0; i < s/2; i++) {
						new Shot(-i*10+x-5, y+17, 0, 1-i/10f);
						new Shot(+i*10+x+5, y+17, 0, 1-i/10f);
					}
			}
			else{
				new Shot(x, y+17, 0, 1.1f);
				for (int i = 0; i < (s-1)/2; i++) {
					new Shot(-i*10+x-10, y+17, 0, 1-i/10f);
					new Shot(+i*10+x+10, y+17, 0, 1-i/10f);
				}
			}
			timer+=cooldown/10;
		}
		
		x+=xm;
		y+=ym;
		polygon.translate(xm, ym);
		
		Input input = Gdx.input;
		if(input.isKeyPressed(Keys.LEFT) || input.isKeyPressed(Keys.A)){
			xm = -speed;
		}else if(input.isKeyPressed(Keys.RIGHT) || input.isKeyPressed(Keys.D)){
			xm = speed;
		}else{
			xm = 0.0f;
		}
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
		life-=damage;
	}


	@Override
	public boolean isdead() {
		return false;
	}
}
