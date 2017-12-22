package com.mygdx.fighters.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
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

	@Override
	protected void result(Object object) {
		
		if (object.equals(true))
		{
			FightersGame.guest(hostIP.getText());
		}
		else if (object.equals(false))
		{
			WelcomeDialog welcome = new WelcomeDialog();
			MenuScreen.stage.addActor(welcome);
			welcome.show(MenuScreen.stage);
		}
		
	}
	
	
}
