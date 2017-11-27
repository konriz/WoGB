package com.mygdx.fighters.inventory;

import java.util.ArrayList;

public class Inventory {

	private ArrayList<Slot> equipment;
// TODO Backpack!
//	private ArrayList<Slot> backpack;
//	private int backpackSize;
	
	public Inventory(Character owner, int backpackSize)
	{
		equipment = new ArrayList<Slot>() {{
			add(new Slot(SlotType.HEAD));
			add(new Slot(SlotType.BODY));
			add(new Slot(SlotType.LEGS));
			add(new Slot(SlotType.MAIN));
			add(new Slot(SlotType.OFF));
		}};
		
//		this.backpackSize = backpackSize;
//		
//		backpack = new ArrayList<Slot>() {{
//			int size = this.getBackpackSize();
//			while (size > 0)
//			{
//				add(new Slot(SlotType.BACKPACK));
//				size --;
//			}
//		}};
	}

	public ArrayList<Slot> getEquipment() {
		return equipment;
	}
	
	public int getAttack() {
		int attack = 0;
		for (Slot i : equipment)
		{
			if (i.getContent() instanceof Weapon)
			{
				attack += ((Weapon) i.getContent()).getAttack();
			}
		}
		return attack;
	}
	
	public int getDefense() {
		int defense = 0;
		for (Slot i : equipment)
		{
			if (i.getContent() instanceof Armor)
			{
				defense += ((Armor) i.getContent()).getDefense();
			}
		}
		return defense;
	}

//	public ArrayList<Slot> getBackpack() {
//		return backpack;
//	}
//	
//	public int getBackpackSize() {
//		return backpackSize;
//	}
	
	
}
