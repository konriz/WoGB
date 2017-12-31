package com.mygdx.fighters.gui;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.MapData;
import com.mygdx.fighters.entities.Flag;
import com.mygdx.fighters.entities.Placeable;
import com.mygdx.fighters.entities.Soldier;
import com.mygdx.fighters.entities.Team;
import com.mygdx.fighters.gui.UI.GameTable;

public class MainScreen implements Screen {
	
	private Skin skin;
	
	private SpriteBatch batch;
	private ArrayList<Soldier> soldiers;
	private Texture texture;
	
	private BarsTextures textures;
	private Texture hpBar, apBar, selected, dead, allowed, attackRange, attack, capture, hit, boosted;
	private Array<Texture> states, directions;
	
	private TiledMapRenderer mapRenderer;
	private OrthographicCamera cameraMenu;
	private OrthographicCamera cameraMap;
	
	private Viewport viewportMenu;
	private Viewport viewportMap;
	
	public static Stage stage;
	private GameTable table;
	
	public MainScreen ()
	{
		
		cameraMenu = new OrthographicCamera();
		viewportMenu = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cameraMenu);
		stage = new Stage(viewportMenu);
		
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		textures = new BarsTextures();
		directions = textures.getDirections();
		states = textures.getStates();
		selected = states.get(0);
		dead = states.get(1); 
		allowed = states.get(2);
		attackRange = states.get(3);
		attack = states.get(4);
		capture = states.get(5);
		hit = states.get(6);
		boosted = states.get(7);
		
		soldiers = new ArrayList<Soldier>();
		for (Team t : GameData.teams)
		{
			for (Soldier s : t.getAll())
			{
				soldiers.add(s);
			}
		}
		
		// render map
		cameraMap = new OrthographicCamera();
		cameraMap.setToOrtho(false, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		cameraMap.zoom = 0.9f;
		cameraMap.update();
		viewportMap = new ScreenViewport(cameraMap);
		mapRenderer = new OrthogonalTiledMapRenderer(FightersGame.data.getMap());
		
		//render menu

		cameraMenu.update();

		

		table = new GameTable();
		stage.addActor(table);
				
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		if (GameData.phase > 1)
		{
			if(GameData.selected.isMoving())
			{
				Gdx.graphics.setCursor(FightersGame.target);
			}
			else
			{
				Gdx.graphics.setCursor(FightersGame.select);
			}
		}
		
			
		cameraMap.update();
		cameraMenu.update();
		
		viewportMap.apply();
		mapRenderer.setView(cameraMap);
		mapRenderer.render();
		
		
		batch.setProjectionMatrix(cameraMap.combined);
		batch.begin();
		
		if (GameData.phase == 1)
		{
			if (GameData.flags.size > 0)
			{
				
				for (Flag f : GameData.flags)
				{
					if (f.getOwner().getName().equals(GameData.getActive().getName()))
					{
						for (int[] coords : f.getRange())
						{
							batch.draw(allowed, coords[0] * MapData.tileSize, coords[1] * MapData.tileSize);
						}
					}
				}
			}		
		}
		
		for (Flag f : GameData.flags)
		{
			texture = f.getFlag();
			batch.draw(texture, f.getPos()[0] * MapData.tileSize, f.getPos()[1] * MapData.tileSize);
		}
		
		if (soldiers.size() > 0)
		{
			
			for (Soldier s : soldiers)
			{
				if (s.getCharacter().isAlive() == false)
				{
					
					batch.draw(dead, s.getPos()[0] * MapData.tileSize, s.getPos()[1] * MapData.tileSize);
				}
			}
			for (Soldier s : soldiers)
			{
				
				if (s.getCharacter().isAlive())
				{
					
					texture = s.getUnit().getSprite(s.getFacing());
					batch.draw(texture, s.getPos()[0] * MapData.tileSize, s.getPos()[1] * MapData.tileSize);
					
					hpBar = textures.getHpBar(s.getCharacter().getStats().getHpPercent());
					batch.draw(hpBar, s.getPos()[0] * MapData.tileSize, s.getPos()[1] * MapData.tileSize);
					
					apBar = textures.getApBar(s.getCharacter().getStats().getApPercent());
					batch.draw(apBar, s.getPos()[0] * MapData.tileSize, s.getPos()[1] * MapData.tileSize);
					
				}
				
				if (s.isHit() || s.isBoosted())
				{
					if (Timer.run)
					{
						if (Timer.get() < 0.5f)
						{
							Timer.tick(Gdx.graphics.getDeltaTime());
							if (s.isHit())
							{
								batch.draw(hit, s.getPos()[0] * MapData.tileSize, s.getPos()[1] * MapData.tileSize);
							}
							else if (s.isBoosted())
							{
								batch.draw(boosted, s.getPos()[0] * MapData.tileSize, s.getPos()[1] * MapData.tileSize);
							}
						}
						else
						{
							Timer.stop();
							
							if (s.isHit())
							{
								s.setHit(false);
							}
							else if (s.isBoosted())
							{
								s.setBoosted(false);
							}
						}
					}
						
					else
					{
						Timer.start();
					}
				}
			}
			
			if(GameData.selected != null && GameData.phase == 2) 
			{
				if(GameData.selected.canMove())
				{
					int i = 0;
					for (int[] dir : GameData.selected.getDirections())
					{
						Placeable target = GameData.occupation(dir);
						
						if (target == null)
						{
							batch.draw(directions.get(i), dir[0] * MapData.tileSize, dir[1] * MapData.tileSize);
						}
						else if (target instanceof Flag)
						{
							batch.draw(capture, dir[0] * MapData.tileSize, dir[1] * MapData.tileSize);
						}
						else if (target instanceof Soldier)
						{
							if (GameData.isEnemy((Soldier) target))
							{
								batch.draw(attack, dir[0] * MapData.tileSize, dir[1] * MapData.tileSize);
							}
						}
						i ++;
					}
				}
				
				batch.draw(selected, GameData.selected.getPos()[0] * MapData.tileSize - 2, GameData.selected.getPos()[1] * MapData.tileSize - 2);	
			}
		}
		
		batch.end();
		
		viewportMenu.apply();
		table.refresh();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

	}
	
	public void endGame(Team winner)
	{
		EndDialog endDialog = new EndDialog(winner);
		stage.addActor(endDialog);
		endDialog.show(stage);
	}

	@Override
	public void resize(int width, int height) {
		viewportMap.update(width, height);
		viewportMenu.update(width, height);

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();
		texture.dispose();
		hpBar.dispose();
		apBar.dispose();
		selected.dispose();
		allowed.dispose();
		attackRange.dispose();
		
		for (Texture t : directions)
		{
			t.dispose();
		}

	}
	
	public OrthographicCamera getMapCamera()
	{
		return this.cameraMap;
	}
	
	public Skin getSkin()
	{
		return this.skin;
	}

	public void addUnit(Soldier u)
	{
		this.soldiers.add(u);
	}
	
	private static class Timer
	{
		protected static float value = 0;
		protected static boolean run = false;
		
		public static void start()
		{
			run = true;
		}
		
		public static void stop()
		{
			value = 0;
			run = false;
		}
		
		public static void tick(float delta)
		{
			value += delta;
		}
		
		public static float get()
		{
			return value;
		}
		
		
	}
}
