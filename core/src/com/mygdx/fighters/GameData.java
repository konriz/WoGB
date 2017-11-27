package com.mygdx.fighters;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.gui.FightersGame;

public class GameData {

	public static MapData map;
	public static GridField field;

	public static FightersGame game;
	
	public static int phase;
	
	public static Teams teams;
	public static int active;
	public static Unit selected;
	
	public static Array<Flag> flags;
	
	public static int turn = 1;

	public static int victoryPoints;
	public static int unitsPoints;
	
	
	public GameData()
	{
		GameData.game = FightersGame.game;
		phase = 0;
	}
	
	public static void selectMap(String mapName)
	{
		GameData.map = new MapData("maps/" + mapName);
		GameData.field = new GridField();
	}
	
	public static Team getActive()
	{
		return teams.get(active);
	}
	
	public MapData getMapData()
	{
		return map;
	}
	
	public TiledMap getMap()
	{
		return map.getMap();
	}
	
	public void select(int[] clickTile)
	{
		
		for (Unit unit : getActive())
		{
			//if (unit.getPos()[0] == clickTile[0] && unit.getPos()[1] == clickTile[1] && unit.getCharacter().isAlive())
			if (unit.getPos().equals(clickTile) && unit.getCharacter().isAlive())
			{
				select(unit);
			}
		}
	}
	
	public void select(Unit unit)
	{
		selected = unit;
		selected.setRange(selected.getCharacter().getCurrentAP());
		selected.setDirections();
	}
	
	/**
	 * Switches active to next team
	 * @return Index of current active team
	 */
	public int switchActive()
	{
		active ++;
		
		if (active == teams.size())
		{
			active = 0;
		}
		
		return active;
	}
	
	public void skipTurn()
	{
		
		if (phase == 0)
		{
			if(switchActive() == 0)
			{
				advance();
				select(getActive().get(0));
			}
		}
		
		else if (phase == 1)
		{
			if (switchActive() == 0)
			{
				turn ++;
			}
			
			if (getActive().isDeployed())
			{
				if (teams.get(0).isDeployed() && teams.get(1).isDeployed())
				{
					advance();
					select(getActive().get(0));
					FightersGame.multiplexer.setCombat();
				}
				else
				{
					skipTurn();
				}
			}

		}
		
		
		else if (phase == 2)
		{
			
			if (switchActive() == 0)
			{
				turn ++;
				pointFlags();
				// max points in index [0] of team from index [1]
				int [] maxPoints = { 0 , -1};
				for (Team t : teams)
				{
					if (t.getPoints() > maxPoints[0])
					{
						maxPoints[0] = t.getPoints();
						maxPoints[1] = teams.indexOf(t);
					}
				}
				if (maxPoints[0] >= victoryPoints)
				{
					FightersGame.screen.endGame(teams.get(maxPoints[1]));
				}
			}
			
			int dead = 0;
			for (Unit u : getActive())
			{
				if (u.getCharacter().isAlive())
				{
					u.rest();
					select(u);
				}
				else
				{
					dead ++;
				}
			}
			if (dead == getActive().size())
			{
				FightersGame.screen.endGame(teams.get(switchActive()));
			}
		}
	}
	
	public static Placeable occupation(int[] target)
	{

		for (Team t : teams)
		{
			for (Unit u : t)
			{
				if (u.samePos(target) && u.getCharacter().isAlive())
				{
						return u;
				}			
			}
		}
		
		for (Flag f : flags)
		{
			if (f.samePos(target))
			{
				return f;
			}
		}
		return null;
	}
	
	public static boolean isEnemy(Unit target)
	{
		if (getActive().contains(target)){
			return false;
		}
		else return true;
	}

	
	public static void resetTurn()
	{
		GameData.turn = 1;
		active = 0;
	}
		
	public void selectNext()
	{
		int unitCount = getActive().size();
		while (unitCount > 0)
		{
			Unit u = getActive().get(getActive().size() - unitCount);
			if (u.getCharacter().isAlive() && u.getApPercent() > 0 && u != selected )
			{
				select(u);
				return;
			}
			unitCount --;
		}
		System.out.println("Side " + getActive().getName() + " can't move! Pass turn!");
		return;
	}
	
	public void advance()
	{
		phase ++;
		resetTurn();
	}

	public boolean isFlag(int[] target) {
		
		for (Flag f : flags)
		{
			if (f.samePos(target))
			{
				return true;
			}
		}
		return false;
	}

	public Flag getFlag(int[] target) {
		
		for (Flag f : flags)
		{
			if (f.samePos(target))
			{
				return f;
			}
		}
		return null;
	}
	
	public void pointFlags()
	{
			for (Flag f : flags)
			{
				Team owner = f.getOwner();
				
				if (owner != null)
				{
					owner.addPoint();
				}
			}
	}
}
