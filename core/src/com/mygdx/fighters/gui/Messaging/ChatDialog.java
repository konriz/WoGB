package com.mygdx.fighters.gui.Messaging;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.gui.FightersGame;
import com.mygdx.fighters.gui.Messaging.messages.ChatMessage;

public class ChatDialog extends Dialog {

	private TextField messageField;
	
	public ChatDialog()
	{
		super("Chat", FightersGame.skin);
		messageField = new TextField("Message", FightersGame.skin);
		getContentTable().add(messageField);
		
		button("Send", true);
		button("Cancel", false);
	}

	@Override
	protected void result(Object object) {
		
		if (object.equals(true))
		{
			GameData.console.add(new ChatMessage(messageField.getText()));
		}
	}
	
	@Override
	public Dialog show(Stage stage) {
		super.show(stage);
		setWidth(500);
		setHeight(100);
		this.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Align.center);
		return this;
	}
}
