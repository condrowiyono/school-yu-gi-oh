package com.terserah.yogs.duel.gui;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Computer;
import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.menu.gui.CardButton;
import com.terserah.yogs.menu.gui.MonsterButton;
import com.terserah.yogs.menu.gui.SpellButton;
import com.terserah.yogs.menu.listener.Main;
import com.terserah.yogs.cards.spells.SpellCard;

public class HandPanel extends JPanel{
	private Player player;
	ArrayList<CardButton> cardButtons;
	public ArrayList<CardButton> getCardButtons() {
		return cardButtons;
	}
	public HandPanel(Player player) throws IOException{
		this.player = player;
		cardButtons = new ArrayList<CardButton>();
		this.setOpaque(false);
		this.setLayout(new FlowLayout());
		//this.setPreferredSize(new Dimension(700, 110));
		
		ArrayList<Card> hand = player.getField().getHand();
		
		for (int i=0; i<hand.size();i++) {
			CardButton button = null;
			if (hand.get(i) instanceof MonsterCard){
				button = new MonsterButton((MonsterCard)hand.get(i));
			}
			else if (hand.get(i) instanceof SpellCard){
				button = new SpellButton((SpellCard) hand.get(i));
			} 
			//System.out.println(button);
			if((player != Card.getBoard().getActivePlayer()) || (player instanceof Computer)){
				button.setText("");
				
				BufferedImage graveyardImage = ImageIO.read(new File("art/cards/back.png"));
				Image cardImage = graveyardImage.getScaledInstance(68, 100,Image.SCALE_SMOOTH);
				button.setIcon(new ImageIcon(cardImage));
			}
			//button.setBounds(0, 68, 0, 100);
			cardButtons.add(button);
			this.add(button);
		}
		
		
	}
	
	public void updatePanel(Player player) throws IOException{
		ArrayList<Card> hand = player.getField().getHand();
		while(!cardButtons.isEmpty()){
			cardButtons.get(0).removeAll();
			cardButtons.get(0).setVisible(false);
			this.remove(cardButtons.get(0));
			cardButtons.remove(cardButtons.get(0));
			
		}
		
		for (int i=0; i<hand.size();i++) {
			CardButton button;
			if (hand.get(i) instanceof MonsterCard)
				button = new MonsterButton((MonsterCard)hand.get(i));
			else
				button = new SpellButton((SpellCard)hand.get(i));
			if((player != Card.getBoard().getActivePlayer()) || (player instanceof Computer)){
				button.setText("");
				
				BufferedImage graveyardImage = ImageIO.read(new File("art/cards/back.png"));
				Image cardImage = graveyardImage.getScaledInstance(68, 100,Image.SCALE_SMOOTH);
				button.setIcon(new ImageIcon(cardImage));
			}
			//System.out.println(button);
			
			cardButtons.add(button);
			this.add(button);
		}
		
	}
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return this.player;
	}
	
	
	

}
