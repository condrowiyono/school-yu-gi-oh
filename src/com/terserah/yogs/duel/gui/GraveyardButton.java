package com.terserah.yogs.duel.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.SpellCard;

public class GraveyardButton extends JButton{
	Player player;
	Card lastCard;
	public Card getLastCard() {
		return lastCard;
	}
	public void setLastCard(Card lastCard) {
		this.lastCard = lastCard;
	}
	public GraveyardButton(Player player) throws IOException {
		super();
		this.player = player;
		this.setPreferredSize(new Dimension(68,100));
		ArrayList<Card> graveyard = player.getField().getGraveyard();
		if(graveyard.size()>0){
			if(graveyard.get(graveyard.size()-1) instanceof MonsterCard){
				MonsterCard lastCard = (MonsterCard)graveyard.get(graveyard.size()-1);
				BufferedImage graveyardImage = lastCard.getImage();
				Image cardImage = graveyardImage.getScaledInstance(120, 140,Image.SCALE_SMOOTH);
				this.setIcon(new ImageIcon(cardImage));
			}
			else{
				SpellCard lastCard = (SpellCard)graveyard.get(graveyard.size()-1);
				BufferedImage graveyardImage = lastCard.getImage();
				Image cardImage = graveyardImage.getScaledInstance(120, 140,Image.SCALE_SMOOTH);
				this.setIcon(new ImageIcon(cardImage));
			}
			
		}
		else{
			BufferedImage graveyardImage = ImageIO.read(new File("art/cards/graveyard.jpg"));
			Image cardImage = graveyardImage.getScaledInstance(120, 140,Image.SCALE_SMOOTH);
			this.setIcon(new ImageIcon(cardImage));
			
		}
	}




}
