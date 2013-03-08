package com.me.quatro_em_linha;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Tabela implements Screen {

	Ficha tab[][];
	MainGame maingame;
	int player_actual;
	float w, h;
	
	Tabela(MainGame n){
		tab = new Ficha[6][7];
		maingame = n;
		player_actual=0;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 0.8f, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		
		for(int i=1; i<7; i++){
			MainGame.renderer.begin(ShapeType.Line);
			MainGame.renderer.line(i*w/7, 0, i*w/7, h);
			MainGame.renderer.end();
		}
		
		for(int i=1; i<6; i++){
			MainGame.renderer.begin(ShapeType.Line);
			MainGame.renderer.line(0, i*h/6, w, i*h/6);
			MainGame.renderer.end();
		}
		
	}
	
	void change_player(){
		if(player_actual==0){
			player_actual=1;
		}
		else{
			player_actual=0;
		}
	}
	
	void nova_ficha(int x){
		for(int i=5;i>0;i--){
			if(tab[i][x]==null){
				tab[i][x]= new Ficha(player_actual);
				change_player();
				break;
			}
		}
	}
	
	boolean check_win(){
		return false;
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
