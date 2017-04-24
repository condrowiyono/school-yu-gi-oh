package com.terserah.yogs.menu.gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.terserah.yogs.cards.spells.SpellCard;


public class SpellButton extends CardButton {
	SpellCard spell;
	public SpellCard getSpell() {
		return spell;
	}

	public void setSpell(SpellCard spell) {
		this.spell = spell;
	}

	public SpellButton (SpellCard spell) { 
		super();
		this.spell = spell;
		
		if(spell!=null){
			// this.setText(spell.getName());
			Image cardImage = this.spell.getImage().getScaledInstance(68, 100,Image.SCALE_SMOOTH);
			this.setIcon(new ImageIcon(cardImage));
		}

	}
  
	public void updateButton() throws IOException{
	  if(spell!=null){
		  this.setVisible(true);
		  Image image;
		  if(this.spell.isHidden()){
			  image = ImageIO.read(new File("art/cards/back.png"));
		  }
		  else{
			  image = ImageIO.read(new File("art/cards/spells/"+spell.getImage()+".jpg"));
		  }
		  Image cardImage = image.getScaledInstance(68, 100, Image.SCALE_SMOOTH);
		  this.setIcon(new ImageIcon(cardImage));
		  //this.setText(spell.getName());
	  }
	  else{
		  this.setIcon(null);
		  //this.setVisible(false);
	  }
  }
}
