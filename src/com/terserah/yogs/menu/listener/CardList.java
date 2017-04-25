package com.terserah.yogs.menu.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
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
import javax.swing.JScrollPane;

import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.CardFactory;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.SpellCard;
import com.terserah.yogs.menu.gui.MonsterButton;
import com.terserah.yogs.menu.gui.SpellButton;
import com.terserah.yogs.menu.gui.WrapLayout;
import com.terserah.yogs.shop.Shop;

public class CardList extends JFrame{
	JLabel cardImage;
	private JPanel cardsContainer ;
	public CardList() throws Exception {
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
		CardList current = this;
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
		
		cardsContainer = new JPanel(new WrapLayout());
		cardsContainer.setOpaque(false);
		cardsContainer.setAutoscrolls(true);
		
		MouseListener ml = new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if(e.getSource() instanceof MonsterButton){
					MonsterCard monster = ((MonsterButton)e.getSource()).getMonster();
					updateImage(monster);
				}
				else if(e.getSource() instanceof SpellButton){
					SpellCard spell = ((SpellButton)e.getSource()).getSpell();
					updateImage(spell);
				} 
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub	
			}
		};
		
		ArrayList<MonsterButton> monsters = new ArrayList<MonsterButton>();
		ArrayList<SpellButton> spells = new ArrayList<SpellButton>();
		
		for (int i=0;i<CardFactory.getInstance().getAllCard().size();i++){
			if ((CardFactory.getInstance().getAllCard().get(i).getJenis()).equals("Monster")) {
				MonsterButton monsterButton = new MonsterButton((MonsterCard) CardFactory.getInstance().getAllCard().get(i));
				monsterButton.setPreferredSize(new Dimension(136,200));
				monsterButton.setIcon(new ImageIcon(monsterButton.getMonster().getImage().getScaledInstance(136, 200, Image.SCALE_SMOOTH)));
				monsterButton.addMouseListener(ml);
				cardsContainer.add(monsterButton);
				monsters.add(monsterButton);
				monsterButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						try {
							JOptionPane.showMessageDialog(null, monsterButton.getMonster().getDescription());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e.getMessage());
						}	
					}
				});
			}
			if ((CardFactory.getInstance().getAllCard().get(i).getJenis()).equals("Spell")) {
				SpellButton spellButton = new SpellButton((SpellCard) CardFactory.getInstance().getAllCard().get(i));
				spellButton.setPreferredSize(new Dimension(136,200));
				spellButton.setIcon(new ImageIcon(spellButton.getSpell().getImage().getScaledInstance(136, 200, Image.SCALE_SMOOTH)));
				spellButton.addMouseListener(ml);
				cardsContainer.add(spellButton);
				spells.add(spellButton);
			}
		}
		
		JScrollPane scrollList = new JScrollPane(cardsContainer);
		scrollList.setPreferredSize(new Dimension(700,760));
		scrollList.setOpaque(false);
		scrollList.getViewport().setBackground(Color.BLACK);
		
		rightSide.add(scrollList);
		background.add(leftSide,BorderLayout.WEST);
		background.add(rightSide,BorderLayout.EAST);
		this.add(background);
		this.setVisible(true);
	}
	
	public void updateImage(Card c){
		Image image;
		if (c instanceof SpellCard)
			image = ((SpellCard)c).getImage();
		else 
			image = ((MonsterCard)c).getImage();
		cardImage.setIcon(new ImageIcon(image.getScaledInstance(272, 400, Image.SCALE_SMOOTH)));
		
	}
}