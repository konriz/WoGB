package com.mygdx.fighters.inventory;
import java.util.ArrayList;

/**
 * Stores items for later.
 * @author konriz
 *
 */

public class Backpack {
	
	private Character owner;
	private ArrayList<Item> backpack;
	
	public Backpack(Character owner)
	{
		this.owner = owner;
		this.backpack = new ArrayList<Item>();
	}
	
	public void addItem(Item item)
	{
		item.setOwner(owner);
		backpack.add(item);
		if (item instanceof Wearable)
		{
			((Wearable) item).setEquiped(false);
		}
	}
	
	public ArrayList<Item> getItems()
	{
		return backpack;
	}
	
	public void removeItem(Item item)
	{
		backpack.remove(item);
	}

}
