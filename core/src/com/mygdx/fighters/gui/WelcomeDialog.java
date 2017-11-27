package com.mygdx.fighters.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class WelcomeDialog extends Dialog {

	
	public WelcomeDialog()
	{
		super("Welcome to War of Golden Blood", FightersGame.skin);
		button("Ok", true);
		button("Exit", false);
		
		this.getContentTable().add(new Image(new Texture(Gdx.files.internal("sprites/welcome.gif"))));
		this.getContentTable().row();
		text("Welcome to War of Golden Blood, turn-based strategy game.");
		this.getContentTable().row();
		text("Use numpad or mouse (click on arrows) to move and attack.");
		this.getContentTable().row();
		text("Tab or click on unit to switch unit within active team.");
		this.getContentTable().row();
		text("Press space or sidebar button to pass turn to other player.");
		this.getContentTable().row();
		text("Copyright - Konriz - 2017");
		
	}

	@Override
	protected void result(Object object) {
		
		if (object.equals(true))
		{
			StartDialog start = new StartDialog(FightersGame.game);
			MenuScreen.stage.addActor(start);
			start.show(MenuScreen.stage);
		}
		
		else if (object.equals(false))
		{
			System.exit(0);
		}
	}
	
	@Override
	public Dialog show(Stage stage) {
		super.show(stage);
		setWidth(500);
		setHeight(600);
		this.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Align.center);
		return this;
	}
	
	
	
	
}
