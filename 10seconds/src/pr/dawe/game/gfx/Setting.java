package pr.dawe.game.gfx;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.*;

import pr.dawe.game.Game;
import pr.dawe.game.level.Level;

public class Setting extends JFrame {

	private int difficulty = 0;
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

	public Setting(String title) {

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

		// Volume
		volumeChange = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		volumeChange.setMajorTickSpacing(1);
		volumeChange.setPaintTicks(true);
		ValueChangeListener myListener = new ValueChangeListener();
		volumeChange.addChangeListener(myListener);
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlPanel.setBackground(new Color(0xFFC800));
		controlPanel.setSize(350, 100);
		volumeNow.setSize(350, 100);
		controlPanel.add(volumeChange,BorderLayout.CENTER);
		controlPanel.add(volumeNow,BorderLayout.CENTER);
		cp.add(controlPanel);
		
		// Difficulty
		jButton2.setBounds(520, 400, 490, 114);
		jButton2.setText("Difficulty: Easy");
		jButton2.setMargin(new Insets(2, 2, 2, 2));
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton2_ActionPerformed(evt);
			}
		});
		jButton2.setBackground(Color.WHITE);
		jButton2.setBorder(BorderFactory.createEtchedBorder(0, Color.GREEN, Color.BLACK));
		cp.add(jButton2);

		// Back to menu
		jButton3.setBounds(580, 650, 370, 114);
		jButton3.setText("Back to menu");
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

	class ValueChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == volumeChange) {
				volumeNow.setText("Volume : " + ((JSlider) e.getSource()).getValue());
			}
		}
	}

	public void jButton1_ActionPerformed(ActionEvent evt) { // Volume

	}

	public void jButton2_ActionPerformed(ActionEvent evt) { // Setting difficulty
		// initial = 0
		// Easy = 0
		// Normal = 1
		// Hard = 2
		if (difficulty == 0) {
			difficulty = 1;
			jButton2.setText("Difficulty: Normal");
		} else if (difficulty == 1) {
			difficulty = 2;
			jButton2.setText("Difficulty: Hard");
		} else if (difficulty == 2) {
			difficulty = 0;
			jButton2.setText("Difficulty: Easy");
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

	public void closeSetting() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

	public static void main(String[] args) {
		new Setting("®É¶¡«iªÌ:Take a breath");
	}
}
