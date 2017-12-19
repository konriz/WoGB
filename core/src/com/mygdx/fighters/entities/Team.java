package com.mygdx.fighters.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.fighters.units.Race;

public class Team {
	
	private List<Soldier> team;
	public static Team plain = new Team();
	private int side;
	private String name;
	private Race race;
	private boolean alive;
	private Texture flag, banner;
	private int points;
	private int deployed;
	
	/**
	 * Used for creating teams
	 * @param side - ally group; different teams can be in one ally group
	 * @param name - name for team
	 * @param race - team's race
	 */
	public Team(int side, String name, Race race)
	{
		this.team = new ArrayList<>();
		this.side = side;
		this.name = name;
		this.race = race;
		this.alive = true;
		this.flag = new Texture(Gdx.files.internal("sprites/" + this.race.getPath() + "/flag.gif"));
		this.banner = new Texture(Gdx.files.internal("sprites/" + this.race.getPath() + "/banner.gif"));
		deployed = 0;
		points = 0;
	}
	
	public Team()
	{
		this.side = -1;
		this.name = "World";
		this.race = new Race();
		this.alive = false;
		this.flag = new Texture(Gdx.files.internal("sprites/" + this.race.getPath() + "/flag.gif"));
	}
	
	public void add(Soldier s)
	{
		team.add(s);
	}
	
	public boolean contains(Soldier s)
	{
		return team.contains(s);
	}
	
	public int getSide()
	{
		return side;
	}
	
	public void setSide(int side)
	{
		this.side = side;
	}
	
	public Soldier get(int i)
	{
		return team.get(i);
	}
	
	public List<Soldier> getAll()
	{
		return team;
	}
	
	public int size()
	{
		return team.size();
	}
	
	public int indexOf(Soldier target) {
		return team.indexOf(target);
	}
	
	public String getName()
	{
		return name;
	}
	
	public Race getRace()
	{
		return this.race;
	}
	
	public boolean getAlive()
	{
		return this.alive;
	}
	
	public void setAlive(boolean alive)
	 {
		this.alive = alive;
	 }

	public Texture getFlag() {
		return flag;
	}
	
	public Texture getBanner()
	{
		return this.banner;
	}
	
	public void addPoint()
	{
		points ++;
	}
	
	public int getPoints()
	{
		return this.points;
	}
	
	public int getDeployed()
	{
		return this.deployed;
	}
	
	public void deploy()
	{
		deployed ++;
	}

	public boolean isDeployed() {
		
		if (deployed == team.size())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public String toString()
	{
		return getName();
	}



}
