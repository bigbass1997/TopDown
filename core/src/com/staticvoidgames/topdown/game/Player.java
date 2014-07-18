package com.staticvoidgames.topdown.game;

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

	private static final float BASICSPEED = 1.0f;
	public int shotamount=100;
	Polygon polygon;
	private float x, y, xm, ym;
	
	private int timer = 10;
	int cooldown = 1000;
	
	float speed = 1.0f;
	
	public Player(float x, float y) {
		polygon= new Polygon(new float[]{
				-10,10,
				10,10,
				10,-10,
				-10,-10,
		});
		polygon.translate(x, y);
		PlayState.entities.add(this);
		this.x = x;
		this.y = y;
	}


	@Override
	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.playerTexture, x-10, y-10, 10*2, 10*2);
	}


	@Override
	public void update() {
		timer--;
		
		if(speed>1)speed-=0.001;
		if(cooldown<100)cooldown++;
		if(shotamount>100)shotamount--;
		speed=BASICSPEED;
		if(timer==0){
			if((shotamount/100)%2==0){
					for (int i = 0; i < (shotamount/100)/2; i++) {
						new Shot(-i*10+x-5, y+17, 0, 2+i/10f);
						new Shot(+i*10+x+5, y+17, 0, 2+i/10f);
					}
			}
			else{
				new Shot(x, y+17, 0, 2);
				for (int i = 0; i < (shotamount/100-1)/2; i++) {
					new Shot(-i*10+x-10, y+17, 0, 2+i/10f);
					new Shot(+i*10+x+10, y+17, 0, 2+i/10f);
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
		
	}


	@Override
	public void hit(int damage) {
		
	}


	@Override
	public boolean isdead() {
		return false;
	}
}
