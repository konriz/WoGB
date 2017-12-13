package com.mygdx.fighters.units;

import com.mygdx.fighters.moves.Move;
import com.mygdx.fighters.moves.Moves;

public class Professions {
	
	// Common
	private static Move[] warriorMoves = {Moves.attack};
	private static int[] warriorStats = {5,5,5,5};
	public static Profession warrior = new ProfessionBuilder().name("Warrior").moves(warriorMoves).stats(warriorStats).buildProfession();
	
	private static Move[] eliteMoves = {Moves.attack, Moves.attackHeavy};
	private static int[] eliteStats = {10,10,10,10};
	public static Profession elite = new ProfessionBuilder().name("Elite").moves(eliteMoves).stats(eliteStats).buildProfession();
	
	private static Move[] scoutMoves = {Moves.attack, Moves.bowShoot};
	private static int[] scoutStats = {0,0,15,0};
	public static Profession scout = new ProfessionBuilder().name("Scout").moves(scoutMoves).stats(scoutStats).buildProfession();
	
	// Humans
	private static Move[] healerMoves = {Moves.attack, Moves.heal};
	private static int[] healerStats = {-5,-5,0,0};
	public static Profession healer = new ProfessionBuilder().name("Healer").moves(healerMoves).stats(healerStats).buildProfession();
	
	// Dwarves
	private static Move[] crossbowMoves = {Moves.attack, Moves.crossbowShoot};
	private static int[] crossbowStats = {5,5,5,0};
	public static Profession crossbow = new ProfessionBuilder().name("Crossbowman").moves(crossbowMoves).stats(crossbowStats).buildProfession();
	
	// Orks
	private static Move[] shamanMoves = {Moves.attack, Moves.buffStrength};
	private static int[] shamanStats = {5,5,5,5};
	public static Profession shaman = new ProfessionBuilder().name("Shaman").moves(shamanMoves).stats(shamanStats).buildProfession();
	
	// Undead
	private static Move[] vampireMoves = {Moves.attack, Moves.leech};
	private static int[] vampireStats = {10,5,15,10};
	public static Profession vampire = new ProfessionBuilder().name("Vampire").moves(vampireMoves).stats(vampireStats).buildProfession();
	
	// Animals
	private static Move[] firebugMoves = {Moves.attack, Moves.spit};
	private static int[] firebugStats = {0,15,-5,0};
	public static Profession firebug = new ProfessionBuilder().name("Firebug").moves(firebugMoves).stats(firebugStats).buildProfession();
	
	private static Move[] mudbugMoves = {Moves.attack, Moves.debuffSpeed};
	private static int[] mudbugStats = {0,15,-5,0};
	public static Profession mudbug = new ProfessionBuilder().name("Mudbug").moves(mudbugMoves).stats(mudbugStats).buildProfession();
	
}
