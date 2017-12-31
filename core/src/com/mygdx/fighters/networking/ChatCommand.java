package com.mygdx.fighters.networking;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.messaging.Message;
import com.mygdx.fighters.messaging.messages.ChatMessage;

public class ChatCommand extends AbstractCommand {

	private static final long serialVersionUID = 1L;
	
	private ChatMessage message;
	
	public ChatCommand(ChatMessage message)
	{
		this.message = message;
	}
	
	public Message communicate()
	{
		return message;
	}
	
	public void perform()
	{
		GameData.console.add(communicate());
	}
	
}
