package com.mygdx.fighters.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Team;
import com.mygdx.fighters.Teams;

public class PlayersDialog extends Dialog {
	
	private SelectBox<Integer> playersBox;
	private Teams teams;
	private Array<Label> players;

	
	public PlayersDialog()
	{
		super("Select players", FightersGame.skin);
		
		Label playersLabel = new Label("Amount of players", FightersGame.skin);
		this.getContentTable().align(Align.top);
		this.getContentTable().add(playersLabel);
		Array<Integer> playersList = new Array<Integer>();
		playersList.addAll(GameData.map.getPlayers());
		
		playersBox = new SelectBox<Integer>(FightersGame.skin);
		playersBox.setItems(playersList);
		this.getContentTable().add(playersBox).colspan(2);
		
		TextButton randomize = new TextButton("Randomize", FightersGame.skin);
		randomize.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				playersTable(playersBox.getSelected());
			}
			
		});
		
		this.getContentTable().add(randomize);
		
		players = new Array<Label>(4);
		players.addAll(new Label("", FightersGame.skin), new Label("", FightersGame.skin), new Label("", FightersGame.skin), new Label("", FightersGame.skin));
		
		for (Label l : players)
		{
			this.getContentTable().row();
			this.getContentTable().add(l).colspan(4);
		}
		
		playersTable(playersBox.getSelected());
		
		playersBox.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				playersTable(playersBox.getSelected());
			}
		});
		
		button("Ok", true);
		button("Back", false);
	}
	
	public void playersTable(Integer playersNo)
	{
		// TODO tables for selecting races/teams
		teams = new Teams(playersNo);
		int i = 0;
		for (Team t : teams)
		{
			players.get(i).setText("Player " +(i+1) + ": " + t.getName());
			i++;
		}
		
		while(i < 4) //3 -> should be Max Players in the game - 1, maybe 8?
		{
			players.get(i).setText("");
			i++;
		}
	}
	
	@Override
	protected void result(Object object) {
		
		if (object.equals(true))
		{
			GameData.teams = new Teams(teams);
			GameData.resetTurn();
			GameData.map.setFlags();
			TeamConstructionDialog teamDialog = new TeamConstructionDialog(GameData.getActive());
			
			FightersGame.game.setScreen(FightersGame.screen);
			FightersGame.multiplexer.setGame();
			
			MainScreen.stage.addActor(teamDialog);
			teamDialog.show(MainScreen.stage);
		}
		
		else if (object.equals(false))
		{
			StartDialog start = new StartDialog(FightersGame.game);
			MenuScreen.stage.addActor(start);
			start.show(MenuScreen.stage);
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
