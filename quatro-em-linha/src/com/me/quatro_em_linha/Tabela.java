package com.me.quatro_em_linha;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
		
		MainGame.batch.setProjectionMatrix(maingame.camera.combined);
		
		if(Gdx.input.isKeyPressed(Keys.BACK))
		{
			maingame.setScreen(maingame.mainmenu);
		}
		
		if (Gdx.input.justTouched()) {
			Gdx.app.log("posx", " " + Gdx.input.getX());
			Gdx.app.log("posy", " " + Gdx.input.getY() + "\n");
		}
		
		if (!end_game_F&&!end_game_W) {
			// TODO Auto-generated method stub
			Gdx.gl.glClearColor(1, 0.8f, 0, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			MainGame.batch.begin();
			MainGame.tabela_spr.draw(MainGame.batch);
			MainGame.batch.end();
			
			MainGame.renderer.setColor(1, 1, 1, 1);
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
				if ((Gdx.input.getX() < w / 7) && (Gdx.input.getX() > 0)) {nova_ficha(0);}
				else if ((Gdx.input.getX() < 2 * w / 7) && (Gdx.input.getX() > w / 7)) {nova_ficha(1);}
				else if ((Gdx.input.getX() < 3 * w / 7) && (Gdx.input.getX() > 2 * w / 7)) {nova_ficha(2);}
				else if ((Gdx.input.getX() < 4 * w / 7) && (Gdx.input.getX() > 3 * w / 7)) {nova_ficha(3);} 
				else if ((Gdx.input.getX() < 5 * w / 7) && (Gdx.input.getX() > 4 * w / 7)) {nova_ficha(4);}
				else if ((Gdx.input.getX() < 6 * w / 7) && (Gdx.input.getX() > 5 * w / 7)) {nova_ficha(5);} 
				else if ((Gdx.input.getX() < w ) && (Gdx.input.getX() > 5*w/7)) {nova_ficha(6);} 
				else {Gdx.app.log("DAFUK", "Dis aint supose to apear!");}
			}
			
			draw_ficha();
			end_game_W = check_win();
			end_game_F = check_full();
		}
		else{
			if(timer >= time){
				maingame.setScreen(maingame.mainmenu);
			}
			else timer++;
			
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			MainGame.batch.begin();
			if(end_game_W){
				if(player_actual==0){
					MainGame.pl1w.draw(MainGame.batch);
				}
				else if(player_actual==1){
					MainGame.pl2w.draw(MainGame.batch);
				}
				else {Gdx.app.log(null, "Who dafuk was playing");}
			}
			else 
				MainGame.draw.draw(MainGame.batch);
			MainGame.batch.end();
			
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
					MainGame.renderer.filledCircle((j*w/7)+w/14, (h-(i+1)*h/6)+h/12, (h/12));
					MainGame.renderer.end();
				}
			}
		}
	}

	
	boolean check_win(){		
		//horizontal
		for(int i=5;i>=0;i--){
			boolean hyp1=true, hyp2=true, hyp3=true, hyp4=true;/*quatro hipoteses de ganhar horizontalmente,
			   se pelo menos um dos espaços nao esta preenchido nao se vai verificar*/
			
			for (int j=0; j<4;j++){
				if(tab[i][j]==null){
					hyp1=false;
					break;
				}
			}
			if(hyp1){
				if((tab[i][0].jogador==tab[i][1].jogador)&&(tab[i][2].jogador==tab[i][3].jogador)&&(tab[i][0].jogador==tab[i][2].jogador)){
					Gdx.app.log("winH", " "+tab[i][0].jogador);
					player_actual=tab[i][0].jogador;
					return true;
				}
			}
				
			for (int j=1; j<5;j++){
				if(tab[i][j]==null){
					hyp2=false;
					break;
				}					
			}
			if(hyp2){
				if((tab[i][1].jogador==tab[i][2].jogador)&&(tab[i][3].jogador==tab[i][4].jogador)&&(tab[i][1].jogador==tab[i][3].jogador)){
					Gdx.app.log("winH", " "+tab[i][1].jogador);
					player_actual=tab[i][1].jogador;
					return true;
				}
			}

			for (int j=2; j<6;j++){
				if(tab[i][j]==null){
					hyp3=false;
					break;
				}
			}
			if(hyp3){
				if((tab[i][2].jogador==tab[i][3].jogador)&&(tab[i][4].jogador==tab[i][5].jogador)&&(tab[i][2].jogador==tab[i][4].jogador)){
					Gdx.app.log("winH", " "+tab[i][2].jogador);
					player_actual=tab[i][2].jogador;
					return true;
				}
			}

			for (int j=3; j<7;j++){
				if(tab[i][j]==null){
					hyp4=false;
					break;
				}
			}
			if(hyp4){
				if((tab[i][3].jogador==tab[i][4].jogador)&&(tab[i][5].jogador==tab[i][6].jogador)&&(tab[i][3].jogador==tab[i][5].jogador)){
					Gdx.app.log("winH", " "+tab[i][3].jogador);
					player_actual=tab[i][3].jogador;
					return true;
				}
			}
		}
		
		
		//Vertical test
		for(int j=0;j<7;j++){
			boolean hyp1=true, hyp2=true, hyp3=true;/*tres hipoteses de ganhar verticalmente,
			   se pelo menos um dos espaços nao esta preenchido nao se vai verificar*/
			
			for(int i=0; i<4;i++){
				if(tab[i][j]==null){
					hyp1=false;
					break;
				}
			}
			if(hyp1){
				if((tab[0][j].jogador==tab[1][j].jogador)&&(tab[2][j].jogador==tab[3][j].jogador)&&(tab[0][j].jogador==tab[2][j].jogador)){
					Gdx.app.log("winH", " "+tab[0][j].jogador);
					player_actual=tab[0][j].jogador;
					return true;
				}
			}
			
			for(int i=1; i<5;i++){
				if(tab[i][j]==null){
					hyp2=false;
					break;
				}
			}
			if(hyp2){
				if((tab[1][j].jogador==tab[2][j].jogador)&&(tab[3][j].jogador==tab[4][j].jogador)&&(tab[1][j].jogador==tab[3][j].jogador)){
					Gdx.app.log("winH", " "+tab[1][j].jogador);
					player_actual=tab[1][j].jogador;
					return true;
				}
			}
			
			for(int i=2; i<6;i++){
				if(tab[i][j]==null){
					hyp3=false;
					break;
				}
			}
			if(hyp3){
				if((tab[2][j].jogador==tab[3][j].jogador)&&(tab[4][j].jogador==tab[5][j].jogador)&&(tab[2][j].jogador==tab[4][j].jogador)){
					Gdx.app.log("winH", " "+tab[2][j].jogador);
					player_actual=tab[2][j].jogador;
					return true;
				}
			}
			
			
		}
		
		
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<6;j++)
			{
				if(check_diag_dir_baixo(i, j)) {
					Gdx.app.log("win diagonal", "baixo");
					return true;
				}
				
				if(check_diag_dir_cima(i, j)) {
					Gdx.app.log("win diagonal", "cima");
					return true;
				}
			}
		}
		
		return false;
	}
	

	boolean check_diag_dir_baixo(int i, int j)
	{
		int jogador,count;
		if(tab[i][j] != null){
			jogador = tab[i][j].jogador;
			count = 0;
		}
		else return false;
		
		for(int a=0;a<4;a++)
		{
			
			if (i+a < 6 && j+a <7) {
				if (tab[i + a][j + a] != null) {
					if (tab[i + a][j + a].jogador != jogador) {
						return false;
					}
					else count++;
				} else
					return false;
			}
		}
		
		if(count > 3)return true;
		else return false;
	}
	
	boolean check_diag_dir_cima(int i, int j)
	{
		int jogador,count;
		if(tab[i][j] != null){
			jogador = tab[i][j].jogador;
			count = 0;
		}
		else return false;
		
		for(int a=0;a<4;a++)
		{
			
			if (i-a >= 0 && j+a <7) {
				if (tab[i - a][j + a] != null) {
					if (tab[i - a][j + a].jogador != jogador) {
						return false;
					}
					else count++;
				} else
					return false;
			}
		}
		
		if(count > 3)return true;
		else return false;
	}
	
	boolean check_full(){
		
			for(int i=0;i<6;i++){
				for(int j=0;j<7;j++){
					if(tab[i][j]==null)
						return false;
				}
			}
			Gdx.app.log("board full", " " + !end_game_F);
			return true;
		
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
