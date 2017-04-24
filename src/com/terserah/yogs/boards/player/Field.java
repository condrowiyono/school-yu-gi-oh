package com.terserah.yogs.boards.player;

import java.io.IOException;
import java.util.ArrayList;

import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.Location;
import com.terserah.yogs.cards.Mode;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.SpellCard;

public class Field {
	private ArrayList<MonsterCard> monstersArea;
	private Phase phase;
	private ArrayList<SpellCard> spellArea;
	private Deck deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> graveyard;
	
	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public ArrayList<MonsterCard> getMonstersArea() {
		return monstersArea;
	}

	public ArrayList<SpellCard> getSpellArea() {
		return spellArea;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}
	
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	
	public Field() throws Exception {
		this.monstersArea = new ArrayList<MonsterCard>(5);
		this.spellArea = new ArrayList<SpellCard>(5);
		this.hand = new ArrayList<Card>();
		this.graveyard  = new ArrayList<Card>();
		deck = new Deck();
		this.setPhase(Phase.MAIN1);

	}

	public Deck getDeck() {
		return deck;
	}

	//extra method to help .
	public int sacrifices(MonsterCard monster) { 
		if(monster.getLevel()<4) return 0 ;
		else 
			if(monster.getLevel() == 5 || monster.getLevel() == 6) return 1 ;
			else
				return 2 ;
	}

	public void addMonsterToField(MonsterCard monster, Mode mode,boolean isHidden){
		if(this.monstersArea.size()<5 ) {
			monster.setLocation(Location.FIELD);
			this.monstersArea.add(monster);
			monster.setMode(mode);
			monster.setHidden(isHidden);
		}
	}

	public void addMonsterToField(MonsterCard monster, Mode mode, ArrayList<MonsterCard>sacrifices) {
			if( sacrifices == null) addMonsterToField(monster,mode,monster.getMode().equals(Mode.ATTACK)? false : true );
			else{
				if(sacrifices(monster)==sacrifices.size()){

					this.monstersArea.add(monster);
					monster.setMode(mode);
					monster.setLocation(Location.FIELD);
					if (mode==Mode.ATTACK)
						monster.setHidden(false);
					else
						monster.setHidden(true);
					removeMonsterToGraveyard(sacrifices);
				}
			}
	}

	public void removeMonsterToGraveyard(MonsterCard monster){
		if(monster.getLoc()==Location.FIELD){
		monster.setLocation(Location.GRAVEYARD);
		this.graveyard.add(monster);
		this.monstersArea.remove(monster);
		}
	}

	public void removeMonsterToGraveyard(ArrayList<MonsterCard> monsters){
		while(monsters.size()>0){
			MonsterCard m = monsters.remove(0);
			m.setLocation(Location.GRAVEYARD);
			this.graveyard.add(m);
			this.monstersArea.remove(m);
		}
	}

	public void addSpellToField(SpellCard action,MonsterCard monster, boolean hidden) throws IOException{
		if(this.spellArea.size() < 5 ) {
			this.spellArea.add(action);
			action.setLocation(Location.FIELD);
			this.hand.remove(action);
			if(!hidden) activateSetSpell(action,monster);
		}
		// missing setting monster to null :D 
	}

	public void activateSetSpell(SpellCard action, MonsterCard monster) throws IOException{
		if (spellArea.contains(action)){
			action.action(monster);
			this.spellArea.remove(action);
			removeSpellToGraveyard(action);
		}
		// handle set monster to null ! 
	}


	public void removeSpellToGraveyard(SpellCard spell){
		if(spell.getLoc() == Location.FIELD){
		spell.setLocation(Location.GRAVEYARD);
		this.graveyard.add(spell);
		this.spellArea.remove(spell);
		}
	}

	public void removeSpellToGraveyard(ArrayList<SpellCard> spells){
		for(int i=0; i<spells.size(); i++) { 
			spells.get(i).setLocation(Location.GRAVEYARD);
			this.graveyard.add(spells.get(i));
		}
		spellArea = new ArrayList<SpellCard>();
	}

	public void addCardToHand(){
		if(this.getDeck().getDeck().size()==0){
			if(this == Card.getBoard().getActivePlayer().getField())
			{
				Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			}
			else
				Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
			return;
				
		}
		Card card = this.deck.drawOneCard();
		this.hand.add(card);
		card.setLocation(Location.HAND);
	}

	public void addNCardsToHand(int n){
		for(int i=0; i<n; i++){
			addCardToHand();
		}
	}
}



