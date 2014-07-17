package com.staticvoidgames.topdown.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.staticvoidgames.topdown.states.GameState;
import com.staticvoidgames.topdown.states.MenuState;
import com.staticvoidgames.topdown.states.PlayState;

public class GameStateManager {
	
	private GameState gameState;
	
	public int currentState;
	public final int MENUSTATE = 0;
	public final int PLAYSTATE = 1;
	
	private MenuState menuState;
	private PlayState playState;
	
	//ShapeRenderer from LibGDX handles that actual shape rendering in DrawManager.
	public ShapeRenderer sr;
	
	//DrawManager is used for drawing Text and simple shapes to the screen.
	public DrawManager dm;
	
	//FontManager is used to store and INIT fonts to be used when drawing text.
	public FontManager fm;
	
	public GameStateManager(SpriteBatch batch){
		//INIT of Managers
		sr = new ShapeRenderer();
		fm = new FontManager();
		dm = new DrawManager(sr, batch); //First notice of the passing of managers to other managers. DrawManager requires ShapeRender for rendering shapes.
		
		//Game State INITs for all gameStates
		menuState = new MenuState(this);
		playState = new PlayState(this);
		
		//Sets the first state to be used when game starts up.
		setState(PLAYSTATE);
	}
	
	public void update(float delta){
		//Updates current active gameState.
		gameState.update(delta);
	}
	
	public void draw(SpriteBatch batch){
		gameState.draw(batch);
	}
	
	public void setState(int state){
		//Function used to switch to a different state.
		switch(state){
		case MENUSTATE:
			gameState = menuState;
			currentState = state;
			break;
		case PLAYSTATE:
			gameState = playState;
			currentState = state;
			break;
		default:
			System.err.println("INPUTTED STATE NOT VALID!");
			System.exit(2);
			break;
		}
	}
	
	//Gets the current gameState. NOT the state itself but the INT representation of that state.
	public int getCurrentState(){ return currentState;}
	
	//Gets the current gameState. Returns the ACTUAL GAMESTATE.
	public GameState getGameState(){ return gameState;}
}
