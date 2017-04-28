package com.terserah.yogs.menu.listener;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.terserah.yogs.boards.player.PlayerFactory;
import com.terserah.yogs.menu.gui.MainMenu;
import com.terserah.yogs.menu.gui.PlayerButton;
import com.terserah.yogs.menu.gui.WrapLayout;

public class Save extends JFrame{
	private JPanel loadContainer ;
	private Save current = this;
	private String callback;
	public Save(String callback) throws Exception {
		this.callback = callback;
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
		JButton back = new JButton("Back");
		back.setText("Back to " + callback);
		JButton save = new JButton("Save");
		
		leftSide.add(back,BorderLayout.SOUTH);
		leftSide.add(save,BorderLayout.CENTER);
		decorButton(back);
		decorButton(save);

		SaveButtonListener(back, save);	
		JPanel rightSide = new JPanel(new FlowLayout(FlowLayout.CENTER));
		rightSide.setPreferredSize(new Dimension(1000,768));
		rightSide.setOpaque(false);
		
		loadContainer = new JPanel(new WrapLayout());
		loadContainer.setOpaque(false);
		loadContainer.setAutoscrolls(true);
		
		ArrayList<PlayerButton> loads = new ArrayList<PlayerButton>();
		
		for (int i=0;i<PlayerFactory.getInstance().getAllPlayer().size();i++){
			PlayerButton load = new PlayerButton(PlayerFactory.getInstance().getAllPlayer().get(i));
			load.setPreferredSize(new Dimension(136,136));
			loadContainer.add(load);
			loads.add(load);
			
			load.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {
						Object[] options = {"Replace",
						"Delete"};
						int n = JOptionPane.showOptionDialog(null,
								"Save or delete",
								"Option",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,     //do not use a custom Icon
								options,  //the titles of buttons
								null); //default button title
						if(n==0){
							int dialogButton = JOptionPane.YES_NO_OPTION;
							int dialogResult = JOptionPane.showConfirmDialog (null, "Will replaced old one?","Warning",dialogButton);
							if(dialogResult == JOptionPane.YES_OPTION){
							  	PlayerFactory.getInstance().getAllPlayer().remove(load.getPlayer());
								PlayerFactory.getInstance().getAllPlayer().add(Main.p1);
								PlayerFactory.exportPlayer(PlayerFactory.getInstance().getAllPlayer());
								JOptionPane.showMessageDialog(null, "Game Saved");
							}
						} else if (n==1) {
						  	PlayerFactory.getInstance().getAllPlayer().remove(load.getPlayer());
							PlayerFactory.exportPlayer(PlayerFactory.getInstance().getAllPlayer());
							JOptionPane.showMessageDialog(null, "Game Deleted");
						}
						current.setVisible(false);
						new Save("menu");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Cannot be Save");
					}	
				}
			});
		}
		
		JScrollPane scrollList = new JScrollPane(loadContainer);
		scrollList.setPreferredSize(new Dimension(700,760));
		scrollList.setOpaque(false);
		scrollList.getViewport().setBackground(Color.BLACK);
		
		rightSide.add(scrollList);
		background.add(leftSide,BorderLayout.WEST);
		background.add(rightSide,BorderLayout.EAST);
		this.add(background);
		this.setVisible(true);
	}
	
	private void decorButton(JButton jb) {
		jb.setPreferredSize(new Dimension(400,100));
		jb.setForeground(Color.GRAY);
		jb.setContentAreaFilled(false);
		jb.setFocusPainted(false);
		jb.setBorderPainted(false);
	}	
	private void SaveButtonListener(JButton back,JButton save) {
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					JOptionPane.showMessageDialog(null, "Game saved");
					PlayerFactory.getInstance();
					PlayerFactory.getInstance().getAllPlayer().add(Main.p1);
					PlayerFactory.exportPlayer(PlayerFactory.getInstance().getAllPlayer());
					current.setVisible(false);
					new Save("menu");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		back.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					switch (callback) {
						case "menu" :  new MainMenu(); current.dispose(); break;
						case "interface" : new Interface(); current.dispose();break;
						case "exit" :  System.exit(ABORT);break;
						default : new MainMenu();break;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				current.dispose();
			}
		});
		
		back.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					SoundFactory.playFX();
				} catch(Exception e1){}
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
		save.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					SoundFactory.playFX();
				} catch(Exception e1){}
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
		

	}
}
