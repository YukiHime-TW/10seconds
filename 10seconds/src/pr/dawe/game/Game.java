package pr.dawe.game;

import java.awt.BorderLayout;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.net.URL;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import pr.dawe.game.entities.NPC;
import pr.dawe.game.entities.PickableItem;
import pr.dawe.game.entities.Player;
import pr.dawe.game.entities.Weapon;
import pr.dawe.game.gfx.Colours;
import pr.dawe.game.gfx.Menu;
import pr.dawe.game.gfx.Music;
import pr.dawe.game.gfx.Screen;
import pr.dawe.game.gfx.SpriteSheet;
import pr.dawe.game.gfx.StageTimer;
import pr.dawe.game.gfx.Volume;
import pr.dawe.game.level.Level;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 4;
	public static final String NAME = "時間勇者:Take a breath";

	public static JFrame frame;
	Random generator = new Random();

	public static boolean running = false;
	public static boolean menuRunning = false;
	public int tickCount = 0;
	long lastTime;
	public static int xOffset;
	public static int yOffset;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colours = new int[6 * 6 * 6];

	private Screen screen;
	public static InputHandler input;
	public static Level level;
	public static GameEvents gameEvents;

	// ENTITIES
	public static Player player;
	public static NPC monster;
	public static Weapon FireBall;
	public static StageTimer Time;

	private static Music music = new Music("/music/BGM_Fight.wav");

	public List<PickableItem> pickableItems = new ArrayList<PickableItem>();

	public Game() {

		frame = new JFrame(NAME);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(".\\res\\levels\\icon.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setUndecorated(true);
		frame.add(this, BorderLayout.CENTER);
		frame.getGraphicsConfiguration().getDevice().setFullScreenWindow(frame);
		frame.pack();

		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public void init() {
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);

					colours[index++] = rr << 16 | gg << 8 | bb;
				}
			}
		}

		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/sprite_sheet.png")); // MAP; PLAYER; SHEEP
		input = new InputHandler(this);
		startLevel1(390, 390);
		addEntities();

	}

	public static void startLevel1(int x, int y) {
		String levelPath = "/levels/level_1.png";
		level = new Level(levelPath);
		player = new Player(level, x, y, input);
		monster = new NPC(level, 600, 500, Colours.get(-1, 19, 254, 23), "Slime", 1, 3, 1);
		Time = new StageTimer(frame, 10);
		// music.play();
		// music.setVolume(Volume.volume);
		level.addEntity(player);
		level.addMonEntity(monster);
		gameEvents = new GameEvents(3);
		Time.TimeGame();
		// ADD ENTITIES
	}

	public static void startLevel2(int x, int y) {
		if(Time.run) {
			Time.stop();
		}
		String levelPath = "/levels/level_2.png";
		level = new Level(levelPath);
		player = new Player(level, x, y, input);
		monster = new NPC(level, 365, 430, Colours.get(-1, 350, 240, 250), "Goblin", 1, 4, 2);
		Time = new StageTimer(frame, 30);
		// music.play();
		// music.setVolume(Volume.volume);
		level.addEntity(player);
		level.addMonEntity(monster);
		gameEvents = new GameEvents(5);
		Time.TimeGame();
		// ADD ENTITIES
	}

	public static void startLevel3(int x, int y) {
		if(Time.run) {
			Time.stop();
		}
		String levelPath = "/levels/level_3.png";
		level = new Level(levelPath);
		player = new Player(level, x, y, input);
		monster = new NPC(level, 365, 430, Colours.get(-1, 500, 536, 350), "Oku", 1, 4, 4);
		Time = new StageTimer(frame, 45);
		// music.play();
		// music.setVolume(Volume.volume);
		level.addEntity(player);
		level.addMonEntity(monster);
		gameEvents = new GameEvents(7);
		Time.TimeGame();
		// ADD ENTITIES
	}

	public static void startLevel4(int x, int y) {
		if(Time.run) {
			Time.stop();
		}
		String levelPath = "/levels/level_4.png";
		level = new Level(levelPath);
		player = new Player(level, x, y, input);
		monster = new NPC(level, 365, 430, Colours.get(-1, 19, 545, 500), "Aerodactyl", 1, 4, 5);
		Time = new StageTimer(frame, 45);
		// music.play();
		// music.setVolume(Volume.volume);
		level.addEntity(player);
		level.addMonEntity(monster);
		gameEvents = new GameEvents(10);
		Time.TimeGame();
		// ADD ENTITIES
	}

	public void addEntities() {
		/*
		 * PickableItem gun = new PickableItem(level, "Gun", Colours.get(-1, 111, 170,
		 * 222), 0, 9, 29, 400, 400); PickableItem gun_mun = new PickableItem(level,
		 * "gun_mun", Colours.get(-1, 111, 540, 321), 0, 8, 29, 380, 430); PickableItem
		 * gun_mun2 = new PickableItem(level, "gun_mun", Colours.get(-1, 111, 540, 321),
		 * 0, 8, 29, 380, 410); PickableItem shotgun_mun = new PickableItem(level,
		 * "shotgun_mun", Colours.get(-1, 111, 300, 540), 0, 10, 29, 380, 450);
		 * pickableItems.add(gun_mun); pickableItems.add(gun_mun2);
		 * pickableItems.add(gun); pickableItems.add(shotgun_mun);
		 */
		level.addPickableItems(pickableItems);

	}

	public static void startIndoorLevel(String levelPath, int x, int y) {
		level = new Level(levelPath);
		Player player = new Player(level, x, y, input);
		level.addEntity(player);
	}

	public synchronized void start() {
		running = true;
		menuRunning = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
		menuRunning = true;
	}

	public static void close() {
		frame.dispose();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000 / 60D;

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		init();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;

			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				// System.out.println("" + ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;
			}
		}
	}

	public void tick() {
		tickCount++;
		level.tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		xOffset = player.x - (screen.width / 2);
		yOffset = player.y - (screen.height / 2);

		level.renderTiles(screen, xOffset, yOffset);

		for (int x = 0; x < level.width; x++) {
			int colour = Colours.get(-1, -1, -1, 000);
			if (x % 10 == 0 && x != 0) {
				colour = Colours.get(-1, -1, -1, 500);
			}

		}

		level.renderEntities(screen); // ENTITIES
		// Player.checkBullet();

		gameEvents.renderInterface(screen, xOffset, yOffset);
		gameEvents.renderPlayerEvents(screen, xOffset, yOffset, input, player, level, monster);

		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colourCode = screen.pixels[x + y * screen.width];
				if (colourCode < 255)
					pixels[x + y * WIDTH] = colours[colourCode];

			}
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		g.dispose();
		bs.show();

	}

	public static void closeGame() {
		close();
	}

	public static void main(String[] args) {
		new Game().start();
	}

}