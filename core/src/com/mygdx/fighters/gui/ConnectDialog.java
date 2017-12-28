package com.mygdx.fighters.gui;

import java.io.IOException;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class ConnectDialog extends Dialog {

	private final TextField hostIP;
	
	public ConnectDialog()
	{
		super("Connect", FightersGame.skin);
		hostIP = new TextField("Host IP", FightersGame.skin);
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
			}
			catch (IOException e)
			{
				e.printStackTrace();
				ConnectDialog connect = new ConnectDialog();
				connect.setFailed();
				MenuScreen.stage.addActor(connect);
				connect.show(MenuScreen.stage);
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
