package com.terserah.yogs.duel.gui;

import java.awt.BorderLayout;
import java.io.IOException;

import com.terserah.yogs.boards.player.Player;

public class FieldPanel1 extends FieldPanel {

	public FieldPanel1(Player player) throws IOException {
		super(player);
		this.add(monstersPanel,BorderLayout.NORTH);
		this.add(spellsPanel,BorderLayout.SOUTH);
	}

}
