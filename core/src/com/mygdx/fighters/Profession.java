package com.mygdx.fighters;

import com.badlogic.gdx.utils.Array;

public class Profession {
	
	public static enum Professions
	{
		WARRIOR,
		ELITE,
		SCOUT,
		ZOMBIE
	}
	
	public static enum Moves
	{
		NORMAL,
		RANGED,
		HEAVY,
		HEAL
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
				add(new Move("normal", Move.Type.ATTACK, 1));
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
				add(new Move("normal", Move.Type.ATTACK, 1));
				add(new Move("heavy", Move.Type.ATTACK, 3));
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
				add(new Move("normal", Move.Type.ATTACK, 1));
				add(new Move("ranged", Move.Type.RANGED, 2));
			}};
			break;
		case ZOMBIE:
			name = "Zombie";
			path = "ZOMBIE";
			str = 10;
			end = 10;
			dex = -5;
			mel = 5;
			this.moves = new Array<Move>() 
			{{
				add(new Move("normal", Move.Type.ATTACK, 1));
				add(new Move("bite", Move.Type.LEECH, 2));
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
