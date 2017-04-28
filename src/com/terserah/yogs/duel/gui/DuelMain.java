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
import com.terserah.yogs.menu.listener.SoundFactory;

public class DuelMain extends JFrame{
	

	public static int gameMode=1;
	Clip clip;
	public Clip getClip() {
		return clip;
	}
	public static Controller controller;
	private Player p1;
	private Player p2;
	GamePanel game;
	
	public DuelMain(Computer pl2) throws Exception{
		Board board = new Board();
		p1 = Main.p1;
		p2 = pl2;
		board.startGame(p1, p2);
		System.out.println(p2.getName());
		game = new GamePanel(p1,p2);

		controller = new Controller(game,board,this);
		
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
		SoundFactory.playBG();

	}



}
