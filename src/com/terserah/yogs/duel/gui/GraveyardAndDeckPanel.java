package com.terserah.yogs.duel.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.SpellCard;

public class GraveyardAndDeckPanel extends JPanel {
	Player p;
	GraveyardButton grave;
	DeckButton deck;
	
	public Player getP() {
		return p;
	}

	public GraveyardButton getGrave() {
		return grave;
	}

	public DeckButton getDeck() {
		return deck;
	}

	public GraveyardAndDeckPanel(Player p) throws IOException{
		super();
		this.p = p;
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(68,260));
		this.setLayout(new BorderLayout());
		grave = new GraveyardButton(p);
		deck = new DeckButton(p);
	}

	public void updatePanel() throws IOException{
		deck.setText(""+this.getP().getField().getDeck().getDeck().size());

		ArrayList<Card> graveyard = this.p.getField().getGraveyard();
		if(graveyard.size()>0){
			if(graveyard.get(graveyard.size()-1) instanceof MonsterCard){
				MonsterCard lastCard = (MonsterCard)graveyard.get(graveyard.size()-1);
				this.grave.setLastCard(lastCard);
				BufferedImage graveyardImage = lastCard.getImage();
				Image cardImage = graveyardImage.getScaledInstance(80, 110,Image.SCALE_SMOOTH);
				this.grave.setIcon(new ImageIcon(cardImage));
			}
			else{
				SpellCard lastCard = (SpellCard)graveyard.get(graveyard.size()-1);
				this.grave.setLastCard(lastCard);
				BufferedImage graveyardImage = lastCard.getImage();
				Image cardImage = graveyardImage.getScaledInstance(80, 110,Image.SCALE_SMOOTH);
				this.grave.setIcon(new ImageIcon(cardImage));
			}
		}
		else{
			BufferedImage graveyardImage = ImageIO.read(new File("art/cards/graveyard.jpg"));
			Image cardImage = graveyardImage.getScaledInstance(120, 140,Image.SCALE_SMOOTH);
			this.grave.setIcon(new ImageIcon(cardImage));
			this.grave.setLastCard(null);
		}
		
	}

	
}
