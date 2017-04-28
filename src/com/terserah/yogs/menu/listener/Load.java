package com.terserah.yogs.menu.listener;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.*;
import javax.swing.*;

import com.terserah.yogs.boards.player.PlayerFactory;
import com.terserah.yogs.menu.gui.MainMenu;
import com.terserah.yogs.menu.gui.PlayerButton;
import com.terserah.yogs.menu.gui.WrapLayout;

public class Load extends JFrame{
	private JPanel loadContainer ;
	
	public Load() throws Exception {
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File("art/backgrounds/main5.jpg"))));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(1344,768));
		background.setLayout(new BorderLayout());
		
		JPanel leftSide = new JPanel(new BorderLayout());
		leftSide.setPreferredSize(new Dimension(344,768));
		leftSide.setOpaque(false);
		JLabel judul = new JLabel("Load");
		leftSide.add(judul, BorderLayout.CENTER);
		JButton back = new JButton("Back");
		
		Load current = this;
		
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
		
		
		back.setPreferredSize(new Dimension(400,100));
		back.setForeground(Color.GRAY);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		
		leftSide.add(back,BorderLayout.SOUTH);
		
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
						Main.changep1(load.getPlayer());
						new MainMenu();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Cannot be Load");
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
	
}
