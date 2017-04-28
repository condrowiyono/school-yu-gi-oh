package com.terserah.yogs.boards.player;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.Location;
import com.terserah.yogs.cards.Mode;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.ChangeOfHeart;
import com.terserah.yogs.cards.spells.SpellCard;
import com.terserah.yogs.exception.CannotAddChangeOfHeartException;
import com.terserah.yogs.exception.DefenseMonsterAttackException;
import com.terserah.yogs.exception.MonsterMultipleAttackException;
import com.terserah.yogs.exception.MultipleMonsterAdditionException;
import com.terserah.yogs.exception.NoMonsterSpaceException;
import com.terserah.yogs.exception.NoSpellSpaceException;
import com.terserah.yogs.exception.WrongAttackedMonsterException;
import com.terserah.yogs.exception.WrongPhaseException;
import com.terserah.yogs.exception.WrongSacrifices;



public class Player implements Duelist{
	private String name;
	private boolean summonedMonster;
	private int lifePoints;
	private Field field;
	private Deck PLAYERDECK;
	private Deck ALLCARD;
	private Point POSISI;
	private int money;
	private String rank;
	
	public Player(String name) throws Exception{
		this.name = name; 
		lifePoints = 2000;
		field = new Field();
		money = 2000;
		summonedMonster = false;
		Point newPos = new Point(150,250);
	    this.POSISI = newPos;
	}
	
	public Player(String name, int x, int y, Deck deck, String rank) throws IOException, Exception {
	       this.name = name;
	       Point newPos = new Point(x,y);
	       this.PLAYERDECK = new Deck();
	       this.POSISI = newPos;
	       this.PLAYERDECK = deck;
	       this.rank = rank;
	       this.field = new Field();
	       this.lifePoints = 2000;
	       this.field = new Field();
	       summonedMonster = false;
	}
    
