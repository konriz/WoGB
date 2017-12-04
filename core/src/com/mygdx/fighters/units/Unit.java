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
	
	private Texture up, down, left, right, tableSelected;
	
	public Unit(Race r, Profession p)
	{
				
		String path = getPath(r, p);
		
		up = new Texture(Gdx.files.internal(path +"/bk.gif"));
		down = new Texture(Gdx.files.internal(path +"/fr.gif"));
		left = new Texture(Gdx.files.internal(path +"/lt.gif"));
		right = new Texture(Gdx.files.internal(path +"/rt.gif"));
		
		tableSelected = new Texture(Gdx.files.internal(path +"/ts.gif"));
	}
	
	public String getPath(Race race, Profession profession)
	{
		String path = "sprites/" + race.getPath() + "/" + profession.getPath();
		return path;
	}

	public Texture getSprite(Direction dir) {
		Texture sprite;
		{
			switch (dir)
			{
			case E:
				sprite = this.right;
				break;
			case N:
				sprite = this.up;
				break;
			case S:
				sprite = this.down;
				break;
			case W:
				sprite = this.left;
				break;
			default:
				sprite = this.down;
				break;
			}
		}
		return sprite;
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
