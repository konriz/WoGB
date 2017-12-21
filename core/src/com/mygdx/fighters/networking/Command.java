package com.mygdx.fighters.networking;

import com.mygdx.fighters.gui.Messaging.Message;

public interface Command {

	public Message communicate();
	public void perform();
}
