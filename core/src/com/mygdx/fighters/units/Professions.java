package com.mygdx.fighters.units;

import com.mygdx.fighters.moves.Move;
import com.mygdx.fighters.moves.Moves;

public class Professions {
	
	private static Move[] warriorMoves = {Moves.attack};
	private static int[] warriorStats = {5,5,5,5};
	public static Profession warrior = new ProfessionBuilder().name("Warrior").moves(warriorMoves).stats(warriorStats).buildProfession();
	
	private static Move[] eliteMoves = {Moves.attack, Moves.attackHeavy};
	private static int[] eliteStats = {10,10,10,10};
	public static Profession elite = new ProfessionBuilder().name("Elite").moves(eliteMoves).stats(eliteStats).buildProfession();
	
	private static Move[] scoutMoves = {Moves.attack};
	private static int[] scoutStats = {0,0,15,0};
	public static Profession scout = new ProfessionBuilder().name("Scout").moves(scoutMoves).stats(scoutStats).buildProfession();
	
	private static Move[] vampireMoves = {Moves.attack, Moves.leech};
	private static int[] vampireStats = {10,5,15,10};
	public static Profession vampire = new ProfessionBuilder().name("Vampire").moves(vampireMoves).stats(vampireStats).buildProfession();
	
	private static Move[] healerMoves = {Moves.attack, Moves.heal};
	private static int[] healerStats = {-5,-5,0,0};
	public static Profession healer = new ProfessionBuilder().name("Healer").moves(healerMoves).stats(healerStats).buildProfession();
	
}
