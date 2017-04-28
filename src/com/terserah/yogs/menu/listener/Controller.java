package com.terserah.yogs.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.terserah.yogs.boards.Board;
import com.terserah.yogs.boards.player.Phase;
import com.terserah.yogs.boards.player.Computer;
import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.ChangeOfHeart;
import com.terserah.yogs.cards.spells.SpellCard;
import com.terserah.yogs.duel.gui.CardDescriptionPanel;
import com.terserah.yogs.duel.gui.DuelMain;
import com.terserah.yogs.duel.gui.GamePanel;
import com.terserah.yogs.duel.gui.GraveyardAndDeckPanel;
import com.terserah.yogs.duel.gui.GraveyardButton;
import com.terserah.yogs.duel.gui.HandPanel;
import com.terserah.yogs.duel.gui.InfoPanel;
import com.terserah.yogs.duel.gui.MonstersPanel;
import com.terserah.yogs.duel.gui.PhasePanel;
import com.terserah.yogs.duel.gui.SpellsPanel;
import com.terserah.yogs.menu.gui.CardButton;
import com.terserah.yogs.menu.gui.MonsterButton;
import com.terserah.yogs.menu.gui.SpellButton;

public class Controller  implements ActionListener,MouseListener{
	private GamePanel game;
	private Board board;
	boolean winnerDeclared = false;
	CardButton firstClick;
	CardButton secondClick;
	CardButton thirdClick;
	static String response;
	DuelMain main;
	public Controller(GamePanel game,Board board,DuelMain main) throws Exception {
		this.game = game;
		this.board = board;
		this.main = main;

		//addActionListenerToBoard();
		//addActionListenerToBoard();
		//addActionListenerToPhase();
		addActionListenersToButtons();
	}

	public void addActionListenerToPhase(PhasePanel phase) {
		phase.getEndPhase().addActionListener(this);
		phase.getEndTurn().addActionListener(this);
	}

