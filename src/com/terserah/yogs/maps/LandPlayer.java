package com.terserah.yogs.maps;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
public class LandPlayer {
	private double x;
	private double y;
	private double dx;
	private double dy;
	private int width;
	private int height;
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private double moveSpeed;
	private TileMap tileMap;
	private boolean topLeft;
	private boolean topRight;
	private boolean bottomLeft;
	private boolean bottomRight;
	public LandPlayer(TileMap tm){
		tileMap = tm;
		width = 32;
		height = 32;
		moveSpeed = 4;
	}
	public int getX() {return (int)x;};
	public int getY() {return (int)y;};
	public void setX(double x) { this.x =x;};
	public void setY(double y) {this.y=y;};
	public void setLeft(boolean b) { left = b;}
	public void setRight(boolean b) { right = b;}
	public void setUp(boolean b) { up = b;}
	public void setDown(boolean b) { down =  b;}
	
	public void update() {
		if (left) {
			x -= moveSpeed;
		}
		else if (right) {
			x += moveSpeed;
		}
		else if (up) {
			y += moveSpeed;
		}
		else if (down) {
			y -= moveSpeed;
		}
		
	}
	public void draw(Graphics2D g) {
		Image aku = new ImageIcon("art/map/aku.png").getImage();
		int tx = tileMap.getx();
		int ty = tileMap.gety();
		g.drawImage(aku,(int)x,(int)y,null);
	}
}
