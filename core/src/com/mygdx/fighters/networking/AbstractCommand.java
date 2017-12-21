package com.mygdx.fighters.networking;

import java.io.Serializable;

import com.mygdx.fighters.gui.Messaging.Message;

public abstract class AbstractCommand implements Command, Serializable {

	private static final long serialVersionUID = 1L;

	public Message communicate()
	{
		return new Message();
	};
	
	public void perform()
	{
		
	};
	
}
