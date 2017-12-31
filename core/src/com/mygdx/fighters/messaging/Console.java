package com.mygdx.fighters.messaging;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.mygdx.fighters.gui.FightersGame;

public class Console extends VerticalGroup {

	public List<Message> messages;
	
	public Console()
	{
		this.align(Align.bottom);
		messages = new ArrayList<>();
	}
	
	public void add(String text)
	{
		Message message = new Message();
		message.setText(text);
		messages.add(message);
		update();
	}
	
	public void add(Message message)
	{
		messages.add(message);
		update();
	}
	
	public void update()
	{
		//TODO console has to scroll, not be limited!

		if(messages.size() > 6)
		{
			messages.remove(0);
		}
		clear();
		for (Message message : messages) {
			addActor(new Container<Label>(new Label(message.getText(), FightersGame.skin)));
		}
	}
}
