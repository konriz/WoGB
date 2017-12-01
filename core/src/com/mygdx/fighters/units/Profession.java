package com.mygdx.fighters.units;

import com.mygdx.fighters.moves.Move;

public class Profession {
	
	private String name;
	private String path;
	private Move[] moves;
	private int[] stats;
	
	public Profession(String name, Move[] moves, int[] stats)
	{
		this.name = name;
		this.path = name.toUpperCase();
		this.moves = moves;
		this.stats = stats;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}

	public int[] getStats()
	{
		return this.stats;
	}
	
	public Move[] getMoves()
	{
		return this.moves;
	}


	
}
