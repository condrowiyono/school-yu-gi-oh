package com.terserah.yogs.menu.gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.terserah.yogs.cards.traps.TrapCard;

public class TrapButton extends CardButton {
	TrapCard traps;
	public TrapCard getTrap() {
		return traps;
	}

	public void setTrap(TrapCard traps) {
		this.traps = traps;
	}

	public TrapButton (TrapCard traps) { 
		super();
		this.traps = traps;
		
		if(traps!=null){
			Image cardImage = this.traps.getImage().getScaledInstance(68, 100,Image.SCALE_SMOOTH);
			this.setIcon(new ImageIcon(cardImage));
		}

	}
  
	public void updateButton() throws IOException{
	  if(traps!=null){
		  this.setVisible(true);
		  Image image;
		  if(this.traps.isHidden()){
			  image = ImageIO.read(new File("art/cards/back.png"));
		  }
		  else{
			  image = ImageIO.read(new File("art/cards/traps/"+traps.getImage()+".jpg"));
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
