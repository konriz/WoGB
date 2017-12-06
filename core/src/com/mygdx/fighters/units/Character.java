package com.mygdx.fighters.units;

import com.mygdx.fighters.moves.Move;

/**
 * Character class for Soldier backend
 * @author konriz
 *
 */

public class Character {
	
	private boolean alive;
	private Race race;
	private Profession profession;
	private Move[] moves;
	
	private String name;
	
	private int[] stats = new int[4];
	private int maxHP, currentHP, maxAP, currentAP, res, dam;
	
	private String path;
	
	private int points;

	// TODO make inventory useful!
	// private Inventory inventory;
	
	/**
	 * Creates character with given parameters
	 * @param n - name of character
	 * @param r - race of character
	 * @param p - profession of character
	 */
	public Character(Race r, Profession p)
	{

		alive = true;
		race = r;
		profession = p;
		this.name = race.getName() + " " + profession.getName();

		
		moves = profession.getMoves();
		// inventory = new Inventory(this, 15);
		
		for(int i=0; i<4; i++)
		{
			this.stats[i] = race.getStats()[i] + profession.getStats()[i] + 10;
		}
		
		maxHP = 20 + stats[1];
		maxAP = stats[2] / 5;
		res = stats[1] / 5;
		dam = stats[0] / 5 + stats[3]/5;
		
		path = race.getPath() + "/" + profession.getPath();
		
		points = stats[0] + stats[1] + stats[2] + stats[3];
		
		resetAP();
		resetHP();
	}	
	
	/**
	 * Creates character with determined race but plain profession
	 * @param name - character name
	 * @param r - Enum defining race
	 */

	public String getName()
	{
		return name;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public void setAlive(boolean state)
	{
		alive = state;
	}
	
	public Race getRace()
	{
		return race;
	}
	
	public Profession getProfession()
	{
		return profession;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public int getMaxHP()
	{
		return this.maxHP;
	}
	
	public int getCurrentHP()
	{
		return currentHP;
	}
	
	public int getHpPercent()
	{
		return currentHP * 100 / maxHP;
	}
	
	public void setCurrentHP(int hp)
	{
		currentHP = hp;
	}
	
	public void dropCurrentHP(int hp)
	{
		currentHP -= hp;
	}
	
	public void resetHP()
	{
		setCurrentHP(maxHP);
	}
	
	public int getMaxAP()
	{
		return maxAP;
	}
	
	public int getCurrentAP()
	{
		return currentAP;
	}
	
	public void setCurrentAP(int ap)
	{
		currentAP = ap;
	}
	
	public int getApPercent()
	{
	return currentAP * 100 / maxAP;
	}
	
	public void dropCurrentAP(int ap)
	{
		currentAP -= ap;
	}
	
	public void resetAP()
	{
		setCurrentAP(maxAP);
	}
	
	public int getPoints()
	{
		return this.points;
	}
	
	public Move[] getMoves()
	{
		return moves;
	}
	
	public int getDamage()
	{
		return dam;
	}
	
	public int getResistance()
	{
		return res;
	}

	public void buffCurrentHP(int heal) {
		if (getCurrentHP() + heal > getMaxHP())
		{
			resetHP();
		}
		else
		{
			setCurrentHP(getCurrentHP() + heal);
		}
	}

	public void hitCurrentHP(int damage) {
		this.dropCurrentHP(damage - getResistance());
	}
	
}
