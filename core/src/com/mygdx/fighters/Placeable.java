package com.mygdx.fighters;

public class Placeable {

	protected int[] pos;
	protected int[][] directions;
	protected int[][] range;
	
	public Placeable(int x, int y)
	{
		pos = new int[2];
		pos[0] = x;
		pos[1] = y;
		setDirections();
	}

	public int[] getPos() {
		return pos;
	}

	public void setPos(int[] pos) {
		GameData.field.getField(pos).clearOccupation();
		this.pos = pos;
		GameData.field.getField(pos).setOccupation(this);
		this.setDirections();
	}
	
	public boolean samePos(Placeable p)
	{
		if(this.getPos()[0] == p.getPos()[0] && this.getPos()[1] == p.getPos()[1])
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean samePos(int[] pos)
	{
		if(this.getPos()[0] == pos[0] && this.getPos()[1] == pos[1])
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//TODO interface ranged
	
	public int[][] getRange()
	{	
		return range;
	}
	
	public void setRange(int[][] range)
	{
		this.range = range;
	}
	
	public int[][] range(int radius)
	{
		int dim = 2 * radius + 1;
		int[][] range = new int[dim * dim][2];
		
		if (radius == 0)
		{
			range[0] = getPos();
			return range;
		}
				
		int i = 0;
		while (i < range.length)
		{
			for (int yOffset = -1*radius; yOffset <= radius; yOffset++)
			{
				for (int xOffset = -1*radius; xOffset <= radius; xOffset++)
				{
					range[i][0] = pos[0] + xOffset;
					range[i][1] = pos[1] + yOffset;
					i++;
				}
			}
		}
		return range;
	}
	
	public int[][] getDirections()
	{
		return this.directions;
	}
		
	public void setDirections()
	{
		directions = new int[8][2];
		
		// TODO loop this!
		
		directions[0][0] = pos[0] - 1;
		directions[0][1] = pos[1] + 1;
		
		directions[1][0] = pos[0];
		directions[1][1] = pos[1] + 1;

		directions[2][0] = pos[0] + 1;
		directions[2][1] = pos[1] + 1;
		
		directions[3][0] = pos[0] - 1;
		directions[3][1] = pos[1];
		
//		directions[4][0] = pos[0];
//		directions[4][1] = pos[1];
		
		directions[4][0] = pos[0] + 1;
		directions[4][1] = pos[1];
		
		directions[5][0] = pos[0] - 1;
		directions[5][1] = pos[1] - 1;
		
		directions[6][0] = pos[0];
		directions[6][1] = pos[1] - 1;
		
		directions[7][0] = pos[0] + 1;
		directions[7][1] = pos[1] - 1;

	}
}
