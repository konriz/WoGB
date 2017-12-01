package com.mygdx.fighters.gui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.Team;
import com.mygdx.fighters.units.Profession;
import com.mygdx.fighters.units.Unit;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.SoldierBuilder;
import com.mygdx.fighters.units.Character;

public class TeamConstructionDialog extends Dialog {

	private Team team;
	private Array<CounterLabel> unitsAmounts;
	private PointsCounter totalPointsCounter;
	
	public TeamConstructionDialog(Team t)
	{
		super("Construct team " + t.getName(), FightersGame.skin);
		
		this.team = t;
		Image banner = new Image(t.getBanner());
		
		this.getContentTable().add(banner).colspan(5);
		this.getContentTable().row();
		
		unitsAmounts = new Array<CounterLabel>();
		
		this.getContentTable().add(new Label("Name", FightersGame.skin));
		this.getContentTable().add(new Label("Amount", FightersGame.skin));
		this.getContentTable().add(new Label("Cost", FightersGame.skin));
		
		for (Profession profession : t.getRace().getProfessions())
		{
			this.addRow(profession);
		}
		
		this.getContentTable().row();
		Label totalPointsLabel = new Label("Total points: ", FightersGame.skin);
		totalPointsCounter = new PointsCounter();
		this.getContentTable().add(totalPointsLabel).colspan(2);
		this.getContentTable().add(totalPointsCounter);
		
		button("Ok", true);
		button("Back", false);
		
	}
	
	@Override
	protected void result(Object object) {
		
		if (object.equals(true))
		{
			
			if (totalPointsCounter.getValue() > 0 && totalPointsCounter.getValue() <= GameData.unitsPoints)
			{
				createTeam();
				FightersGame.data.skipTurn();
				if (GameData.phase == 0)
				{
					TeamConstructionDialog teamDialog = new TeamConstructionDialog(GameData.getActive());
					MainScreen.stage.addActor(teamDialog);
					teamDialog.show(MainScreen.stage);
				}
				else
				{
					Gdx.input.setInputProcessor(new SelectProcessor());
				}
			}
			else
			{
				TeamConstructionDialog teamDialog = new TeamConstructionDialog(GameData.getActive());
				MainScreen.stage.addActor(teamDialog);
				teamDialog.show(MainScreen.stage);
			}
			
		}
		
		else
		{
			GameData.resetTurn();
			StartDialog dialog = new StartDialog(GameData.game);
			MainScreen.stage.addActor(dialog);
			dialog.show(MainScreen.stage);
		}
	}
	
	@Override
	public Dialog show(Stage stage) {
		super.show(stage);
		setWidth(400);
		setHeight(300);
		this.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Align.center);
		return this;
	}
	
	public void createTeam()
	{
		for (CounterLabel unitsCount : unitsAmounts)
		{
			Unit unit = new Unit(team.getRace(), unitsCount.getProfession());
			int i = 0;
			while (i < unitsCount.getValue())
			{
				this.team.add(new SoldierBuilder().setCharacter(team.getRace(), unitsCount.getProfession()).setUnit(unit).setTeam(team).buildSoldier());
				i++;
			}
		}
	}
	
	public void addRow(Profession profession)
	{
		final int unitPoints = new Character(team.getRace(), profession).getPoints();

		this.getContentTable().row();
		final Label professionName = new Label(profession.getName(), FightersGame.skin);
		final CounterLabel unitsLabel = new CounterLabel(profession, unitPoints);
		unitsAmounts.add(unitsLabel);
		final Label pointsLabel = new Label(Integer.toString(unitPoints), FightersGame.skin);
		TextButton lessButton = new TextButton("-1", FightersGame.skin);
		lessButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y)
			{
				if (unitsLabel.getValue() > 0)
				{
					unitsLabel.modValue(-1);
					totalPointsCounter.refresh();
				}
			}
		});
		
		TextButton moreButton = new TextButton("+1", FightersGame.skin);
		moreButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y)
			{
				if (unitsLabel.getValue() >= 0)
				{
					unitsLabel.modValue(+1);
					totalPointsCounter.refresh();
				}
			}
		});
		
		this.getContentTable().add(professionName);
		this.getContentTable().add(unitsLabel);
		this.getContentTable().add(pointsLabel);
		this.getContentTable().add(lessButton);
		this.getContentTable().add(moreButton);
	}
	
	public class CounterLabel extends Label
	{
		private int counter = 0;
		private Profession profession;
		private int pointsValue;
		
		public CounterLabel(Profession profession, int pointsValue)
		{
			super("0", FightersGame.skin);
			this.profession = profession;
			this.pointsValue = pointsValue;
		}
		
		public int getValue()
		{
			return this.counter;
		}
		
		public void modValue(int mod)
		{
			this.counter += mod;
			this.setText(Integer.toString(this.counter));
		}
		
		public Profession getProfession()
		{
			return this.profession;
		}
		
		public int getPoints()
		{
			return this.counter * this.pointsValue;
		}
	}
	public class PointsCounter extends Label
	{
		private int value = 0;
		
		public PointsCounter()
		{
			super("0 / " + GameData.unitsPoints, FightersGame.skin);
			this.refresh();
		}
		
		public void refresh()
		{
			this.value = 0;
			for (CounterLabel units : unitsAmounts)
			{
				this.value += units.getPoints();
			}
			this.setText(Integer.toString(this.value) + " / " + Integer.toString(GameData.unitsPoints));
		}
		
		public int getValue()
		{
			return this.value;
		}
	}
	
}
