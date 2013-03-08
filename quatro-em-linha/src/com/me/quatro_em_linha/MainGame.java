package com.me.quatro_em_linha;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainGame extends Game {
	public OrthographicCamera camera;
	public static SpriteBatch batch;
	public static ShapeRenderer renderer;
	float ratio;
	
	//public static Sprite draw;
	
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
	    
	    //this.loadAssets();
	    
		jogo_actual= new Tabela(this);
		this.setScreen(jogo_actual);
	}
	
	/*public void loadAssets(){
		draw = new Sprite(new Texture(Gdx.files.internal("data/draw.png")));
	    draw.setSize(0.2f, 0.2f * draw.getHeight() / draw.getWidth());
	    draw.setOrigin(draw.getWidth()/2, draw.getHeight()/2);
	    draw.setPosition(-draw.getWidth()/2, draw.getHeight()/2);
	}*/
	
	public void restartGame(){
		jogo_actual=null;
		jogo_actual = new Tabela(this);
		setScreen(jogo_actual);
	}
	
}