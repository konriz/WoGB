package com.mygdx.fighters.messaging.messages;

import com.mygdx.fighters.messaging.Message;

public class ChatMessage extends Message {

	private static final long serialVersionUID = 42L;

	public ChatMessage(String message)
	{
		setText(message);
	}
}
