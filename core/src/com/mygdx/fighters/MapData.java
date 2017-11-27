package com.mygdx.fighters;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;

public class MapData {
	
	private TiledMap map;
	private MapProperties prop;
	private int mapWidth, mapHeight, players;
	public static int tileSize;
	private Array<Flag> flags;

	public TiledMap getMap()
	{
		return map;
	}
	
	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public MapData(String path)
	{
		map = new TmxMapLoader().load(path);
		prop = map.getProperties();
		players = (Integer) prop.get("players");
		mapWidth = prop.get("width", Integer.class);
		mapHeight = prop.get("height", Integer.class);
		tileSize = prop.get("tilewidth", Integer.class);
	}
	
	public Array<Integer> getPlayers()
	{
		int[] maxPlayers = {2, 3, 4};
		
		Array<Integer> posPlayers = new Array<Integer>();
		int i = -1;
		do
		{
			i++;
			posPlayers.add(maxPlayers[i]);
			
		}
		while (i < this.players - 2);
		
		return posPlayers;
	}
	
	public void setFlags()
	{
		flags = new Array<Flag>();
		for (int xPos = 0; xPos < mapWidth; xPos ++)
		{
			for (int yPos = 0; yPos < mapHeight; yPos ++)
			{
				
				if ((((TiledMapTileLayer)this.map.getLayers().get(2)).getCell(xPos, yPos) != null))
				{
					int targetFlagOwner = (Integer) ((TiledMapTileLayer)this.map.getLayers().get(2)).getCell(xPos, yPos).getTile().getProperties().get("side");
					
					if (targetFlagOwner == -1)
					{
						flags.add(new Flag(xPos, yPos));
					}
					else if (targetFlagOwner < GameData.teams.size)
					{
						flags.add(new Flag(xPos, yPos, GameData.teams.get(targetFlagOwner)));
					}
				}
			}
		}
		GameData.flags = flags;
	}
	
	public Array<Flag> getFlags()
	{
		return this.flags;
	}
	
	public int getAPCost(int [] target)
	{
		
		try
		{
			int mod = (Integer) ((TiledMapTileLayer)this.map.getLayers().get(0)).getCell(target[0], target[1]).getTile().getProperties().get("moveAP");
			return mod;
		}
		catch (NullPointerException e)
		{
			return 1;
		}
	}
	
	public boolean isBlocked(int[] pos)
	{

		if (((TiledMapTileLayer)this.map.getLayers().get(1)).getCell(pos[0], pos[1]) == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
