package com.mygdx.fighters;

import com.mygdx.fighters.moves.Move;
import com.mygdx.fighters.units.Character;
import com.mygdx.fighters.units.Unit;
import com.mygdx.fighters.units.Unit.Direction;

public class Soldier extends Placeable implements Fight {
	
	private Character character;
	private Unit unit;
	private Team team;
	
	private Direction facing = Direction.S;
	
	private boolean boosted = false;
	private boolean hit = false;
	private boolean moving = false;
	
	private Move move;

	public Soldier(Character character, Unit unit, Team team)
	{
		super();
		this.character = character;
		this.unit = unit;
		this.team = team;
	}
	
	public Character getCharacter() {
		return this.character;
	}
	
	public Unit getUnit()
	{
		return this.unit;
	}
	
	public Team getTeam()
	{
		return this.team;
	}

	public int getHpPercent() {
		return character.getStats().getHpPercent();
	}

	public int getApPercent() {
		return character.getStats().getApPercent();
	}

	public boolean canMove() {
		if (this.getCharacter().getStats().getCurrentAP() >= GameData.map.getAPCost(this.pos)) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	public void step(Direction dir) {
		this.rotate(dir);
		GameData.field.getField(getPos()).clearOccupation();
		int moveAP = GameData.map.getAPCost(this.pos);

			switch (dir)
			{
			case S:
				{
					getPos()[1] --;
					this.getCharacter().getStats().dropCurrentAP(moveAP);
				}
				break;
			case W:
				{
					getPos()[0] --;
					this.getCharacter().getStats().dropCurrentAP(moveAP);
				}
				break;
			case E:
				{
					getPos()[0] ++;
					this.getCharacter().getStats().dropCurrentAP(moveAP);
				}
				break;
			case N:
				{
					getPos()[1] ++;
					this.getCharacter().getStats().dropCurrentAP(moveAP);
				}
				break;

			}
			this.setDirections();
			GameData.field.getField(getPos()).setOccupation(this);

	}

	@Override
	public void move(int[] position) {
		Placeable target = GameData.occupation(position);
		if (target == null && this.canMove())
		{
			GameData.field.getField(getPos()).clearOccupation();
			int apCost = GameData.map.getAPCost(GameData.selected.getPos());
			int index = 0;
			while (index < 8)
			{
				if (this.directions[index][0] == position[0] && this.directions[index][1] == position[1])
				{
					// Rotate direction; can be changed to 8-directional character
					if (index == 0 || index == 1 || index == 2)
					{
						this.rotate(Direction.N);
					}
					else if (index == 3)
					{
						this.rotate(Direction.W);
					}
					else if (index == 4)
					{
						this.rotate(Direction.E);
					}
					else if (index == 5 || index == 6 || index == 7)
					{
						this.rotate(Direction.S);
					}
					
					// Check if unit can move, if not print error
					
					if (GameData.map.isBlocked(position))
					{
						System.out.println("Spot blocked!");
					}
					
					// Move
					else
					{
						GameData.selected.getCharacter().getStats().dropCurrentAP(apCost);
						this.setPos(position);
					}
					break;
				}
				index ++;
			}
		}
		
		// Capture the flag!
		else if (target instanceof Flag)
		{
			Flag targetFlag = (Flag) target;
			if (targetFlag.getOwner().getName() != GameData.selected.getTeam().getName())
			{
				this.getCharacter().getStats().dropCurrentAP(GameData.map.getAPCost(this.pos));
				targetFlag.setOwner(GameData.getActive());
			}
		}
		
		else if (target instanceof Soldier)
		{
			Soldier targetUnit = (Soldier) target;
			if (GameData.isEnemy(targetUnit))
			{
				this.useMove(this.getCharacter().getMoves()[0], targetUnit);
				return;
			}
		}
			
			// Update current unit state
			this.setDirections();
			GameData.field.getField(getPos()).setOccupation(this);

	}

	@Override
	public void rotate(Direction dir) {
		this.setFacing(dir);
	}

	@Override
	public void rotate(int[] dir) {
		if (dir[0] == this.pos[0] && dir[1] > this.pos[1])
		{
			this.rotate(Direction.N);
		}
		else if (dir[0] == this.pos[0] && dir[1] < this.pos[1])
		{
			this.rotate(Direction.S);
		}
		else if (dir[0] > this.pos[0] && dir[1] == this.pos[1])
		{
			this.rotate(Direction.E);
		}
		else if (dir[0] < this.pos[0] && dir[1] == this.pos[1])
		{
			this.rotate(Direction.W);
		}

	}

	public void setFacing(Direction direction) {
		this.facing = direction;
	}

	public Direction getFacing() {
		return this.facing;
	}

	public void setMoving(boolean b) {
		this.moving = false;

	}

	public boolean isMoving() {
		return this.moving;
	}

	public void setMove(Move m) {
		this.move = m;
		this.setRange(range(move.getRange()));
		if (m.getApCost() > this.getCharacter().getStats().getCurrentAP())
		{
			System.out.println("Not enough AP");
			this.moving = false;
		}
		else
		{
			this.moving = true;
		}

	}

	public Move getMove() {
		return this.move;
	}
	
	public boolean useMove(Move move, Soldier target) {
		if(move.getApCost() > getCharacter().getStats().getCurrentAP())
		{
			return false;	
		}
		else
		{
			move.useOn(target);
		}
		return true;
	}

	public boolean isHit()
	{
		return this.hit;
	}
	
	public void setHit(boolean hit)
	{
		this.hit = hit;
	}
	
	public void setBoosted(boolean b) {
		this.boosted = b;

	}

	public boolean isBoosted() {
		return boosted;
	}
	
	public boolean checkAlive()
	{
		if (character.getStats().getCurrentHP() <= 0)
		{
			character.setAlive(false);
			GameData.field.getField(pos).clearOccupation();
			return false;
		}
		else
			return true;
	}

	public void rest() {
		this.getCharacter().getStats().rest();

	}
	

}
