package com.mygdx.fighters;

public class GridField {

	private Grid[][] field;
	
	public GridField()
	{
		int mapWidth = GameData.map.getMapWidth();
		int mapHeight = GameData.map.getMapHeight();
		field = new Grid[mapWidth][mapHeight];
		
		for (int i = 0; i < mapWidth; i++)
		{
			for (int j = 0; j < mapHeight; j++)
			{
				field[i][j] = new Grid(i, j);
			}
		}
	}
	
	public Grid getField(int[] pos)
	{
		return this.field[pos[0]][pos[1]];
	}
	
}
