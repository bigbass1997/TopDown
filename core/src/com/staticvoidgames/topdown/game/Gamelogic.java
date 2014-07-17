package com.staticvoidgames.topdown.game;

import java.util.Vector;

public class Gamelogic{
	public volatile static Vector<Entity> entities= new Vector<Entity>();
	
	public Gamelogic() {
		new Player(300, 300);
		
	}
	/**
	 * Multiple calls are necessary, depending on the time passed.
	 */
	public void update(){
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
