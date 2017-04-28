package com.terserah.yogs.menu.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import com.terserah.yogs.boards.player.Player;

public class PlayerButton extends JButton{
	Player player;
	
	public PlayerButton(Player player){
		super(player.getName());
		this.setPreferredSize(new Dimension(100,100));
		this.player = player;
		this.setBackground(Color.WHITE);
	}
	public Player getPlayer() {
		return player;
	}
	
}