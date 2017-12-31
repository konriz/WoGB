package com.mygdx.fighters.networking;

import com.mygdx.fighters.entities.Fraction;
import com.mygdx.fighters.entities.Player;
import com.mygdx.fighters.entities.Team;
import com.mygdx.fighters.networking.gui.NetPlayersDialog;

public class PlayerCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int side;
	private Fraction team;
	
	public void setName(String name) {
		this.name = name;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public void setTeam(Fraction team) {
		this.team = team;
	}
	
	public Team getTeam()
	{
		Team team = this.team.getTeam();
		team.setPlayer(new Player(this.name, this.side));
		return team;
	}

	@Override
	public void perform() {
		NetPlayersDialog.playersList.add(getTeam());
	}

}
