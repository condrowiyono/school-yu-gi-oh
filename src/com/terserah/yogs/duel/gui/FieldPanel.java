package com.terserah.yogs.duel.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Player;

public class FieldPanel extends JPanel{
	 Player player ;
	 MonstersPanel monstersPanel;
	
	 public MonstersPanel getMonstersPanel() {
		return monstersPanel;
	}

	public SpellsPanel getSpellsPanel() {
		return spellsPanel;
	}

	SpellsPanel spellsPanel;
	

	public FieldPanel (Player player) throws IOException { 
		super(); 
		this.setOpaque(false);
		this.player = player;
		//this.setLayout(new GridLayout(2,5));
		this.setPreferredSize(new Dimension(600, 260));
		this.setLayout(new BorderLayout());
		monstersPanel = new MonstersPanel(player);
		spellsPanel = new SpellsPanel(player);
	}
}


