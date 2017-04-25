package com.terserah.yogs.duel.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.terserah.yogs.boards.player.Player;


public class DeckButton extends JButton{
	private Player player;
	public DeckButton(Player player) throws IOException {
		super();
		this.player = player;
		this.setPreferredSize(new Dimension(68,100));
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		//this.setEnabled(false);
		this.setOpaque(false);
		this.setText(""+player.getField().getDeck().getDeck().size());
		BufferedImage img = ImageIO.read(new File("art/cards/deck.png"));
		Image cardImg = img.getScaledInstance(80, 100, Image.SCALE_SMOOTH);
		this.setIcon(new ImageIcon(cardImg));
		this.setVerticalTextPosition(SwingConstants.CENTER);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setForeground(Color.WHITE);
	}
	
	public void updatePanel(Player player){
		this.setText(""+player.getField().getDeck().getDeck().size());
		
	}

}
