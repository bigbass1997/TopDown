package com.staticvoidgames.topdown.states;

import java.util.Vector;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.staticvoidgames.topdown.game.Entity;
import com.staticvoidgames.topdown.game.Obstacle;
import com.staticvoidgames.topdown.game.Player;
import com.staticvoidgames.topdown.game.PowerUp;
import com.staticvoidgames.topdown.game.Rock;
import com.staticvoidgames.topdown.game.Switch;
import com.staticvoidgames.topdown.managers.GameStateManager;

public class PlayState extends GameState{
	public static boolean[] Active=new boolean[3];
	private static final float TIMESTEP = 0.005f;
	private float remaining=0;
	
	

	public PlayState(GameStateManager gsm) {
		super(gsm);
		new Player(100, 100);
	}
	@Override
	public void init() {
		seed= (int) (Math.random()*10000);
		time=0;
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
	public static Color[] colors= new Color[]{
		new Color(1, 0, 0, 1),new Color(0, 1, 0, 1),new Color(1, 0, 1, 1),
	};
	int time;
	private int seed;
	/**
	 * Multiple calls are necessary, depending on the time passed.
	 */
	public void tick(){
		time++;
		if(time%199==0)new Obstacle((time*time*(seed-time))%600, 600, 200, 50, time%3, time%2==0);
		if(time%419==30)new PowerUp((time*time*(seed-time))%600, 600,time%3);
		if(time%799==10)new Rock((time*time*(seed-time-10))%600, 650);
		if(time%611==10)new Switch((100*time&seed)%600, 600, time%3);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < entities.size(); i++) {
			for (int j = i+1; j < entities.size(); j++) {
				if(colision(entities.get(i),entities.get(j))){
					System.out.println(i+":"+j);
					entities.get(i).collide(entities.get(j));
					entities.get(j).collide(entities.get(i));
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
	private boolean colision(Entity e1, Entity e2) {
		Polygon[] c1=e1.getPolygons();
		Polygon[] c2=e2.getPolygons();
		for (int i = 0; i < c1.length; i++) {
			for (int j = 0; j < c2.length; j++) {
				if(Intersector.overlapConvexPolygons(c1[i], c2[j])){
					System.out.println("hit");
					return true;
				}
			}
		}
		return false;
	}

}
