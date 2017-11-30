package com.mygdx.fighters.gui;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.MapData;
import com.mygdx.fighters.Unit;
import com.mygdx.fighters.gui.UI.MovesDialog;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

public class FightProcessor implements InputProcessor {

	private GameData gameData;
	private OrthographicCamera camera;
	
	public FightProcessor()
	{
		this.gameData =  FightersGame.data;
		this.camera = FightersGame.screen.getMapCamera();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		int[] target = new int[2];
		target[0] = GameData.selected.getPos()[0];
		target[1] = GameData.selected.getPos()[1];
		
		switch (keycode)
		{
			
		// Arrows steering block
		
			case Keys.UP:
			{
				target[1] += 1;
				GameData.selected.move(target);
				break;
			}
			
			case Keys.DOWN:
			{
				target[1] -= 1;
				GameData.selected.move(target);
				break;
			}
			
			case Keys.LEFT:
			{
				target[0] -= 1;
				GameData.selected.move(target);
				break;
			}
			
			case Keys.RIGHT:
			{
				target[0] += 1;
				GameData.selected.move(target);
				break;
			}
			
		// Numpad steering block
			
			case Keys.NUMPAD_7:
			{
				target[0] -= 1;
				target[1] += 1;
				GameData.selected.move(target);
				break;
			}
			
			case Keys.NUMPAD_8:
			{
				target[0] += 0;
				target[1] += 1;
				GameData.selected.move(target);
				break;
			}
			case Keys.NUMPAD_9:
			{
				target[0] += 1;
				target[1] += 1;
				GameData.selected.move(target);
				break;
			}
			case Keys.NUMPAD_4:
			{
				target[0] -= 1;
				target[1] += 0;
				GameData.selected.move(target);
				break;
			}
			case Keys.NUMPAD_6:
			{
				target[0] += 1;
				target[1] += 0;
				GameData.selected.move(target);
				break;
			}
			case Keys.NUMPAD_1:
			{
				target[0] -= 1;
				target[1] -= 1;
				GameData.selected.move(target);
				break;
			}
			case Keys.NUMPAD_2:
			{
				target[0] += 0;
				target[1] -= 1;
				GameData.selected.move(target);
				break;
			}
			case Keys.NUMPAD_3:
			{
				target[0] += 1;
				target[1] -= 1;
				GameData.selected.move(target);
				break;
			}

		// Additional control keys
			
			case Keys.SPACE:
			{
				gameData.skipTurn();
				break;
			}
			
			case Keys.TAB:
			{
				gameData.selectNext();
				break;
			}
			
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
		
		int[] clickTile = {x/MapData.tileSize, y/MapData.tileSize};
		
		if (button == Buttons.RIGHT)
		{
			GameData.selected.setMoving(false);
			return true;
		}
		
		
		else if (GameData.occupation(clickTile) instanceof Unit)
		{
			Unit target = (Unit) GameData.occupation(clickTile);
			
			if (GameData.selected.isMoving())
			{
				// TODO disable friendly fire and enemy healing - or not?
				// TODO range of attack!
				GameData.selected.getMove().tryOn(target);
				return true;
			}
			else if (target.equals(GameData.selected))
			{
				MovesDialog moves = new MovesDialog();
				MainScreen.stage.addActor(moves);
				moves.show(MainScreen.stage);
				return true;
			}
			else if (GameData.getActive().contains(target))
			{
				gameData.select(target);
				return true;
			}
			else
			{
				for(int[] pos : GameData.selected.range(1))
				{
					if(Arrays.equals(pos, clickTile))
					{
						GameData.selected.move(clickTile);
						return true;
					}
				}
				return true;
			}
		}
		else
		{
			GameData.selected.move(clickTile);
			return true;
		}
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
		if (screenX > 900)
		{
			camera.translate(5, 0);
		}
		else if (screenX < 100)
		{
			camera.translate(-5, 0);
		}
		else if (screenY > 700)
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
