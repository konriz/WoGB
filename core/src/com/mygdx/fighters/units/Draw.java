package com.mygdx.fighters.units;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.fighters.units.Unit.Direction;

public interface Draw {
	
	public String getPath(Race race, Profession profession);
	
	public Texture getSprite(Direction dir);
	
	public Texture getUp();
	public Texture getDown();
	public Texture getLeft();
	public Texture getRight();
	
	public Texture getSelected();
	

}
