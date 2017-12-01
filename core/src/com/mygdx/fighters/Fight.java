package com.mygdx.fighters;

import com.mygdx.fighters.moves.Move;
import com.mygdx.fighters.units.Character;
import com.mygdx.fighters.units.Unit;
import com.mygdx.fighters.units.Unit.Direction;

public interface Fight {
	
	public Character getCharacter();
	public Unit getUnit();
	public Team getTeam();
	
	public int getHpPercent();
	public int getApPercent();
	
	public boolean canMove();
	public void step(Direction dir);
	public void move(int[] position);
	public void rotate(Direction dir);
	public void rotate(int[] dir);
	
	public void setFacing(Direction direction);
	public Direction getFacing();
	
	public boolean useMove(Move move, Soldier target);
	
	public void setBoosted(boolean b);
	public boolean isBoosted();
	
	public void setMoving(boolean b);
	public boolean isMoving();
	
	public void setMove(Move m);
	public Move getMove();
	
	public void rest();
	
}
