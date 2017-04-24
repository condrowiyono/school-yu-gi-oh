package com.terserah.yogs.cards.spells;

import java.io.IOException;

import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.Location;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.exception.NoMonsterSpaceException;


public class ChangeOfHeart extends SpellCard {

	public ChangeOfHeart(String name, String desc, Location loc, float prob) throws IOException {
		super(name, desc, loc, prob);
		// TODO Auto-generated constructor stub
	}
	
	public void action(MonsterCard monster){
		if (Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5)
			throw new NoMonsterSpaceException();
		if (Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()<5){
			Card.getBoard().getOpponentPlayer().getField().getMonstersArea().remove(monster);
			Card.getBoard().getActivePlayer().getField().getMonstersArea().add(monster);
		}
		
	}

	@Override
	public String getJenis() {
		// TODO Auto-generated method stub
		return "Spell";
	}

}
