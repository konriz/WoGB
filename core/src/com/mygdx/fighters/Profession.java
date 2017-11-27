package com.mygdx.fighters;

import java.util.ArrayList;

import com.mygdx.fighters.Enums.Professions;

public class Profession {
	
	private String name;
	private String path;
	private int str;
	private int end;
	private int dex;
	private int mel;
	
	private ArrayList<Move> moves;
	
	public Profession(Professions profession)
	{
		switch(profession)
		{
		case WARRIOR:
			name = "Warrior";
			path = "";
			str = 5;
			end = 5;
			dex = 5;
			mel = 5;
			this.moves = new ArrayList<Move>() 
			{{
				add(new Move("normal", 1));
			}};
			break;
		
		case ELITE:
			name = "Elite Warrior";
			path = "ELITE";
			str = 10;
			end = 5;
			dex = 5;
			mel = 5;
			this.moves = new ArrayList<Move>() 
			{{
				add(new Move("normal", 1));
				add(new Move("heavy", 3));
			}};
			break;
		case SCOUT:
			name = "Scout";
			path = "SCOUT";
			str = -5;
			end = -5;
			dex = 10;
			mel = 5;
			this.moves = new ArrayList<Move>() 
			{{
				add(new Move("normal", 1));
				add(new Move("aimed", 2));
			}};
			break;
		}
	}
	
	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public int getStr() {
		return str;
	}

	public int getEnd() {
		return end;
	}

	public int getDex() {
		return dex;
	}

	public int getMel() {
		return mel;
	}
	
	public ArrayList<Move> getMoves()
	{
		return this.moves;
	}


	
}
