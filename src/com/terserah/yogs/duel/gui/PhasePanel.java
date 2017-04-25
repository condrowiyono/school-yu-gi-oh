package com.terserah.yogs.duel.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.terserah.yogs.cards.Card;


public class PhasePanel extends JPanel{
	
	JButton endPhase;
	public JButton getEndPhase() {
		return endPhase;
	}
	public JButton getEndTurn() {
		return endTurn;
	}
	JButton endTurn;
	JLabel phase;
	public JLabel getPhase() {
		return phase;
	}
	public PhasePanel() {
		
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(300, 500));
		this.setOpaque(false);
		
		
		phase = new JLabel("Current Phase: "+Card.getBoard().getActivePlayer().getField().getPhase().toString());
		endPhase = new JButton("End Phase");
		
		
		endTurn = new JButton("End Turn");
		//phase.setPreferredSize(new Dimension(200,30));
		//this.setAlignmentY(CENTER_ALIGNMENT);
		phase.setForeground(new Color(255, 203, 41));
		endPhase.setPreferredSize(new Dimension(100,100));
		endTurn.setPreferredSize(new Dimension(100,100));
		
		//endTurn.setOpaque(false);
		//endTurn.setContentAreaFilled(false);
		endPhase.setForeground(Color.GRAY);
		endPhase.setBackground(Color.BLACK);
		
		//endPhase.setOpaque(false);
		//endPhase.setContentAreaFilled(false);
		//Color back = new Color(0,0,0);
		//back = new Color(back.getRed(), back.getGreen(), back.getBlue(), 20);
		endTurn.setForeground(Color.GRAY);
		endTurn.setBackground(Color.BLACK);
		
		this.add(endPhase);
		this.add(phase);
		
		this.add(endTurn);
		
		
	}
	
	public void updatePanel(){
		this.phase.setText("Current Phase: "+Card.getBoard().getActivePlayer().getField().getPhase().toString());
	}

}
