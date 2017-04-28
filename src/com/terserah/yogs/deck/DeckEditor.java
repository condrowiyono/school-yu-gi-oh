package com.terserah.yogs.deck;

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
import javax.swing.JScrollPane;

import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.CardFactory;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.SpellCard;
import com.terserah.yogs.menu.gui.MainMenu;
import com.terserah.yogs.menu.gui.MonsterButton;
import com.terserah.yogs.menu.gui.SpellButton;
import com.terserah.yogs.menu.gui.WrapLayout;
import com.terserah.yogs.menu.listener.CardList;
import com.terserah.yogs.menu.listener.Interface;
import com.terserah.yogs.menu.listener.Main;
import com.terserah.yogs.menu.listener.SoundFactory;

public class DeckEditor extends JFrame {
	JLabel cardImage;
	private JPanel cardsContainer ;
	private JPanel cardsContainer2 ;
	private ArrayList<MonsterButton> monsters = new ArrayList<MonsterButton>();
	private ArrayList<SpellButton> spells = new ArrayList<SpellButton>();
	private ArrayList<MonsterButton> monsters2 = new ArrayList<MonsterButton>();
	private ArrayList<SpellButton> spells2 = new ArrayList<SpellButton>();
	private DeckEditor current = this;
	
	public DeckEditor() throws Exception {
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
		JLabel count = new JLabel("Count");
		backAction(back);
		
		
		count.setPreferredSize(new Dimension(400,100));
		count.setForeground(Color.GRAY);
		count.setHorizontalAlignment(count.CENTER);
		
		leftSide.add(cardImage, BorderLayout.NORTH);
		leftSide.add(count, BorderLayout.CENTER);
		leftSide.add(back,BorderLayout.SOUTH);
		
		JPanel rightSide = new JPanel(new FlowLayout(FlowLayout.LEADING));
		rightSide.setPreferredSize(new Dimension(1000,768));
		rightSide.setOpaque(false);
		
		cardsContainer = new JPanel(new WrapLayout());
		cardsContainer.setOpaque(false);
		cardsContainer.setAutoscrolls(true);

		//all deck
		MouseListener ml = new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new DeckEditor();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				SoundFactory.playFX();
				int countByName = 0;
				if(e.getSource() instanceof MonsterButton){
					MonsterCard monster = ((MonsterButton)e.getSource()).getMonster();
					updateImage(monster);
					countByName = Main.p1.getDeck().countbyName(((MonsterButton)e.getSource()).getMonster().getName());
				}
				else if(e.getSource() instanceof SpellButton){
					SpellCard spell = ((SpellButton)e.getSource()).getSpell();
					updateImage(spell);
					countByName = Main.p1.getDeck().countbyName(((SpellButton)e.getSource()).getSpell().getName());
				} 
				count.setText(Integer.toString(countByName));
			}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		};

		//untuk playerdeck
		for (int i=0;i<Main.p1.getDeck().getDeck().size();i++){
			if ((Main.p1.getDeck().getDeck().get(i).getJenis()).equals("Monster")) {
				MonsterButton monsterButton = new MonsterButton((MonsterCard) Main.p1.getDeck().getDeck().get(i));
				monsterButton.setPreferredSize(new Dimension(136,200));
				monsterButton.setIcon(new ImageIcon(monsterButton.getMonster().getImage().getScaledInstance(136, 200, Image.SCALE_SMOOTH)));
				monsterButton.addMouseListener(ml);
				cardsContainer.add(monsterButton);
				monsters.add(monsterButton);
				
				monsterButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						try {
							Main.p1.getDeck().getDeck().remove(monsterButton.getMonster());
							Main.p1.getAllCard().getDeck().add(monsterButton.getMonster());
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}	
					}
				});
				
			} else
			if ((Main.p1.getDeck().getDeck().get(i).getJenis()).equals("Spell")) {
				SpellButton spellButton = new SpellButton((SpellCard) Main.p1.getDeck().getDeck().get(i));
				spellButton.setPreferredSize(new Dimension(136,200));
				spellButton.setIcon(new ImageIcon(spellButton.getSpell().getImage().getScaledInstance(136, 200, Image.SCALE_SMOOTH)));
				spellButton.addMouseListener(ml);
				cardsContainer.add(spellButton);
				spells.add(spellButton);
				spellButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						try {
							Main.p1.getDeck().getDeck().remove(spellButton.getSpell());
							Main.p1.getAllCard().getDeck().add(spellButton.getSpell());
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}	
					}
				});
			} 
		}

		
		JScrollPane scrollList1 = new JScrollPane(cardsContainer);
		scrollList1.setPreferredSize(new Dimension(350,760));
		scrollList1.setOpaque(false);
		scrollList1.getViewport().setBackground(Color.BLACK);
		
		//all deck
		MouseListener ml2 = new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new DeckEditor();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				SoundFactory.playFX();
				int countByName = 0;
				if(e.getSource() instanceof MonsterButton){
					MonsterCard monster = ((MonsterButton)e.getSource()).getMonster();
					updateImage(monster);
					countByName = Main.p1.getAllCard().countbyName(((MonsterButton)e.getSource()).getMonster().getName());
				}
				else if(e.getSource() instanceof SpellButton){
					SpellCard spell = ((SpellButton)e.getSource()).getSpell();
					updateImage(spell);
					countByName = Main.p1.getAllCard().countbyName(((SpellButton)e.getSource()).getSpell().getName());
				} 
				count.setText(Integer.toString(countByName));
			}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		};

		cardsContainer2 = new JPanel(new WrapLayout());
		cardsContainer2.setOpaque(false);
		cardsContainer2.setAutoscrolls(true);
		
		//untuk allCard
		for (int i=0;i<Main.p1.getAllCard().getDeck().size();i++){
			if ((Main.p1.getAllCard().getDeck().get(i).getJenis()).equals("Monster")) {
					MonsterButton monsterButton = new MonsterButton((MonsterCard) Main.p1.getAllCard().getDeck().get(i));
					monsterButton.setPreferredSize(new Dimension(136,200));
					monsterButton.setIcon(new ImageIcon(monsterButton.getMonster().getImage().getScaledInstance(136, 200, Image.SCALE_SMOOTH)));
					monsterButton.addMouseListener(ml2);
					cardsContainer2.add(monsterButton);
					monsters2.add(monsterButton);
					monsterButton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
							try {
								if (Main.p1.getDeck().countbyName(monsterButton.getMonster().getName()) <= 2 ) {
									Main.p1.getDeck().getDeck().add(monsterButton.getMonster());
									Main.p1.getAllCard().getDeck().remove(monsterButton.getMonster());
								} else {
									JOptionPane.showMessageDialog(null, "Sudah ada 2 kartu di deck");
								}
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
							}	
						}
					});
			} else
			if ((Main.p1.getAllCard().getDeck().get(i).getJenis()).equals("Spell")) {
					SpellButton spellButton = new SpellButton((SpellCard) Main.p1.getAllCard().getDeck().get(i));
					spellButton.setPreferredSize(new Dimension(136,200));
					spellButton.setIcon(new ImageIcon(spellButton.getSpell().getImage().getScaledInstance(136, 200, Image.SCALE_SMOOTH)));
					spellButton.addMouseListener(ml2);
					cardsContainer2.add(spellButton);
					spells2.add(spellButton);
					spellButton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
							try {
								if (Main.p1.getDeck().countbyName(spellButton.getSpell().getName()) <= 2 ) {
									Main.p1.getDeck().getDeck().add(spellButton.getSpell());
									Main.p1.getAllCard().getDeck().remove(spellButton.getSpell());
								} else {
									JOptionPane.showMessageDialog(null, "Sudah ada 2 kartu di deck");
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, e.getMessage());
							}	
						}
					});
			}
		}

		JScrollPane scrollList2 = new JScrollPane(cardsContainer2);
		scrollList2.setPreferredSize(new Dimension(350,760));
		scrollList2.setOpaque(false);
		scrollList2.getViewport().setBackground(Color.BLACK);
		
		rightSide.add(scrollList2);
		rightSide.add(scrollList1);
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
	
	private void backAction(JButton jb) {
		jb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (Main.p1.getDeck().getDeck().size()>=10 &&
						Main.p1.getDeck().getDeck().size()<=15 	) 
						new MainMenu();
					else {
						JOptionPane.showMessageDialog(null, "Jumlah kartu di deck salah. Pastikan kartu di deck 10-15");
						new DeckEditor();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current.dispose();
			}
		});
		
		jb.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {}

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
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		jb.setPreferredSize(new Dimension(400,100));
		jb.setForeground(Color.GRAY);
		jb.setContentAreaFilled(false);
		jb.setFocusPainted(false);
		jb.setBorderPainted(false);
	}

}
