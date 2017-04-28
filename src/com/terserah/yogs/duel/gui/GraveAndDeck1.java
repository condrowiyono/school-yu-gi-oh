package com.terserah.yogs.duel.gui;

import java.awt.BorderLayout;
import java.io.IOException;

import com.terserah.yogs.boards.player.Player;


public class GraveAndDeck1 extends GraveyardAndDeckPanel {
	public GraveAndDeck1(Player p) throws IOException {
		super(p);
		this.add(grave,BorderLayout.NORTH);
		this.add(deck,BorderLayout.SOUTH);
	}

}
