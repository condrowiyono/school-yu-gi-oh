package com.terserah.yogs.duel.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.menu.gui.MonsterButton;


public class MonstersPanel extends JPanel{
	Player player;
	public Player getPlayer() {
		return player;
	}
	ArrayList<MonsterButton> monsterButtons = new ArrayList<MonsterButton>();
	public ArrayList<MonsterButton> getMonsterButtons() {
		return monsterButtons;
	}
	public MonstersPanel(Player player) throws IOException{
		this.player = player;
		this.setOpaque(false);
		//this.setLayout(new GridLayout(2,5));
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(600, 110));
		monsterButtons= new ArrayList<MonsterButton>();
		ArrayList<MonsterCard> monsterArea = player.getField().getMonstersArea();
		for (int i=0; i<3;i++) {
			JPanel buttonContainer = new JPanel();
			buttonContainer.setPreferredSize(new Dimension(100,105));
			//buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER)); // delete !!
			buttonContainer.setOpaque(false);
			//buttonContainer.setBackground(new Color(0,0,0,200));
			buttonContainer.setBorder(BorderFactory.createLineBorder(Color.GRAY,2,false));
			MonsterButton monsterButton;
			if(i<monsterArea.size())
				monsterButton = new MonsterButton(monsterArea.get(i));
			else{
				monsterButton = new MonsterButton(null);
				monsterButton.setEnabled(false);
				BufferedImage img = ImageIO.read(new File("art/cards/back.png"));
				Image cardImg = img.getScaledInstance(68, 100, Image.SCALE_SMOOTH);
				
				//monsterButton.setIcon(new ImageIcon(cardImg));
				monsterButton.setOpaque(false);
				monsterButton.setBorderPainted(false);
			}
			/*if(i==0){
				monsterButton.setPreferredSize(new Dimension(100,68));
				BufferedImage img = ImageIO.read(new File("art/cards/monsters/Vorse Raider - Defense.jpg"));
				Image cardImg = img.getScaledInstance(100, 68, Image.SCALE_SMOOTH);
				
				monsterButton.setIcon(new ImageIcon(cardImg));
				JLabel x = new JLabel();
				x.setPreferredSize(new Dimension(100,7));	
				buttonContainer.add(x);
			}*/
			buttonContainer.add(monsterButton);
			monsterButtons.add(monsterButton);
			this.add(buttonContainer);
		}
	}
	public void updatePanel(Player player) throws IOException{
		ArrayList<MonsterCard> monsterArea = player.getField().getMonstersArea();
		
		/*while(!monsterButtons.isEmpty()){
			monsterButtons.get(0).setVisible(false);
			this.remove(monsterButtons.get(0));
			monsterButtons.remove(monsterButtons.get(0));
		}
		for (int i=0; i<5;i++) {
			MonsterButton button;
			if(i<monsterArea.size())
				button = new MonsterButton(monsterArea.get(i));
			else
				button = new MonsterButton(null);
			
			//System.out.println(button);
			monsterButtons.add(button);
			this.add(button);
		}*/
		for (int i=0; i<3;i++) {
			if(i<monsterArea.size()){
				MonsterCard monster = monsterArea.get(i);
				//monsterButtons.get(i).setVisible(false);
				//monsterButtons.set(i, new MonsterButton(monster));
				monsterButtons.get(i).setMonster(monster);
				monsterButtons.get(i).updateButton();
				monsterButtons.get(i).setEnabled(true);
				monsterButtons.get(i).setBorderPainted(true);
				this.setVisible(true);
			}
			else{
				//monsterButtons.get(i).setVisible(false);
				//monsterButtons.set(i, new MonsterButton(null));
				monsterButtons.get(i).setMonster(null);
				monsterButtons.get(i).updateButton();
				monsterButtons.get(i).setBorderPainted(false);
				//monsterButtons.get(i).setEnabled(false);
				//monsterButtons.get(i).setIcon(new ImageIcon(ImageIO.read(new File("art/cards/back.png"))));
			}
		}
		
	}
}
