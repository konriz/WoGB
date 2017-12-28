package com.mygdx.fighters.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LobbyDialog extends Dialog {
	
	

	public LobbyDialog()
	{
		super("Lobby", FightersGame.skin);
		Label connection = new Label("Not connected!", FightersGame.skin);
		TextButton refresh = new TextButton("Refresh", FightersGame.skin);
		refresh.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (FightersGame.connection.isConnected())
				{
					connection.setText("Connected");
				}
				else
				{
					if(FightersGame.connection.connect())
					{
						connection.setText("Connected");
					}
					else
					{
						connection.setText("Connection failed");
					}
				}
			}
			
		});
		
		getContentTable().add(connection);
		getContentTable().add(refresh);
		
		button("Start game", true);
		button("Cancel", false);
	}
}
