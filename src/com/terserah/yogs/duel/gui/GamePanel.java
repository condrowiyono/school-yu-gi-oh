package com.terserah.yogs.duel.gui;

import java.awt.BorderLayout;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Player;


public class GamePanel extends JPanel{
	private SidePanel sidePanel;
	public SidePanel getSidePanel() {
		return sidePanel;
	}
	public MainPanel getMainPanel() {
		return mainPanel;
	}

	private MainPanel mainPanel;

	public MonstersPanel getActiveMonstersPanel(){
		return this.mainPanel.getActiveBoard().getFieldPanel().getMonstersPanel();
	}
	public MonstersPanel getOpponentMonstersPanel(){
		return this.mainPanel.getOpponentBoard().getFieldPanel().getMonstersPanel();
	}
	public SpellsPanel getActiveSpellsPanel(){
		return this.mainPanel.getActiveBoard().getFieldPanel().getSpellsPanel();
	}
	public SpellsPanel getOpponentSpellsPanel(){
		return this.mainPanel.getOpponentBoard().getFieldPanel().getSpellsPanel();
	}
	public HandPanel getActiveHandPanel(){
		return this.mainPanel.getP1Hand();
	}
	public HandPanel getOpponentHandPanel(){
		return this.mainPanel.getP2Hand();
	}
	public GraveyardAndDeckPanel getActiveGraveAndDeck(){
		return this.mainPanel.getActiveBoard().getGraveAndDeck();
	}
	public GraveyardAndDeckPanel getOpponentGraveAndDeck(){
		return this.mainPanel.getOpponentBoard().getGraveAndDeck();
	}
	public InfoPanel getActiveInfoPanel(){
		return this.sidePanel.getP1Info();
	}
	public InfoPanel getOpponentInfoPanel(){
		return this.sidePanel.getP1Info();
	}

	public GamePanel(Player p1, Player p2) throws IOException, FontFormatException {
		super();
		this.setLayout(new BorderLayout());
		//this.img = new ImageIcon("art/backgrounds/main.jpg").getImage();
		//this.setBackground(Color.BLACK);
		sidePanel = new SidePanel(p1,p2);
		mainPanel = new MainPanel(p1,p2);
		this.add(sidePanel, BorderLayout.WEST);
		this.add(mainPanel, BorderLayout.EAST);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(new File("art/backgrounds/main8.jpg")), 0, 0, null);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
}
