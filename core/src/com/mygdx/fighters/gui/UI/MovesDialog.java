package com.mygdx.fighters.gui.UI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.gui.FightersGame;
import com.mygdx.fighters.moves.Move;

public class MovesDialog extends Dialog {

	public MovesDialog()
	{
		super("Available moves", FightersGame.skin);
		for (Move m : GameData.selected.getCharacter().getMoves())
		{
			this.getContentTable().add(new MoveButton(m));
			this.getContentTable().row();
		}
		this.button("Exit", false);
		
	}
	
	
	
	@Override
	protected void result(Object object) {
		if (object.equals(false))
		{
			hide();
		}
	}



	class MoveButton extends TextButton
	{
		public MoveButton(Move move)
		{
			super(move.getName() + " (" + move.getApCost() + ")", FightersGame.skin);
			final Move buttonMove = move; 
			
			addListener(new ClickListener(){
				
				public void clicked(InputEvent event, float x, float y)
				{
					if (buttonMove.getApCost() <= GameData.selected.getCharacter().getStats().getCurrentAP())
					{
						GameData.selected.setMove(buttonMove);
					}
					hide();
				}
			});
			
			addListener(new TextTooltip(move.getDescription(), FightersGame.skin));
		}
	}
}
