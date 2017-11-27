package com.mygdx.fighters.inventory;

enum SlotType {
	BACKPACK,
	HEAD,
	BODY,
	LEGS,
	MAIN,
	OFF
}

public class Slot {
	
	private Item content;
	private SlotType type;
	
	public Slot(SlotType t)
	{
		content = null;
		type = t;
	}

	public Item getContent()
	{
		return this.content;
	}
	
	public void setContent(Item content)
	{
		this.content = content;
	}

	public SlotType getType() {
		return type;
	}	
	
}