	public Player(String name, Deck deck) {
		this.name = name;
        this.money = 2000;
        this.ALLCARD = new Deck();
        this.ALLCARD = deck;
        Point newPos = new Point(150,250);
	    this.PLAYERDECK = new Deck();
	    this.POSISI = newPos;
	    try {
			this.field = new Field();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    this.money = 2000;
	    this.lifePoints = 2000;
    }
   
    
	public Player(String name, int x, int y, int money, Deck deck, String rank, Deck all) throws IOException, Exception {
	       this.name = name;
	       Point newPos = new Point(x,y);
	       this.PLAYERDECK = new Deck();
	       this.POSISI = newPos;
	       this.money = money;
	       this.PLAYERDECK = deck;
	       this.ALLCARD = all;
	       this.rank = rank;
	       this.field = new Field();
	       this.lifePoints = 2000;
	       summonedMonster = false;
	}
	
	public Player(String name, Deck deck, String rank) throws IOException, Exception {
	       this.name = name;
	       this.PLAYERDECK = new Deck();
	       this.PLAYERDECK = deck;
	       this.rank = rank;
	       this.field = new Field();
	       this.lifePoints = 2000;
	       summonedMonster = false;
	}
	
	public Player(String name, int x, int y, int money, Deck deck, String rank) throws IOException, Exception {
	       this.name = name;
	       Point newPos = new Point(x,y);
	       this.POSISI = newPos;
	       this.money = money;
	       this.PLAYERDECK = deck;
	       this.rank = rank;
	       this.field = new Field();
	       this.lifePoints = 2000;
	       summonedMonster = false;
	}
	
	public String getRank() {
		return this.rank;
	}
	public Point getPosisi() {
		return this.POSISI;
	}
	
	public void setPosisi(double x, double y) {
		this.POSISI.setLocation(x, y);
	}
	public int getMoney() {
		return money;
	}
	
	public boolean isSummonedMonster() {
		return summonedMonster;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	public void setSummonedMonster(boolean summonedMonster) {
		this.summonedMonster = summonedMonster;
	}

	public String getName() {
		return name;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	   
	public Deck getDeck() {
	       return this.PLAYERDECK;
	}
	public Deck getAllCard() {
	       return this.ALLCARD;
	}
	public void setPlayerDeck(Deck deck) {
		this.PLAYERDECK = deck;
	}
	   
	public boolean overDeck() {
		return ((this.PLAYERDECK.getDeck().size()<10) || 
				(this.PLAYERDECK.getDeck().size()>15));
	}
	public boolean summonMonster(MonsterCard monster) {
		
		if (this.isSummonedMonster())
			throw new MultipleMonsterAdditionException();
		if (!this.getField().getHand().contains(monster))
			return false;
		if (this.getField().getMonstersArea().size()>=3)
			throw new NoMonsterSpaceException();
		if (this == Card.getBoard().getOpponentPlayer())
			return false;
		if ( Card.getBoard().isGameEnds())
			return false;
		if(this.getField().getPhase()==Phase.BATTLE)
			throw new WrongPhaseException();
		this.getField().addMonsterToField(monster, Mode.ATTACK,false);
		monster.setLocation(Location.FIELD);
		this.getField().getHand().remove(monster);
		this.setSummonedMonster(true);
		return true;
	}
	
	public boolean summonMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices) {

		
		if(this.isSummonedMonster())
			throw new MultipleMonsterAdditionException();
		if(!this.getField().getHand().contains(monster))
			return false;
		if(this == Card.getBoard().getOpponentPlayer())
			return false;
		if( Card.getBoard().isGameEnds() )
			return false;
		if(this.getField().getPhase()==Phase.BATTLE)
			throw new WrongPhaseException();
		if(Card.getBoard().getActivePlayer().getField().sacrifices(monster)!=sacrifices.size())
			return false;
		for(int i=0; i<sacrifices.size(); i++){
			if(!Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(sacrifices.get(i)))
				throw new WrongSacrifices();
		}
		this.getField().addMonsterToField(monster, Mode.ATTACK,sacrifices);
		this.setSummonedMonster(true);
		monster.setLocation(Location.FIELD);
		this.getField().getHand().remove(monster);
		return true;
	}

	public boolean setMonster(MonsterCard monster){
		
		if(this.isSummonedMonster())
			throw new MultipleMonsterAdditionException();
		if(!this.getField().getHand().contains(monster))
			return false;
		if( Card.getBoard().isGameEnds())
			return false;
		if(this.getField().getMonstersArea().size()>=3)
			throw new NoMonsterSpaceException();
		if( this == Card.getBoard().getOpponentPlayer())
			return false;
		if(this.getField().getPhase()==Phase.BATTLE)
			throw new WrongPhaseException();
		this.getField().addMonsterToField(monster, Mode.DEFENSE,true);
		monster.setLocation(Location.FIELD);
		this.getField().getHand().remove(monster);
		this.setSummonedMonster(true);
		return true;
	}
	public boolean setMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices){ 

		
		if(this.isSummonedMonster())
			throw new MultipleMonsterAdditionException();
		if(!this.getField().getHand().contains(monster))
			return false;
		if(Card.getBoard().isGameEnds() )
			return false;
		if(this == Card.getBoard().getOpponentPlayer())
			return false;
		
		if(this.getField().getPhase()==Phase.BATTLE)
			throw new WrongPhaseException();
		if( Card.getBoard().getActivePlayer().getField().sacrifices(monster)!=sacrifices.size())
			return false;
		for(int i=0; i<sacrifices.size(); i++){
			if(!Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(sacrifices.get(i)))
				throw new WrongSacrifices();
		}
		this.getField().addMonsterToField(monster, Mode.DEFENSE,sacrifices);
		this.setSummonedMonster(true);
		monster.setLocation(Location.FIELD);
		this.getField().getHand().remove(monster);
		return true;
	}
	
	public boolean setSpell(SpellCard spell) throws IOException{ 
		
		if(this == Card.getBoard().getOpponentPlayer())
			return false;
		if(this.getField().getPhase()==Phase.BATTLE)
			throw new WrongPhaseException();
		if(!this.getField().getHand().contains(spell))
			return false;
		if(Card.getBoard().isGameEnds())
			return false;
		if(this.getField().getSpellArea().size()>=3)
			throw new NoSpellSpaceException();
		this.getField().addSpellToField(spell, null, true);
		return true;
	}

	public boolean activateSpell(SpellCard spell, MonsterCard monster) throws Exception{ 
		
		if(this == Card.getBoard().getOpponentPlayer())
			return false;
		if(this.getField().getPhase()==Phase.BATTLE)
			throw new WrongPhaseException();
		if(Card.getBoard().isGameEnds())
			return false;
		if(spell instanceof ChangeOfHeart){
			if (Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=3)
				throw new NoMonsterSpaceException();
			if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()==0)
				throw new CannotAddChangeOfHeartException();
		}
		
		if(spell.getLoc()== Location.HAND){
			if(this.getField().getSpellArea().size()>=3)
				throw new NoSpellSpaceException();
			else
				this.getField().addSpellToField(spell, monster, false);
		}else{
			if(spell.getLoc()== Location.FIELD)
				this.getField().activateSetSpell(spell, monster);
		}
		return true;
	}