	public void addActionListenersToButtons() throws Exception{

		MonstersPanel activeMonstersPanel = getActiveMonstersPanel();
		MonstersPanel opponentMonstersPanel = getOpponentMonstersPanel();
		SpellsPanel activeSpellsPanel = getActiveSpellsPanel();
		SpellsPanel opponentSpellsPanel = getOpponentSpellsPanel();
		HandPanel activeHandPanel = getActiveHandPanel();
		HandPanel opponentHandPanel = getOpponentHandPanel();
		PhasePanel phasePanel = getPhasePanel();
		GraveyardAndDeckPanel activeGraveAndDeck = getActiveGraveAndDeck();
		GraveyardAndDeckPanel opponentGraveAndDeck = getOpponentGraveAndDeck();

		if(activeGraveAndDeck.getGrave().getMouseListeners().length==1)
			activeGraveAndDeck.getGrave().addMouseListener(this);
		if(opponentGraveAndDeck.getGrave().getMouseListeners().length==1)
			opponentGraveAndDeck.getGrave().addMouseListener(this);

		for (CardButton b: activeMonstersPanel.getMonsterButtons()){
			if(b.getActionListeners().length==0)
				b.addActionListener(this);
			if(b.getMouseListeners().length==1)
				b.addMouseListener(this);
		}

		for (CardButton b: opponentMonstersPanel.getMonsterButtons()){
			if(b.getActionListeners().length==0)
				b.addActionListener(this);
			if(b.getMouseListeners().length==1)
				b.addMouseListener(this);
		}


		for (CardButton b: activeSpellsPanel.getSpellButtons()){
			if(b.getActionListeners().length==0)
				b.addActionListener(this);
			if(b.getMouseListeners().length==1)
				b.addMouseListener(this);
		}

		for (CardButton b: opponentSpellsPanel.getSpellButtons()){
			if(b.getActionListeners().length==0)
				b.addActionListener(this);
			if(b.getMouseListeners().length==1)
				b.addMouseListener(this);
		}

		for (CardButton b: activeHandPanel.getCardButtons()){
			if(b.getActionListeners().length==0){
				b.addActionListener(this);

			}
			if(b.getMouseListeners().length==1){

				b.addMouseListener(this);
			}
		}

		for (CardButton b: opponentHandPanel.getCardButtons()){
			if(b.getActionListeners().length==0)
				b.addActionListener(this);
			if(b.getMouseListeners().length==1)
				b.addMouseListener(this);
		}

		if(phasePanel.getEndPhase().getActionListeners().length==0){
			phasePanel.getEndPhase().addActionListener(this);
			phasePanel.getEndTurn().addActionListener(this);
		}

		//for (SpellButton s: field.getSpellButtons())
		//s.addActionListener(this);

		//graveyard.getGraveyardButton().addActionListener(this);



	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(((JButton)e.getSource()).getText().equals("End Phase")){
			Player p = Card.getBoard().getActivePlayer();
			try {
				Card.getBoard().getActivePlayer().endPhase();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			if(p != Card.getBoard().getActivePlayer()){
				if(DuelMain.gameMode==1){
					try {
						((Computer)Card.getBoard().getActivePlayer()).computerTurn();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e2.getMessage());
					}
				}
			}
			try{
				getActiveHandPanel().updatePanel(getActiveHandPanel().getPlayer());
				getOpponentHandPanel().updatePanel(getOpponentHandPanel().getPlayer());
			}
			catch(Exception e1){
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			getPhasePanel().updatePanel();
			try {
				addActionListenersToButtons();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			try {
				getActiveInfoPanel().updatePanel(getActiveInfoPanel().getPlayer());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			getOpponentInfoPanel().updatePanel(getOpponentInfoPanel().getPlayer());
			firstClick=null;
			secondClick=null;
			return;
		}
		else if(((JButton)e.getSource()).getText().equals("End Turn")){
			try {
				Card.getBoard().getActivePlayer().endTurn();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			if(DuelMain.gameMode==1){
				try {
					((Computer)Card.getBoard().getActivePlayer()).computerTurn();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e2.getMessage());
				}
			}
			try{
				getActiveHandPanel().updatePanel(getActiveHandPanel().getPlayer());
				getOpponentHandPanel().updatePanel(getOpponentHandPanel().getPlayer());
			}
			catch(Exception e1){
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			getPhasePanel().updatePanel();
			try {
				getActiveInfoPanel().updatePanel(getActiveInfoPanel().getPlayer());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			getOpponentInfoPanel().updatePanel(getOpponentInfoPanel().getPlayer());
			try {
				addActionListenersToButtons();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			try {
				getActiveGraveAndDeck().updatePanel();
				getOpponentGraveAndDeck().updatePanel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}

			firstClick=null;
			thirdClick=null;
			return;
		}
		if(firstClick==null){
			firstClick = (CardButton) e.getSource();
			if(e.getSource() instanceof MonsterButton){

				MonsterButton clickedButton = (MonsterButton) e.getSource();
				MonsterCard clickedMonster = clickedButton.getMonster();
				if(Card.getBoard().getActivePlayer().getField().getHand().contains(clickedMonster)){
					Object[] summonOrSet = {"Summon","Set"};
					response = (String)JOptionPane.showInputDialog(
							this.game,
							"Summon or Set?",
							"Action",
							JOptionPane.PLAIN_MESSAGE,
							null,
							summonOrSet,
							"Summon");
					if (response==null)
					{
						firstClick=null;
						return;
					}
					if(sacrifices(clickedMonster)==0){
						//System.out.println(clickedMonster.getName()+" "+sacrifices(clickedMonster));
						try {

							clickedMonsterInHand(clickedMonster,response);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1.getMessage());
							firstClick=null;
							secondClick=null;
							thirdClick=null;
						}
					}
					else if(sacrifices(clickedMonster)==1){
						if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()==0){
							JOptionPane.showMessageDialog(null, "You don't have enough monsters for sacrifice!");
							firstClick=null;
						}
						else
							JOptionPane.showMessageDialog(null, "Select 1 monster as sacrifice");
					}
					else{
						if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()<2){
							JOptionPane.showMessageDialog(null, "You don't have enough monsters for sacrifice!");
							firstClick=null;
						}
						else
							JOptionPane.showMessageDialog(null, "Select 2 monsters as sacrifice");
					}
				}
				else if (Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(clickedMonster)){
					if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE){
						if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()==0){
							int attackChoice = JOptionPane.showConfirmDialog(null, "Attack opponent?", "Attack", JOptionPane.OK_CANCEL_OPTION);
							if(attackChoice!=0){
								firstClick=null;
								return;
							}
							try{
								Card.getBoard().getActivePlayer().declareAttack(clickedMonster);
							}
							catch(Exception e1){
								JOptionPane.showMessageDialog(null,e1.getMessage());
							}
							try {
								getActiveInfoPanel().updatePanel(getActiveInfoPanel().getPlayer());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,e1.getMessage());
							}
							getOpponentInfoPanel().updatePanel(getOpponentInfoPanel().getPlayer());
							firstClick=null;
						}
						else{
							int attackChoice = JOptionPane.showConfirmDialog(null, "Select the monster to attack", "Attack", JOptionPane.OK_CANCEL_OPTION);
							if(attackChoice!=0)
							{
								firstClick=null;
								return;
							}
						}
					}
					else{
						try {
							int defChoice = JOptionPane.showConfirmDialog(null, "Switch monster's mode?", "Switch Mode", JOptionPane.OK_CANCEL_OPTION);

							if(defChoice==0)
								switchMonsterMode(clickedMonster);

							firstClick=null;
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,e1.getMessage());
						}
					}
				}
				else{
					firstClick=null;
					secondClick=null;
					thirdClick=null;
				}
			}
			else if(e.getSource() instanceof SpellButton){
				SpellCard clickedCard = ((SpellButton)e.getSource()).getSpell();
				//System.out.println(clickedCard.getName());
				if(Card.getBoard().getActivePlayer().getField().getHand().contains(clickedCard)){
					//clicked spell card is in hand 
					Object[] activateOrSet = {"Activate","Set"};
					response = (String)JOptionPane.showInputDialog(
							this.game,
							"Activate or Set?",
							"Action",
							JOptionPane.PLAIN_MESSAGE,
							null,
							activateOrSet,
							"Summon");
					if(response != null && response.length()>0){
						if(response.equals("Set")){
							try {
								Card.getBoard().getActivePlayer().setSpell(clickedCard);

							} catch (Exception e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,e1.getMessage());
							}
							firstClick=null;
						}
						else{
						}
						try{
							getActiveGraveAndDeck().updatePanel();
							getOpponentGraveAndDeck().updatePanel();
							getActiveHandPanel().updatePanel(getActiveHandPanel().getPlayer());
							getOpponentHandPanel().updatePanel(getOpponentHandPanel().getPlayer());
							getActiveMonstersPanel().updatePanel(getActiveMonstersPanel().getPlayer());
							getOpponentMonstersPanel().updatePanel(getOpponentMonstersPanel().getPlayer());
							getActiveSpellsPanel().updatePanel(getActiveSpellsPanel().getPlayer());
							getOpponentSpellsPanel().updatePanel(getOpponentSpellsPanel().getPlayer());
							getActiveInfoPanel().updatePanel(getActiveInfoPanel().getPlayer());
							getOpponentInfoPanel().updatePanel(getOpponentInfoPanel().getPlayer());
							addActionListenersToButtons();
						}
						catch(Exception e1){
							JOptionPane.showMessageDialog(null,e1.getMessage());
						}
					}
					else
						firstClick=null;
				}
				else if (Card.getBoard().getActivePlayer().getField().getSpellArea().contains(clickedCard)){
					//clicked card lies in spell area
					int activateChoice = JOptionPane.showConfirmDialog(null, "Activate Spell?", "Activate", JOptionPane.OK_CANCEL_OPTION);
					if(activateChoice!=0){
						firstClick=null;
						return;
					}

				}
				else
				{
					//clicked card is not in a valid place to activate
					firstClick=null;
				}
			}
			else{
				firstClick=null;
				secondClick=null;
				thirdClick=null;
			}
		}
		else if (secondClick==null){


			if(!(e.getSource() instanceof MonsterButton))
			{
				JOptionPane.showMessageDialog(null, "INVALID. This card is not a monster! Action declined.");
				firstClick=null;
				return;
			}

			secondClick = (CardButton) e.getSource();
			if(firstClick instanceof SpellButton){
				SpellCard clickedSpell = ((SpellButton)firstClick).getSpell(); 
				MonsterCard clickedMonster = ((MonsterButton)secondClick).getMonster();
				if(clickedSpell instanceof ChangeOfHeart && !Card.getBoard().getOpponentPlayer().getField().getMonstersArea().contains(clickedMonster)){
					JOptionPane.showMessageDialog(null,"You can only activate this card on an opponent monster!");
					firstClick=null;
					secondClick=null;
					return;
				}
				try {
					Card.getBoard().getActivePlayer().activateSpell(clickedSpell, clickedMonster);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
				try{
					getActiveGraveAndDeck().updatePanel();
					getOpponentGraveAndDeck().updatePanel();
					getActiveHandPanel().updatePanel(getActiveHandPanel().getPlayer());
					getOpponentHandPanel().updatePanel(getOpponentHandPanel().getPlayer());
					getActiveMonstersPanel().updatePanel(getActiveMonstersPanel().getPlayer());
					getOpponentMonstersPanel().updatePanel(getOpponentMonstersPanel().getPlayer());
					getActiveSpellsPanel().updatePanel(getActiveSpellsPanel().getPlayer());
					getOpponentSpellsPanel().updatePanel(getOpponentSpellsPanel().getPlayer());
					addActionListenersToButtons();
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
				firstClick=null;
				secondClick=null;
				thirdClick=null;
				return;

			}
			//check card exists in hand or monsterarea
			//JOptionPane.showMessageDialog(null, "Entered second click");
			if(Card.getBoard().getActivePlayer().getField().getHand().contains(((MonsterButton)firstClick).getMonster())){
				//clicked monster is the sacrifice of first click

				MonsterCard firstMonster =  ((MonsterButton)firstClick).getMonster();
				MonsterCard secondMonster = ((MonsterButton)e.getSource()).getMonster();
				if(!Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(secondMonster)){
					JOptionPane.showMessageDialog(null, "INVALID! You can only sacrifice with monsters from your field!");
					firstClick=null;
					secondClick=null;
					thirdClick=null;
					return;
				}
				if(sacrifices(firstMonster)==1){

					ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
					sacrifices.add(secondMonster);
					try {
						clickedMonsterNeedsOneSacrifice(firstMonster,sacrifices);
					}catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
			else if(Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(((MonsterButton)firstClick).getMonster())){
				MonsterCard firstMonster =  ((MonsterButton)firstClick).getMonster();
				MonsterCard secondMonster = ((MonsterButton)e.getSource()).getMonster();
				if(Card.getBoard().getActivePlayer().getField().getPhase()!=Phase.BATTLE || !Card.getBoard().getOpponentPlayer().getField().getMonstersArea().contains(secondMonster)){
					JOptionPane.showMessageDialog(null, "INVALID! You can only attack opponent monster!");
					firstClick=null;
					secondClick=null;
					thirdClick=null;
					return;
				}
				try{
					Card.getBoard().getActivePlayer().declareAttack(firstMonster, secondMonster);
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

				try{
					getActiveMonstersPanel().updatePanel(getActiveMonstersPanel().getPlayer());
					getOpponentMonstersPanel().updatePanel(getOpponentMonstersPanel().getPlayer());
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
				try {
					getActiveGraveAndDeck().updatePanel();
					getOpponentGraveAndDeck().updatePanel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}

				try {
					getActiveInfoPanel().updatePanel(getActiveInfoPanel().getPlayer());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
				getOpponentInfoPanel().updatePanel(getOpponentInfoPanel().getPlayer());
				firstClick=null;
				secondClick=null;
				thirdClick=null;
			}
			else{
				firstClick=null;
				secondClick=null;
				thirdClick=null;
			}
		}
		else{
			if(!(e.getSource() instanceof MonsterButton)){
				JOptionPane.showMessageDialog(null, "INVALID! This card is not a monster. Action Declined.");
				firstClick=null;
				secondClick=null;
				thirdClick=null;
				return;
			}
			thirdClick = (CardButton) e.getSource();
			//send sacrifices with second and third click
			if(Card.getBoard().getActivePlayer().getField().getHand().contains(((MonsterButton)firstClick).getMonster())){
				//clicked monster is the sacrifice of first click
				MonsterCard firstMonster =  ((MonsterButton)firstClick).getMonster();
				MonsterCard secondMonster = ((MonsterButton)secondClick).getMonster();

				MonsterCard  thirdMonster = ((MonsterButton)thirdClick).getMonster();
				if(sacrifices(firstMonster)==2 && secondMonster!=thirdMonster){

					//JOptionPane.showMessageDialog(null, "Entered third click");
					ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
					sacrifices.add(secondMonster);
					sacrifices.add(thirdMonster);
					try {
						clickedMonsterNeedsTwoSacrifice(firstMonster,sacrifices);
					}catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
						firstClick=null;
						secondClick=null;
						thirdClick=null;
					}


				}
				else{
					JOptionPane.showMessageDialog(null, "Invalid click! Action declined.");
					firstClick=null;
					secondClick=null;
					thirdClick=null;
				}

			}
			else{
				firstClick=null;
				secondClick=null;
				thirdClick=null;
			}
		}
	}

	private void switchMonsterMode(MonsterCard clickedMonster) throws Exception {
		if(Card.getBoard().getActivePlayer().switchMonsterMode(clickedMonster))
			JOptionPane.showMessageDialog(null, "Monster mode is switched to "+clickedMonster.getMode());
		else
			JOptionPane.showMessageDialog(null, "Cannot switch this monster's mode again!");
		getActiveMonstersPanel().updatePanel(getActiveMonstersPanel().getPlayer());
		getOpponentMonstersPanel().updatePanel(getOpponentMonstersPanel().getPlayer());
		addActionListenersToButtons();
		firstClick = null;
	}

	private void clickedMonsterNeedsTwoSacrifice(MonsterCard firstMonster,
			ArrayList<MonsterCard> sacrifices) throws Exception {
		if ((response != null) && (response.length() > 0)) {
			if(response.equals("Summon")){
				Card.getBoard().getActivePlayer().summonMonster(firstMonster, sacrifices);
			}
			else
			{
				Card.getBoard().getActivePlayer().setMonster(firstMonster, sacrifices);
				//System.out.println(Card.getBoard().getActivePlayer().getField().getMonstersArea().size());
			}
			getActiveGraveAndDeck().updatePanel();
			getOpponentGraveAndDeck().updatePanel();
			getActiveMonstersPanel().updatePanel(getActiveMonstersPanel().getPlayer());
			getOpponentMonstersPanel().updatePanel(getOpponentMonstersPanel().getPlayer());
			getActiveHandPanel().updatePanel(getActiveHandPanel().getPlayer());
			getOpponentHandPanel().updatePanel(getOpponentHandPanel().getPlayer());
			addActionListenersToButtons();
		}
		firstClick=null;
		secondClick=null;
		thirdClick=null;

	}

	private void clickedMonsterNeedsOneSacrifice(MonsterCard firstMonster, ArrayList<MonsterCard> sacrifices) throws Exception {
		if ((response != null) && (response.length() > 0)) {
			if(response.equals("Summon")){
				Card.getBoard().getActivePlayer().summonMonster(firstMonster, sacrifices);
			}
			else
			{
				Card.getBoard().getActivePlayer().setMonster(firstMonster, sacrifices);

			}
			getActiveGraveAndDeck().updatePanel();
			getOpponentGraveAndDeck().updatePanel();
			getActiveMonstersPanel().updatePanel(getActiveMonstersPanel().getPlayer());
			getOpponentMonstersPanel().updatePanel(getOpponentMonstersPanel().getPlayer());
			getActiveHandPanel().updatePanel(getActiveHandPanel().getPlayer());
			getOpponentHandPanel().updatePanel(getOpponentHandPanel().getPlayer());
			addActionListenersToButtons();
		}
		firstClick=null;
		secondClick=null;
		thirdClick=null;

	}

	private void clickedMonsterInHand(MonsterCard clickedMonster, String s) throws Exception {


		//If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
			if(s.equals("Summon")){
				if(clickedMonster.getLevel()<=4)
					Card.getBoard().getActivePlayer().summonMonster(clickedMonster);

			}
			else
			{
				if(clickedMonster.getLevel()<=4)
					Card.getBoard().getActivePlayer().setMonster(clickedMonster);

			}
			getActiveMonstersPanel().updatePanel(getActiveMonstersPanel().getPlayer());
			getOpponentMonstersPanel().updatePanel(getOpponentMonstersPanel().getPlayer());
			getActiveHandPanel().updatePanel(getActiveHandPanel().getPlayer());
			getOpponentHandPanel().updatePanel(getOpponentHandPanel().getPlayer());
			addActionListenersToButtons();
		}
		firstClick=null;
		secondClick=null;
		thirdClick=null;

	}




	public int sacrifices(MonsterCard monster) { 
		if(monster.getLevel()<=4) return 0 ;
		else 
			if(monster.getLevel() == 5 || monster.getLevel() == 6) return 1 ;
			else
				return 2 ;
	}
	public MonstersPanel getActiveMonstersPanel(){
		return game.getMainPanel().getActiveBoard().getFieldPanel().getMonstersPanel();
	}
	public MonstersPanel getOpponentMonstersPanel(){
		return game.getMainPanel().getOpponentBoard().getFieldPanel().getMonstersPanel();
	}
	public SpellsPanel getActiveSpellsPanel(){
		return game.getMainPanel().getActiveBoard().getFieldPanel().getSpellsPanel();
	}
	public SpellsPanel getOpponentSpellsPanel(){
		return game.getMainPanel().getOpponentBoard().getFieldPanel().getSpellsPanel();
	}
	public HandPanel getActiveHandPanel() throws Exception{
		if(Card.getBoard().getWinner()!=null){

			declareWinner();
			winnerDeclared = true;
		}
		return game.getMainPanel().getP1Hand();
	}
	public HandPanel getOpponentHandPanel(){
		return game.getMainPanel().getP2Hand();
	}
	public GraveyardAndDeckPanel getActiveGraveAndDeck(){
		return game.getMainPanel().getActiveBoard().getGraveAndDeck();
	}
	public GraveyardAndDeckPanel getOpponentGraveAndDeck(){
		return game.getMainPanel().getOpponentBoard().getGraveAndDeck();
	}
	public InfoPanel getActiveInfoPanel() throws Exception{
		if(Card.getBoard().getWinner()!=null){

			declareWinner();
			winnerDeclared = true;
		}
		return game.getSidePanel().getP1Info();
	}


	public InfoPanel getOpponentInfoPanel(){
		return game.getSidePanel().getP2Info();
	}
	public PhasePanel getPhasePanel(){
		return game.getMainPanel().getPhase();
	}
	public CardDescriptionPanel getCardDescriptionPanel(){
		return game.getSidePanel().getCardDescriptionPanel();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		Card c;
		if(e.getSource() instanceof GraveyardButton){
			c = ((GraveyardButton)e.getSource()).getLastCard();
		}
		else if(e.getSource() instanceof MonsterButton){
			c = ((MonsterButton)e.getSource()).getMonster();
		}
		else
			c = ((SpellButton)e.getSource()).getSpell();
		getCardDescriptionPanel().setCard(c);
		getCardDescriptionPanel().updatePanel();

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private void declareWinner() throws Exception {
		if(!winnerDeclared){
			new GameOver(Card.getBoard().getWinner());
			if(main.getClip()!=null)
				main.getClip().stop();
			main.dispose();
		}

	}

}
