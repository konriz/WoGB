package com.mygdx.fighters;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.entities.Flag;
import com.mygdx.fighters.entities.Placeable;
import com.mygdx.fighters.entities.Soldier;
import com.mygdx.fighters.entities.Team;
import com.mygdx.fighters.entities.Teams;
import com.mygdx.fighters.gui.FightersGame;
import com.mygdx.fighters.messaging.Console;
import com.mygdx.fighters.messaging.messages.TurnMessage;

public class GameData {

	public static String mapName;
	public static MapData map;
	public static GridField field;

	public static int phase;
	
	public static Teams teams;
	public static int activeIndex;
	public static int selectedIndex = 0;
	public static Soldier selected;
	
	public static Array<Flag> flags;
	
	public static int turn = 1;

	public static int victoryPoints;
	public static int unitsPoints;
	
	public static Console console;
	
	public GameData()
	{
		phase = 0;
		console = new Console();
		console.add("Welcome to WoGB!");
	}
	
	public static void selectMap(String mapName)
	{
		GameData.map = new MapData(mapName);
		GameData.mapName = map.getName();
		GameData.field = new GridField();
	}
	
	public static Team getActive()
	{
		return teams.get(activeIndex);
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
			int index = getActive().indexOf(target);
			if(index > -1)
			{
				selectedIndex = index;
				select(target);
			}
		}
	}
	
	public void select(Soldier soldier)
	{
		selected = soldier;
		selected.setDirections();
	}
	
	public void selectNext()
	{
		if (selectedIndex >= teams.get(activeIndex).size())
		{
			selectedIndex = 0;
		}
		
		Soldier s = getActive().get(selectedIndex);
		if (s.getCharacter().isAlive() && selected != s)
		{
			select(s);
		}
		else
		{
			selectedIndex ++;
			selectNext();
		}
	}
	
	/**
	 * Switches active to next team
	 * @return Index of current active team
	 */
	public int switchActive()
	{
		activeIndex ++;
		
		if (activeIndex == teams.size)
		{
			activeIndex = 0;
		}
		
		return activeIndex;
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
				GameData.console.add(new TurnMessage());
			}
			
			else
			{
				getActive().setAlive(false);
				FightersGame.screen.endGame(teams.get(switchActive()));
				// TODO check if all teams dead
//				skipTurn();
			}
		}
	}
	
	public static Placeable occupation(int[] target)
	{
		return field.getField(target).getOccupation();
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
		activeIndex = 0;
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
