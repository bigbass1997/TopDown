package com.staticvoidgames.topdown;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.staticvoidgames.topdown.managers.GameStateManager;

public class GraphicsMain extends ApplicationAdapter {
	
	//Camera. BOTTOM LEFT corner of screen is origin 0,0. Up = +y. Right = +x
	//(X,Y) Position of all objects originates in their BOTTOM LEFT corner as well
	public OrthographicCamera camera;
	public static SpriteBatch batch;
	
	//GameStateManager aka GSM. Manager to manage other managers and gameStates.
	private GameStateManager gsm;
	
	//Static floats to easily keep track of width and height of game screen.
	public static float sWidth;
	public static float sHeight;
	
	@Override
	public void create () {
		//INITs width and height vars
		sWidth = Gdx.graphics.getWidth();
		sHeight = Gdx.graphics.getHeight();
		
		//Sets up camera based on those values.
		camera = new OrthographicCamera();
		camera.setToOrtho(false, sWidth/sHeight, 1);
		
		//INIT batch
		batch = new SpriteBatch();
		
		//INIT GSM
		gsm = new GameStateManager();
	}

	@Override
	public void render () {
		//Main Game Loop
		//Although called RENDER in LibGDX both the update and actual rendering takes place here.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		
		//GSM Update and Draw(render) methods
		gsm.update(Gdx.graphics.getDeltaTime());
		batch.begin();
		gsm.draw(batch);
		batch.end();
	}
}
