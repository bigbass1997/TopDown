package com.staticvoidgames.topdown.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.staticvoidgames.topdown.GraphicsMain;

public class DrawManager {
	
	private SpriteBatch batch;
	private ShapeRenderer sr;
	
	public DrawManager(ShapeRenderer sr, SpriteBatch batch){
		this.sr = sr;
		batch = GraphicsMain.batch;
	}
	
	//Draws a rectangle
	public void Rect(float x, float y, float width, float height, int color, ShapeType type){
		batch.end();
		batch.begin();
		
		sr.setColor(new Color(color));
		sr.begin(type);
		sr.rect(x, y, width, height);
		sr.end();

		batch.end();
		batch.begin();
	}
	
	//Draws a Polygon based on its vertices. (at least 3 vertices must be passed in)
	public void Polygon(float[] vertices, int color, ShapeType type){
		batch.end();
		batch.begin();
		
		sr.setColor(new Color(color));
		sr.begin(type);
		sr.polygon(vertices);
		sr.end();
		
		batch.end();
		batch.begin();
	}
	
	//Draws text. Must pass in a font from the FontManager. (e.g. dm.String("", 0, 0, fm.FONTVARIABLE, 0xAARRGGBB);)
	public void String(String s, float x, float y, BitmapFont font, int color){
		batch.end();
		batch.begin();
		
		font.setColor(Color.WHITE); //Resets color
		font.setColor(new Color(color));
		font.draw(batch, s, x, y);

		batch.end();
		batch.begin();
	}
}
