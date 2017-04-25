package com.terserah.yogs.duel.gui;

import java.awt.BorderLayout;
import java.io.IOException;

import com.terserah.yogs.boards.player.Player;


public class FieldPanel2 extends FieldPanel {

	public FieldPanel2(Player player) throws IOException {
		super(player);
		this.add(monstersPanel,BorderLayout.SOUTH);
		this.add(spellsPanel,BorderLayout.NORTH);
	}

}
