package com.terserah.yogs.maps;

import java.awt.Dialog;

import javax.swing.JFrame;

public class Land {
	private static JFrame window = new JFrame("Platformer");
	
	public static void openMap() {
		window.setContentPane(new GamePanel());
		window.pack();
		window.setVisible(true);
	}
	
	public static void hide() {
		window.setVisible(false);
	}
	public static void show() {
		window.setVisible(false);
	}

}
