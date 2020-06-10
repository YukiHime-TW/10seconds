//進入點
package pr.dawe.game.gfx;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import pr.dawe.game.Game;

public class Menu extends JFrame {

	private static final long serialVersionUID = 6153436629361090300L;
	private JButton jButton1 = new JButton();
	private JButton jButton2 = new JButton();
	private JButton jButton3 = new JButton();
	private Music music = new Music("/music/BGM_Menu.wav");
	public static boolean running = false;

	public Menu(String title) {
		super(title);
		music.play();
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
		BackgroundPanel bgp;

		// Game Start
		jButton1.setBounds(430, 280, 640, 114);
		jButton1.setText("Start New Level");
		jButton1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 80));
		jButton1.setForeground(Color.DARK_GRAY);
		jButton1.setBackground(null);
		//jButton1.setOpaque(false);
		jButton1.setContentAreaFilled(false);
		jButton1.setBorderPainted(false);
		jButton1.setFocusPainted(false);
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton1_ActionPerformed(evt);
			}
		});
		cp.add(jButton1);

		// Setting
		jButton2.setBounds(490, 490, 520, 114);
		jButton2.setText("Setting");
		jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 80));
		jButton2.setForeground(Color.DARK_GRAY);
		jButton2.setBackground(null);
		//jButton2.setOpaque(false);
		jButton2.setContentAreaFilled(false);
		jButton2.setBorderPainted(false);
		jButton2.setFocusPainted(false);
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton2_ActionPerformed(evt);
			}
		});
		cp.add(jButton2);

		// Close Game
		jButton3.setBounds(550, 700, 400, 114);
		jButton3.setText("Close Game");
		jButton3.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 60));
		jButton3.setForeground(Color.LIGHT_GRAY);
		jButton3.setBackground(null);
		//jButton3.setOpaque(false);
		jButton3.setContentAreaFilled(false);
		jButton3.setBorderPainted(false);
		jButton3.setFocusPainted(false);
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton3_ActionPerformed(evt);
			}
		});
		cp.add(jButton3);

		//BackGround
		bgp = new BackgroundPanel((new ImageIcon(".\\res\\backGround\\BG.png")).getImage());
		bgp.setBounds(0,0,d.width,d.height);
		cp.add(bgp);

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

	public void jButton3_ActionPerformed(ActionEvent evt) { // CLOSE GAME

		try {
			Cheat.openFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Cheat.cheat = 0;
		Cheat.writeIn();
		System.exit(1);
	}

	public void closeMenu() {
		music.stop();
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
	
	class BackgroundPanel extends JPanel{
		Image im;
		public BackgroundPanel(Image im)
		{
		   this.im=im;
		   this.setOpaque(true);
		}
		//Draw the back ground.
		public void paintComponent(Graphics g)
		{
		   super.paintComponents(g);
		   g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
	
		}
	}

	public static void main(String[] args) {
		new Menu("時間勇者:Take a breath");
	}

}
