package com.mygdx.fighters.networking;

import com.mygdx.fighters.gui.FightersGame;

public class TerminateCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void perform() {
		FightersGame.connection.disconnect();
	}
	
	

}
