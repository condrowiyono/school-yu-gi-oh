package com.terserah.yogs.boards.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.CardFactory;
import com.terserah.yogs.cards.Location;
import com.terserah.yogs.cards.Mode;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.SpellCard;
import com.terserah.yogs.exception.EmptyFieldException;
import com.terserah.yogs.exception.MissingFieldException;
import com.terserah.yogs.exception.UnknownCardTypeException;
import com.terserah.yogs.shop.Shop;
import com.terserah.yogs.*;

public class Deck {
    public final static int MAX_DECK_SIZE = 30;
    public final static int MIN_DECK_SIZE = 20;
    
	private static ArrayList<Card> monsters = new ArrayList<Card>();
	private static ArrayList<Card> spells = new ArrayList<Card>();
	private ArrayList<Card> deck;
	int count = 0;
	
	public static ArrayList<Card> getMonsters() {
		return monsters;
	}

	public static void setMonsters(ArrayList<Card> monsters) {
		Deck.monsters = monsters;
	}

	public static ArrayList<Card> getSpells() {
		return spells;
	}

	public static void setSpells(ArrayList<Card> spells) {
		Deck.spells = spells;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	public Deck(){
		deck = new ArrayList<Card>();
		this.shuffleDeck();

	}
	
    public Deck(ArrayList<Card> initialDeck){
        this.deck = new ArrayList<Card>();
        for(int i = 0; i < initialDeck.size(); i++){
            deck.add(initialDeck.get(i));
        }
        shuffleDeck();
    }
    
	Scanner sc = new Scanner(System.in);
	
	public ArrayList<Card> loadCardsFromFile(String path) throws Exception{
		ArrayList<Card> cards = CardFactory.getInstance().getAllCard();
		return cards;
	}

	public void shuffleDeck(){
		Collections.shuffle(this.deck);
	}

	public ArrayList<Card> drawNCards(int n){
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i=0; i<n; i++){
			cards.add(this.deck.remove(0));
		}
		return cards;
	}

	public Card drawOneCard(){
		return this.deck.remove(0);
	}
    public void addToBottom(Card c){
        deck.add(c);
    }
    
    public static Deck getRandCard(int n) {
        ArrayList<Card> arrCard = new ArrayList<Card>();
        
        while (arrCard.size() < n) {
            Random rand = new Random(); 
            Card kartu = CardFactory.getInstance().getAllCard().get(rand.nextInt(CardFactory.getInstance().getAllCard().size()));
            if (!arrCard.contains(kartu)) {
                arrCard.add(kartu);
            }
        }
        return new Deck(arrCard);
    }
    public static Deck getRandCard(Deck deck, int n) {
        ArrayList<Card> arrCard = new ArrayList<Card>();
        
        while (arrCard.size() < n) {
            Random rand = new Random(); 
            Card kartu = deck.getDeck().get(rand.nextInt(deck.getDeck().size()));
            if (!arrCard.contains(kartu)) {
                arrCard.add(kartu);
            }
        }
        return new Deck(arrCard);
    }
    public Card getbyName(String name) {
        boolean flag = false;
        int idx = 0;
        int i = 0 ;
        while ((i < deck.size()) || (!flag)){
	        if (name.equals(deck.get(i).getName())) {
	            flag = true;
	            idx = i;
	        } else 
	        	i++;
        }
        if (flag) {
            return deck.get(idx);
        } else {
            return null;
        }
    }
    public Deck getByJenis(String jenis) {
        Deck exportdeck = new Deck();
        for (int i = 0; i < deck.size(); i++) {
	        if (jenis.equals(deck.get(i).getJenis())) {
	            exportdeck.addToBottom(deck.get(i));
	        } 
        }
        return exportdeck;
    }
    public int countbyName(String name) {
        int count = 0;
        for (int i = 0; i < deck.size(); i++) {
	        if (name.equals(deck.get(i).getName())) {
	            count++;
	        } 
        }
        return count;
    }

}
