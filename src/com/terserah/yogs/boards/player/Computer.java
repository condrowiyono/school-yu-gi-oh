package com.terserah.yogs.boards.player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.Mode;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.SpellCard;
import com.terserah.yogs.duel.gui.DuelMain;

public class Computer extends Player {

	public Computer(String name, Deck deck, String rank) throws Exception {
		super(name,deck,rank);
	}
	public Computer(String name, Deck deck) throws Exception {
		super(name,deck);
	}
	
	Timer t;
	
	public void computerTurn() throws Exception{

		Computer p = this;
		 t = new Timer(350, new ActionListener() {
			int phase=1;
			public void actionPerformed(ActionEvent e) {
				if(phase == 1){
					try{
						p.mainPhase1();
					}
					catch(Exception e1){
						//do nothing
					}
					p.updateGUI();
				}
				else if (phase==2){
					try{
						p.endPhase();
						p.battlePhase();
						p.updateGUI();
					}
					catch(Exception e2){

					}
				}
				else if (phase==3){
					try{
						p.endPhase();
						p.mainPhase2();
						p.updateGUI();
					}
					catch(Exception e2){

					}
				}
				else{
					try{
						endPhase();
						updateGUI();
					}
					catch(Exception e2){
						
					}
					((Timer)e.getSource()).stop();
				}
				phase++;
			}
		});
		t.setRepeats(true);
		t.start();
		


		
	}

	public void mainPhase1() throws Exception{
		for (Card c:this.getField().getHand())
			System.out.println(c.getName());
		System.out.println("---");
		
		SpellCard containsCardDestruction = containsCardDestruction();
		SpellCard containsChangeOfHeart = containsChangeOfHeart();
		SpellCard containsDarkHole = containsDarkHole();
		SpellCard containsGracefulDice = containsGracefulDice();
		SpellCard containsHarpieFeather = containsHarpieFeather();
		SpellCard containsHeavyStorm = containsHeavyStorm();
		SpellCard containsMagePower = containsMagePower();
		SpellCard containsPotOfGreed= containsPotOfGreed();
		SpellCard containsRaigeki= containsRaigeki();
		SpellCard containsMonsterReborn  = containsMonsterReborn();

		MonsterCard maxAttackMonsterInHand = maxAttackMonsterInHand();
		MonsterCard maxAttackMonsterInHandOneSacrifice = maxAttackMonsterInHandOneSacrifice();
		MonsterCard maxAttackMonsterInHandTwoSacrifice = maxAttackMonsterInHandTwoSacrifice();
		MonsterCard maxDefenseMonsterInHand = maxDefenseMonsterInHand();
		MonsterCard minAttackMonsterInHand = minAttackMonsterInHand();

		MonsterCard maxAttackMonsterInMyField = maxAttackMonsterInMyField();
		MonsterCard maxAttackMonsterInOpponentField = maxAttackMonsterInOpponentField();

		MonsterCard maxAttackMonsterInMyGraveyard = maxAttackMonsterInMyGraveyard();
		MonsterCard maxAttackMonsterInOpponentGraveyard = maxAttackMonsterInOpponentGraveyard();


		if(containsPotOfGreed!=null){
			if(this.getField().getDeck().getDeck().size()>3){
				this.activateSpell(containsPotOfGreed, null);
				
				containsCardDestruction = containsCardDestruction();
				containsChangeOfHeart = containsChangeOfHeart();
				containsDarkHole = containsDarkHole();
				containsGracefulDice = containsGracefulDice();
				containsHarpieFeather = containsHarpieFeather();
				containsHeavyStorm = containsHeavyStorm();
				containsMagePower = containsMagePower();
				containsPotOfGreed= containsPotOfGreed();
				containsRaigeki= containsRaigeki();

				maxAttackMonsterInHand = maxAttackMonsterInHand();
				maxDefenseMonsterInHand = maxDefenseMonsterInHand();
				minAttackMonsterInHand = minAttackMonsterInHand();
				maxAttackMonsterInHandOneSacrifice = maxAttackMonsterInHandOneSacrifice();
				maxAttackMonsterInHandTwoSacrifice = maxAttackMonsterInHandTwoSacrifice();
			}
		}
		if(containsHarpieFeather!=null){
			if(Card.getBoard().getActivePlayer().getField().getSpellArea().size()>1){
				this.activateSpell(containsHarpieFeather, null);
				
			}
			containsHarpieFeather = containsHarpieFeather();
		}
		if(containsHeavyStorm!=null){
			if(Card.getBoard().getActivePlayer().getField().getSpellArea().size()>=2 && this.getField().getSpellArea().size()<=1){
				this.activateSpell(containsHeavyStorm, null);
				
			}
			containsHeavyStorm = containsHeavyStorm();
		}
		if(containsRaigeki!=null){
			boolean activated = false;
			if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()>2)
				activated = true;
			else if(maxAttackMonsterInMyField == null && maxAttackMonsterInOpponentField!=null)
				activated = true;
			else if(maxAttackMonsterInMyField !=null && maxAttackMonsterInOpponentField !=null && maxAttackMonsterInMyField.getATK()<maxAttackMonsterInOpponentField.getATK())
				activated = true;
			if(activated){
				this.activateSpell(containsRaigeki, null);
				
				maxAttackMonsterInOpponentField = maxAttackMonsterInOpponentField();
				containsRaigeki = containsRaigeki();
			}
			
		}

