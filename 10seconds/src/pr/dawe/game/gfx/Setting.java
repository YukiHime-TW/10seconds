package pr.dawe.game.gfx;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.*;

import java.io.*;
import java.util.Formatter;
import java.util.FormatterClosedException;

import pr.dawe.game.Game;
import pr.dawe.game.level.Level;

public class Setting extends JFrame {

	public int difficulty = 0;
	private Difficulty di = new Difficulty();
	private JSlider volumeChange;
	private JLabel volumeNow = new JLabel("Volume : 50", JLabel.CENTER);
	private JPanel controlPanel;
	private JButton jButton2 = new JButton();
	private JButton jButton3 = new JButton();
	public static boolean enterLevel;
	public static boolean enterDungeonForest;
	public static boolean credtis;
	public static boolean running = false;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private ImageIcon img = new ImageIcon("C:\\Users\\majik\\Documents\\GitHub\\10seconds\\10seconds\\res\\levels\\icon.png");
	
	public Setting(String title) {

		super(title);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setUndecorated(true);
		getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setIconImage(img.getImage());
		setLocation(x, y);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);

		// Volume
		volumeChange = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		volumeChange.setMajorTickSpacing(5);
		ValueChangeListener myListener = new ValueChangeListener();
		volumeChange.addChangeListener(myListener);

		controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout(5, 10));
		controlPanel.setBackground(new Color(0xFFFFFF));
		controlPanel.setBackground(null);
		controlPanel.setOpaque(false);
		controlPanel.setSize(500, 50);
		controlPanel.setLocation(540, 300);
		volumeNow.setSize(100, 100);
		controlPanel.add(volumeChange,BorderLayout.CENTER);
		controlPanel.add(volumeNow,BorderLayout.EAST);
		cp.add(controlPanel);

		
		// Difficulty
		jButton2.setBounds(490, 490, 520, 114);
		try {
			Difficulty.openFile();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if(Difficulty.diff==0) {
			jButton2.setText("Difficulty: Easy");
			jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 60));
			jButton2.setForeground(Color.CYAN);
		}else if(Difficulty.diff==1) {
			jButton2.setText("Difficulty: Normal");
			jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 60));
			jButton2.setForeground(Color.DARK_GRAY);
		}else if(Difficulty.diff==2) {
			jButton2.setText("Difficulty: Hard");
			jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 60));
			jButton2.setForeground(Color.RED);
		}
		difficulty = Difficulty.diff;
		jButton2.setBackground(null);
		jButton2.setOpaque(false);
		jButton2.setBorderPainted(false);
		jButton2.setFocusPainted(false);
		// jButton2.setMargin(new Insets(2, 2, 2, 2));
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					jButton2_ActionPerformed(evt);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		jButton2.setBackground(Color.WHITE);
		jButton2.setBorder(BorderFactory.createEtchedBorder(0, Color.GREEN, Color.BLACK));
		cp.add(jButton2);

		// Back to menu
		jButton3.setBounds(520, 700, 460, 114);
		jButton3.setText("Back To Menu");
		jButton3.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 60));
		jButton3.setForeground(Color.LIGHT_GRAY);
		jButton3.setBackground(null);
		jButton3.setOpaque(false);
		jButton3.setBorderPainted(false);
		jButton3.setFocusPainted(false);
		// jButton3.setMargin(new Insets(2, 2, 2, 2));
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton3_ActionPerformed(evt);
			}
		});
		//jButton3.setBackground(Color.WHITE);
		//jButton3.setBorder(BorderFactory.createEtchedBorder(0, Color.GREEN, Color.BLACK));
		cp.add(jButton3);

		cp.setBackground(new Color(0xFFC800));

		setVisible(true);
	}

	class ValueChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == volumeChange) {
				volumeNow.setText("Volume : " + ((JSlider) e.getSource()).getValue());
			}
		}
	}

	public void jButton2_ActionPerformed(ActionEvent evt) throws FileNotFoundException { // Setting difficulty
		// initial = 0
		// Easy = 0
		// Normal = 1
		// Hard = 2
		Difficulty.openFile();
		if (difficulty == 0) {
			difficulty = 1;
			Difficulty.writeIn(difficulty);
			Difficulty.diff = difficulty;
			jButton2.setText("Difficulty: Normal");
			jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 60));
			jButton2.setForeground(Color.DARK_GRAY);
		} else if (difficulty == 1) {
			difficulty = 2;
			Difficulty.writeIn(difficulty);
			Difficulty.diff = difficulty;
			jButton2.setText("Difficulty: Hard");
			jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 60));
			jButton2.setForeground(Color.RED);
		} else if (difficulty == 2) {
			difficulty = 0;
			Difficulty.writeIn(difficulty);
			Difficulty.diff = difficulty;
			jButton2.setText("Difficulty: Easy");
			jButton2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 60));
			jButton2.setForeground(Color.CYAN);
		}
		
		Difficulty.closeFile();
	}

	public void jButton3_ActionPerformed(ActionEvent evt) { // Back to menu
		if (running == false) {
			Menu.main(null);
			closeSetting();
		} else {
			System.out.println("Already running!");
		}
	}

	public void closeSetting() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

	public static void main(String[] args) {
		new Setting("Time Brave:Take a breath");
	}
}
