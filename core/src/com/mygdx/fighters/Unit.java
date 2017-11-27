package com.mygdx.fighters;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.fighters.Character;
import com.mygdx.fighters.Dice;
import com.mygdx.fighters.Move;

/**
 * Class for storing backend data of each unit
 * Includes Character, posiotion and textures
 * @author konriz
 *
 */
public class Unit extends Placeable{
	
	private Texture sprite, up, down, left, right, tableSelected;
	private Character character;

	private boolean hit;
	
	private int percent;
	private Team team;
	private Enums.Direction facing;
	
	// position in TILES!
	/**
	 * Creates Unit of with character in team, on position x_pos y_pos (tiles)!
	 * @param character - unit's character (statistics, equipment etc)
	 * @param team - team unit is in
	 * @param x_pos - horizontal position
	 * @param y_pos - vertical position
	 */
	public Unit(Race r, Profession p, Team team, int xPos, int yPos)
	{
		super(xPos, yPos);
		this.character = new Character(r, p);
		this.team = team;
		this.hit = false;
		GameData.field.getField(this.pos).setOccupation(this);
				
		/*
		 * Part responsible for loading images and creating animations;
		 * 
		 */

		String path = "sprites/" + this.character.getPath();
		
		up = new Texture(Gdx.files.internal(path +"/bk.gif"));
		down = new Texture(Gdx.files.internal(path +"/fr.gif"));
		left = new Texture(Gdx.files.internal(path +"/lt.gif"));
		right = new Texture(Gdx.files.internal(path +"/rt.gif"));
		
		tableSelected = new Texture(Gdx.files.internal(path +"/ts.gif"));
					
		sprite = down;
					
	}
	
	public Unit(Race r, Profession p, Team team)
	{
		this(r, p, team, 1, 1);
	}
	
	public Character getCharacter()
	{
		return this.character;
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
	
	public Texture getTableSelected() {
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
	
	public void step(Enums.Direction dir)
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
		this.setRange(this.getCharacter().getCurrentAP());
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
						this.rotate(Enums.Direction.N);
					}
					else if (index == 3)
					{
						this.rotate(Enums.Direction.W);
					}
					else if (index == 4)
					{
						this.rotate(Enums.Direction.E);
					}
					else if (index == 5 || index == 6 || index == 7)
					{
						this.rotate(Enums.Direction.S);
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
				this.hit(targetUnit);
				return;
			}
		}
			
			// Update current unit state
			this.setRange(this.getCharacter().getCurrentAP());
			this.setDirections();
			GameData.field.getField(getPos()).setOccupation(this);
		
	}
	

	public void setFacing(Enums.Direction direction)
	{
		this.facing = direction;
	}
	
	public Enums.Direction getFacing()
	{
		return this.facing;
	}
	
	public void rotate(Enums.Direction dir)
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
			this.rotate(Enums.Direction.N);
		}
		else if (dir[0] == this.pos[0] && dir[1] < this.pos[1])
		{
			this.rotate(Enums.Direction.S);
		}
		else if (dir[0] > this.pos[0] && dir[1] == this.pos[1])
		{
			this.rotate(Enums.Direction.E);
		}
		else if (dir[0] < this.pos[0] && dir[1] == this.pos[1])
		{
			this.rotate(Enums.Direction.W);
		}
	}
	
	/**
	 * Allows movement to adjecent sqare in direction (dir) if it is not blocked (tileType == 0) and no one is on it (occupation == -1)
	 * 
	 * @param dir - enum Direction.dir for movement direction
	 */

	
	/**
	 * Hits target unit 
	 * @param target - target enemy
	 * @return true if enemy was hit
	 */
	public boolean hit(Unit target)
	{
		Character attacker = getCharacter();
		Character defender = target.getCharacter();
		
		Move move = new Move();
		int moveCost = move.getApCost();
		int currentAP = attacker.getCurrentAP();
		
		if(moveCost > currentAP) {return false;}
		attacker.dropCurrentAP(moveCost);
		
		/* TODO implement various move powers! */
		int movePower = move.getApCost();
		
		/* TODO let the weapon modify dice roll */
		int damage = (attacker.getToHit() + Dice.use(6)) * movePower;
		
		target.setHit(true);
		defender.dropCurrentHP(damage);
		defender.checkAlive();
		return true;
		
	}
	
	/**
	 * resets unit's AP
	 */
	public void rest()
	{
		this.getCharacter().resetAP();
	}

}