		if(containsDarkHole != null){
			boolean activated = false;
			if(maxAttackMonsterInMyField == null && maxAttackMonsterInOpponentField!=null){
				activated = true;
			}
			else if(maxAttackMonsterInMyField !=null && maxAttackMonsterInOpponentField !=null && maxAttackMonsterInMyField.getATK()<maxAttackMonsterInOpponentField.getATK() && maxAttackMonsterInHand==null){
				activated = true;
			}
			else if(maxAttackMonsterInMyField !=null && maxAttackMonsterInOpponentField !=null  && maxAttackMonsterInHand!=null && maxAttackMonsterInMyField.getATK()<maxAttackMonsterInOpponentField.getATK() && maxAttackMonsterInOpponentField.getATK()>maxAttackMonsterInHand.getATK()){
				activated = true;
				
			}
			if(activated){
				this.activateSpell(containsDarkHole, null);
				System.out.println("ATTENTION: DARK HOLE!");
				maxAttackMonsterInMyField = maxAttackMonsterInMyField();
				maxAttackMonsterInOpponentField = maxAttackMonsterInOpponentField();
				containsDarkHole = containsDarkHole();
				
			}
			
		}

		if(containsChangeOfHeart!=null){
			if(maxAttackMonsterInOpponentField!=null && maxAttackMonsterInOpponentField.getATK()>1500)
			{
				this.activateSpell(containsChangeOfHeart, maxAttackMonsterInOpponentField);
				containsChangeOfHeart = containsChangeOfHeart();
				maxAttackMonsterInOpponentField = maxAttackMonsterInOpponentField();
				maxAttackMonsterInMyField = maxAttackMonsterInMyField();
				
			}
		}
		//summon monster
		if(maxAttackMonsterInOpponentField==null){
			//check two sacrifices
			if(this.getField().getMonstersArea().size()>=2 && maxAttackMonsterInHandTwoSacrifice!=null){
				MonsterCard min1=null;
				MonsterCard min2=null;
				int attack1 = 100000;
				int attack2 = 100000;
				for (MonsterCard c:this.getField().getMonstersArea()){
					if(c.getATK()<attack1){
						attack2=attack1;
						min2=min1;
						min1=c;
						attack1=c.getATK();
					}
					else if(c.getATK()<attack2){
						attack2=c.getATK();
						min2=c;
					}
				}
				if(maxAttackMonsterInHandTwoSacrifice.getATK()>attack2){
					ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
					sacrifices.add(min1);
					sacrifices.add(min2);
					this.summonMonster(maxAttackMonsterInHandTwoSacrifice, sacrifices);
					maxAttackMonsterInHandTwoSacrifice = maxAttackMonsterInHandTwoSacrifice();
					maxAttackMonsterInMyField = maxAttackMonsterInMyField();
					minAttackMonsterInHand = minAttackMonsterInHand();
				}
			}
			//check one sacrifice
			else if(this.getField().getMonstersArea().size()>=1 && maxAttackMonsterInHandOneSacrifice!=null){
				MonsterCard min1=null;
				int attack1 = 100000;

				for (MonsterCard c:this.getField().getMonstersArea()){
					if(c.getATK()<attack1){
						min1=c;
						attack1=c.getATK();
					}
				}
				if(maxAttackMonsterInHandOneSacrifice.getATK()>attack1){
					ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
					sacrifices.add(min1);
					this.summonMonster(maxAttackMonsterInHandOneSacrifice, sacrifices);
					maxAttackMonsterInHandOneSacrifice = maxAttackMonsterInHandTwoSacrifice();
					maxAttackMonsterInMyField = maxAttackMonsterInMyField();
					minAttackMonsterInHand = minAttackMonsterInHand();
				}
			}
			if(maxAttackMonsterInHand!=null){
				this.summonMonster(maxAttackMonsterInHand);
				

			}

		}
		else{
			//opponent monster area has monsters
			if(maxAttackMonsterInMyField==null){
				if(maxAttackMonsterInHand!=null){
					if(maxAttackMonsterInHand.getATK()>=maxAttackMonsterInOpponentField.getATK() || (containsMagePower!=null && maxAttackMonsterInHand.getATK()*(500+countSpells())>=maxAttackMonsterInOpponentField.getATK()) || (containsGracefulDice!=null && maxAttackMonsterInHand.getATK()+400>=maxAttackMonsterInOpponentField.getATK()) )
						this.summonMonster(maxAttackMonsterInHand);
					else if(maxAttackMonsterInHand.getATK()<maxAttackMonsterInOpponentField.getATK()){
						if(maxDefenseMonsterInHand.getDEF()>maxAttackMonsterInOpponentField.getATK())
							setMonster(maxDefenseMonsterInHand);
						else
							setMonster(minAttackMonsterInHand);
					}
				}
			}
			else{
				//check two sacrifices
				if(this.getField().getMonstersArea().size()>=2 && maxAttackMonsterInHandTwoSacrifice !=null && (maxAttackMonsterInHandTwoSacrifice.getATK()>maxAttackMonsterInOpponentField.getATK()  || (containsMagePower!=null && maxAttackMonsterInHandTwoSacrifice.getATK()*(500+countSpells())>=maxAttackMonsterInOpponentField.getATK()) || (containsGracefulDice!=null && maxAttackMonsterInHandTwoSacrifice.getATK()+400>=maxAttackMonsterInOpponentField.getATK()) )){

					MonsterCard min1=null;
					MonsterCard min2=null;
					int attack1 = 100000;
					int attack2 = 100000;
					for (MonsterCard c:this.getField().getMonstersArea()){
						if(c.getATK()<=attack1 && c!=min1){
							attack2=attack1;
							min2=min1;
							min1=c;
							attack1=c.getATK();
						}
						else if(c.getATK()<attack2){
							attack2=c.getATK();
							min2 = c;
						}
					}
					if(maxAttackMonsterInHandTwoSacrifice.getATK()>attack2){
						ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
						sacrifices.add(min1);
						sacrifices.add(min2);
						this.summonMonster(maxAttackMonsterInHandTwoSacrifice, sacrifices);
						maxAttackMonsterInHandTwoSacrifice = maxAttackMonsterInHandTwoSacrifice();
						maxAttackMonsterInMyField = maxAttackMonsterInMyField();
						minAttackMonsterInHand = minAttackMonsterInHand();
					}

				}
				//check one sacrifice
				else if(this.getField().getMonstersArea().size()>=1 && maxAttackMonsterInHandOneSacrifice!=null && (maxAttackMonsterInHandOneSacrifice.getATK()>maxAttackMonsterInOpponentField.getATK() || maxAttackMonsterInMyField.getATK()>maxAttackMonsterInOpponentField.getATK()  || (containsMagePower!=null && maxAttackMonsterInHandOneSacrifice.getATK()*(500+countSpells())>=maxAttackMonsterInOpponentField.getATK()) || (containsGracefulDice!=null && maxAttackMonsterInHandOneSacrifice.getATK()+400>=maxAttackMonsterInOpponentField.getATK()) )){
					MonsterCard min1=null;
					int attack1 = 100000;

					for (MonsterCard c:this.getField().getMonstersArea()){
						if(c.getATK()<attack1){
							min1=c;
							attack1=c.getATK();
						}
					}
					if(maxAttackMonsterInHandOneSacrifice.getATK()>attack1){
						ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
						sacrifices.add(min1);
						this.summonMonster(maxAttackMonsterInHandOneSacrifice, sacrifices);
						maxAttackMonsterInHandOneSacrifice = maxAttackMonsterInHandTwoSacrifice();
						maxAttackMonsterInMyField = maxAttackMonsterInMyField();
						minAttackMonsterInHand = minAttackMonsterInHand();
					}
				}
				if(maxAttackMonsterInHand!=null && (maxAttackMonsterInHand.getATK()>maxAttackMonsterInOpponentField.getATK() || maxAttackMonsterInMyField.getATK()>maxAttackMonsterInOpponentField.getATK()  || (containsMagePower!=null && maxAttackMonsterInHand.getATK()*(500+countSpells())>=maxAttackMonsterInOpponentField.getATK()) || (maxAttackMonsterInHand!=null && maxAttackMonsterInHandOneSacrifice.getATK()+400>=maxAttackMonsterInOpponentField.getATK()))){
					this.summonMonster(maxAttackMonsterInHand);
					maxAttackMonsterInHand = maxAttackMonsterInHand();
					maxAttackMonsterInMyField = maxAttackMonsterInMyField();
					minAttackMonsterInHand = minAttackMonsterInHand();
				}
				else if(maxDefenseMonsterInHand!=null && maxDefenseMonsterInHand.getDEF()>=maxAttackMonsterInOpponentField.getATK())
					setMonster(maxDefenseMonsterInHand);
				else if(maxDefenseMonsterInHand!=null && maxDefenseMonsterInHand.getDEF()<maxAttackMonsterInOpponentField.getATK())
					setMonster(minAttackMonsterInHand());
			}
		}
		maxAttackMonsterInHand = maxAttackMonsterInHand();
		maxAttackMonsterInMyField = maxAttackMonsterInMyField();
		minAttackMonsterInHand = minAttackMonsterInHand();
		

