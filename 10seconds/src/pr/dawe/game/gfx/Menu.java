//進入點
package pr.dawe.game.gfx;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

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
		jButton1.setBounds(460, 150, 610, 114);
		jButton1.setText("Start new Level");
		jButton1.setMargin(new Insets(2, 2, 2, 2));
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton1_ActionPerformed(evt);
			}
		});
		jButton1.setBackground(Color.WHITE);
		jButton1.setBorder(BorderFactory.createEtchedBorder(0, Color.GREEN, Color.BLACK));
		cp.add(jButton1);
		
		//Setting
		jButton2.setBounds(520, 400, 490, 114);
		jButton2.setText("Setting");
		jButton2.setMargin(new Insets(2, 2, 2, 2));
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton2_ActionPerformed(evt);
			}
		});
		jButton2.setBackground(Color.WHITE);
		jButton2.setBorder(BorderFactory.createEtchedBorder(0, Color.GREEN, Color.BLACK));
		cp.add(jButton2);
		
		//Close Game
		jButton3.setBounds(580, 650, 370, 114);
		jButton3.setText("Close Game");
		jButton3.setMargin(new Insets(2, 2, 2, 2));
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton3_ActionPerformed(evt);
			}
		});
		jButton3.setBackground(Color.WHITE);
		jButton3.setBorder(BorderFactory.createEtchedBorder(0, Color.GREEN, Color.BLACK));
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
