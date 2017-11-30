package com.mygdx.fighters;

import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.moves.Move;
import com.mygdx.fighters.moves.Moves;

public class Profession {
	
	public static enum Professions
	{
		WARRIOR,
		ELITE,
		SCOUT,
		VAMPIRE,
		HEALER
	}
	
	private String name;
	private String path;
	private int str;
	private int end;
	private int dex;
	private int mel;
	
	private Array<Move> moves;
	
	
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
			this.moves = new Array<Move>() 
			{{
				add(Moves.attack);
			}};
			break;
		
		case ELITE:
			name = "Elite Warrior";
			path = "ELITE";
			str = 10;
			end = 5;
			dex = 5;
			mel = 5;
			this.moves = new Array<Move>() 
			{{
				add(Moves.attack);
				add(Moves.attackHeavy);
			}};
			break;
		case SCOUT:
			name = "Scout";
			path = "SCOUT";
			str = -5;
			end = -5;
			dex = 10;
			mel = 5;
			this.moves = new Array<Move>() 
			{{
				add(Moves.attack);
				// TODO ranged attack here
			}};
			break;
		case VAMPIRE:
			name = "Vampire";
			path = "VAMPIRE";
			str = 10;
			end = 5;
			dex = 15;
			mel = 10;
			this.moves = new Array<Move>() 
			{{
				add(Moves.attack);
				add(Moves.leech);
			}};
			break;
		case HEALER:
			name = "Healer";
			path = "HEALER";
			str = -5;
			end = -5;
			dex = 0;
			mel = 0;
			this.moves = new Array<Move>() 
			{{
				add(Moves.attack);
				add(Moves.heal);
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
	
	public Array<Move> getMoves()
	{
		return this.moves;
	}


	
}
