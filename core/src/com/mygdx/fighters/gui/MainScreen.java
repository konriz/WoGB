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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fighters.Flag;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.MapData;
import com.mygdx.fighters.Placeable;
import com.mygdx.fighters.Team;
import com.mygdx.fighters.Unit;
import com.mygdx.fighters.gui.UI.GameTable;

public class MainScreen implements Screen {
	
	private FightersGame game;
	private Skin skin;
	
	private SpriteBatch batch;
	private ArrayList<Unit> units;
	private Texture texture;
	
	private BarsTextures textures;
	private Texture hpBar, apBar, selected, dead, allowed, attackRange, attack, capture, hit, boosted;
	private Texture[] states;
	private Texture[] directions;
	
	private TiledMapRenderer mapRenderer;
	private OrthographicCamera cameraMenu;
	private OrthographicCamera cameraMap;
	
	private Viewport viewportMenu;
	private Viewport viewportMap;
	
	public static Stage stage;
	private GameTable table;
	
	public MainScreen ()
	{
		this.game = GameData.game;
		
		cameraMenu = new OrthographicCamera();
		viewportMenu = new FitViewport(1024, 800, cameraMenu);
		stage = new Stage(viewportMenu);
		
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		textures = new BarsTextures();
		directions = textures.getDirections();
		states = textures.getStates();
		selected = states[0];
		dead = states[1]; 
		allowed = states[2];
		attackRange = states[3];
		attack = states[4];
		capture = states[5];
		hit = states[6];
		
		units = new ArrayList<Unit>();
		for (Team t : GameData.teams)
		{
			for (Unit u : t)
			{
				units.add(u);
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
		
		if (units.size() > 0)
		{
			
			for (Unit u : units)
			{
				if (u.getCharacter().isAlive() == false)
				{
					
					batch.draw(dead, u.getPos()[0] * MapData.tileSize, u.getPos()[1] * MapData.tileSize);
				}
			}
			for (Unit u : units)
			{
				
				if (u.getCharacter().isAlive())
				{
					
					texture = u.getSprite();
					batch.draw(texture, u.getPos()[0] * MapData.tileSize, u.getPos()[1] * MapData.tileSize);
					
					hpBar = textures.getHpBar(u.getHpPercent());
					batch.draw(hpBar, u.getPos()[0] * MapData.tileSize, u.getPos()[1] * MapData.tileSize);
					
					apBar = textures.getApBar(u.getApPercent());
					batch.draw(apBar, u.getPos()[0] * MapData.tileSize, u.getPos()[1] * MapData.tileSize);
					
				}
				
				if (u.isHit() || u.isBoosted())
				{
					if (Timer.run)
					{
						if (Timer.get() < 0.5f)
						{
							Timer.tick(Gdx.graphics.getDeltaTime());
							if (u.isHit())
							{
								batch.draw(hit, u.getPos()[0] * MapData.tileSize, u.getPos()[1] * MapData.tileSize);
							}
							else if (u.isBoosted())
							{
								batch.draw(boosted, u.getPos()[0] * MapData.tileSize, u.getPos()[1] * MapData.tileSize);
							}
						}
						else
						{
							Timer.stop();
							
							if (u.isHit())
							{
								u.setHit(false);
							}
							else if (u.isBoosted())
							{
								u.setBoosted(false);
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
							batch.draw(directions[i], dir[0] * MapData.tileSize, dir[1] * MapData.tileSize);
						}
						else if (target instanceof Flag)
						{
							batch.draw(capture, dir[0] * MapData.tileSize, dir[1] * MapData.tileSize);
						}
						else if (target instanceof Unit)
						{
							if (GameData.isEnemy((Unit) target))
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

	public void addUnit(Unit u)
	{
		this.units.add(u);
	}
	
	public FightersGame getGame()
	{
		return this.game;
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
