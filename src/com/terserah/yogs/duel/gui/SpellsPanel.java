package com.terserah.yogs.duel.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.spells.SpellCard;
import com.terserah.yogs.menu.gui.SpellButton;

public class SpellsPanel extends JPanel{
	Player player;
	public Player getPlayer() {
		return player;
	}
	ArrayList<SpellButton> spellButtons = new ArrayList<SpellButton>();
	public ArrayList<SpellButton> getSpellButtons() {
		return spellButtons;
	}
	public SpellsPanel(Player player) throws IOException{
		this.player = player;
		this.setOpaque(false);
		//this.setLayout(new GridLayout(2,5));
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(600, 110));
		spellButtons= new ArrayList<SpellButton>();
		ArrayList<SpellCard> spellArea = player.getField().getSpellArea();
		for (int i=0; i<3;i++) {
			JPanel buttonContainer = new JPanel();
			buttonContainer.setPreferredSize(new Dimension(100,115));
			buttonContainer.setOpaque(false);
			
			//buttonContainer.setBorder(BorderFactory.createLineBorder(Color.GRAY,2,false));
			SpellButton spellButton;
			
			if(i<spellArea.size())
				spellButton = new SpellButton(spellArea.get(i));
			else{
				
				spellButton = new SpellButton(null);
				//spellButton.setBackground(new Color(0,0,0,200));
				//spellButton.setEnabled(false);
				spellButton.setOpaque(false);
				//spellButton.setBorderPainted(false);
				spellButton.setEnabled(false);
				spellButton.setContentAreaFilled(false);
				//BufferedImage img = ImageIO.read(new File("art/cards/back.png"));
				//Image cardImg = img.getScaledInstance(68, 100, Image.SCALE_SMOOTH);
				//spellButton.setIcon(new ImageIcon(cardImg));
			}
			
			buttonContainer.add(spellButton);
			spellButtons.add(spellButton);
			this.add(buttonContainer);
		}
	}
	public void updatePanel(Player player) throws IOException{
		ArrayList<SpellCard> spellArea = player.getField().getSpellArea();
		for (int i=0; i<3;i++) {
			if(i<spellArea.size()){
				
				SpellCard spell = spellArea.get(i);
				spellButtons.get(i).setSpell(spell);
				spellButtons.get(i).updateButton();
				spellButtons.get(i).setEnabled(true);
			}
			else{
				spellButtons.get(i).setSpell(null);
				spellButtons.get(i).updateButton();
				spellButtons.get(i).setEnabled(false);
				//spellButtons.get(i).setIcon(new ImageIcon(ImageIO.read(new File("art/cards/back.png"))));
			}
		}
		
	}
}
