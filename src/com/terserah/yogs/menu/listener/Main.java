package com.terserah.yogs.menu.listener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.terserah.yogs.boards.player.Deck;
import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.menu.gui.MainMenu;



@SuppressWarnings("serial")
public class Main extends JFrame {
	public static Controller controller;
	public static Player p1;
	public static Player p2;
	
	
	public Main() throws Exception{
		String player1;
		player1 = (String) JOptionPane.showInputDialog(null,"Please enter the first player's name","Player 1",JOptionPane.QUESTION_MESSAGE);
		if(player1==null || player1.length()==0){
			this.dispose();
			new Interface();
			return;
		}
		new MainMenu();
		Main.p1 = new Player(player1, Deck.getRandCard(15));
		Main.p1.setPlayerDeck(Deck.getRandCard(Main.p1.getAllCard(), 11));
	}

}
