package com.mygdx.fighters.units;

public class Statistics {

	private int str;
	private int end;
	private int dex;
	private int mel;
	private int hp;
	private int ap;
	private int res;
	private int dam;
	private int carry;
	
	public Statistics(Race r, Profession p)
	{
		str = 10 + r.getStr() + p.getStr();
		end = 10 + r.getEnd() + p.getEnd();
		dex = 10 + r.getDex() + p.getDex();
		mel = 10 + r.getMel() + p.getMel();
		
		hp = 20 + end;
		ap = dex / 5;
		res = end / 5;
		dam = str / 5;
		carry = str;
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

	public int getHp() {
		return hp;
	}

	public int getAp() {
		return ap;
	}

	public int getRes() {
		return res;
	}

	public int getDam() {
		return dam;
	}

	public int getCarry() {
		return carry;
	}
	
}
