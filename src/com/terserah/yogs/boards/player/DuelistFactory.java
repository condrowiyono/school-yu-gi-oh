package com.terserah.yogs.boards.player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.CardFactory;
import com.terserah.yogs.maps.DuelistLand;

public class DuelistFactory {
	private ArrayList<Player> allDuelist = new ArrayList<Player>();
	private static final DuelistFactory INSTANCE = new DuelistFactory();
	private DuelistFactory() {
        JSONParser parser = new JSONParser();
        System.out.println();
        try {
        	Object obj = parser.parse(new FileReader("save/duelist.json"));
	 
        	JSONObject jsonObject = (JSONObject) obj;
        	Long jumlah  = (Long) jsonObject.get("count");

        	JSONArray player = (JSONArray) jsonObject.get("save");
        	for (int i = 0; i<player.size();i++) {
        		String name,rank;
        		Long x,y,money ;
             
        		JSONObject jsonAnggota = (JSONObject) player.get(i);
        		JSONObject biodata = (JSONObject) jsonAnggota.get("biodata");
        		name = (String) biodata.get("name");
                x = (Long) biodata.get("x");
                y = (Long) biodata.get("y");
                rank = (String) biodata.get("rank");
                JSONObject deck = (JSONObject) jsonAnggota.get("deck");
                //Array List<Card>
                Deck playerDeck;
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
	                try {
	                	this.getAllDuelist().add(new Player(name,x.intValue(), y.intValue(), playerDeck, rank));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         }
		} catch (FileNotFoundException ex) {
		   ex.printStackTrace();
		} catch (IOException ex) {
		   ex.printStackTrace();
		} catch (org.json.simple.parser.ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}  
	}
	public static DuelistFactory getInstance() {
		return INSTANCE;
	}
	
	public ArrayList<Player> getAllDuelist() {
		return allDuelist;
	}
}
