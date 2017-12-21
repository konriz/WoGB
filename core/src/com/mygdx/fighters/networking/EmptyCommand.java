package com.mygdx.fighters.networking;

import com.mygdx.fighters.gui.Messaging.Message;

public class EmptyCommand implements Command {

	@Override
	public Message communicate() {
		return new Message();
	}

	@Override
	public void perform() {
		
	}

}
