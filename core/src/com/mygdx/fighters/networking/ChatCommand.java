package com.mygdx.fighters.networking;

import com.mygdx.fighters.gui.Messaging.Message;
import com.mygdx.fighters.gui.Messaging.messages.ChatMessage;

public class ChatCommand extends AbstractCommand {

	private static final long serialVersionUID = 1L;
	
	private ChatMessage message;
	
	public ChatCommand(String message)
	{
		this.message = new ChatMessage(message);
	}
	
	public Message communicate()
	{
		return message;
	}
	
	public void perform()
	{
		communicate();
	}
	
}
