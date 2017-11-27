package com.mygdx.fighters;

import java.util.Random;

public class Dice {
	
	private static Random dice = new Random();
	
	public static int use(int k)
	{
		int result = dice.nextInt();
		if (result < 0) { result *= -1;}
		return (result % k) + 1;
	}
}
