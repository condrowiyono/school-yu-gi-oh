package com.terserah.yogs.duel.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Player;


public class OpponentFieldPanel extends PlayerFieldPanel {
	
	public OpponentFieldPanel(Player player) throws IOException, FontFormatException {
		super(player);
		JPanel rightSide = new JPanel();
		this.setPreferredSize(new Dimension(1020,340));
		this.setLayout(new BorderLayout());
		rightSide.setLayout(new GridLayout(2,1));
		rightSide.add(graveyardPanel);
		rightSide.add(deckButton);
	    
	    this.add(infoPanel,BorderLayout.WEST);
	    this.add(fieldPanel,BorderLayout.CENTER);
	    this.add(handPanel,BorderLayout.NORTH);
	    //this.add(phasePanel,BorderLayout.NORTH);
	   
	    this.add(rightSide,BorderLayout.EAST);
		
 	}
	
	

}
