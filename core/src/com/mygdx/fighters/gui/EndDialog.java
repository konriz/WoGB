package com.mygdx.fighters.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.mygdx.fighters.Team;

public class EndDialog extends Dialog {

	public EndDialog(Team winner)
	{
		super("Game over!", FightersGame.skin);
		
		this.getContentTable().add(new Image(winner.getBanner()));
		this.getContentTable().row();
		
		text(winner.getName() + " won the game!");
		
		button("Ok", true);
	}
	
	@Override
	protected void result(Object result)
	{
		if (result.equals(true))
		{
			System.exit(0);
		}
	}

	@Override
	public Dialog show(Stage stage) {
		super.show(stage);
		Gdx.input.setInputProcessor(stage);
		setWidth(400);
		setHeight(300);
		this.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Align.center);
		return this;
	}
	
	
}
