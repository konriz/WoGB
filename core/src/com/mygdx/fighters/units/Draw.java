package com.mygdx.fighters.units;

import com.badlogic.gdx.graphics.Texture;

public interface Draw {
	
	public String getPath();
	
	public Texture getSprite();
	public void setSprite(Texture sprite);
	
	public Texture getUp();
	public Texture getDown();
	public Texture getLeft();
	public Texture getRight();
	
	public Texture getSelected();
	

}
