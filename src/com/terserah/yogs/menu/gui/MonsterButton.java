package com.terserah.yogs.menu.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.terserah.yogs.cards.Mode;
import com.terserah.yogs.cards.MonsterCard;

public class MonsterButton extends CardButton {
	private MonsterCard monster;
	
	public MonsterCard getMonster() {
		return monster;
	}

	public MonsterButton (MonsterCard monster) { 
		super();
		this.monster = monster;

		if(monster!=null){
			Image cardImage = this.monster.getImage().getScaledInstance(68, 100,Image.SCALE_SMOOTH);
			this.setIcon(new ImageIcon(cardImage));
			this.setForeground(Color.WHITE);
		}
	}

	public void updateButton() throws IOException{
		if(monster!=null){
			if(monster.getMode()==Mode.ATTACK){
				this.setPreferredSize(new Dimension(68,100));
				
				Image cardImage = this.monster.getImage().getScaledInstance(68, 100,Image.SCALE_SMOOTH);
				this.setIcon(new ImageIcon(cardImage));
			}
			else{
				Image cardImage;
				cardImage = ImageIO.read(new File("art/cards/back - Copy.png")).getScaledInstance(100, 68, Image.SCALE_SMOOTH);
				
				this.setPreferredSize(new Dimension(100,68));
				this.setIcon(new ImageIcon(cardImage));
			}
			this.setVisible(true);
		}
		else{
			this.setIcon(null);
			this.setVisible(false);
		}
	}

	public void setMonster(MonsterCard monster) {
		this.monster = monster;

	}
}
