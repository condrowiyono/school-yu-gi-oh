package com.terserah.yogs.cards;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MonsterCard extends Card{
	
	public BufferedImage getImage() {
		return image;
	}


	private BufferedImage image;
	
	//attributes
	private int ATK, DEF, level;
	private String elemen, type;
    private Mode mode;
    //dueling need
    private boolean attackingOption;
    private boolean switchingOption;
	
    //konstruktor
	public MonsterCard(String name, String desc, Location loc, float prob, 
                    int ATK, int DEF, int level, String elemen, String type, 
                    Mode mode, boolean hidden) throws IOException {
        super(name, desc, loc, prob,hidden);
		this.ATK = ATK;
		this.DEF = DEF;
		this.level = level;
		this.elemen = elemen;
		this.type = type;
		this.mode = mode;
        this.attackingOption = true;
        this.switchingOption = true;
		this.image = ImageIO.read(new File("art/cards/monsters/"+this.getName()+".jpg"));
	}
	private boolean attacked;

	private boolean switched;
	
	//setter getter
	public void setATK(int atk) {
		this.ATK = atk;
	}

	public void setDEF(int def) {
		this.DEF = def;
	}

	public void setLevel(int lvl) {
		this.level = lvl;
	}

	public void setElemen(String elemen) {
		this.elemen = elemen;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public int getATK() {
		return this.ATK;
	}

	public int getDEF() {
		return this.DEF;
	}

	public int getLevel() {
		return this.level;
	}

	public String getElemen() {
		return this.elemen;
	}

	public String getType() {
		return this.type;
	}

	public Mode getMode() {
		return this.mode;
	}
    @Override
    public String getJenis() {
        return "Monster";
    }

	public void summon() {
		this.setLocation(Location.FIELD);
		this.setMode(Mode.ATTACK);
	}

	public void summon(MonsterCard m) {
		m.substituted();
		this.summon();
	}

	public void summon(MonsterCard m, MonsterCard n) {
		m.substituted();
		n.substituted();
		this.summon();
	}

	public void set() {
		this.setLocation(Location.FIELD);
		this.setMode(Mode.DEFENSE);
	}

	public void destroyed() {
		this.setLocation(Location.GRAVEYARD);
		//this.setPosition("Other");
	}

	public void substituted() {
		this.setLocation(Location.GRAVEYARD);
		//this.setPosition("Other");
	}

	public void changeToATK() {
		this.setMode(Mode.ATTACK);
	}

	public void changeToDEF() {
		this.setMode(Mode.DEFENSE);
	}

	public void flip() {
		this.setMode(Mode.ATTACK);
	}

	public void flipSummon() {
		this.setMode(Mode.ATTACK);
	}
        
    public boolean isAttackingOption() {
        return this.attackingOption;
    } 
    public boolean isSwitchingOption() {
        return this.switchingOption;
    } 
    public void setAttackingOption(boolean a) {
        this.attackingOption = a;
    }
    
    public void setSwitchingOption(boolean a) {
        this.switchingOption = a;
    }
	public boolean isAttacked() {
		return attacked;
	}
	public boolean isSwitched() {
		return switched;
	}
	public void setSwitched(boolean switched) {
		this.switched = switched;
	}
    //direct attack
	public void action() {
		int pLifePoints = Card.getBoard().getOpponentPlayer().getLifePoints();

		Card.getBoard().getOpponentPlayer().setLifePoints(pLifePoints-this.getATK());
		if (Card.getBoard().getOpponentPlayer().getLifePoints()<=0)
			Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
	}
	
	//attack on monster
	public void action(MonsterCard monster) {
		int pLifePoints = Card.getBoard().getOpponentPlayer().getLifePoints();
		int aLifePoints = Card.getBoard().getActivePlayer().getLifePoints();

		int pAttack = monster.getATK();
		int pDef = monster.getDEF();
		int aAttack = this.getATK();
		int aDef = this.getDEF();

		boolean lifePointsAffected = true;
		
		if(monster.getMode()==Mode.ATTACK){ 
			if(aAttack > pAttack) {
				pLifePoints -= aAttack-pAttack;
				Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
			}
			else{
				if(aAttack < pAttack) { 
					aLifePoints -= pAttack - aAttack;
					Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
				}else{
					Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
					Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
					lifePointsAffected=false;
				}

			}
		}else { 
			lifePointsAffected=false;
			if(aAttack>pDef){
				Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
				
			}else if (aAttack < pDef){
				aLifePoints -= pDef-aAttack;
				lifePointsAffected=true;
			}
		}

		if(lifePointsAffected){
			try{
				String soundName = "sounds/Life Point Cycle.wav";
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			}
			catch(Exception e1){

			}
		}

		if(pLifePoints <= 0 ) Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
		else if(aLifePoints<=0)  Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());

		Card.getBoard().getActivePlayer().setLifePoints(aLifePoints);
		Card.getBoard().getOpponentPlayer().setLifePoints(pLifePoints);

	}
        
}
