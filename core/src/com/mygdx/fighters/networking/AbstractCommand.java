package com.mygdx.fighters.networking;

import java.io.Serializable;

public abstract class AbstractCommand implements Command, Serializable {

	private static final long serialVersionUID = 1L;

	public abstract void perform();
	
}
