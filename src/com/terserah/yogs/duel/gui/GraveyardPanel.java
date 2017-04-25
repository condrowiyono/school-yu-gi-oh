package com.terserah.yogs.duel.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.SpellCard;


public class GraveyardPanel extends JPanel{
	   private GraveyardButton graveyardButton;
	   	public GraveyardButton getGraveyardButton() {
		return graveyardButton;
	}

		Player player;
	   	JLabel graveText;
		public GraveyardPanel(Player player) throws IOException {
			super(); 
			this.player = player;
			this.setLayout(new GridLayout(2,1));
			this.setPreferredSize(new Dimension(100, 130));
			
			graveText = new JLabel("Graveyard ("+player.getField().getGraveyard().size()+")");
			graveyardButton = new GraveyardButton(player);
			
			this.add(graveText);
			this.add(graveyardButton);
		}
		
		public void updatePanel(Player player){
			ArrayList<Card> graveyard = player.getField().getGraveyard();
			
			if(graveyard.size()>0){

				if(graveyard.get(graveyard.size()-1) instanceof MonsterCard){
					MonsterCard c = (MonsterCard)graveyard.get(graveyard.size()-1);
					this.getGraveyardButton().setText(c.getName() + "\n" + c.getATK() + "\n" + c.getDEF() + "\n" + c.getLevel());

				}
				else{
					SpellCard c = (SpellCard)graveyard.get(graveyard.size()-1);
					this.getGraveyardButton().setText(c.getName());
				}
			}
			else
				this.getGraveyardButton().setText("Empty");
			graveText.setText("Graveyard ("+player.getField().getGraveyard().size()+")");
		}

	}
