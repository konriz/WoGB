package com.mygdx.fighters.units;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class for storing frontend data of each Soldier
 * Includes textures
 * @author konriz
 *
 */
public class Unit implements Draw{
	
	public static enum Direction{
		N,
		S,
		W,
		E
	}
	
	private Texture sprite, up, down, left, right, tableSelected;
	
	public Unit(Race r, Profession p)
	{
				
		String path = getPath(r, p);
		
		up = new Texture(Gdx.files.internal(path +"/bk.gif"));
		down = new Texture(Gdx.files.internal(path +"/fr.gif"));
		left = new Texture(Gdx.files.internal(path +"/lt.gif"));
		right = new Texture(Gdx.files.internal(path +"/rt.gif"));
		
		tableSelected = new Texture(Gdx.files.internal(path +"/ts.gif"));
					
		sprite = down;
					
	}
	
	public String getPath(Race race, Profession profession)
	{
		String path = "sprites/" + race.getPath() + "/" + profession.getPath();
		return path;
	}

	public Texture getSprite() {
		return sprite;
	}
	
	public void setSprite(Texture sprite) {
		this.sprite = sprite;
	}

	public Texture getUp() {
		return up;
	}

	public Texture getDown() {
		return down;
	}

	public Texture getLeft() {
		return left;
	}

	public Texture getRight() {
		return right;
	}
	
	public Texture getSelected() {
		return tableSelected;
	}
	
}
