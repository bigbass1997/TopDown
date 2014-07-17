package com.staticvoidgames.topdown.game;

import java.util.Vector;

public class Gamelogic{
	volatile Vector<Entity> entities= new Vector<Entity>();
	public Gamelogic() {
		
	}
	/**
	 * Multiple calls are necessary, depending on the time passed.
	 */
	void update(){
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < entities.size(); i++) {
			for (int j = i+1; j < entities.size(); j++) {
				colision(entities.get(i),entities.get(j));
			}
		}
	}
	private void colision(Entity e1, Entity e2) {
		Circle[] c1=e1.getCircles();
		Circle[] c2=e1.getCircles();
		for (int i = 0; i < c1.length; i++) {
			for (int j = 0; j < c2.length; j++) {
				if(Math.abs(c1[i].x-c2[j].x)<c1[i].radius+c2[j].radius&&Math.abs(c1[i].y-c2[j].y)<c1[i].radius+c2[j].radius&&Math.hypot(c1[i].x-c2[j].x, c1[i].y-c2[j].y)<c1[i].radius+c2[j].radius){
					//do collision
				}
			}
		}
	}
}
