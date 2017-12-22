package com.mygdx.fighters.gui.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Teams;
import com.mygdx.fighters.gui.FightersGame;
import com.mygdx.fighters.gui.MainScreen;
import com.mygdx.fighters.gui.MenuScreen;
import com.mygdx.fighters.gui.StartDialog;

public class PlayersDialog extends Dialog {
	
	private SelectBox<Integer> playersBox;
	private TeamBox teams;

	
	public PlayersDialog()
	{
		super("Select players", FightersGame.skin);
		
		Label playersLabel = new Label("Amount of players", FightersGame.skin);
		getContentTable().align(Align.top);
		getContentTable().add(playersLabel);
		Array<Integer> playersList = new Array<Integer>();
		playersList.addAll(GameData.map.getPlayers());
		
		playersBox = new SelectBox<Integer>(FightersGame.skin);
		playersBox.setItems(playersList);
		getContentTable().add(playersBox).colspan(2);
		
		teams = new TeamBox(playersBox.getSelected());
		getContentTable().row();
		getContentTable().add(teams).colspan(4);
		
		playersBox.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				teams.createTable(playersBox.getSelected());
			}
		});
		
		button("Ok", true);
		button("Back", false);
	}
	
	@Override
	protected void result(Object object) {
		
		if (object.equals(true))
		{
			if (teams.areUnique())
			{
				GameData.teams = new Teams(teams.getTeams());
				GameData.resetTurn();
				GameData.map.setFlags();
				TeamConstructionDialog teamDialog = new TeamConstructionDialog(GameData.getActive());
				
				FightersGame.game.setScreen(FightersGame.screen);
				FightersGame.multiplexer.setGame();
				
				MainScreen.stage.addActor(teamDialog);
				teamDialog.show(MainScreen.stage);
			}
			else
			{
				PlayersDialog playersDialog = new PlayersDialog();
				MenuScreen.stage.addActor(playersDialog);
				playersDialog.show(MenuScreen.stage);
			}
		}
		
		else if (object.equals(false))
		{
			StartDialog start = new StartDialog();
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
