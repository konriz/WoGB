package com.mygdx.fighters.networking.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Teams;
import com.mygdx.fighters.gui.FightersGame;
import com.mygdx.fighters.gui.MainScreen;
import com.mygdx.fighters.gui.players.TeamConstructionDialog;
import com.mygdx.fighters.gui.players.TeamSelectionBar;
import com.mygdx.fighters.networking.PlayerCommand;

public class NetPlayersDialog extends Dialog {

	private TeamSelectionBar teamSelect;
	public static NetPlayersList playersList;
	
	public NetPlayersDialog()
	{
		super("Players creation", FightersGame.skin);
		
		teamSelect = new TeamSelectionBar(2);
		getContentTable().add(teamSelect);
		
		TextButton select = new TextButton("Select", FightersGame.skin);
		select.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				PlayerCommand player = new PlayerCommand();
				player.setName(teamSelect.getName());
				player.setSide(teamSelect.getSide());
				player.setTeam(teamSelect.getFraction());
				
				if (FightersGame.connection.isConnected())
				{
					FightersGame.connection.sendData(player);
				}
				
				player.perform();
			}
		});
		getContentTable().add(select);
		
		getContentTable().row();
		playersList = new NetPlayersList();
		getContentTable().add(playersList).colspan(2).fillX().height(100);
		
		button("Accept", true);
		// TODO gameplay
	}

	@Override
	protected void result(Object object) {
		
		if(object.equals(true))
		{
			GameData.teams = new Teams(playersList.getTeams());
			GameData.resetTurn();
			GameData.map.setFlags();
			TeamConstructionDialog teamDialog = new TeamConstructionDialog(GameData.getActive());
			
			FightersGame.game.setScreen(FightersGame.screen);
			FightersGame.multiplexer.setGame();
			
			MainScreen.stage.addActor(teamDialog);
			teamDialog.show(MainScreen.stage);
			
		}
	}
	
	@Override
	public Dialog show(Stage stage) {
		super.show(stage);
		setWidth(300);
		setHeight(500);
		this.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Align.center);
		return this;
	}
	
	
}
