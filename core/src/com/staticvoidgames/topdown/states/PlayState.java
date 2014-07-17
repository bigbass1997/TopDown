package com.staticvoidgames.topdown.states;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.staticvoidgames.topdown.game.Circle;
import com.staticvoidgames.topdown.game.Entity;
import com.staticvoidgames.topdown.game.Player;
import com.staticvoidgames.topdown.managers.GameStateManager;

public class PlayState extends GameState{

	private static final float TIMESTEP = 0.01f;
	private float remaining=0;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		new Player(100, 100);
	}
	@Override
	public void init() {
		
	}

	@Override
	public void update(float delta) {
		remaining+=delta;
		while (remaining>TIMESTEP) {
			remaining-= TIMESTEP;
			tick();
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		Entity[] torender=new Entity[entities.size()];
		entities.toArray(torender);
		for (int i = 0; i < torender.length; i++) {
			torender[i].render(batch);
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	public volatile static Vector<Entity> entities= new Vector<Entity>();
	

	/**
	 * Multiple calls are necessary, depending on the time passed.
	 */
	public void tick(){
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < entities.size(); i++) {
			for (int j = i+1; j < entities.size(); j++) {
				if(colision(entities.get(i),entities.get(j))){
					entities.get(i).collide(angle1to2,entities.get(j));
					entities.get(j).collide(Math.PI+angle1to2,entities.get(i));
				}
			}
		}
		int i=0;
		while (i < entities.size()) {
			if(entities.get(i).isdead())entities.remove(i);
			else i++;
		}
		//System.out.println(entities.size());
	}
	private double angle1to2;
	private boolean colision(Entity e1, Entity e2) {
		Circle[] c1=e1.getCircles();
		Circle[] c2=e1.getCircles();
		for (int i = 0; i < c1.length; i++) {
			for (int j = 0; j < c2.length; j++) {
				if(Math.abs(c1[i].x-c2[j].x)<c1[i].radius+c2[j].radius&&Math.abs(c1[i].y-c2[j].y)<c1[i].radius+c2[j].radius&&Math.hypot(c1[i].x-c2[j].x, c1[i].y-c2[j].y)<c1[i].radius+c2[j].radius){
					angle1to2=Math.atan2((c1[i].y-c2[j].y),c1[i].x-c2[j].x);
					return true;
				}
			}
		}
		return false;
	}

}
