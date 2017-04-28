package com.terserah.yogs.menu.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.maps.Land;


public class GameOver  extends JFrame {

	public GameOver(Player winner) throws  Exception{
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File("art/backgrounds/main5Updated.jpg"))));

		background.setLayout(new BorderLayout());
		//this.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.add(background);
		JPanel menu = new JPanel(new FlowLayout());
		menu.setPreferredSize(new Dimension(200,500));


		JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
		header.setPreferredSize(new Dimension(300,150));
				
		JLabel winnerName = new JLabel(winner.getName()+" won the duel!");
		winnerName.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,25));
		winnerName.setForeground(Color.WHITE);
		
		header.add(winnerName);
		header.setOpaque(false);

		JButton exit = new JButton("Exit to Map");
		


		JPanel buttonsContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonsContainer.setOpaque(false);
		exit.setPreferredSize(new Dimension(200,100));
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);
		exit.setForeground(Color.GRAY);
		menu.add(exit);

		menu.setOpaque(false);
		buttonsContainer.add(menu);
		background.add(header,BorderLayout.NORTH);
		background.add(buttonsContainer,BorderLayout.CENTER);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String soundName = "sounds/bg.wav";
		try{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
		catch(Exception e1){
			e1.printStackTrace();
		}

		addActionListeners(exit,this);
		addMouseListeners(exit);
	}

	private void addMouseListeners(JButton button) {
		button.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				SoundFactory.playFX();
				button.setForeground(Color.WHITE);

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				button.setForeground(Color.GRAY);

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

	}

	private void addActionListeners(JButton exit, GameOver gameOver) {

		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameOver.dispose();
				Land.show();
			}

		});

	}

	
}
