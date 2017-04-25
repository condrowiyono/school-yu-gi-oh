package com.terserah.yogs.duel.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Player;

public class SidePanel extends JPanel {
	Player p1;
	Player p2;
	private InfoPanel p1Info;
	private CardDescriptionPanel cardDescriptionPanel;
	public CardDescriptionPanel getCardDescriptionPanel() {
		return cardDescriptionPanel;
	}


	public InfoPanel getP1Info() {
		return p1Info;
	}


	public InfoPanel getP2Info() {
		return p2Info;
	}


	private InfoPanel p2Info;
	public SidePanel(Player p1,Player p2) throws IOException, FontFormatException{
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.setPreferredSize(new Dimension(250,768));
		//this.setOpaque(false);
		//this.setBorder(BorderFactory.createLineBorder(new Color(255, 203, 41), 3, true));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);
		p1Info = new InfoPanel(p1);
		p2Info = new InfoPanel(p2);
		cardDescriptionPanel = new CardDescriptionPanel(null);
		
		/*cardDescriptionPanel.setBorder(
				BorderFactory.createTitledBorder(
						BorderFactory.createEtchedBorder(
								EtchedBorder.RAISED, Color.GRAY
								, Color.DARK_GRAY), "Card Description"));*/
		
		this.add(p1Info,BorderLayout.SOUTH);
		this.add(cardDescriptionPanel, BorderLayout.CENTER);
		this.add(p2Info,BorderLayout.NORTH);

	}


	
}
