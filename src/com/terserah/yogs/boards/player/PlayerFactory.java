package com.terserah.yogs.boards.player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.terserah.yogs.cards.CardFactory;

public class PlayerFactory {
	public static ArrayList<Player> allPlayer = new ArrayList<Player>();
	private static PlayerFactory instance = null;
	
	private PlayerFactory() {
		importPlayer();
	}
	
	public static PlayerFactory getInstance() {
		if(instance == null) {
	         instance = new PlayerFactory();
	      }
	      return instance;
	}
	
	public ArrayList<Player> getAllPlayer() {
		return allPlayer;
	}
	
	public void importPlayer() {
		JSONParser parser = new JSONParser();
        System.out.println();
        try {
        	Object obj = parser.parse(new FileReader("save/player.json")); 
        	JSONObject jsonObject = (JSONObject) obj;
        	Long jumlah  = (Long) jsonObject.get("count");

        	JSONArray player = (JSONArray) jsonObject.get("save");
        	for (int i = 0; i<player.size();i++) {
             String name;
             Long money ;
             Double x,y;
             JSONObject jsonAnggota = (JSONObject) player.get(i);
             JSONObject biodata = (JSONObject) jsonAnggota.get("biodata");
                name = (String) biodata.get("name");
                x = (Double) biodata.get("x");
                y = (Double) biodata.get("y");
                money = (Long) biodata.get("money");
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
	public static void exportPlayer(ArrayList<Player> allPlayer) {
		JSONObject obj = new JSONObject();
        obj.put("count", allPlayer.size() );
        JSONArray duelistmember = new JSONArray();
        for (int o=0; o<allPlayer.size();o++) {
            JSONObject member = new JSONObject();
            //Tingkat 2
            JSONObject biodata = new JSONObject();
            
            biodata.put("name", allPlayer.get(o).getName());
            biodata.put("x",allPlayer.get(o).getPosisi().getX());
            biodata.put("y", allPlayer.get(o).getPosisi().getY());
            biodata.put("money", allPlayer.get(o).getMoney());
            JSONObject deck = new JSONObject();
            JSONObject allcard = new JSONObject();
                //Tingkat 4
                JSONArray monstertype = new JSONArray();
                JSONArray spelltype = new JSONArray();
                JSONArray traptype = new JSONArray();
                //Array monster dari deck pemain
                for (int i = 0; i< allPlayer.get(o).getDeck().getByJenis("Monster").getDeck().size();i++) {
                    monstertype.add(allPlayer.get(o).getDeck().getByJenis("Monster").getDeck().get(i).getName());
                }
                for (int i = 0; i< allPlayer.get(o).getDeck().getByJenis("Spell").getDeck().size();i++) {
                    spelltype.add(allPlayer.get(o).getDeck().getByJenis("Spell").getDeck().get(i).getName());
                }
                for (int i = 0; i< allPlayer.get(o).getDeck().getByJenis("Trap").getDeck().size();i++) {
                    traptype.add(allPlayer.get(o).getDeck().getByJenis("Trap").getDeck().get(i).getName());
                }

            deck.put("monster", monstertype);
            deck.put("trap", traptype);
            deck.put("spell", spelltype);
            //Tingkat 4
                JSONArray allmonstertype = new JSONArray();
                JSONArray allspelltype = new JSONArray();
                JSONArray alltraptype = new JSONArray();
                //Array monster dari deck pemain
                for (int i = 0; i< allPlayer.get(o).getAllCard().getByJenis("Monster").getDeck().size();i++) {
                    allmonstertype.add(allPlayer.get(o).getAllCard().getByJenis("Monster").getDeck().get(i).getName());
                }
                for (int i = 0; i< allPlayer.get(o).getAllCard().getByJenis("Spell").getDeck().size();i++) {
                    allspelltype.add(allPlayer.get(o).getAllCard().getByJenis("Spell").getDeck().get(i).getName());
                }
                for (int i = 0; i< allPlayer.get(o).getAllCard().getByJenis("Trap").getDeck().size();i++) {
                    alltraptype.add(allPlayer.get(o).getAllCard().getByJenis("Trap").getDeck().get(i).getName());
                }

            allcard.put("monster", allmonstertype);
            allcard.put("trap", alltraptype);
            allcard.put("spell", allspelltype);

            member.put("allcard", allcard);
            member.put("biodata", biodata);
            member.put("deck", deck);
            
            duelistmember.add(member);
        }
        obj.put("save", duelistmember);

        try {
            FileWriter file = new FileWriter("save/player.json");
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.print(obj);

        }
}