	public boolean declareAttack(MonsterCard activeMonster, MonsterCard opponentMonster) throws Exception {
		
		if(this == Card.getBoard().getOpponentPlayer())
			return false;
		if( Card.getBoard().isGameEnds())
			return false;
		if(this.getField().getPhase()!=Phase.BATTLE)
			throw new WrongPhaseException();
		if(activeMonster.isAttacked())
			throw new MonsterMultipleAttackException();
		if(activeMonster.getMode()==Mode.DEFENSE)
			throw new DefenseMonsterAttackException();
		if(!Card.getBoard().getOpponentPlayer().getField().getMonstersArea().contains(opponentMonster))
			throw new WrongAttackedMonsterException();
		
		activeMonster.action(opponentMonster);
		activeMonster.setAttackingOption(true);
		return true;
	}

	public boolean declareAttack(MonsterCard activeMonster)throws Exception {


		if(this == Card.getBoard().getOpponentPlayer()) 
			return false; 
		if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()>0)
			return false;
		if(Card.getBoard().isGameEnds()) 
			return false;
		if( this.getField().getPhase()!=Phase.BATTLE )
			throw new WrongPhaseException();
		if(activeMonster.isAttacked()) 
			throw new MonsterMultipleAttackException();
		if(activeMonster.getMode()==Mode.DEFENSE) 
			throw new DefenseMonsterAttackException();
		String soundName = "sounds/Monster Destroyed.wav";    
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
		activeMonster.action();
		activeMonster.setAttackingOption(true);
		return true;
	}

	@Override
	public void addCardToHand() {
		if (this.getField().getDeck().getDeck().size()==0)
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
		else	
			this.getField().addCardToHand();

	}

	@Override
	public void addNCardsToHand(int n) {
		if(Card.getBoard().isGameEnds())
			return;
		if (this.getField().getDeck().getDeck().size()<n  || Card.getBoard().isGameEnds())
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
		else
			this.getField().addNCardsToHand(n);

	}
	public void endPhase() throws Exception{
		if(Card.getBoard().isGameEnds())
			return;
		Phase current = this.getField().getPhase();
		if (current == Phase.MAIN1)
			current = Phase.BATTLE;
		else if (current == Phase.BATTLE)
			current = Phase.MAIN2;
		else if (current == Phase.MAIN2)
			this.endTurn();
		this.getField().setPhase(current);
	}

	@Override
	public boolean endTurn() throws Exception{
		if(this == Card.getBoard().getOpponentPlayer() )
			return false; 
		if(Card.getBoard().isGameEnds())
			return false; 
		reInitialize();
		Card.getBoard().nextPlayer();
		String soundName = "sounds/Draw Card (2).wav";    
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
		return true;
	}

	public void reInitialize(){
		if (this == Card.getBoard().getOpponentPlayer())
			return;
		for(MonsterCard c:this.getField().getMonstersArea()){
			c.setAttackingOption(false);
			c.setSwitched(false);
		}
	}

	@Override
	public boolean switchMonsterMode(MonsterCard monster) {

		if(this == Card.getBoard().getOpponentPlayer())
			return false; 
		if(monster.isSwitched())
			return false;
		if(!this.getField().getMonstersArea().contains(monster))
			return false; 
		if(this.getField().getPhase()==Phase.BATTLE) 
			throw new WrongPhaseException();
		if (monster.getMode()==Mode.ATTACK)
		{
			monster.setMode(Mode.DEFENSE);
			monster.setHidden(true);
		}
		else{
			monster.setMode(Mode.ATTACK);
			monster.setHidden(false);
		}
		monster.setSwitched(true);
		return true;
	}
	


}
