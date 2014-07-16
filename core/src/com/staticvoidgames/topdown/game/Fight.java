package com.staticvoidgames.topdown.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.staticvoidgames.topdown.managers.GameStateManager;
import com.staticvoidgames.topdown.states.GameState;

public class Fight extends GameState{

	private static final float TIMESTEP = 0.01f;
	private Gamelogic gamelogic;
	private int remaining;

	public Fight(GameStateManager gsm,Gamelogic gamelogic) {
		super(gsm);
		this.gamelogic=gamelogic;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		while (remaining>0) {
			remaining-= TIMESTEP;
			gamelogic.update();
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		Entity[] torender=new Entity[gamelogic.entities.size()];
		gamelogic.entities.toArray(torender);
		for (int i = 0; i < torender.length; i++) {
			torender[i].render(batch);
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
