package com.terserah.yogs.duel.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Player;
public class PlayerFieldPanel extends JPanel{
	private Player player;
    public Player getPlayer() {
		return player;
	}

	public FieldPanel getFieldPanel() {
		return fieldPanel;
	}

	public InfoPanel getInfoPanel() {
		return infoPanel;
	}

	public HandPanel getHandPanel() {
		return handPanel;
	}

	

	public GraveyardPanel getGraveyardPanel() {
		return graveyardPanel;
	}

	protected FieldPanel fieldPanel;
    public DeckButton getDeckButton() {
		return deckButton;
	}

	protected InfoPanel infoPanel;
    protected HandPanel handPanel;
   
    protected GraveyardPanel graveyardPanel;
    protected DeckButton deckButton;
	public PlayerFieldPanel(Player player) throws IOException, FontFormatException {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(100,220));
		this.player = player;
		
		this.setOpaque(false);
		 fieldPanel = new FieldPanel(player);
	     infoPanel = new InfoPanel(player);
	     handPanel = new HandPanel(player);
	     graveyardPanel = new GraveyardPanel(player);
	     deckButton = new DeckButton(player);
		
 	}

}
