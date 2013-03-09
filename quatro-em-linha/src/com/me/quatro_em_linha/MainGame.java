package com.me.quatro_em_linha;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainGame extends Game {
	public OrthographicCamera camera;
	public static SpriteBatch batch;
	public static ShapeRenderer renderer;
	float ratio;
	
	public static Sprite draw, pl1w, pl2w, tabela_spr,mn_back;
	
	Tabela jogo_actual;
	Menu mainmenu;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		float w = Gdx.graphics.getWidth();
	    float h = Gdx.graphics.getHeight();
	    ratio = h/w;
	    camera = new OrthographicCamera(1, ratio);
	    batch = new SpriteBatch();
	    renderer = new ShapeRenderer();
	    
	    this.loadAssets();
		mainmenu = new Menu(this,mn_back);
		
		this.setScreen(this.mainmenu);
	}
	
	private void loadAssets(){
		mn_back = new Sprite(new Texture(Gdx.files.internal("data/flash_screen.png")));
	    mn_back.setSize(1, 0.665f *mn_back.getHeight() /mn_back.getWidth());
	    mn_back.setOrigin(mn_back.getWidth()/2,mn_back.getHeight()/2);
	    mn_back.setPosition(-mn_back.getWidth()/2, -mn_back.getHeight()/2);
	    mn_back.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	    
		draw = new Sprite(new Texture(Gdx.files.internal("data/draw.png")));
	    draw.setSize(0.2f, 0.2f * draw.getHeight() / draw.getWidth());
	    draw.setOrigin(draw.getWidth()/2, draw.getHeight()/2);
	    draw.setPosition(-draw.getWidth()/2, draw.getHeight()/2);
	    draw.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	    
	    pl1w = new Sprite(new Texture(Gdx.files.internal("data/pl1w.png")));
	    pl1w.setSize(0.5f, 0.5f * pl1w.getHeight() / pl1w.getWidth());
	    pl1w.setOrigin(pl1w.getWidth()/2, pl1w.getHeight()/2);
	    pl1w.setPosition(-pl1w.getWidth()/2, -pl1w.getHeight()/2);
	    pl1w.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	    
	    pl2w = new Sprite(new Texture(Gdx.files.internal("data/pl2w.png")));
	    pl2w.setSize(0.5f, 0.5f *pl2w.getHeight() /pl2w.getWidth());
	    pl2w.setOrigin(pl2w.getWidth()/2,pl2w.getHeight()/2);
	    pl2w.setPosition(-pl2w.getWidth()/2, -pl2w.getHeight()/2);
	    pl2w.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	    
	    tabela_spr = new Sprite(new Texture(Gdx.files.internal("data/table.png")));
	    tabela_spr.setSize(1, 0.665f *tabela_spr.getHeight() /tabela_spr.getWidth());
	    tabela_spr.setOrigin(tabela_spr.getWidth()/2,tabela_spr.getHeight()/2);
	    tabela_spr.setPosition(-tabela_spr.getWidth()/2, -tabela_spr.getHeight()/2);
	    tabela_spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	    
	    
	}
	
	public Tabela create_new_game(){
		jogo_actual=null;
		jogo_actual = new Tabela(this);
		return jogo_actual;
	}
	
}