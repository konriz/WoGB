package com.mygdx.fighters.moves;

public class Moves {

	public static Attack attack = new Attack("Normal attack", 1, 1);
	public static Attack attackHeavy = new Attack("Heavy attack", 3, 3);
	
	public static Ranged bowShoot = new Ranged("Bow shot", 2, 1, 5);
	public static Ranged crossbowShoot = new Ranged("Crossbow shot", 3, 2, 8);
	
	public static Heal heal = new Heal("Heal", 2, 1);
	public static Leech leech = new Leech("Leech", 2, 1);
	
	public static Ranged spit = new Ranged("Spit", 3, 3, 3);
}
