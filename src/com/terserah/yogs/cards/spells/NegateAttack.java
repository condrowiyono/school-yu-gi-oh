package com.terserah.yogs.cards.spells;

import java.io.IOException;

import com.terserah.yogs.cards.Location;
import com.terserah.yogs.cards.MonsterCard;

public class NegateAttack extends SpellCard {

	public NegateAttack(String name, String desc, Location loc, float prob) throws IOException {
		super(name, desc, loc, prob);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action(MonsterCard monster) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getJenis() {
		// TODO Auto-generated method stub
		return "Spell";
	}

}
