package com.terserah.yogs.cards;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.terserah.yogs.boards.player.Deck;
import com.terserah.yogs.cards.spells.ChangeOfHeart;
import com.terserah.yogs.cards.spells.Fissure;
import com.terserah.yogs.cards.spells.FollowWind;
import com.terserah.yogs.cards.spells.StopDefense;
import com.terserah.yogs.cards.spells.MirrorForce;
import com.terserah.yogs.cards.spells.NegateAttack;
import com.terserah.yogs.cards.spells.TrapHole;
import com.terserah.yogs.shop.Shop;

public final class CardFactory {
	private ArrayList<Card> allCard = new ArrayList<Card>();
	private static final CardFactory INSTANCE = new CardFactory();

	private CardFactory() {
		JSONParser parser = new JSONParser();
		System.out.println();
		try {
			Object obj = parser.parse(new FileReader("save/kartu.json"));
	 
			JSONObject jsonObject = (JSONObject) obj;
			Long jumlah  = (Long) jsonObject.get("count");
	        JSONArray monsterlist = (JSONArray) jsonObject.get("monster"); 
                for (int j=0; j< monsterlist.size();j++) {
                    JSONObject monstermember = (JSONObject) monsterlist.get(j);
                    String cardname,desc,post,attrib,type,position;
                    Double prob;
                    Long ATK,DEF,level;
                    
                    cardname = (String) monstermember.get("name");
                    desc = (String) monstermember.get("description");
                    attrib = (String) monstermember.get("attr");
                    position = (String) monstermember.get("pos");
                    type = (String) monstermember.get("type");
                    prob = (Double) monstermember.get("probability");
                    ATK = (Long) monstermember.get("ATK");
                    DEF = (Long) monstermember.get("DEF");
                    level = (Long) monstermember.get("level");
                    MonsterCard monster = new MonsterCard(cardname,desc,Location.DECK,
                                          prob.floatValue(), ATK.intValue() , DEF.intValue(), 
                                          level.intValue(),
                                          attrib,type,Mode.ATTACK, true);
                    
                    this.getAllCard().add(monster);
                }
                
                JSONArray spelllist = (JSONArray) jsonObject.get("spell");
                
                for (int j=0; j<spelllist.size();j++) {
                    JSONObject spellmember = (JSONObject) spelllist.get(j);
                    String cardname,desc,type;
                    Double prob;
                    
                    cardname = (String) spellmember.get("name");
                    desc = (String) spellmember.get("description");
                    type = (String) spellmember.get("type");
                    prob = (Double) spellmember.get("probability");
                    //System.out.println(j);
                    //System.out.println(cardname);
                    switch(cardname){
                    	case "Change of Heart": this.getAllCard().add(new ChangeOfHeart(cardname, desc, Location.DECK, prob.floatValue()));break;
                    	case "Fissure": this.getAllCard().add(new Fissure(cardname, desc, Location.DECK, prob.floatValue()));break;
                    	case "Follow Wind": this.getAllCard().add(new FollowWind(cardname, desc, Location.DECK, prob.floatValue()));break;
                    	case "Stop Defense": this.getAllCard().add(new StopDefense(cardname, desc, Location.DECK, prob.floatValue())); break;
                    	default : break;
                    }
                }
                
                JSONArray traplist = (JSONArray) jsonObject.get("trap");
                
                for (int j=0; j<traplist.size();j++) {
                    JSONObject trapmember = (JSONObject) traplist.get(j);
                    String cardname,desc,type;
                    Double prob;
                    
                    cardname = (String) trapmember.get("name");
                    desc = (String) trapmember.get("description");
                    type = (String) trapmember.get("type");
                    prob = (Double) trapmember.get("probability");
                    switch(cardname){
	                	case "Mirror Force": this.getAllCard().add(new MirrorForce(cardname, desc, Location.DECK, prob.floatValue())); break;
	                	case "Negate Attack": this.getAllCard().add(new NegateAttack(cardname, desc, Location.DECK, prob.floatValue())); break;
	                	case "Trap Hole": this.getAllCard().add(new TrapHole(cardname, desc, Location.DECK, prob.floatValue())); break;
                    }
                }
         
		} catch (FileNotFoundException ex) {
		   ex.printStackTrace();
		} catch (IOException ex) {
		   ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static CardFactory getInstance() {
		return INSTANCE;
	}
	
	public ArrayList<Card> getAllCard() {
		return allCard;
	}
	
	public Card getbyName(String name) {
        boolean flag = false;
        
        int idx = 0;
        for (int i = 0; i < CardFactory.getInstance().getAllCard().size(); i++) {
            if (name.equals(CardFactory.getInstance().getAllCard().get(i).getName())) {
                flag = true;
                idx = i;
            }
        }
        if (flag) {
            return CardFactory.getInstance().getAllCard().get(idx);
        } else {
            return null;
        }
    }
    
    public ArrayList<Card> getbyJenis(String jenis) {
    	ArrayList<Card> getDeck = new ArrayList<Card>();
        int count = 0;
        while (count<CardFactory.getInstance().getAllCard().size()) {
            if (jenis.equals(CardFactory.getInstance().getAllCard().get(count).getJenis())) {
                getDeck.add(CardFactory.getInstance().getAllCard().get(count));
            }
            count++;
        }
            return getDeck;
    }

    
}
