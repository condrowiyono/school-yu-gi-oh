package com.terserah.yogs.menu.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.imageio.ImageIO;

import javax.swing.*;

import com.terserah.yogs.boards.player.PlayerFactory;
import com.terserah.yogs.deck.DeckEditor;
import com.terserah.yogs.maps.Land;
import com.terserah.yogs.menu.listener.Controller;
import com.terserah.yogs.menu.listener.Interface;
import com.terserah.yogs.menu.listener.Main;
import com.terserah.yogs.menu.listener.Save;
import com.terserah.yogs.menu.listener.SoundFactory;

public class MainMenu extends JFrame{
	JLabel cardImage;
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
		SoundFactory.playBG();
		
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
				SoundFactory.playFX();
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
		
		
		decorButton(back);
		
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
		
		decorButton(info);
		decorButton(back);
		decorButton(land);
		decorButton(deck);
		decorButton(save);
		decorButton(mainmenu);
		decorButton(exit);
				
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
						//new DuelMain();
						JOptionPane.showMessageDialog(null, 
														"Nama : " + Main.p1.getName() + '\n' +
														"Uang : " + Main.p1.getMoney() + '\n'
								);
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
						Land.openMap();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}	
				}
			});
			
			save.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {
						new Save("menu");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//JOptionPane.showMessageDialog(null, e.getMessage());
					}	
				}
			});
			
			mainmenu.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog (null, "Save the game? or return to main menu","Warning",dialogButton);
						if(dialogResult == JOptionPane.YES_OPTION){
							new Save("interface");
						} else if(dialogResult == JOptionPane.NO_OPTION){
							new Interface();
						}
						current.dispose();
						//new Shop();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//JOptionPane.showMessageDialog(null, e.getMessage());
					}	
				}
			});		
			
			exit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog (null, "Save the game?","Warning",dialogButton);
						if(dialogResult == JOptionPane.YES_OPTION){
							new Save("exit");
						} else if(dialogResult == JOptionPane.NO_OPTION){
							System.exit(ABORT);
						}
						current.dispose();
						//new Shop();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//JOptionPane.showMessageDialog(null, e.getMessage());
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
					SoundFactory.playFX();
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
		
		private void decorButton(JButton jb) {
			jb.setPreferredSize(new Dimension(400,50));
			jb.setForeground(Color.GRAY);
			jb.setContentAreaFilled(false);
			jb.setFocusPainted(false);
			jb.setBorderPainted(false);
		}
	}
