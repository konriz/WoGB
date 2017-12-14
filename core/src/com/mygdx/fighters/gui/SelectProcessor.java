package com.mygdx.fighters.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.fighters.Flag;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.MapData;
import com.mygdx.fighters.Soldier;
import com.badlogic.gdx.Input.Keys;

public class SelectProcessor implements InputProcessor {

	private GameData gameData;
	private OrthographicCamera camera;
	
	public SelectProcessor()
	{
		this.gameData = FightersGame.data;
		
		this.camera = FightersGame.screen.getMapCamera();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		switch (keycode)
		{
			case Keys.ESCAPE:
			{
				MainMenu mainMenu = new MainMenu();
				MainScreen.stage.addActor(mainMenu);
				mainMenu.show(MainScreen.stage);
				break;
			}
			
			// map controls
			
			case Keys.W:
			{
				camera.translate(0, 32);
				break;
			}
			
			case Keys.S:
			{
				camera.translate(0, -32);
				break;
			}
			
			case Keys.A:
			{
				camera.translate(-32, 0);
				break;
			}
			
			case Keys.D:
			{
				camera.translate(32, 0);
				break;
			}
		}
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		int x = (int) (((screenX - Gdx.graphics.getWidth()/2.0) * camera.zoom + camera.position.x));
		int y = (int) ((Gdx.graphics.getHeight()/2.0 - screenY) * camera.zoom + camera.position.y );
		
		
		x = x / MapData.tileSize;
		y = y / MapData.tileSize;
		int [] pos = {x, y};
		
		try
		{
			if (x >= gameData.getMapData().getMapWidth() || y >= gameData.getMapData().getMapHeight() || x < 0 || y < 0)
			{
				System.out.println("Out of map!");
				return true;
			}
			
			if (gameData.getMapData().isBlocked(pos))
			{
				System.out.println("Cell blocked!");
			}
			else if (GameData.field.getField(pos).getOccupation() != null)
			{
				if (GameData.field.getField(pos).getOccupation() instanceof Soldier)
				{
					System.out.println("Unit already here!");
				}
				else if (GameData.field.getField(pos).getOccupation() instanceof Flag)
				{
					System.out.println("Flag already here!");
				}
				else
				{
					System.out.println("Object already here!");
				}
			}
			

			
			else if (GameData.field.getField(pos).getMaster().getOwner().getName().equals(GameData.getActive().getName()))
			{
				GameData.getActive().get(GameData.getActive().getDeployed()).setPos(pos);
				FightersGame.screen.addUnit(GameData.getActive().get(GameData.getActive().getDeployed()));
				GameData.getActive().deploy();
				gameData.skipTurn();
			}

			else
			{
				System.out.println("Place unit in Your flag range!");
			}
		}
		catch (NullPointerException e)
		{
			System.out.println("Place unit in Your flag range!");
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		if (screenX > Gdx.graphics.getWidth() - 100)
		{
			camera.translate(5, 0);
		}
		else if (screenX < 100)
		{
			camera.translate(-5, 0);
		}
		else if (screenY > Gdx.graphics.getHeight() - 100)
		{
			camera.translate(0, -5);
		}
		else if (screenY < 100)
		{
			camera.translate(0, 5);
		}
		

		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		float zoom = this.camera.zoom;
		if (zoom > 0.5 && zoom < 2)
		{
			this.camera.zoom += 0.1 * amount;
		}
		else if (zoom < 0.5 && amount > 0)
		{
			this.camera.zoom += 0.1 * amount;
		}
		else if (zoom > 2 && amount < 0)
		{
			this.camera.zoom += 0.1 * amount;
		}
		
		return true;
	}

}
