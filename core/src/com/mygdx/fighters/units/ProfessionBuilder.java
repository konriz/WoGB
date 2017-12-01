package com.mygdx.fighters.units;

import com.mygdx.fighters.moves.Move;

public class ProfessionBuilder {

	private String name = "person";
	private Move[] moves = {};
	private int[] stats = {0, 0, 0, 0};
	
	public ProfessionBuilder() {};
	
	public Profession buildProfession()
	{
		return new Profession(name, moves, stats);
	}
	
	public ProfessionBuilder name(String name)
	{
		this.name = name;
		return this;
	}
	
	public ProfessionBuilder moves(Move[] moves)
	{
		this.moves = moves;
		return this;
	}
	
	public ProfessionBuilder stats(int[] stats)
	{
		this.stats = stats;
		return this;
	}
}
