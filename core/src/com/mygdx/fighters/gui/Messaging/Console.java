package com.mygdx.fighters.gui.Messaging;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.mygdx.fighters.gui.FightersGame;

//TODO maybe container for vertical group? or vertical group of label containers?
public class Console extends VerticalGroup {

	public List<Message> messages;
	
	public Console()
	{
		this.align(Align.bottom);
		messages = new ArrayList<>();
		add("Console test");
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
