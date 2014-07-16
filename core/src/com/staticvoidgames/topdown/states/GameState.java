package com.staticvoidgames.topdown.states;

import com.staticvoidgames.topdown.managers.GameStateManager;

public abstract class GameState {
	
	public GameStateManager gsm;
	
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public abstract void init();
	public abstract void update(float delta);
	public abstract void draw();
	public abstract void dispose();
}
