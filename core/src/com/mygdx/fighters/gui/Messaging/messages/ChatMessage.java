package com.mygdx.fighters.gui.Messaging.messages;

import com.mygdx.fighters.gui.Messaging.Message;

public class ChatMessage extends Message {

	private static final long serialVersionUID = 1L;

	public ChatMessage(String message)
	{
		setText(message);
	}
}
