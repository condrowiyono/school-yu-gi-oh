package com.terserah.yogs.duel.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.terserah.yogs.cards.Card;
import com.terserah.yogs.cards.MonsterCard;
import com.terserah.yogs.cards.spells.SpellCard;

public class CardDescriptionPanel extends JPanel{
	private Card card;
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	static Image defaultImage;
	JLabel attackAndDef;
	JLabel cardImage;
	JTextArea desc;
	public CardDescriptionPanel(Card card) throws IOException {

		super();
		defaultImage = ImageIO.read(new File("art/cards/back.png")).getScaledInstance(198, 300, Image.SCALE_SMOOTH);
		this.card = card;
		
		this.setPreferredSize(new Dimension(250,500));
		this.setOpaque(false);
		this.setLayout(new BorderLayout());

		JPanel imageSection = new JPanel(new FlowLayout(FlowLayout.CENTER));
		imageSection.setOpaque(false);
		imageSection.setPreferredSize(new Dimension(250,400));

		//descriptionSection.setOpaque(false);
		//descriptionSection.getViewport().setOpaque(false);
		attackAndDef = new JLabel();
		attackAndDef.setForeground(new Color(255, 203, 41));
		Image image;
		ImageIcon cardImg;
		desc = new JTextArea();
		desc.setEditable(false);
		
		if (card==null){
			image = defaultImage;
			desc.setText("");
		}
		
		else{
			if(card instanceof SpellCard){
				if(card.isHidden()){
					if(Card.getBoard().getActivePlayer().getField().getSpellArea().contains(card)|| Card.getBoard().getActivePlayer().getField().getHand().contains(card)){
						image = ((SpellCard)card).getImage().getScaledInstance(198, 300, Image.SCALE_SMOOTH);
						desc.setText(card.getDescription());
					}
					else{
						image = defaultImage;
						desc.setText("");
					}
				}
				else{
					image = ((SpellCard)card).getImage().getScaledInstance(198, 300, Image.SCALE_SMOOTH);
					desc.setText("");
				}

			}
			else{
				MonsterCard monster = (MonsterCard)card;
				if(card.isHidden()){
					if(Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(card) || Card.getBoard().getActivePlayer().getField().getHand().contains(card)){
						image = monster.getImage().getScaledInstance(198, 300, Image.SCALE_SMOOTH);
						attackAndDef.setText("Attack: "+monster.getATK()+", Defense: "+monster.getDEF());
						desc.setText(card.getDescription());
					}
					else{
						image = defaultImage;
						desc.setText("");
					}
				}
				else{
					image = monster.getImage().getScaledInstance(198, 300, Image.SCALE_SMOOTH);
					attackAndDef.setText("Attack: "+monster.getATK()+", Defense: "+monster.getDEF());
					desc.setText(card.getDescription());
				}



			}
		}
		cardImg = new ImageIcon(image);
		cardImage = new JLabel(cardImg);
		imageSection.add(cardImage);
		imageSection.add(attackAndDef);


		
		desc.setLineWrap(true);
		//desc.setPreferredSize(new Dimension(150,80));
		//desc.setOpaque(false);
		desc.setBackground(new Color(0,0,0));
		desc.setForeground(new Color(255, 203, 41));
		desc.setFont(new Font(Font.MONOSPACED,Font.PLAIN,15));
		JScrollPane descriptionSection = new JScrollPane(desc);
		//descriptionSection.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7, true));
		descriptionSection.setPreferredSize(new Dimension(250,100));
		this.add(imageSection,BorderLayout.NORTH);
		this.add(descriptionSection,BorderLayout.SOUTH);
		


	}
	
	public void updatePanel(){
		Image image;
		ImageIcon cardImg;
		if (card==null){
			image = defaultImage;
			desc.setText("");
		}
		else{
			if(card instanceof SpellCard){
				attackAndDef.setText("");
				if(card.isHidden()){
					if(Card.getBoard().getActivePlayer().getField().getSpellArea().contains(card) || Card.getBoard().getActivePlayer().getField().getHand().contains(card) || Card.getBoard().getActivePlayer().getField().getGraveyard().contains(card) || Card.getBoard().getOpponentPlayer().getField().getGraveyard().contains(card)){
						image = ((SpellCard)card).getImage().getScaledInstance(198, 300, Image.SCALE_SMOOTH);
						desc.setText(card.getDescription());
					}
					else{
						image = defaultImage;
						desc.setText("");
					}
				}
				else{
					image = ((SpellCard)card).getImage().getScaledInstance(198, 300, Image.SCALE_SMOOTH);
					desc.setText(card.getDescription());
				}

			}
			else{
				MonsterCard monster = (MonsterCard)card;
				if(card.isHidden()){
					if(Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(card) || Card.getBoard().getActivePlayer().getField().getHand().contains(card)  || Card.getBoard().getActivePlayer().getField().getHand().contains(card) || Card.getBoard().getActivePlayer().getField().getGraveyard().contains(card) || Card.getBoard().getOpponentPlayer().getField().getGraveyard().contains(card)){
						image = monster.getImage().getScaledInstance(198, 300, Image.SCALE_SMOOTH);
						attackAndDef.setText("Attack: "+monster.getATK()+", Defense: "+monster.getDEF());
						desc.setText(card.getDescription());
					}
					else{
						image = defaultImage;
						attackAndDef.setText("");
						desc.setText("");
					}
				}
				else{
					image = monster.getImage().getScaledInstance(198, 300, Image.SCALE_SMOOTH);
					attackAndDef.setText("Attack: "+monster.getATK()+", Defense: "+monster.getDEF());
					desc.setText(card.getDescription());
				}



			}
		}
		cardImage.setIcon(new ImageIcon(image));
	}

	

}
