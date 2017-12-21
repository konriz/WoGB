package com.mygdx.fighters.gui.Messaging;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String text;
	
	public String getText()
	{
		return this.text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
}
