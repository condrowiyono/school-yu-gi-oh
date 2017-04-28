package com.terserah.yogs.maps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Computer;
import com.terserah.yogs.boards.player.DuelistFactory;
import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.duel.gui.DuelMain;
import com.terserah.yogs.menu.listener.Main;
import com.terserah.yogs.shop.Shop;

public class GamePanel extends JPanel implements Runnable,KeyListener{
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS = 30;
	private int targetTime = 1000/FPS;
	private TileMap tileMap;
	private LandPlayer player;
	public GamePanel() {
		super();
		setPreferredSize(new Dimension( WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	public LandPlayer getPlayer() {
		return this.player;
	}
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		addKeyListener(this);
	}
	public void run() {
		init();
		long startTime;
		long urdTime;
		long waitTime;
		while (running) {
			startTime = System.nanoTime();
			update();
			render();
			draw();
			urdTime = (System.nanoTime() - startTime) / 10000000;
			waitTime = targetTime - urdTime;
			try {
				Thread.sleep(waitTime);
			}
			catch (Exception e){}		
		}
		
	}
	public void init() {
		running = true;
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		tileMap = new TileMap("art/map/testmap.txt",31);
		player = new LandPlayer(tileMap);
		
		player.setX(Main.p1.getPosisi().getX());
		player.setY(Main.p1.getPosisi().getY());
		
		tileMap.loadTiles("art/map/map5.gif");
	}
	private void update() {
		tileMap.update();
		player.update();
	}
	private void render() {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		tileMap.draw(g);
		player.draw(g);
		Main.p1.setPosisi(player.getX(), player.getY());
		
		//shop and duelist
		if (player.getX() > DuelistFactory.getInstance().getAllDuelist().get(0).getPosisi().getX()
				&& player.getX() < DuelistFactory.getInstance().getAllDuelist().get(0).getPosisi().getX() + 32 
				&& player.getY() > DuelistFactory.getInstance().getAllDuelist().get(0).getPosisi().getY()
				&& player.getY() < DuelistFactory.getInstance().getAllDuelist().get(0).getPosisi().getY() + 32)  {
			
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Hey dude, wanna duel with " + 
					DuelistFactory.getInstance().getAllDuelist().get(0).getName(),"Warning", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				try {
					Player duelist = DuelistFactory.getInstance().getAllDuelist().get(0);
					Computer cp = new Computer(duelist.getName(), 
											   duelist.getDeck(),
											   duelist.getRank()
											   );
					new DuelMain(cp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			noMove();
			player.setY(player.getY()+32);
		}
		//shop and duelist
		if (player.getX() > DuelistFactory.getInstance().getAllDuelist().get(1).getPosisi().getX()
				&& player.getX() < DuelistFactory.getInstance().getAllDuelist().get(1).getPosisi().getX() + 32 
				&& player.getY() > DuelistFactory.getInstance().getAllDuelist().get(1).getPosisi().getY()
				&& player.getY() < DuelistFactory.getInstance().getAllDuelist().get(1).getPosisi().getY() + 32)  {		
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Hey dude, wanna duel with " + 
					DuelistFactory.getInstance().getAllDuelist().get(1).getName() ,"Warning", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				try {
					Player duelist = DuelistFactory.getInstance().getAllDuelist().get(1);
					Computer cp = new Computer(duelist.getName(), 
							   duelist.getDeck(),
							   duelist.getRank()
							   );
					new DuelMain(cp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			noMove();
			player.setY(player.getY()+32);
		}
		
		//shop
		if (player.getX() > 80
				&& player.getX() < 80 + 32 
				&& player.getY() > 15
				&& player.getY() < 30 + 32)  {		
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Hey dude, wanna enter the shop","Confirm", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				try {
					new Shop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			noMove();
			player.setY(player.getY()+32);
		}
	}
	private void draw() {
		Graphics g2 = getGraphics();
		g2.drawImage(image,0,0,null);
		g2.dispose();
	}

	public void keyPressed(KeyEvent key) {
		int code = key.getKeyCode();
		if (code == KeyEvent.VK_LEFT) {
			player.setLeft(true);
			if (player.getX() < 0)
				player.setLeft(false);
		}
		if(code == KeyEvent.VK_RIGHT) {
			player.setRight(true);
			if (player.getX() > WIDTH-50)
				player.setRight(false);
		}
		if (code == KeyEvent.VK_UP) {
			player.setDown(true);
			if (player.getY() < 0)
				player.setDown(false);
		}
		if(code == KeyEvent.VK_DOWN) {
			player.setUp(true);
			if (player.getY() > HEIGHT-50)
				player.setUp(false);
		}
	}
	public void keyReleased(KeyEvent key) {
		int code = key.getKeyCode();
		if (code == KeyEvent.VK_LEFT) {
			player.setLeft(false);
		}
		if(code == KeyEvent.VK_RIGHT) {
			player.setRight(false);
		}
		if (code == KeyEvent.VK_UP) {
			player.setDown(false);
		}
		if(code == KeyEvent.VK_DOWN) {
			player.setUp(false);
		}
	}

	public void keyTyped(KeyEvent e) {

	}
	
	private void noMove() {
		player.setLeft(false);
		player.setRight(false);
		player.setUp(false);
		player.setDown(false);
	}

}
