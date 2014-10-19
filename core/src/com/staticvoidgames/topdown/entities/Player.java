package com.staticvoidgames.topdown.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.managers.TextureManager;
import com.staticvoidgames.topdown.states.PlayState;

/**
 * Someone had to do this
 * @author Gaspard__
 *
 */
public class Player implements Entity{

	public int shotamount=1;
	Polygon polygon;
	float x;
	float y;
	private float xm;
	private float ym;
	
	private int timer = 10;
	int cooldown = 10;
	
	float speed = 0.2f;
	public int life;
	public int ammo;
	public int heal;
	final int DamageThreshold=100;
	
	public Player(float x, float y) {
		life=1000;
		ammo=200;
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
	public void update(){
		Input input = Gdx.input;
		timer--;
		if(timer<0){
			if(ammo>0&&input.isKeyPressed(Keys.SPACE)){
				int s= ammo/50;
				ammo-=s;
				float basespeed=3f;
				if(s%2==0){
						for (int i = 0; i < s/2; i++) {
							new Shot(-i*10+x-5, y+20, 0, basespeed-i/10f);
							new Shot(+i*10+x+5, y+20, 0, basespeed-i/10f);
						}
				}
				else{
					new Shot(x, y+20, 0, basespeed);
					for (int i = 0; i < (s-1)/2; i++) {
						new Shot(-i*10+x-10, y+20, 0, basespeed-i/10f);
						new Shot(+i*10+x+10, y+20, 0, basespeed-i/10f);
					}
				}
				timer=cooldown;
			}
		}
		
		x+=xm;
		y+=ym;
		polygon.translate(xm, ym);
		
		
		if(input.isKeyPressed(Keys.LEFT) || input.isKeyPressed(Keys.A)){
			xm += -speed;
		}else if(input.isKeyPressed(Keys.RIGHT) || input.isKeyPressed(Keys.D)){
			xm += speed;
		}
		if(input.isKeyPressed(Keys.UP) || input.isKeyPressed(Keys.W)){
			ym += speed;
		}else if(input.isKeyPressed(Keys.DOWN) || input.isKeyPressed(Keys.S)){
			ym += -speed;
		}
		if(Math.abs(GraphicsMain.SIZE/2-x)>GraphicsMain.SIZE/4)xm+=(GraphicsMain.SIZE/2-x)/2000f;
		ym+=(GraphicsMain.SIZE/4-y)/500f;
		xm*=0.95;
		ym*=0.95;
		if(heal>0){
			heal--;
			life++;
		}
	}
	public int losepowerups(){
		return 0;
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
		heal+=damage/2;
		life-=damage;
	}


	@Override
	public boolean isdead() {
		return false;
	}
}
