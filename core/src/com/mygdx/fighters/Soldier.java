package com.mygdx.fighters;

import com.mygdx.fighters.moves.Move;
import com.mygdx.fighters.units.Character;
import com.mygdx.fighters.units.Unit;
import com.mygdx.fighters.units.Unit.Direction;

public class Soldier implements Fight {
	
	private Character character;
	private Unit unit;

	@Override
	public Character getCharacter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHpPercent() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getApPercent() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void step(Direction dir) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(int[] position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rotate(Direction dir) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rotate(int[] dir) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFacing(Direction direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public Direction getFacing() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean useMove(Move move, Unit target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBoosted(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isBoosted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMoving(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isMoving() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMove(Move m) {
		// TODO Auto-generated method stub

	}

	@Override
	public Move getMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rest() {
		// TODO Auto-generated method stub

	}

}
