package com.mygdx.fighters.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BarsTextures {

	private Texture[] hpBars;
	private int[] hpLimits = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
	
	private Texture[] apBars;
	private int[] apLimits = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
	
	private Texture[] states;
	private Texture[] directions;
	
	public BarsTextures()
	{
		states = new Texture[8];
		states[0] = new Texture(Gdx.files.internal("sprites/selected.gif"));
		states[1] = new Texture(Gdx.files.internal("sprites/dead.gif"));
		states[2] = new Texture(Gdx.files.internal("sprites/Move/allowed.gif"));
		states[3] = new Texture(Gdx.files.internal("sprites/Move/attacking.gif"));
		states[4] = new Texture(Gdx.files.internal("sprites/Move/attack.gif"));
		// TODO make capture image
		states[5] = new Texture(Gdx.files.internal("sprites/Move/attack.gif"));
		states[6] = new Texture(Gdx.files.internal("sprites/hit.gif"));
		states[7] = new Texture(Gdx.files.internal("sprites/boosted.gif"));
		
		hpBars = new Texture[hpLimits.length - 1];
		for (int i = 0; i < hpBars.length; i++)
		{
			hpBars[i] = new Texture(Gdx.files.internal("sprites/Bars/hp" + hpLimits[i+1] + ".gif"));
		}
		
		apBars = new Texture[apLimits.length];
		for (int i = 0; i < apBars.length; i++)
		{
			apBars[i] = new Texture(Gdx.files.internal("sprites/Bars/ap" + apLimits[i] + ".gif"));
		}
		
		directions = new Texture[8];
		directions[0] = new Texture(Gdx.files.internal("sprites/Move/NW.gif"));
		directions[1] = new Texture(Gdx.files.internal("sprites/Move/N.gif"));
		directions[2] = new Texture(Gdx.files.internal("sprites/Move/NE.gif"));
		directions[3] = new Texture(Gdx.files.internal("sprites/Move/W.gif"));
		directions[4] = new Texture(Gdx.files.internal("sprites/Move/E.gif"));
		directions[5] = new Texture(Gdx.files.internal("sprites/Move/SW.gif"));
		directions[6] = new Texture(Gdx.files.internal("sprites/Move/S.gif"));
		directions[7] = new Texture(Gdx.files.internal("sprites/Move/SE.gif"));
	}
	
	public Texture[] getStates()
	{
		return this.states;
	}
	
	public Texture getHpBar(int hpPercent)
	{
		
		for (int i = 1; i < hpLimits.length; i++)
		{
			if (hpPercent <= hpLimits[i] && hpPercent > 0)
			{
				return hpBars[i-1];
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
				return apBars[i];
			}

		}
		return null;
	}
	
	public Texture[] getDirections()
	{
		return this.directions;
	}
}
