package com.me.quatro_em_linha;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainGame extends Game {
	public OrthographicCamera camera;
	public static SpriteBatch batch;
	public static ShapeRenderer renderer;
	float ratio;
	
	Tabela jogo_actual;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		float w = Gdx.graphics.getWidth();
	    float h = Gdx.graphics.getHeight();
	    ratio = h/w;
	    camera = new OrthographicCamera(1, ratio);
	    batch = new SpriteBatch();
	    renderer = new ShapeRenderer();
	    
		jogo_actual= new Tabela(this);
		this.setScreen(jogo_actual);
	}
	
}