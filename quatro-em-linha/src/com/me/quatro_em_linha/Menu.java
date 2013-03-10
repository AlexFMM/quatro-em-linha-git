package com.me.quatro_em_linha;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Menu implements Screen{

	Button btns[];
	Sprite img;
	MainGame maingame;
	Vector3 mpos = new Vector3(0,0,0);
	
	public Menu(MainGame mg, Sprite bg)
	{
		this.img = bg;
		this.maingame = mg;
		btns  = new Button[2];
		
		//load das regions - packs de imagens - checa a assets folder
		Texture btn_play = new Texture(Gdx.files.internal("data/btn_play.png"));
		btn_play.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture btn_close = new Texture(Gdx.files.internal("data/btn_close.png"));
		btn_close.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		btns[0] = new Button(btn_play,"Play",new Vector2(-0.25f,-0.2f),new Vector2(0.2f,0.2f),new Vector2(0,0),maingame);
		btns[1] = new Button(btn_close,"Close",new Vector2(0.05f,-0.2f),new Vector2(0.2f,0.2f),new Vector2(0,0),maingame);

	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		Gdx.gl.glClearColor(1, 0.8f, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mpos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		maingame.camera.unproject(mpos);
		
		if (Gdx.input.justTouched()) {
			Gdx.app.log("posx", " " + mpos.x);
			Gdx.app.log("posy", " " + mpos.y);
		}
		
		
		MainGame.batch.begin();
		this.img.draw(MainGame.batch);
		btns[0].img.draw(MainGame.batch);
		btns[1].img.draw(MainGame.batch);
		
		MainGame.batch.end();
		
		btns[0].update(mpos.x, mpos.y);
		btns[1].update(mpos.x, mpos.y);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		btns[0].screen = maingame.create_new_game();
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
		maingame.dispose();
	}

}
