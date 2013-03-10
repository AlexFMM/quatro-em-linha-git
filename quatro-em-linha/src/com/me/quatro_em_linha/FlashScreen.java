package com.me.quatro_em_linha;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class FlashScreen implements Screen
{
	int time;
	int timer = 0;

	MainGame main;

	boolean up = true;
	
	float alpha = 0.0f;
	Sprite image;
	Screen nextScreen;

	public FlashScreen(MainGame main, Sprite flashImage, Screen next, int timeOn)
	{
		this.main = main;
		image = flashImage;
		nextScreen = next;
		this.time = timeOn;
	}

	
	
	private void animateBanner()
	{
		if(up)
		{
			
			if(alpha >= 0.99f)
			{
				alpha = 1.0f;
				if(timer >=time)
				{
					up = false;
				}
				else timer++;
			}
			else
			{
				alpha += 0.01f;
			}
		}
		else
		{
			alpha -= 0.01f;
			if(alpha <= 0.01f)
			{
				this.main.setScreen(nextScreen);
			}
		}
		
		image.draw(MainGame.batch,alpha);
		
	
	}
	
	@Override
	public void render(float delta) 
	{
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched()) main.setScreen(this.nextScreen);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		MainGame.batch.begin();
		this.animateBanner();
		MainGame.batch.end();
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}