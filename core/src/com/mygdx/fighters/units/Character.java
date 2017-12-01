package com.mygdx.fighters.units;

import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.moves.Move;


/**
 * Character class for Unit backend
 * @author konriz
 *
 */

public class Character {
	
	private boolean alive;
	private Race race;
	private Profession profession;
	private Array<Move> moves;
	
	private String name;
	
	private int str, end, dex, mel, maxHP, maxAP, res, dam;
	
	private String path;
	
	private int points;

	
	private int toHit;
	private int toRes;
	private int currentHP;
	private int currentAP;
	
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
		
		str = 10 + race.getStr() + profession.getStr();
		end = 10 + race.getEnd() + profession.getEnd();
		dex = 10 + race.getDex() + profession.getDex();
		mel = 10 + race.getMel() + profession.getMel();
		
		maxHP = 20 + end;
		maxAP = dex / 5;
		res = end / 5;
		dam = str / 5 + mel/5;
		
		path = race.getPath() + "/" + profession.getPath();
		
		points = str + end + dex + mel;
		
		toHit = dam;
		toRes = res;
		
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
	
	/**
	 * Checks if unit is alive, changes state if dead
	 * @return true if alive, false if dead
	 */
	public boolean checkAlive()
	{
		if (getCurrentHP() <= 0)
		{
			setAlive(false);
			return false;
		}
		else
			return true;
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
	
	public Array<Move> getMoves()
	{
		return moves;
	}
	
	public int getToHit()
	{
		return toHit;
	}
	
	public int getToRes()
	{
		return toRes;
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
	
}
