package pr.dawe.game.gfx;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import pr.dawe.game.gfx.Menu.BackgroundPanel;

import java.io.*;

public class Setting extends JFrame {

	private static final long serialVersionUID = 1L;
	public int difficulty = 0;
	private JSlider volumeChange;
	private JLabel volumeNow;
	private JLabel successLabel = new JLabel("");
	private JLabel failLabel = new JLabel("");
	private JPanel controlPanel;
	private JPanel cheatPanel = new JPanel();
	private JTextField cheat = new JTextField();
	private JButton cheatSubmit = new JButton();
	private JButton jButton2 = new JButton();
	private JButton jButton3 = new JButton();
	private Music music = new Music("/music/BGM_Menu.wav");
	public static boolean running = false;

	public Setting(String title) {
		super(title);
		music.play();
		music.setVolume(Volume.volume);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setUndecorated(true);
		getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\res\\levels\\icon.png"));
		setLocation(x, y);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);
		BackgroundPanel bgp;

		// Volume
		volumeNow = new JLabel();
		try {
			Volume.openFile();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		volumeChange = new JSlider(JSlider.HORIZONTAL, 0, 100, Volume.volume);
		volumeChange.setMajorTickSpacing(5);
		ValueChangeListener myListener = new ValueChangeListener();
		volumeChange.addChangeListener(myListener);

		volumeNow.setText(String.format("Volume : %d", Volume.volume));

		controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout(5, 10));
		controlPanel.setBackground(null);
		controlPanel.setOpaque(false);
		controlPanel.setSize(500, 50);
		controlPanel.setLocation(540, 450);
		volumeNow.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 20));
		controlPanel.add(volumeChange, BorderLayout.CENTER);
		controlPanel.add(volumeNow, BorderLayout.EAST);
		cp.add(controlPanel);

		// Difficulty
		jButton2.setBounds(490, 550, 570, 114);
		if (Difficulty.diff == 0) {
			jButton2.setText("Difficulty: Easy");
			jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 60));
			jButton2.setForeground(Color.CYAN);
		} else if (Difficulty.diff == 1) {
			jButton2.setText("Difficulty: Normal");
			jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 60));
			jButton2.setForeground(Color.DARK_GRAY);
		} else if (Difficulty.diff == 2) {
			jButton2.setText("Difficulty: Hard");
			jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 60));
			jButton2.setForeground(Color.RED);
		}
		difficulty = Difficulty.diff;
		jButton2.setBackground(null);
		//jButton2.setOpaque(false);
		jButton2.setContentAreaFilled(false);
		jButton2.setBorderPainted(false);
		jButton2.setFocusPainted(false);
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					jButton2_ActionPerformed(evt);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		cp.add(jButton2);

		// Back to menu
		jButton3.setBounds(540, 750, 480, 114);
		jButton3.setText("Back To Menu");
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

		// Cheat Code: C8763
		if (Cheat.cheat == 1) {
			cheatPanel.removeAll();
			successLabel.setText("Star Burst Stream!");
			successLabel.setForeground(Color.BLACK);
			successLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 56));
			cheatPanel.setLayout(new BorderLayout(5, 10));
			cheatPanel.setBackground(null);
			cheatPanel.setOpaque(false);
			cheatPanel.setSize(500, 50);
			cheatPanel.setLocation(540, 300);
			cheatPanel.add(successLabel);
			setVisible(true);
		} else {
			cheatPanel.removeAll();
			cheatSubmit.setText("Link Start!");
			cheatSubmit.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 40));
			cheatSubmit.setForeground(Color.DARK_GRAY);
			cheatSubmit.setBackground(null);
			//cheatSubmit.setOpaque(false);
			cheatSubmit.setContentAreaFilled(false);
			cheatSubmit.setBorderPainted(false);
			cheatSubmit.setFocusPainted(false);
			cheatSubmit.setSize(100, 50);
			cheatSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					cheat_ActionPerformed(evt);
				}
			});
			cheatPanel.setLayout(new BorderLayout(5, 10));
			cheatPanel.setBackground(null);
			cheatPanel.setOpaque(false);
			cheatPanel.setSize(500, 50);
			cheatPanel.setLocation(580, 300);
			cheatPanel.add(cheat, BorderLayout.CENTER);
			cheatPanel.add(cheatSubmit, BorderLayout.EAST);
			setVisible(true);
		}

		cp.add(cheatPanel);

		//BackGround
		bgp = new BackgroundPanel((new ImageIcon(".\\res\\backGround\\BG.png")).getImage());
		bgp.setBounds(0,0,d.width,d.height);
		cp.add(bgp);

		setVisible(true);
	}

	class ValueChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == volumeChange) {
				volumeNow.setText("Volume : " + ((JSlider) e.getSource()).getValue());
				music.setVolume(((JSlider) e.getSource()).getValue());
				Volume.volume = ((JSlider) e.getSource()).getValue();
			}
		}
	}

	public void jButton2_ActionPerformed(ActionEvent evt) throws FileNotFoundException { // Setting difficulty
		// initial = 0
		// Easy = 0
		// Normal = 1
		// Hard = 2
		if (difficulty == 0) {
			difficulty = 1;
			Difficulty.diff = difficulty;
			jButton2.setText("Difficulty: Normal");
			jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 60));
			jButton2.setForeground(Color.DARK_GRAY);
		} else if (difficulty == 1) {
			difficulty = 2;
			Difficulty.diff = difficulty;
			jButton2.setText("Difficulty: Hard");
			jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 60));
			jButton2.setForeground(Color.RED);
		} else if (difficulty == 2) {
			difficulty = 0;
			Difficulty.diff = difficulty;
			jButton2.setText("Difficulty: Easy");
			jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 60));
			jButton2.setForeground(Color.CYAN);
		}
	}

	public void jButton3_ActionPerformed(ActionEvent evt) { // Back to menu
		if (running == false) {
			Menu.main(null);
			closeSetting();
		} else {
			System.out.println("Already running!");
		}
	}

	public void cheat_ActionPerformed(ActionEvent evt) {
		String cheatText = cheat.getText();
		if (cheatText.equals("C8763") || cheatText.equals("c8763")) {
			cheatPanel.removeAll();
			successLabel.setText("Star Burst Stream!");
			successLabel.setForeground(Color.BLACK);
			successLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 56));
			cheatPanel.add(successLabel);
			setVisible(true);
			Cheat.cheat = 1;
		} else {
			cheatPanel.removeAll();
			failLabel.setText("KIRITO!!!!!!!");
			failLabel.setForeground(Color.RED);
			failLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC, 60));
			cheatPanel.add(failLabel);
			setVisible(true);
			Cheat.cheat = 0;
		}
	}

	public void closeSetting() {
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
		new Setting("Time Brave:Take a breath");
	}
}
