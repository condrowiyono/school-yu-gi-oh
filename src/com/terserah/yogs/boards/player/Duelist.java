package com.terserah.yogs.boards.player;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.SpellCard;

public interface Duelist {

	public boolean summonMonster(MonsterCard monster);
	public boolean summonMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices);
	public boolean setMonster(MonsterCard monster);
	public boolean setMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices);
	public boolean setSpell(SpellCard spell) throws IOException;
	public boolean activateSpell(SpellCard spell, MonsterCard monster) throws IOException, Exception;
	public boolean declareAttack(MonsterCard activeMonster, MonsterCard opponentMonster) throws Exception;
	public boolean declareAttack(MonsterCard activeMonster) throws LineUnavailableException, Exception;
	public void addCardToHand();
	public void addNCardsToHand(int n);
	public boolean endTurn() throws Exception;
	public void endPhase() throws Exception;
	public boolean switchMonsterMode(MonsterCard monster);
	
}
