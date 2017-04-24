package com.terserah.yogs.cards.spells;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.Location;
import com.terserah.yogs.cards.MonsterCard;

abstract public class SpellCard extends Card {
	private BufferedImage image;
	public BufferedImage getImage() {
		return image;
	}

	public SpellCard(String name, String desc, Location loc, float prob) throws IOException{
		super(name, desc, loc, prob);
		try{
			this.image = ImageIO.read(new File("art/cards/spells/"+this.getName()+".jpg"));
		}
			catch(Exception e){
				this.image = ImageIO.read(new File("art/cards/spells/blank.jpg"));
			}
	}

	@Override
	abstract public void action(MonsterCard monster) throws IOException;
	
	
}    
