package com.terserah.yogs.menu.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.terserah.yogs.boards.Board;
import com.terserah.yogs.boards.player.Computer;
import com.terserah.yogs.boards.player.Deck;
import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.CardFactory;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.SpellCard;
import com.terserah.yogs.deck.DeckEditor;
import com.terserah.yogs.duel.gui.DuelMain;
import com.terserah.yogs.duel.gui.GamePanel;
import com.terserah.yogs.menu.listener.CardList;
import com.terserah.yogs.menu.listener.Controller;
import com.terserah.yogs.menu.listener.Interface;
import com.terserah.yogs.menu.listener.Main;
import com.terserah.yogs.shop.Shop;

public class MainMenu extends JFrame{
	JLabel cardImage;
	private JPanel cardsContainer ;
	public static Controller controller;
	public MainMenu getCurrent() {
		return this;
	}
	
	public MainMenu() throws Exception {
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File("art/backgrounds/main5Updated.jpg"))));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(1344,768));
		background.setLayout(new BorderLayout());
		JPanel leftSide = new JPanel(new BorderLayout());
		leftSide.setPreferredSize(new Dimension(344,768));
		leftSide.setOpaque(false);
		cardImage = new JLabel(new ImageIcon(ImageIO.read(new File("art/cards/back.png")).getScaledInstance(272, 400, Image.SCALE_SMOOTH)));
		JButton back = new JButton("Back");
		MainMenu current = this;
		back.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Interface();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current.dispose();
			}
		});
		
		back.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				String soundName = "sounds/Draw Card (2).wav";
				try{
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
				}
				catch(Exception e1){

				}
				((JComponent) e.getSource()).setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				((JComponent) e.getSource()).setForeground(Color.GRAY);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		
		back.setPreferredSize(new Dimension(400,100));
		back.setForeground(Color.GRAY);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		
		leftSide.add(cardImage, BorderLayout.NORTH);
		leftSide.add(back,BorderLayout.SOUTH);
		
		JPanel rightSide = new JPanel(new FlowLayout(FlowLayout.CENTER));
		rightSide.setPreferredSize(new Dimension(1000,768));
		rightSide.setOpaque(false);
		
		//for right side
		JPanel menu = new JPanel(new FlowLayout());
		menu.setPreferredSize(new Dimension(200,500));
		JPanel buttonsContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonsContainer.setOpaque(false);
		
		JButton info = new JButton("Informasi");
		JButton land = new JButton("DuelistLand");
		JButton deck = new JButton("Deck Editor");
		JButton save = new JButton("Save");
		JButton mainmenu = new JButton("Main Menu");
		JButton exit = new JButton("Exit");
		
		menu.add(info);
		menu.add(land);
		menu.add(deck);
		menu.add(save);
		menu.add(mainmenu);
		menu.add(exit);
		menu.setOpaque(false);
		
		exit.setPreferredSize(new Dimension(400,50));
		exit.setForeground(Color.GRAY);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);
		exit.setBorderPainted(false);
		
		mainmenu.setPreferredSize(new Dimension(400,50));
		mainmenu.setForeground(Color.GRAY);
		mainmenu.setContentAreaFilled(false);
		mainmenu.setFocusPainted(false);
		mainmenu.setBorderPainted(false);
		
		save.setPreferredSize(new Dimension(400,50));
		save.setForeground(Color.GRAY);
		save.setContentAreaFilled(false);
		save.setFocusPainted(false);
		save.setBorderPainted(false);
		
		deck.setPreferredSize(new Dimension(400,50));
		deck.setForeground(Color.GRAY);
		deck.setContentAreaFilled(false);
		deck.setFocusPainted(false);
		deck.setBorderPainted(false);
		
		land.setPreferredSize(new Dimension(400,50));
		land.setForeground(Color.GRAY);
		land.setContentAreaFilled(false);
		land.setFocusPainted(false);
		land.setBorderPainted(false);
		
		info.setPreferredSize(new Dimension(400,50));
		info.setForeground(Color.GRAY);
		info.setContentAreaFilled(false);
		info.setFocusPainted(false);
		info.setBorderPainted(false);
		
		buttonsContainer.add(menu);
		addTheActions(info,land,deck,save,mainmenu,exit,this);
		
		rightSide.add(menu);
		background.add(leftSide,BorderLayout.WEST);
		background.add(rightSide,BorderLayout.EAST);
		this.add(background);
		this.setVisible(true);
	}
		
	public void addTheActions(JButton info, JButton land, JButton deck, JButton save, JButton mainmenu, JButton exit, MainMenu current){
			info.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {
						new DuelMain();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			deck.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {
						new DeckEditor();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage());
					}	
				}
			});
			
			land.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {
						new Shop();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage());
					}	
				}
			});
			
			addMouseListeners(info);
			addMouseListeners(land);
			addMouseListeners(deck);
			addMouseListeners(save);
			addMouseListeners(mainmenu);
			addMouseListeners(exit);
		}
		
		private void addMouseListeners(JButton b) {
			b.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					String soundName = "sounds/Draw Card (2).wav";
					try{
						AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
						Clip clip = AudioSystem.getClip();
						clip.open(audioInputStream);
						clip.start();
					}
					catch(Exception e1){

					}
					b.setForeground(Color.WHITE);
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					b.setForeground(Color.GRAY);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub	
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub	
				}
			});
		}
	}
