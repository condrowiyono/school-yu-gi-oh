package com.terserah.yogs.shop;

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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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

import org.json.simple.*;
import org.json.simple.parser.*;

import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.CardFactory;
import com.terserah.yogs.cards.Location;
import com.terserah.yogs.cards.Mode;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.ChangeOfHeart;
import com.terserah.yogs.cards.spells.Fissure;
import com.terserah.yogs.cards.spells.FollowWind;
import com.terserah.yogs.cards.spells.SpellCard;
import com.terserah.yogs.cards.spells.StopDefense;
import com.terserah.yogs.cards.spells.MirrorForce;
import com.terserah.yogs.cards.spells.NegateAttack;
import com.terserah.yogs.cards.spells.TrapHole;
import com.terserah.yogs.menu.gui.MainMenu;
import com.terserah.yogs.menu.gui.MonsterButton;
import com.terserah.yogs.menu.gui.SpellButton;
import com.terserah.yogs.menu.gui.WrapLayout;
import com.terserah.yogs.menu.listener.CardList;
import com.terserah.yogs.menu.listener.Interface;
import com.terserah.yogs.menu.listener.Main;

public class Shop extends JFrame{
	JLabel cardImage;
	private JPanel cardsContainer ;
	public Shop() throws Exception {
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
		JButton buy = new JButton("Buy");
		Shop current = this;
		
		buy.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Mau Beli?", "Warning", dialogButton);
					if(dialogResult == JOptionPane.YES_OPTION){
						if (Main.p1.getMoney()<200) {
							JOptionPane.showMessageDialog(null, "Uang Tidak Cukup" );
						} else {
							Main.p1.setMoney(Main.p1.getMoney()-200);
							for (int z =1; z <=3; z++){
		                        Random rand = new Random();
		                        int v = rand.nextInt(99);
		                        if ((v >=0) && (v <= 1)) {
		                            Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(0));
		                        }
		                        else if ((v >=2) && (v <= 8)) {
		                        	 Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(1));
		                        }
		                        else if ((v >=9) && (v <= 14)) {
		                        	 Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(2));
		                        } 
		                        else if ((v >=15) && (v <= 24)) {
		                        	 Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(3));
		                        }
		                        else if ((v >=25) && (v <= 34)) {
		                        	Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(4));
		                        }
		                        else if ((v >=35) && (v <= 40)) {
		                        	Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(5));
		                        }
		                        else if ((v >=41) && (v <= 46)) {
		                        	Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(6));
		                        }
		                        else if ((v >=47) && (v <= 50)) {
		                        	Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(7));
		                        }
		                        else if ((v >=51) && (v <= 57)) {
		                        	Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(8));
		                        }
		                        else if ((v >=51) && (v <= 57)) {
		                        	Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(9));
		                        }
		                        else if ((v >=58) && (v <= 65)) {
		                        	Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(10));
		                        }
		                        else if ((v >=66) && (v <= 72)) {
		                        	Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(11));
		                        }
		                        else if ((v >=73) && (v <= 79)) {
		                        	Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(12));
		                        }

		                        else if ((v >=80) && (v <= 86)) {
		                        	Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(13));
		                        }
		                        else if ((v >=87) && (v <= 94)) {
		                        	Main.p1.getAllCard().addToBottom(CardFactory.getInstance().getAllCard().get(14));
		                        }
							}
						}
						new Shop();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current.dispose();
			}
		});
		
		back.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new MainMenu();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				current.dispose();
			}
		});
		
		buy.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});	
		
		back.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {}

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
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		
		back.setPreferredSize(new Dimension(400,100));
		back.setForeground(Color.GRAY);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		
		buy.setPreferredSize(new Dimension(400,100));
		buy.setForeground(Color.GRAY);
		buy.setContentAreaFilled(false);
		buy.setFocusPainted(false);
		buy.setBorderPainted(false);
		
		leftSide.add(cardImage, BorderLayout.NORTH);
		leftSide.add(buy,BorderLayout.CENTER);
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
		
		for (int i=0;i<Main.p1.getAllCard().getDeck().size();i++){
			if ((Main.p1.getAllCard().getDeck().get(i).getJenis()).equals("Monster")) {
				MonsterButton monsterButton = new MonsterButton((MonsterCard) Main.p1.getAllCard().getDeck().get(i));
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
			} else
			if ((Main.p1.getAllCard().getDeck().get(i).getJenis()).equals("Spell")) {
				SpellButton spellButton = new SpellButton((SpellCard) Main.p1.getAllCard().getDeck().get(i));
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
