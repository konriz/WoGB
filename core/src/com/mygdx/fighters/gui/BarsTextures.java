package com.mygdx.fighters.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class BarsTextures {

	private Array<Texture> hpBars;
	private int[] hpLimits = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
	
	private Array<Texture> apBars;
	private int[] apLimits = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
	
	private Array<Texture> states;
	private Array<Texture> directions;
	
	public BarsTextures()
	{
		states = new Array<Texture>();
		states.add(new Texture(Gdx.files.internal("sprites/selected.gif")));
		states.add(new Texture(Gdx.files.internal("sprites/dead.gif")));
		states.add(new Texture(Gdx.files.internal("sprites/Move/allowed.gif")));
		states.add(new Texture(Gdx.files.internal("sprites/Move/attacking.gif")));
		states.add(new Texture(Gdx.files.internal("sprites/Move/attack.gif")));
		// TODO make capture image
		states.add(new Texture(Gdx.files.internal("sprites/Move/attack.gif")));
		states.add(new Texture(Gdx.files.internal("sprites/hit.gif")));
		states.add(new Texture(Gdx.files.internal("sprites/boosted.gif")));
		
		hpBars = new Array<Texture>(hpLimits.length - 1);
		for (int i = 0; i < (hpLimits.length - 1); i++)
		{
			hpBars.add(new Texture(Gdx.files.internal("sprites/Bars/hp" + hpLimits[i+1] + ".gif")));
		}
		
		apBars = new Array<Texture>(apLimits.length);
		for (int i = 0; i < apLimits.length; i++)
		{
			apBars.add(new Texture(Gdx.files.internal("sprites/Bars/ap" + apLimits[i] + ".gif")));
		}
		
		directions = new Array<Texture>(8);
		directions.add(new Texture(Gdx.files.internal("sprites/Move/NW.gif")));
		directions.add(new Texture(Gdx.files.internal("sprites/Move/N.gif")));
		directions.add(new Texture(Gdx.files.internal("sprites/Move/NE.gif")));
		directions.add(new Texture(Gdx.files.internal("sprites/Move/W.gif")));
		directions.add(new Texture(Gdx.files.internal("sprites/Move/E.gif")));
		directions.add(new Texture(Gdx.files.internal("sprites/Move/SW.gif")));
		directions.add(new Texture(Gdx.files.internal("sprites/Move/S.gif")));
		directions.add(new Texture(Gdx.files.internal("sprites/Move/SE.gif")));
	}
	
	public Array<Texture> getStates()
	{
		return this.states;
	}
	
	public Texture getHpBar(int hpPercent)
	{
		
		for (int i = 1; i < hpLimits.length; i++)
		{
			if (hpPercent <= hpLimits[i] && hpPercent > 0)
			{
				return hpBars.get(i-1);
			}
		}
		return null;
	}
	
	public Texture getApBar(int apPercent)
	{
		for (int i = 0; i < apLimits.length; i++)
		{
			if (apPercent <= apLimits[i] && apPercent >= 0)
			{
				return apBars.get(i);
			}
		}
		return null;
	}
	
	public Array<Texture> getDirections()
	{
		return this.directions;
	}
}
