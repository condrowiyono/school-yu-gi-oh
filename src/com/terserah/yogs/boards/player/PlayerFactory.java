package com.terserah.yogs.boards.player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.terserah.yogs.cards.CardFactory;

public class PlayerFactory {
	private ArrayList<Player> allPlayer = new ArrayList<Player>();
	private static final PlayerFactory INSTANCE = new PlayerFactory ();
	private PlayerFactory() {
		JSONParser parser = new JSONParser();
        System.out.println();
        try {
        	Object obj = parser.parse(new FileReader("save/player.json")); 
        	JSONObject jsonObject = (JSONObject) obj;
        	Long jumlah  = (Long) jsonObject.get("count");

        	JSONArray player = (JSONArray) jsonObject.get("save");
        	for (int i = 0; i<player.size();i++) {
             String name, lastplayed;
             Long x,y,money ;
             
             JSONObject jsonAnggota = (JSONObject) player.get(i);
             JSONObject biodata = (JSONObject) jsonAnggota.get("biodata");
                name = (String) biodata.get("name");
                x = (Long) biodata.get("x");
                y = (Long) biodata.get("y");
                money = (Long) biodata.get("money");
                lastplayed = (String) biodata.get("lastplayed");
            JSONObject deck = (JSONObject) jsonAnggota.get("deck");
            //Array List<Card>
            Deck playerDeck = null;
			try {
				playerDeck = new Deck();
				JSONArray monsterlist = (JSONArray) deck.get("monster");
                
                for (int j=0; j<monsterlist.size();j++) {
                    String cardname = (String) monsterlist.get(j);
                    playerDeck.addToBottom(CardFactory.getInstance().getbyName(cardname));
                }
                
                JSONArray spelllist = (JSONArray) deck.get("spell");
                
                for (int j=0; j<spelllist.size();j++) {          
                    String cardname = (String) spelllist.get(j);
                    playerDeck.addToBottom(CardFactory.getInstance().getbyName(cardname));
                }
                JSONArray traplist = (JSONArray) deck.get("trap");
                
                for (int j=0; j<traplist.size();j++) {
                    String cardname = (String) traplist.get(j);
                    playerDeck.addToBottom(CardFactory.getInstance().getbyName(cardname));
                }
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

            JSONObject all = (JSONObject) jsonAnggota.get("allcard");
            //Array List<Card>
            Deck allCard = null;
			try {
				allCard = new Deck();
				JSONArray allmonsterlist = (JSONArray) all.get("monster");
                
                for (int j=0; j<allmonsterlist.size();j++) {
                    String cardname = (String) allmonsterlist.get(j);
                    allCard.addToBottom(CardFactory.getInstance().getbyName(cardname));
                }
                
                JSONArray allspelllist = (JSONArray) all.get("spell");
                
                for (int j=0; j<allspelllist.size();j++) {
                    String cardname = (String) allspelllist.get(j);
                    allCard.addToBottom(CardFactory.getInstance().getbyName(cardname));
                }
                JSONArray alltraplist = (JSONArray) all.get("trap");
                
                for (int j=0; j<alltraplist.size();j++) {
                    String cardname = (String) alltraplist.get(j);
                    allCard.addToBottom(CardFactory.getInstance().getbyName(cardname));
                }
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            try {
				this.allPlayer.add(new Player(name, x.intValue(),y.intValue(),money.intValue(),playerDeck, "ss", allCard) );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
	} catch (FileNotFoundException ex) {
	   ex.printStackTrace();
	} catch (IOException ex) {
	   ex.printStackTrace();
	} catch (ParseException ex) {
	   ex.printStackTrace();
	}       
	}
	
	public static PlayerFactory getInstance() {
		return INSTANCE;
	}
	
	public ArrayList<Player> getAllPlayer() {
		return allPlayer;
	}
}
