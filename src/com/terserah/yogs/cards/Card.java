package com.terserah.yogs.cards;

import java.io.IOException;

import com.terserah.yogs.boards.Board;

public abstract class Card{

    //attributes
    String name;
	private String description, slug;
    private Location loc;
	private float probability = 0;
    private boolean isHidden;
	//for dueling option
    private static Board board;
    //konstruktor
    
    public Card(String name) {
    	this.name = name;
    	this.slug = name.replaceAll(" ", "").toLowerCase();
        this.isHidden = true;
    }
	public Card(String name, String description, Location pos, float probability) {
		this.name = name;
		this.description = description;
		this.probability = probability;
                this.slug = name.replaceAll(" ", "").toLowerCase();
                this.isHidden = true;
        }
	public Card(String name, String description, Location pos, float probability, boolean hidden) {
		this.name = name;
		this.description = description;
		this.probability = probability;
                this.slug = name.replaceAll(" ", "").toLowerCase();
                this.isHidden = hidden;
        }
    //setter getter
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}
        
    public boolean isHidden() {
		return isHidden;
	}
    public void setHidden(boolean b) {
		isHidden = b;
	}

	public void setLocation(Location location) {
		this.loc = location;
	}

	public void setProability(float prob) {
		this.probability = prob;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public Location getLoc() {
		return this.loc;
	}

	public float getProbability() {
		return this.probability;
	}
	public String getSlug() {
		return this.slug;
	}
    
    public abstract String getJenis() ;
        
    public static void setBoard(Board b) {
		board = b;
	}
        
    public static Board getBoard() {
        return Card.board; 
	}
	
    public abstract void action(MonsterCard monster) throws IOException; 
}