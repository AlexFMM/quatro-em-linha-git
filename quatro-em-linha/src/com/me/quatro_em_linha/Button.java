package com.me.quatro_em_linha;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Button {

	Sprite idle,higlight, img;
	CharSequence text, description;
	Vector2 Pos;
	Vector2 size;
	Vector2 txtPosRel;
	Screen screen = null;
	MainGame maingame;
	
	public Button(Texture t,CharSequence text, Vector2 Pos, Vector2 Size,Vector2 txtPosRel,MainGame mg)
	{
		this.size = Size;
		TextureRegion idle_t = new TextureRegion(t,0,0,128,128);
		TextureRegion h_t = new TextureRegion(t,128,0,128,128);
		this.idle = new Sprite(idle_t);
		idle.setSize(Size.x, Size.y);
		idle.setPosition(Pos.x, Pos.y);
		this.higlight = new Sprite(h_t);
		higlight.setSize(Size.x, Size.y);
		higlight.setPosition(Pos.x, Pos.y);
		this.img = this.idle;
		this.text = text;
		this.Pos = Pos;
		this.txtPosRel = txtPosRel;
		this.screen = screen;
		this.maingame = mg;
	}
	
	public void update(float x, float y)
	{
		if(hit(x,y)){
			this.img = this.higlight;
			
			if(Gdx.input.justTouched()){
				this.click();
			}
		}
		else this.img = this.idle;
		
	}
	
	public void click()
	{
		if(screen != null){
		this.img = this.idle;
		maingame.setScreen(screen);
		}
		else
		{
			Gdx.app.exit();
		}
	}
	
	boolean hit(float x, float y)
	{
		if(x >= Pos.x && x <= Pos.x + (size.x) && y >= Pos.y && y <= Pos.y + (size.y) ){
			return true;
		}
		return false;
	}
}
