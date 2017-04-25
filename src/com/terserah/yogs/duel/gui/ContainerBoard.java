package com.terserah.yogs.duel.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Player;


public class ContainerBoard extends JPanel {
	
	public Player getPlayer() {
		return player;
	}
	public FieldPanel getFieldPanel() {
		return fieldPanel;
	}
	public GraveyardAndDeckPanel getGraveAndDeck() {
		return graveAndDeck;
	}
	 Player player;
	 FieldPanel fieldPanel;
	 GraveyardAndDeckPanel graveAndDeck;
	public ContainerBoard(Player p){
		super();
		this.player = p;
		this.setPreferredSize(new Dimension(800,260));
		this.setLayout(null);
		this.setOpaque(false);
	}
	
	
}
