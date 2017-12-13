package com.mygdx.fighters.units;

public class Races {

	private static Profession[] humanProfessions = {Professions.warrior, Professions.elite, Professions.healer, Professions.scout};
	private static int[] humanStats = {5, 5, 5, 5};
	public static Race human = new RaceBuilder().name("Human").professions(humanProfessions).stats(humanStats).buildRace();
	
	private static Profession[] dwarfProfessions = {Professions.warrior, Professions.elite, Professions.crossbow};
	private static int[] dwarfStats = {5, 10, 0, 10};
	public static Race dwarf = new RaceBuilder().name("Dwarf").professions(dwarfProfessions).stats(dwarfStats).buildRace();
	
	private static Profession[] orkProfessions = {Professions.warrior, Professions.elite, Professions.shaman};
	private static int[] orkStats = {10, 10, 0, 5};
	public static Race ork = new RaceBuilder().name("Ork").professions(orkProfessions).stats(orkStats).buildRace();
	
	private static Profession[] undeadProfessions = {Professions.warrior, Professions.vampire};
	private static int[] undeadStats = {5, 10, 5, 0};
	public static Race undead = new RaceBuilder().name("Undead").professions(undeadProfessions).stats(undeadStats).buildRace();
	
	private static Profession[] animalProfessions = {Professions.warrior, Professions.firebug, Professions.mudbug};
	private static int[] animalStats = {5, 5, 10, 0};
	public static Race animal = new RaceBuilder().name("Animal").professions(animalProfessions).stats(animalStats).buildRace();
	
}
