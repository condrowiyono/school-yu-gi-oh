package com.terserah.yogs.duel.gui;

import java.io.IOException;

import com.terserah.yogs.boards.player.Player;

public class ActiveBoard extends ContainerBoard {

	public ActiveBoard(Player p) throws IOException {
		super(p);
		fieldPanel = new FieldPanel1(p);
		graveAndDeck = new GraveAndDeck1(p);
		
		fieldPanel.setSize(600, 220);
		fieldPanel.setLocation(200,2);
		
		graveAndDeck.setSize(80,210);
		graveAndDeck.setLocation(800,5);
		
		this.add(fieldPanel);
		this.add(graveAndDeck);
	}

}
