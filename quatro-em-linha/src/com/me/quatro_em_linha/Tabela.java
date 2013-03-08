package com.me.quatro_em_linha;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Tabela implements Screen {

	Ficha tab[][];
	MainGame maingame;
	int player_actual;
	float w, h;
	Vector3 mpos = new Vector3(0, 0, 0);
	boolean end_game_W, end_game_F;
	int time=180;
	int timer=0;
	
	Tabela(MainGame n){
		tab = new Ficha[6][7];
		maingame = n;
		player_actual=0;
		end_game_W=false;
		end_game_F=false;
		make_null();
		
		
	}
	
	@Override
	public void render(float delta) {
		
		if (Gdx.input.justTouched()) {
			Gdx.app.log("posx", " " + Gdx.input.getX());
			Gdx.app.log("posy", " " + Gdx.input.getY() + "\n");
		}
		
		if (!end_game_F&&!end_game_W) {
			// TODO Auto-generated method stub
			Gdx.gl.glClearColor(1, 0.8f, 0, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			mpos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			maingame.camera.unproject(mpos);
			w = Gdx.graphics.getWidth();
			h = Gdx.graphics.getHeight();
			
			for (int i = 1; i < 7; i++) {
				MainGame.renderer.begin(ShapeType.Line);
				MainGame.renderer.line(i * w / 7, 0, i * w / 7, h);
				MainGame.renderer.end();
			}
			for (int i = 1; i < 6; i++) {
				MainGame.renderer.begin(ShapeType.Line);
				MainGame.renderer.line(0, i * h / 6, w, i * h / 6);
				MainGame.renderer.end();
			}
			if (Gdx.input.justTouched()) {
				if ((Gdx.input.getX() < w / 7) && (Gdx.input.getX() > 0)) {
					nova_ficha(0);
				} else if ((Gdx.input.getX() < 2 * w / 7)
						&& (Gdx.input.getX() > w / 7)) {
					nova_ficha(1);
				} else if ((Gdx.input.getX() < 3 * w / 7)
						&& (Gdx.input.getX() > 2 * w / 7)) {
					nova_ficha(2);
				} else if ((Gdx.input.getX() < 4 * w / 7)
						&& (Gdx.input.getX() > 3 * w / 7)) {
					nova_ficha(3);
				} else if ((Gdx.input.getX() < 5 * w / 7)
						&& (Gdx.input.getX() > 4 * w / 7)) {
					nova_ficha(4);
				} else if ((Gdx.input.getX() < 6 * w / 7)
						&& (Gdx.input.getX() > 5 * w / 7)) {
					nova_ficha(5);
				} else if ((Gdx.input.getX() < w)) {
					nova_ficha(6);
				} else {
					Gdx.app.log("DAFUK", "Dis aint supose to apear!");
				}
			}
			draw_ficha();
			end_game_W = check_win();
			end_game_F = check_full();
			Gdx.app.log("win", " " + end_game_W);
			Gdx.app.log("board full", " " + end_game_F);
		}
		else{
			if(timer >= time)
				maingame.restartGame();
			else timer++;
			
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			/*MainGame.batch.begin();
			if(player_actual==3)
				MainGame.draw.draw(MainGame.batch);
			MainGame.batch.end();*/
			
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
		for(int i=5;i>=0;i--){
			if(tab[i][x]==null){
				tab[i][x]= new Ficha(player_actual);
				change_player();
				break;
			}
		}
	}
	
	void make_null(){
		for(int i=0;i<6;i++){
			for(int j=0;j<7;j++){
				tab[i][j]=null;
			}
		}
	}
	
	void draw_ficha(){
		for(int i=0;i<6;i++){
			for(int j=0;j<7;j++){
				if(tab[i][j]!=null){
					MainGame.renderer.begin(ShapeType.FilledCircle);
					MainGame.renderer.setColor(tab[i][j].color);
					MainGame.renderer.filledCircle((j*w/7)+w/14, (h-(i+1)*h/6)+h/12, 20);
					MainGame.renderer.end();
				}
			}
		}
	}
	
	boolean check_win(){
		//horizontal test
		for (int i=5;i<=0;i--){
			if((tab[i][0].jogador==tab[i][1].jogador)&&(tab[i][2].jogador==tab[i][3].jogador)){
				Gdx.app.log("winH", " "+tab[i][0].jogador);
				return true;
			}
		}
	
		
		
		return false;
	}
	
	boolean check_full(){
		while(true){
			for(int i=0;i<6;i++){
				for(int j=0;j<7;j++){
					if(tab[i][j]==null)
						return false;
				}
			}
			player_actual=3;
			return true;
		}
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
