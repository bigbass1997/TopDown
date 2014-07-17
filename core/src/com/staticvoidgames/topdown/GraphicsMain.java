package com.staticvoidgames.topdown;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.staticvoidgames.topdown.managers.GameStateManager;

public class GraphicsMain extends ApplicationAdapter {
	
	//Camera. BOTTOM LEFT corner of screen is origin 0,0. Up = +y. Right = +x
	//(X,Y) Position of all objects originates in their BOTTOM LEFT corner as well
	public OrthographicCamera camera;
	public static SpriteBatch batch;
	public static ShapeRenderer shaperenderer;
	
	//GameStateManager aka GSM. Manager to manage other managers and gameStates.
	private GameStateManager gsm;
	
	//Static floats to easily keep track of width and height of game screen.
	public static final float SIZE=600;
	public static float sWidth;
	public static float sHeight;
	
	@Override
	public void create () {
		//INITs width and height vars
		sWidth = Gdx.graphics.getWidth();
		sHeight = Gdx.graphics.getHeight();
		
		//Sets up camera based on those values.
		camera = new OrthographicCamera();
		camera.setToOrtho(false, sWidth*SIZE/sHeight, SIZE);
		camera.position.set(1/2f*SIZE, 1/2f*SIZE, camera.position.z);
		camera.update();
		//INIT batch
		batch = new SpriteBatch();
		shaperenderer= new ShapeRenderer();
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
		shaperenderer.setProjectionMatrix(camera.combined);
		shaperenderer.begin(ShapeType.Filled);
		shaperenderer.setColor(0, 0, 1, 1);
		if(sWidth>sHeight){
			shaperenderer.rect(SIZE,0,SIZE,SIZE);
			shaperenderer.rect(-SIZE,0,SIZE,SIZE);
		}
		else{
			shaperenderer.rect(0,SIZE,SIZE,SIZE);
			shaperenderer.rect(0,-SIZE,SIZE,SIZE);
		}
		shaperenderer.end();
		
	}
	@Override
	public void resize(int width, int height) {
		//INITs width and height vars
		sWidth = Gdx.graphics.getWidth();
		sHeight = Gdx.graphics.getHeight();
		
		//Sets up camera based on those values.
		if(sWidth>sHeight){
			camera.setToOrtho(false, sWidth*SIZE/sHeight, SIZE);
			camera.position.set(1/2f*SIZE, 1/2f*SIZE, camera.position.z);
			camera.update();
		}
		else{
			camera.setToOrtho(false, SIZE, SIZE*sHeight/sWidth);
			camera.position.set(1/2f*SIZE, 1/2f*SIZE, camera.position.z);
			camera.update();
		}
		super.resize(width, height);
	}
}
