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

	ShotType shotType;
	Polygon polygon;
	private float x, y, xm, ym;
	
	private int timer = 10;
	private int cooldown = 100;
	
	private float speed = 1.0f;
	
	public Player(float x, float y) {
		shotType=ShotType.BASIC;
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
		if(timer==0){
			switch (shotType) {
			case BASIC:
				new Shot(x, y+17, 0, 3);
				break;
			case DOUBLE:
				new Shot(x-5, y+17, 0, 3);
				new Shot(x+5, y+17, 0, 3);
				break;
			case TRIPLE:
				new Shot(x-10, y+17, 0, 3);
				new Shot(x, y+17, 0, 3);
				new Shot(x+10, y+17, 0, 3);
				break;
			default:
				break;
			
			}
			timer+=cooldown;
			
			
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
		if(entity.getClass()==PowerUp.class){
			((PowerUp)entity).taken=true;
			this.shotType= ((PowerUp)entity).shottype;
		}
	}


	@Override
	public void hit(int damage) {
		
	}


	@Override
	public boolean isdead() {
		return false;
	}
}
