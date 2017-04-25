package com.terserah.yogs.duel.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.terserah.yogs.boards.player.Player;


public class MainPanel extends JPanel {
	Player p1;
	Player p2;
	
	private HandPanel p1Hand;
	public Player getP1() {
		return p1;
	}

	public Player getP2() {
		return p2;
	}

	public HandPanel getP1Hand() {
		return p1Hand;
	}

	public HandPanel getP2Hand() {
		return p2Hand;
	}

	public ActiveBoard getActiveBoard() {
		return activeBoard;
	}

	public OpponentBoard getOpponentBoard() {
		return opponentBoard;
	}

	public PhasePanel getPhase() {
		return phase;
	}

	private HandPanel p2Hand;
	
	private ActiveBoard activeBoard;
	private OpponentBoard opponentBoard;
	private PhasePanel phase;
	
	
	public MainPanel(Player p1, Player p2) throws IOException{
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1000,768));
		p1Hand = new HandPanel(p1);
		p2Hand = new HandPanel(p2);
		
		activeBoard = new ActiveBoard(p1);
		opponentBoard = new OpponentBoard(p2);
		
		JPanel wholeBoard  = new JPanel();
		wholeBoard.setLayout(null);
		wholeBoard.add(activeBoard);
		wholeBoard.setOpaque(false);
		activeBoard.setSize(1000,240);
		activeBoard.setLocation(10, 260);
		wholeBoard.add(opponentBoard);
		opponentBoard.setSize(1000,240);
		opponentBoard.setLocation(10, 20);
		
		
		phase = new PhasePanel();
		wholeBoard.add(phase);
		phase.setSize(200,300);
		phase.setLocation(50, 128);
		
		JScrollPane hand1 = new JScrollPane(p1Hand);
		JScrollPane hand2 = new JScrollPane(p2Hand);
		hand1.getViewport().setOpaque(false);
		hand1.setOpaque(false);
		hand2.getViewport().setOpaque(false);
		hand2.setOpaque(false);
		hand1.setBorder(null);
		hand2.setBorder(null);
		//hand1.setPreferredSize(new Dimension(700,120));
		//hand2.setPreferredSize(new Dimension(700,120));
		
		this.add(hand1, BorderLayout.SOUTH);
		this.add(wholeBoard, BorderLayout.CENTER);
		this.add(hand2, BorderLayout.NORTH);
	}
	
}
