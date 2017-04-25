package com.terserah.yogs.duel.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.cards.Card;


public class InfoPanel extends JPanel{
	Player player;
	ArrayList<JLabel> labels;
	public InfoPanel(Player player) throws FontFormatException, IOException {
		this.setOpaque(false);
		this.player = player;
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		labels = new ArrayList<JLabel>();

		this.setPreferredSize(new Dimension(366, 140));
		/*this.setBorder(
	            BorderFactory.createTitledBorder(
	                    BorderFactory.createEtchedBorder(
	                            EtchedBorder.RAISED, Color.GRAY
	                            , Color.DARK_GRAY), "Phase"));*/
		JLabel name = new JLabel(player.getName());
		JLabel lifePoints = new JLabel(""+player.getLifePoints());

		String active = (player == Card.getBoard().getActivePlayer())?"Active":"Inactive";
		//this.setBorder(BorderFactory.createLineBorder(new Color(255, 203, 41), 2));
		//String active="";
		//this.setForeground(Color.WHITE);
		JLabel status = new JLabel(active);

		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(366,140));
		container.setOpaque(false);
		container.setLayout(null);
		container.add(name);
		container.add(lifePoints);
		container.add(status);
		name.setLocation(95, 0);
		name.setSize(250,100);
		lifePoints.setSize(250,100);
		status.setSize(250,100);
		lifePoints.setLocation(95, 28);
		status.setLocation(95, 56);
		name.setForeground(new Color(255, 203, 41));
		lifePoints.setForeground(new Color(232, 91, 91));
		status.setForeground(new Color(255, 203, 41));
		labels.add(name);
		labels.add(lifePoints);
		labels.add(status);


		this.add(container);
	}

	public Player getPlayer() {
		return player;
	}

	public void updatePanel(Player player){
		int dec = (Integer.parseInt(labels.get(1).getText())-player.getLifePoints())/5;

		Timer t = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int origLP = Integer.parseInt(labels.get(1).getText());
				
					origLP-=50;
					labels.get(1).setText(""+(origLP));
					if(origLP<=player.getLifePoints()){
						((Timer)e.getSource()).stop();
						labels.get(1).setText(""+player.getLifePoints());
					}	
			}
		});
		
		t.start();

		//this.labels.get(1).setText(""+player.getLifePoints());

		String active = (player == Card.getBoard().getActivePlayer())?"Active":"Inactive";
		this.labels.get(2).setText(active);
	}



}