		if(containsMonsterReborn!=null){
			if(maxAttackMonsterInMyGraveyard!=null || maxAttackMonsterInOpponentGraveyard!=null){
				MonsterCard max;
				if(maxAttackMonsterInMyGraveyard==null)
					max = maxAttackMonsterInOpponentGraveyard;
				else if(maxAttackMonsterInOpponentGraveyard==null)
					max = maxAttackMonsterInMyGraveyard;
				else{
					if(maxAttackMonsterInMyGraveyard.getATK()>maxAttackMonsterInOpponentGraveyard.getATK())
						max = maxAttackMonsterInMyGraveyard;
					else
						max = maxAttackMonsterInOpponentGraveyard;
				}
				boolean activated = false;
				if(maxAttackMonsterInOpponentField==null)
					activated =true;
				else if(maxAttackMonsterInMyField==null && maxAttackMonsterInOpponentField!=null && max.getATK()>maxAttackMonsterInOpponentField.getATK())
					activated = true;
				else if(maxAttackMonsterInMyField!=null && maxAttackMonsterInOpponentField!=null && (maxAttackMonsterInMyField.getATK()>maxAttackMonsterInOpponentField.getATK() || max.getATK()>=maxAttackMonsterInOpponentField.getATK()))
					activated = true;
				if(activated){
					this.activateSpell(containsMonsterReborn, null);
					
				}
				maxAttackMonsterInMyGraveyard = maxAttackMonsterInMyGraveyard();
				maxAttackMonsterInOpponentGraveyard = maxAttackMonsterInOpponentGraveyard();
				maxAttackMonsterInMyField = maxAttackMonsterInMyField();
				containsMonsterReborn = containsMonsterReborn();


			}
		}
		if(containsGracefulDice!=null){
			if(maxAttackMonsterInOpponentField==null && maxAttackMonsterInMyField!=null && maxAttackMonsterInMyField.getATK()>1500){
				this.activateSpell(containsGracefulDice, null);
				
			}
			else if(maxAttackMonsterInOpponentField!=null && maxAttackMonsterInMyField!=null && maxAttackMonsterInOpponentField.getATK()<=maxAttackMonsterInMyField.getATK()+500){
				this.activateSpell(containsGracefulDice, null);
				
			}
			containsGracefulDice = containsGracefulDice();
		}


