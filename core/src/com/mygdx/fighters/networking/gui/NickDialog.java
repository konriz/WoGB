package com.mygdx.fighters.networking.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.fighters.gui.FightersGame;
import com.mygdx.fighters.gui.MenuScreen;
import com.mygdx.fighters.gui.WelcomeDialog;

public class NickDialog extends Dialog {

	private TextField nickname;
	
	public NickDialog()
	{
		super("Your name", FightersGame.skin);
		
		nickname = new TextField("Nick", FightersGame.skin);
		getContentTable().add(nickname);
		button("Accept", true);
		button("Cancel", false);
	}

	@Override
	protected void result(Object object) {
		if (object.equals(true))
		{
			FightersGame.nickname = nickname.getText();
			LobbyDialog lobby = new LobbyDialog();
			MenuScreen.stage.addActor(lobby);
			lobby.show(MenuScreen.stage);
		}
		else
		{
			WelcomeDialog dialog = new WelcomeDialog();
			MenuScreen.stage.addActor(dialog);
			dialog.show(MenuScreen.stage);
		}
		
	}
	
	
}
