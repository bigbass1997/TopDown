package com.staticvoidgames.topdown.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.GraphicsMain;
import com.staticvoidgames.topdown.managers.TextureManager;
import com.staticvoidgames.topdown.states.PlayState;

public class LShot implements Entity {
		Polygon polygon;
		float speed;
		float targetx;
		float x;
		float y;
		private boolean dead;
		private boolean up;
		
		public LShot(float x, float y,float targetx,float speed,boolean up) {
			polygon= new Polygon(new float[]{
					-2.5f,10f,
					2.5f,10f,
					2.5f,-10f,
					-2.5f,-10f,
			});
			PlayState.entities.add(this);
			polygon.translate(x, y);
			this.x=x;
			this.y=y;
			this.targetx=targetx;
			this.speed=speed;
			this.up=up;
		}


		@Override
		public void render(SpriteBatch batch) {
			batch.draw(TextureManager.shotTexture, x-2.5f, y-10f, 2.5f*2, 10f*2);
		}


		@Override
		public void update() {
			if(x!=targetx){
				if(Math.abs(targetx-x)<speed){
					polygon.translate(targetx-x, 0);
					x=targetx;
				}
				else{
					polygon.translate(Math.signum(targetx-x)*speed, 0);
					x+=Math.signum(targetx-x)*speed;
					
				}
			}
			else{
				if(up){
					y+=speed;
					polygon.translate(0, speed);
				}
				else{
					y-=speed;
					polygon.translate(0, -speed);
				}
				
			
			}
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