		if(containsMagePower!=null){
			if(maxAttackMonsterInMyField!=null){
				ArrayList<SpellCard> spells = new ArrayList<SpellCard>();
				for(Card c:this.getField().getHand()){
					if(c instanceof SpellCard && c!=containsMagePower){
						spells.add((SpellCard)c);
					}
				}
				while(!spells.isEmpty()){
					this.setSpell(spells.remove(0));
				}
				this.activateSpell(containsMagePower, maxAttackMonsterInMyField);
				
				containsMagePower = containsMagePower();
			}
		}
		if(containsCardDestruction!=null){
			if(this.getField().getHand().size()>1 && this.getField().getDeck().getDeck().size()>this.getField().getHand().size()+2 && (maxAttackMonsterInHand==null || maxAttackMonsterInHand.getATK()<2000) && maxAttackMonsterInHandOneSacrifice==null && maxAttackMonsterInHandTwoSacrifice==null){
				if(containsRaigeki!=null)
					this.setSpell(containsRaigeki);
				if(containsDarkHole!=null)
					this.setSpell(containsDarkHole);
				if(containsMagePower!=null)
					this.setSpell(containsMagePower);
				if(containsMonsterReborn!=null)
					this.setSpell(containsMonsterReborn);
				this.activateSpell(containsCardDestruction, null);
				

				minAttackMonsterInHand = minAttackMonsterInHand();
				maxAttackMonsterInHand = maxAttackMonsterInHand();
				maxDefenseMonsterInHand = maxDefenseMonsterInHand();
				containsCardDestruction = containsCardDestruction();
			}

		}

