package com.mygdx.fighters.networking;

import com.mygdx.fighters.GameData;

public class InitDataCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mapName;
	private int unitsPoints;
	private int victoryPoints;

	public InitDataCommand()
	{
		mapName = GameData.map.getName();
		unitsPoints = GameData.unitsPoints;
		victoryPoints = GameData.victoryPoints;
	}
	
	@Override
	public void perform() {
		GameData.mapName = mapName;
		GameData.unitsPoints = unitsPoints;
		GameData.victoryPoints = victoryPoints;
		
		System.out.println("GameData updated!");

	}

}
