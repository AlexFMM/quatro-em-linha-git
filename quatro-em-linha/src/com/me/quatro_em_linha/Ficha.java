package com.me.quatro_em_linha;

import com.badlogic.gdx.graphics.Color;

public class Ficha {
	int jogador;
	Color color;
	
	Ficha(int a){
		jogador=a;
		if(a==0)
			color = new Color(1,1,1,1);
		else
			color = new Color(0.5f,0.5f,1,1);
	}

}
