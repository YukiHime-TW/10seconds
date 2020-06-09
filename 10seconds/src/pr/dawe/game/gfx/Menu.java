//進入點
package pr.dawe.game.gfx;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.*;

import pr.dawe.game.Game;
import pr.dawe.game.level.Level;

public class Menu extends JFrame {

	private JButton jButton1 = new JButton();
	private JButton jButton2 = new JButton();
	private JButton jButton3 = new JButton();
	public static boolean enterLevel;
	public static boolean enterDungeonForest;
	public static boolean credtis;
	public static boolean running = false;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	public Menu(String title) {

		super(title);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\res\\levels\\icon.png"));
		setUndecorated(true);
		getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);
		
		//Game Start
		jButton1.setBounds(430, 280, 640, 114);
		jButton1.setText("Start New Level");
		jButton1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 80));
		jButton1.setForeground(Color.LIGHT_GRAY);
		jButton1.setBackground(null);
		jButton1.setOpaque(false);
		jButton1.setBorderPainted(false);
		jButton1.setFocusPainted(false);
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton1_ActionPerformed(evt);
			}
		});
		cp.add(jButton1);
		
		//Setting
		jButton2.setBounds(490, 490, 520, 114);
		jButton2.setText("Setting");
		jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 80));
		jButton2.setForeground(Color.LIGHT_GRAY);
		jButton2.setBackground(null);
		jButton2.setOpaque(false);
		jButton2.setBorderPainted(false);
		jButton2.setFocusPainted(false);
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton2_ActionPerformed(evt);
			}
		});
		cp.add(jButton2);
		
		//Close Game
		jButton3.setBounds(550, 700, 400, 114);
		jButton3.setText("Close Game");
		jButton3.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 60));
		jButton3.setForeground(Color.LIGHT_GRAY);
		jButton3.setBackground(null);
		jButton3.setOpaque(false);
		jButton3.setBorderPainted(false);
		jButton3.setFocusPainted(false);
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton3_ActionPerformed(evt);
			}
		});
		cp.add(jButton3);
		
		cp.setBackground(new Color(0xFFC800));

		setVisible(true);
	}

	public void jButton1_ActionPerformed(ActionEvent evt) { // GAME START
		if (running == false) {
			Game.main(null);
			closeMenu();
		} else {
			System.out.println("Already running!");
		}
	}
	
	public void jButton2_ActionPerformed(ActionEvent evt) { // SETTING
		if (running == false) {
			Setting.main(null);
			closeMenu();
		} else {
			System.out.println("Already running!");
		}
	}
	
	public void jButton3_ActionPerformed(ActionEvent evt) { // CLOSE　GAME
		System.exit(1);
	}

	public void closeMenu() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

	public static void main(String[] args) {
		new Menu("時間勇者:Take a breath");
	}

}
