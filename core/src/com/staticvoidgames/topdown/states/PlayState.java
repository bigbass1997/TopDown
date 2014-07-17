package com.staticvoidgames.topdown.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.staticvoidgames.topdown.game.Entity;
import com.staticvoidgames.topdown.game.Gamelogic;
import com.staticvoidgames.topdown.managers.GameStateManager;

public class PlayState extends GameState{

	private static final float TIMESTEP = 0.01f;
	private Gamelogic gamelogic;
	private float remaining=0;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		
	}
	public void setGameLogic(Gamelogic gamelogic){
		this.gamelogic = gamelogic;
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		remaining+=delta;
		while (remaining>TIMESTEP) {
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
