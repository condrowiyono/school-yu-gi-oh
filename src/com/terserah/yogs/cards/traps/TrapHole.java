package com.terserah.yogs.cards.traps;

import java.io.IOException;

import com.terserah.yogs.cards.Location;
import com.terserah.yogs.cards.MonsterCard;

public class TrapHole extends TrapCard {

	public TrapHole(String name, String desc, Location loc, float prob) throws IOException {
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
		return "Trap";
	}

}
