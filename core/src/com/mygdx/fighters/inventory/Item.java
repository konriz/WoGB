package com.mygdx.fighters.inventory;

class Item {
	
	enum ItemType {NONE, OFFENSE, DEFENSE }

	private String name;
	private Character owner;
	private int weight;
	private int value;
	private ItemType itemType;
	
	public Item(String n, Character o, int w, int v, ItemType iT)
	{
		setName(n);
		setOwner(o);
		setWeight(w);
		setValue(v);
		setItemType(iT);
	}
	
	public Item(Character o)
	{
		this("", o, 0, 0, ItemType.NONE);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Character getOwner() {
		return owner;
	}

	public void setOwner(Character owner) {
		this.owner = owner;
	}

	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public ItemType getItemType()
	{
		return itemType;
	}
	
	public void setItemType(ItemType it)
	{
		itemType = it;
	}
}
