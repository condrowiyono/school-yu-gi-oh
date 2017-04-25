package com.terserah.yogs.duel.gui;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.terserah.yogs.boards.Board;
import com.terserah.yogs.boards.player.Computer;
import com.terserah.yogs.boards.player.Deck;
import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.cards.Card;
import com.terserah.yogs.menu.listener.Controller;
import com.terserah.yogs.menu.listener.Main;

public class DuelMain extends JFrame{
	

	public static int gameMode;
	Clip clip;
	public Clip getClip() {
		return clip;
	}
	public static Controller controller;
	
	GamePanel game;
	public DuelMain() throws Exception{
		Board board = new Board();
		Object[] options = {"Two Players",
		"Play vs. Computer"};
		int n = JOptionPane.showOptionDialog(null,
				"Select game mode:",
				"Start Game",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,     //do not use a custom Icon
				options,  //the titles of buttons
				null); //default button title

		String player1;
		
		Player p1;
		Player p2;
		if(n==0){
			gameMode = 0;
			player1 = (String) JOptionPane.showInputDialog(null,"Please enter the first player's name","Player 1",JOptionPane.QUESTION_MESSAGE);
			if(player1==null || player1.length()==0){
				this.dispose();
				return;
			}
			
			String player2 = (String) JOptionPane.showInputDialog(null,"Please enter the second player's name","Player 2",JOptionPane.QUESTION_MESSAGE);
			if(player2==null || player2.length()==0){
				this.dispose();
				return;
			}
			p1 = Main.p1;
			p2 = new Player(player2, Deck.getRandCard(15));
			p2.setPlayerDeck(Deck.getRandCard(p2.getAllCard(), 11));
		}
		else{
			gameMode = 1;
			player1 = (String) JOptionPane.showInputDialog(null,"Please enter the first player's name","Player 1",JOptionPane.QUESTION_MESSAGE);
			if(player1==null || player1.length()==0){
				this.dispose();
				return;
			}
			p1 = Main.p1;
			p2 = new Computer("Compute", Deck.getRandCard(15));
			p2.setPlayerDeck(Deck.getRandCard(p2.getAllCard(), 11));
		}


		board.startGame(p1, p2);
		System.out.println(p2.getName());
		game = new GamePanel(p1,p2);

		controller = new Controller(game,board);
		
		if(Card.getBoard().getActivePlayer() instanceof Computer){
			((Computer)Card.getBoard().getActivePlayer()).computerTurn();
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(game);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setSize(1366,768);
		this.setVisible(true);
		this.validate();
		String soundName = "sounds/Yu-Gi-Oh! Power of Chaos Yugi The Destiny - Duel Theme.wav";
		try{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			clip.loop(10);

		}
		catch(Exception e1){

		}

	}



}
