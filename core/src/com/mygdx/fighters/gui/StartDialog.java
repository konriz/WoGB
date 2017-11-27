package com.mygdx.fighters.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.GameData;

public class StartDialog extends Dialog {

	private Skin skin;
	private Slider unitsSlider, pointsSlider;
	private SelectBox<String> mapBox;
	
	public StartDialog(FightersGame game)
	{
		super("Choose parameters", FightersGame.skin);
		this.skin = FightersGame.skin;
		
		Array<String> mapsList = new Array<String>();
		mapsList.add("small_test.tmx");
		mapsList.add("1st40x35.tmx");
		mapsList.add("big_test.tmx");
		
		mapBox = new SelectBox<String>(FightersGame.skin);
		mapBox.setItems(mapsList);
		this.getContentTable().add(mapBox).align(Align.center).colspan(3);
		
		this.getContentTable().row();
		
		unitsSlider = new Slider(300, 1500, 20, false, skin);
		this.getContentTable().add(new Label("Units poits limit", skin));
		this.getContentTable().add(unitsSlider);
		final Label unitsCountLabel = new Label(String.valueOf((int) unitsSlider.getValue()), skin);
		this.getContentTable().add(unitsCountLabel);
		
		unitsSlider.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				final int unitsCount = (int)unitsSlider.getValue();
				unitsCountLabel.setText(String.valueOf(unitsCount));
			}
		});
		
		this.getContentTable().row();
		
		pointsSlider = new Slider(30, 200, 10, false, skin);
		this.getContentTable().add(new Label("Victory points limit", skin));
		this.getContentTable().add(pointsSlider);
		final Label pointsCountLabel = new Label(String.valueOf((int) pointsSlider.getValue()), skin);
		this.getContentTable().add(pointsCountLabel);
		
		pointsSlider.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				final int pointsLimit = (int)pointsSlider.getValue();
				pointsCountLabel.setText(String.valueOf(pointsLimit));
			}
		});
		
		button("Ok", true);
		button("Exit", false);

	}
	
	@Override
	protected void result(Object object) {
		
		if (object.equals(true))
		{
			FightersGame.data = new GameData();
			GameData.selectMap(mapBox.getSelected());
			GameData.unitsPoints = (int) unitsSlider.getValue();
			GameData.victoryPoints = (int) pointsSlider.getValue();
			
			PlayersDialog playersDialog = new PlayersDialog();
			MenuScreen.stage.addActor(playersDialog);
			playersDialog.show(MenuScreen.stage);
			
		}
		
		else if (object.equals(false))
		{
			
			System.exit(0);
		}
	}

	@Override
	public Dialog show(Stage stage) {
		super.show(stage);
		setWidth(400);
		setHeight(300);
		this.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Align.center);
		return this;
	}
	
}
