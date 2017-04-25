package com.terserah.yogs.duel.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Player;


public class ActiveFieldPanel extends PlayerFieldPanel {
	
	public ActiveFieldPanel(Player player) throws IOException, FontFormatException {
		super(player);
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(700,340));
		JPanel rightSide = new JPanel();
		
		rightSide.setPreferredSize(new Dimension(68,250));
		rightSide.setLayout(new GridLayout(2,1));
		rightSide.add(graveyardPanel);
		rightSide.add(deckButton);
	    this.add(fieldPanel,BorderLayout.CENTER);
	    this.add(infoPanel,BorderLayout.WEST);
	    this.add(handPanel,BorderLayout.SOUTH);
	    //this.add(phasePanel,BorderLayout.NORTH);
	   // this.add(graveyardPanel,BorderLayout.EAST);
	    this.add(rightSide,BorderLayout.EAST);
		
 	}
}
