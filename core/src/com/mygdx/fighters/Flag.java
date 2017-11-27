package com.mygdx.fighters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Flag extends Placeable {

	private Texture flag;
	private Team owner;
	private int[] pos;
	
	public Flag(int xPos, int yPos, Team owner)
	{
		super(xPos, yPos);
		
		this.pos = new int[2];
		pos[0] = xPos;
		pos[1] = yPos;
		GameData.field.getField(pos).setOccupation(this);
		flag = new Texture(Gdx.files.internal("sprites/flag.gif"));
		this.setRange(3);
		
		this.owner = owner;

		if(owner != Team.plain)
		{
			flag = owner.getFlag();
			
			for (int[] coords : getRange())
			{
				GameData.field.getField(coords).setMaster(this);
			}
		}
	}
	
	public Flag(int xPos, int yPos)
	{
		this(xPos, yPos, Team.plain);
	}
	
	public Team getOwner()
	{
		return this.owner;
	}
	
	public void setOwner(Team owner)
	{
		this.owner = owner;
		this.flag = owner.getFlag();
	}
	
	public Texture getFlag()
	{
		return this.flag;
	}
	
}