		//switch monsters' mode

		if(maxAttackMonsterInMyField!=null && maxAttackMonsterInOpponentField!=null && maxAttackMonsterInMyField.getATK()<maxAttackMonsterInOpponentField.getATK()){
			for (MonsterCard c:this.getField().getMonstersArea())
				if(c.getMode()==Mode.ATTACK){
					this.switchMonsterMode(c);
					
				}
		}
		else if(maxAttackMonsterInOpponentField==null || maxAttackMonsterInMyField!=null && maxAttackMonsterInOpponentField!=null && maxAttackMonsterInMyField.getATK()>maxAttackMonsterInOpponentField.getATK()){
			for (MonsterCard c:this.getField().getMonstersArea())
				if(c.getMode()==Mode.DEFENSE){
					this.switchMonsterMode(c);
					
				}
		}


	}



	private int countSpells() {
		int count = 0;
		return count;
	}

	private MonsterCard minAttackMonsterInHand() {
		int minAttack = 100000;
		MonsterCard min = null;
		for (Card c:this.getField().getHand()){
			if(c instanceof MonsterCard && ((MonsterCard)c).getATK()<minAttack && ((MonsterCard)c).getLevel()<=4){
				min = (MonsterCard) c;
				minAttack = min.getATK();
			}
		}
		return min;
	}



	private MonsterCard maxAttackMonsterInHandTwoSacrifice() {
		int maxAttack = 0;
		MonsterCard max = null;
		for (Card c:this.getField().getHand()){
			if(c instanceof MonsterCard && ((MonsterCard)c).getATK()>maxAttack && ((MonsterCard)c).getLevel()>6){
				max = (MonsterCard) c;
				maxAttack = max.getATK();
			}
		}
		return max;
	}

	private MonsterCard maxAttackMonsterInHandOneSacrifice() {
		int maxAttack = 0;
		MonsterCard max = null;
		for (Card c:this.getField().getHand()){
			if(c instanceof MonsterCard && ((MonsterCard)c).getATK()>maxAttack && (((MonsterCard)c).getLevel()==5 || ((MonsterCard)c).getLevel()==6)){
				max = (MonsterCard) c;
				maxAttack = max.getATK();
			}
		}
		return max;
	}

	private MonsterCard maxAttackMonsterInMyGraveyard() {
		MonsterCard max = null;
		int maxAttack = 0;
		for (Card c:this.getField().getGraveyard()){
			if(c instanceof MonsterCard)
				if(((MonsterCard)c).getATK()>maxAttack){
					max = (MonsterCard)c;
					maxAttack = max.getATK();
				}
		}
		return max;
	}

	private MonsterCard maxAttackMonsterInOpponentGraveyard() {
		MonsterCard max = null;
		int maxAttack = 0;
		for (Card c:Card.getBoard().getOpponentPlayer().getField().getGraveyard()){
			if(c instanceof MonsterCard)
				if(((MonsterCard)c).getATK()>maxAttack){
					max = (MonsterCard)c;
					maxAttack = max.getATK();
				}
		}
		return max;
	}

	private MonsterCard maxAttackMonsterInOpponentField() {
		MonsterCard max = null;
		int maxAttack = 0;

		for (MonsterCard c:Card.getBoard().getOpponentPlayer().getField().getMonstersArea())
			if(c.getATK()>maxAttack){
				maxAttack = c.getATK();
				max = c;
			}
		return max;
	}

	private MonsterCard maxAttackMonsterInMyField() {
		MonsterCard max = null;
		int maxAttack = 0;

		for (MonsterCard c:this.getField().getMonstersArea())
			if(c.getATK()>maxAttack){
				maxAttack = c.getATK();
				max = c;
			}
		return max;
	}

	private MonsterCard maxDefenseMonsterInHand() {

		int maxDefense = 0;
		MonsterCard max = null;
		for (Card c:this.getField().getHand()){
			if(c instanceof MonsterCard && ((MonsterCard)c).getDEF()>maxDefense && ((MonsterCard)c).getLevel()<=4){
				max = (MonsterCard) c;
				maxDefense = max.getDEF();
			}
		}
		return max;
	}

	private MonsterCard maxAttackMonsterInHand() {
		int maxAttack = 0;
		MonsterCard max = null;
		for (Card c:this.getField().getHand()){
			if(c instanceof MonsterCard && ((MonsterCard)c).getATK()>maxAttack && ((MonsterCard)c).getLevel()<=4){
				max = (MonsterCard) c;
				maxAttack = max.getATK();
			}
		}
		return max;
	}

	private SpellCard containsRaigeki() {
		return null;
	}

	private SpellCard containsPotOfGreed() {
		return null;
	}

	private SpellCard containsMagePower() {
		return null;
	}
	private SpellCard containsMonsterReborn() {
		return null;
	}

	private SpellCard containsHeavyStorm() {
		return null;
	}

	private SpellCard containsHarpieFeather() {
		return null;
	}

	private SpellCard containsGracefulDice() {
		return null;
	}

	private SpellCard containsDarkHole() {
		return null;
	}

	private SpellCard containsChangeOfHeart() {
		return null;
	}

	private SpellCard containsCardDestruction() {
		return null;
	}

	public void battlePhase() throws Exception{
		ArrayList<MonsterCard> myMonsters = new ArrayList<MonsterCard>();
		for(MonsterCard c:this.getField().getMonstersArea())
			if(c.getMode()==Mode.ATTACK)
				myMonsters.add(c);

		if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()>0){
			sortByAttack(myMonsters);

			ArrayList<MonsterCard> opponentMonsters = Card.getBoard().getOpponentPlayer().getField().getMonstersArea();

			for(MonsterCard monster:myMonsters){
				if(opponentMonsters.size()==0)
					this.declareAttack(monster);
				else
					attackWithMonster(monster,opponentMonsters);
				opponentMonsters = Card.getBoard().getOpponentPlayer().getField().getMonstersArea();
			}
		}
		else{
			for(MonsterCard monster:myMonsters){
				if(monster.getMode().equals(Mode.ATTACK)){
					this.declareAttack(monster);
					
				}
			}
		}


	}

	private void attackWithMonster(MonsterCard monster,
			ArrayList<MonsterCard> opponentMonsters) throws Exception {
		int maxNumber = -1;
		MonsterCard max = null;
		for(MonsterCard oppMonster:opponentMonsters){
			if(oppMonster.getMode()==Mode.ATTACK && oppMonster.getATK()>maxNumber && monster.getATK()>oppMonster.getATK()){
				maxNumber = oppMonster.getATK();
				max = oppMonster;
			}
		}
		for(MonsterCard oppMonster:opponentMonsters){
			if(oppMonster.getMode()==Mode.DEFENSE && oppMonster.getDEF()>maxNumber && monster.getATK()>oppMonster.getDEF()){
				maxNumber = oppMonster.getDEF();
				max = oppMonster;
			}
		}
		if(max!=null){
			this.declareAttack(monster, max);
			
		}
		

	}

	private void sortByAttack(ArrayList<MonsterCard> monsters) {
		for(int i=0; i<monsters.size()-1; i++){
			for (int j=0;j<monsters.size()-1-i; j++){
				if(monsters.get(j).getATK()<monsters.get(j+1).getATK()){
					MonsterCard temp = monsters.get(j);
					monsters.set(j, monsters.get(j+1));
					monsters.set(j+1, temp);
				}
			}
		}

	}

	public void mainPhase2(){
		//no need
	}


	public void updateGUI() {
		try{
			
			DuelMain.controller.getActiveGraveAndDeck().updatePanel();
			DuelMain.controller.getOpponentGraveAndDeck().updatePanel();
			DuelMain.controller.getActiveHandPanel().updatePanel(DuelMain.controller.getActiveHandPanel().getPlayer());
			DuelMain.controller.getOpponentHandPanel().updatePanel(DuelMain.controller.getOpponentHandPanel().getPlayer());
			DuelMain.controller.getActiveInfoPanel().updatePanel(DuelMain.controller.getActiveInfoPanel().getPlayer());
			DuelMain.controller.getOpponentInfoPanel().updatePanel(DuelMain.controller.getOpponentInfoPanel().getPlayer());
			DuelMain.controller.getActiveMonstersPanel().updatePanel(DuelMain.controller.getActiveMonstersPanel().getPlayer());
			DuelMain.controller.getOpponentMonstersPanel().updatePanel(DuelMain.controller.getOpponentMonstersPanel().getPlayer());
			DuelMain.controller.getActiveSpellsPanel().updatePanel(DuelMain.controller.getActiveSpellsPanel().getPlayer());
			DuelMain.controller.getOpponentSpellsPanel().updatePanel(DuelMain.controller.getOpponentSpellsPanel().getPlayer());
			DuelMain.controller.getPhasePanel().updatePanel();
			DuelMain.controller.addActionListenersToButtons();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
