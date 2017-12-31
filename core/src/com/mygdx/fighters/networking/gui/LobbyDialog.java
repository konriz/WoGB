package com.mygdx.fighters.networking.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Teams;
import com.mygdx.fighters.gui.FightersGame;
import com.mygdx.fighters.gui.MenuScreen;
import com.mygdx.fighters.messaging.Console;
import com.mygdx.fighters.messaging.messages.ChatMessage;
import com.mygdx.fighters.networking.ChatCommand;
import com.mygdx.fighters.networking.ConnectionThread;
import com.mygdx.fighters.networking.Host;
import com.mygdx.fighters.networking.Master;
import com.mygdx.fighters.networking.RefreshCommand;
import com.mygdx.fighters.networking.Slave;

public class LobbyDialog extends Dialog {
	
	private static Label connectionLabel;
	private Console console;
	private TextField messageField;
	private ConnectionThread lobbyConnection;

	public LobbyDialog()
	{
		super("Lobby", FightersGame.skin);
		connectionLabel = new Label("Not connected!", FightersGame.skin);
		
		refresh();
		if(!FightersGame.isHost())
		{
			lobbyConnection = new Slave();
			lobbyConnection.start();
			FightersGame.connection.sendData(new RefreshCommand());
		}

		
		getContentTable().add(connectionLabel);
		getContentTable().row();
		
		console = GameData.console;
		ScrollPane consoleBox = new ScrollPane(console, FightersGame.skin);
		getContentTable().add(consoleBox).colspan(2).fillX().expandY().height(Value.percentHeight(.80f, this)).width(Value.percentWidth(.70f, this));
		
		messageField = new TextField("Message", FightersGame.skin);
		getContentTable().row();
		getContentTable().add(messageField).fillX();
		// TODO listener for enter

		
		TextButton button = new TextButton("Send", FightersGame.skin);
		button.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				sendMessage();
			}
		});
		getContentTable().add(button);
		
		button("Select races", true);
		button("Cancel", false);
		
	}
	
	@Override
	protected void result(Object object) {
		if (object.equals(true))
		{
			GameData.selectMap(GameData.mapName);
			GameData.teams = new Teams();
			NetPlayersDialog playersDialog = new NetPlayersDialog();
			MenuScreen.stage.addActor(playersDialog);
			playersDialog.show(MenuScreen.stage);
			
		}
		else if (object.equals(false))
		{
			lobbyConnection.terminate();
			if (FightersGame.connection.isConnected())
			{
				FightersGame.connection.disconnect();
			}
		}
	}
	
	public void sendMessage()
	{
		ChatMessage chat = new ChatMessage(messageField.getText());
		GameData.console.add(chat);
		FightersGame.connection.sendData(new ChatCommand(chat));
		messageField.clear();
	}

	public static void refresh()
	{
		if (FightersGame.isHost())
			{
				countPlayers(((Host) FightersGame.connection).getConnections());
				if (((Host) FightersGame.connection).getConnections() < GameData.map.getMaxPlayers())
				{
					// refresh is accessed by master thread only - after player connecting 
					new Thread(new Master()).start();
				}
			}
	}
	
	public static void countPlayers(int connections)
	{
		connectionLabel.setText("Players: " + connections);
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
