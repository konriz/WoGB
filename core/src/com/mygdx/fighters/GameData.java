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
	public static Soldier selected;
	
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
		if (occupation(clickTile) instanceof Soldier)
		{
			Soldier target = (Soldier) occupation(clickTile);
			
			if(getActive().contains(target))
			{
				select(target);
			}
		}
	}
	
	public void select(Soldier soldier)
	{
		selected = soldier;
		selected.setDirections();
	}
	
	/**
	 * Switches active to next team
	 * @return Index of current active team
	 */
	public int switchActive()
	{
		active ++;
		
		if (active == teams.size)
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
				if (teams.areDeployed())
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
						maxPoints[1] = teams.indexOf(t, false);
					}
				}
				if (maxPoints[0] >= victoryPoints)
				{
					FightersGame.screen.endGame(teams.get(maxPoints[1]));
				}
			}
			
			if (getActive().getAll().stream().filter(u->u.getCharacter().isAlive()).findFirst().isPresent())
			{
				getActive().getAll().stream().filter(u->u.getCharacter().isAlive()).forEach(u->u.rest());
				select(getActive().getAll().stream().filter(u->u.getCharacter().isAlive()).findFirst().get());
			}
			
			else
			{
				getActive().setAlive(false);
				FightersGame.screen.endGame(teams.get(switchActive())); // TODO check if all teams dead
//				skipTurn();
			}
		}
	}
	
	public static Placeable occupation(int[] target)
	{
// TODO get occupation data from map field!
		for (Team t : teams)
		{
			for (Soldier s : t.getAll())
			{
				if (s.samePos(target) && s.getCharacter().isAlive())
				{
						return s;
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
	
	public static boolean isEnemy(Soldier target)
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
			Soldier s = getActive().get(getActive().size() - unitCount);
			if (s.getCharacter().isAlive() && s.getApPercent() > 0 && s != selected )
			{
				select(s);
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
