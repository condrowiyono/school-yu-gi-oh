package com.terserah.yogs.menu.listener;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.terserah.yogs.boards.player.Deck;
import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.cards.Card;
import com.terserah.yogs.menu.gui.MainMenu;



@SuppressWarnings("serial")
public class Main extends JFrame {
	public static Controller controller;
	public static Player p1;
	public static Player p2;
	
	public static void changep1(Player p1) {
		Main.p1 = p1;
	}
	
	public Main() throws Exception{
		String player1;
		
		player1 = (String) JOptionPane.showInputDialog(null,"Fill your name ","Player 1",JOptionPane.QUESTION_MESSAGE);
		if(player1==null || player1.length()==0){
			this.dispose();
			new Interface();
			return;
		}
		
		//init p1
		Main.p1 = new Player(player1, Deck.getRandCard(15));
		ArrayList<Card> hand = new ArrayList<Card>(); 
		for (int i = 0 ; i < 11; i++) {
			hand.add(Main.p1.getAllCard().getDeck().get(0));
			Main.p1.getAllCard().getDeck().remove(0);
		}
		Main.p1.setPlayerDeck(new Deck(hand));
	
		new MainMenu();
	}

}
