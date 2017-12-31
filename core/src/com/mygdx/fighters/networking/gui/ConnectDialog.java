package com.mygdx.fighters.networking.gui;

import java.io.IOException;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.fighters.gui.FightersGame;
import com.mygdx.fighters.gui.MenuScreen;
import com.mygdx.fighters.gui.WelcomeDialog;

public class ConnectDialog extends Dialog {

	private final TextField hostIP;
	
	public ConnectDialog()
	{
		super("Connect", FightersGame.skin);
		hostIP = new TextField("localhost", FightersGame.skin);
		getContentTable().add(hostIP);
		button("Connect", true);
		button("Cancel", false);
		
	}
	
	public void setFailed()
	{
		getContentTable().row();
		getContentTable().add(new Label("Connection failed!", FightersGame.skin));
	}

	@Override
	protected void result(Object object) {
		
		if (object.equals(true))
		{
			try
			{
				FightersGame.guest(hostIP.getText());
				FightersGame.connection.connect();
				NickDialog nickname = new NickDialog();
				MenuScreen.stage.addActor(nickname);
				nickname.show(MenuScreen.stage);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				ConnectDialog connectDialog = new ConnectDialog();
				connectDialog.setFailed();
				MenuScreen.stage.addActor(connectDialog);
				connectDialog.show(MenuScreen.stage);
			}
		}
		else if (object.equals(false))
		{
			WelcomeDialog welcome = new WelcomeDialog();
			MenuScreen.stage.addActor(welcome);
			welcome.show(MenuScreen.stage);
		}
		
	}
	
	
}
