package com.mygdx.fighters.gui.UI;

//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Pixmap;
//import com.badlogic.gdx.graphics.Pixmap.Format;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class StatusContainer extends Container<StatusLabel> {

	private StatusLabel label;
	
	
	public StatusContainer(StatusLabel label)
	{
		super(label);
//		Pixmap pm = new Pixmap(1, 1, Format.RGB565);
//		pm.setColor(Color.BLACK);
//		pm.fill();
//		this.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pm))));
		this.label = label;
		label.refresh();
	}
	
	public void refresh()
	{
		
		this.label.refresh();
	}
}
