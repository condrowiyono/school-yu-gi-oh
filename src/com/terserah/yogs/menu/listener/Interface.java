package com.terserah.yogs.menu.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Interface extends JFrame{
	JLabel background;
	
	public Interface() throws IOException, FontFormatException {
		
		background = new JLabel(new ImageIcon(ImageIO.read(new File("art/backgrounds/main5.jpg"))));		
		background.setLayout(new BorderLayout());
		JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
		header.setPreferredSize(new Dimension(300,150));
		
		JLabel yugioh = new JLabel("Yu-Gi-Oh!");
		yugioh.setForeground(new Color(255, 123, 13));
		header.add(yugioh);
		header.setOpaque(false);
		
		JPanel menu = new JPanel(new FlowLayout());
		menu.setPreferredSize(new Dimension(200,500));
		
		JButton startGame = new JButton("Start Game");
		JButton exit = new JButton("Exit");
		JButton loadGame = new JButton("Load Game");
		
		JPanel buttonsContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonsContainer.setOpaque(false);
		menu.add(startGame);
		menu.add(loadGame);
		menu.add(exit);
		decorButton(loadGame);
		decorButton(startGame);
		decorButton(exit);
		menu.setOpaque(false);
		
		buttonsContainer.add(menu);
		background.add(header, BorderLayout.NORTH);
		background.add(buttonsContainer,BorderLayout.CENTER);
		this.add(background);
		addTheActions(startGame,exit,loadGame,this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		this.validate();
		SoundFactory.playBG();
	}
	
	
	public void addTheActions(JButton b1, JButton b2,JButton loadGame,Interface current){
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Main();
					current.dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				}	
			}
		});
		
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Mau keluar?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
				  System.exit(ABORT);
				}
			}
		});
		loadGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Load();
				} catch (Exception e) {
					e.printStackTrace();
				}
				current.dispose();
			}
			
		});
		addMouseListeners(b1);
		addMouseListeners(b2);
		addMouseListeners(loadGame);
	}
	
	private void addMouseListeners(JButton b) {
		b.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {}

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
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		});
	}
	
	private void decorButton(JButton jb) {
		jb.setPreferredSize(new Dimension(400,100));
		jb.setForeground(Color.GRAY);
		jb.setContentAreaFilled(false);
		jb.setFocusPainted(false);
		jb.setBorderPainted(false);
	}
	
	public static void main(String[] args) throws IOException, FontFormatException{
		new Interface();
	}
}