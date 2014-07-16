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
		
	}
}
