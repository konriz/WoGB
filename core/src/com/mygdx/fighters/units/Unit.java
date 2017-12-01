package com.mygdx.fighters.units;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.fighters.Flag;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Placeable;
import com.mygdx.fighters.Team;
import com.mygdx.fighters.moves.Move;

/**
 * Class for storing backend data of each unit
 * Includes Character, posiotion and textures
 * @author konriz
 *
 */
public class Unit implements Draw{
	
	public static enum Direction{
		N,
		S,
		W,
		E
	}
	
	private Texture sprite, up, down, left, right, tableSelected;

	private Race race;
	private Profession profession;
	
	private boolean moving;
	private Move move;
	
	private int percent;
	private Team team;
	private Unit.Direction facing;
	
	// position in TILES!
	/**
	 * Creates Unit of with character in team, on position x_pos y_pos (tiles)!
	 * @param character - unit's character (statistics, equipment etc)
	 * @param team - team unit is in
	 * @param x_pos - horizontal position
	 * @param y_pos - vertical position
	 */
	public Unit(Race r, Profession p)
	{
		GameData.field.getField(this.pos).setOccupation(this);
		this.moving = false;
		this.move = null;
				
		/*
		 * Part responsible for loading images and creating animations;
		 * 
		 */

		String path = getPath();
		
		up = new Texture(Gdx.files.internal(path +"/bk.gif"));
		down = new Texture(Gdx.files.internal(path +"/fr.gif"));
		left = new Texture(Gdx.files.internal(path +"/lt.gif"));
		right = new Texture(Gdx.files.internal(path +"/rt.gif"));
		
		tableSelected = new Texture(Gdx.files.internal(path +"/ts.gif"));
					
		sprite = down;
					
	}
	
	public String getPath()
	{
		String path = "sprites/" + race.getPath() + "/" + profession.getPath();
		return path;
	}
	
	public Team getTeam()
	{
		return team;
	}
	
	public boolean isHit()
	{
		return this.hit;
	}
	
	public void setHit(boolean hit)
	{
		this.hit = hit;
	}

	public Texture getSprite() {
		return sprite;
	}
	
	public Texture getSelected() {
		return tableSelected;
	}
	
	public int getHpPercent()
	{
		percent = (this.getCharacter().getCurrentHP() * 100) / ((this.getCharacter().getMaxHP()));
		return percent;
	}
	
	public int getApPercent()
	{
		percent = (this.getCharacter().getCurrentAP() * 100) / ((this.getCharacter().getMaxAP()));
		return percent;
	}
	
	public void setSprite(Texture sprite) {
		this.sprite = sprite;
	}

	public Texture getUp() {
		return up;
	}

	public Texture getDown() {
		return down;
	}

	public Texture getLeft() {
		return left;
	}

	public Texture getRight() {
		return right;
	}
	
	/**
	 * Checks if unit has enough AP to move from given field
	 * @param tileID - ID of tile from move layer(2)
	 * @return true if unit can move, false if not
	 */
	public boolean canMove()
	{

		int moveAP = GameData.map.getAPCost(this.pos);
		if (this.getCharacter().getCurrentAP() >= moveAP) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public void step(Direction dir)
	{

	this.rotate(dir);
	GameData.field.getField(getPos()).clearOccupation();
	int moveAP = GameData.map.getAPCost(this.pos);

		switch (dir)
		{
		case S:
			{
				getPos()[1] --;
				this.getCharacter().dropCurrentAP(moveAP);
			}
			break;
		case W:
			{
				getPos()[0] --;
				this.getCharacter().dropCurrentAP(moveAP);
			}
			break;
		case E:
			{
				getPos()[0] ++;
				this.getCharacter().dropCurrentAP(moveAP);
			}
			break;
		case N:
			{
				getPos()[1] ++;
				this.getCharacter().dropCurrentAP(moveAP);
			}
			break;

		}
		this.setDirections();
		GameData.field.getField(getPos()).setOccupation(this);
	}
	
	public void move(int[] position)
	{
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
						GameData.selected.getCharacter().dropCurrentAP(apCost);
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
				this.getCharacter().dropCurrentAP(GameData.map.getAPCost(this.pos));
				targetFlag.setOwner(GameData.getActive());
			}
		}
		
		else if (target instanceof Unit)
		{
			Unit targetUnit = (Unit) target;
			if (GameData.isEnemy(targetUnit))
			{
				this.useMove(this.getCharacter().getMoves().get(0), targetUnit);
				return;
			}
		}
			
			// Update current unit state
			this.setDirections();
			GameData.field.getField(getPos()).setOccupation(this);
		
	}
	

	public void setFacing(Direction direction)
	{
		this.facing = direction;
	}
	
	public Direction getFacing()
	{
		return this.facing;
	}
	
	public void rotate(Direction dir)
	{
		this.setFacing(dir);
		switch (dir)
		{
		case S:
			this.setSprite(this.getDown());
			
			break;
		case W:
			this.setSprite(this.getLeft());
			break;
		case E:
			this.setSprite(this.getRight());
			break;
		case N:
			this.setSprite(this.getUp());
			break;
		}	
	}
	
	public void rotate(int[] dir)
	{
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
	
	/**
	 * Allows movement to adjecent sqare in direction (dir) if it is not blocked (tileType == 0) and no one is on it (occupation == -1)
	 * 
	 * @param dir - enum Direction.dir for movement direction
	 */

	public boolean useMove(Move move, Unit target)
	{
		if(move.getApCost() > getCharacter().getCurrentAP())
		{
			return false;	
		}
		else
		{
			move.useOn(target);
		}
		return true;
	}
	
	public void setBoosted(boolean b) {
		this.boosted = b;
	}
	
	public boolean isBoosted()
	{
		return boosted;
	}

	/**
	 * resets unit's AP
	 */
	public void rest()
	{
		this.getCharacter().resetAP();
	}

	public boolean isMoving() {
		return moving;
	}
	
	public void setMoving(boolean b)
	{
		this.moving = false;
	}
	
	public void setMove(Move m)
	{
		this.move = m;
		if (m.getApCost() > this.getCharacter().getCurrentAP())
		{
			System.out.println("Not enough AP");
			this.moving = false;
		}
		else
		{
			this.moving = true;
		}
		
	}
	
	public Move getMove()
	{
		return this.move;
	}
	

}
