package pr.dawe.game;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import pr.dawe.game.entities.Mob;
import pr.dawe.game.entities.PickableItem;
import pr.dawe.game.entities.Player;
import pr.dawe.game.entities.Weapon;
import pr.dawe.game.gfx.Colours;
import pr.dawe.game.gfx.Font;
import pr.dawe.game.gfx.Screen;
import pr.dawe.game.gfx.StageTimer;
import pr.dawe.game.level.Level;

public class GameEvents {
	static Random generator = new Random();
	static long lastTime;
	static boolean playerIsIndoor = false;
	private long lastShot;
	private long Time;
	private int nowLevel = 1;
	public static boolean overItem = false;
	public static boolean overCoin = false;

	private int green = Colours.get(-1, 555, 141, 400);
	private int blue = Colours.get(-1, 555, 115, 400);
	private int orange = Colours.get(-1, 555, 542, 400);
	private int red = Colours.get(-1, 555, 500, 400);
	private int black = Colours.get(-1, 555, 000, 400);

	private int playerHealth = 10;
	static int bullets = 0;
	public static int shotbullet = 0;

	public GameEvents() {

	}

	public void renderInterface(Screen screen, int x, int y) {
		if (!playerIsIndoor) {
			switch (playerHealth) {// HEALTH
			case 10:
				Font.render("cccccccccc", screen, x + 1, y, Colours.get(-1, 555, -1, 400), 1);
				break;
			case 9:
				Font.render("ccccccccc", screen, x + 1, y, Colours.get(-1, 555, -1, 400), 1);
				break;
			case 8:
				Font.render("cccccccc", screen, x + 1, y, Colours.get(-1, 555, -1, 400), 1);
				break;
			case 7:
				Font.render("ccccccc", screen, x + 1, y, Colours.get(-1, 555, -1, 400), 1);
				break;
			case 6:
				Font.render("cccccc", screen, x + 1, y, Colours.get(-1, 555, -1, 400), 1);
				break;
			case 5:
				Font.render("ccccc", screen, x + 1, y, Colours.get(-1, 555, -1, 400), 1);
				break;
			case 4:
				Font.render("cccc", screen, x + 1, y, Colours.get(-1, 555, -1, 400), 1);
				break;
			case 3:
				Font.render("ccc", screen, x + 1, y, Colours.get(-1, 555, -1, 400), 1);
				break;
			case 2:
				Font.render("cc", screen, x + 1, y, Colours.get(-1, 555, -1, 400), 1);
				break;
			case 1:
				Font.render("c", screen, x + 1, y, Colours.get(-1, 555, -1, 400), 1);
				break;
			}

			Font.render(String.format("%d", Game.Time.midTime), screen, x, y + 8, Colours.get(-1, 255, 255, 255), 1);
		}

		Font.render("m" + bullets, screen, x, y + 7, Colours.get(-1, 111, 540, 111), 1);
	}

	public void renderPlayerEvents(Screen screen, int x, int y, InputHandler input, Player player, Level level) {

		if (input.shoot.isPressed() && Player.triggeredWEAPON) { // Take out WEAPON
			Game.FireBall = new Weapon(level, Screen.xOffset + 75, Screen.yOffset + 55, "Sword");
			level.addEntity(Game.FireBall);
			System.out.printf("Weapon Taken Out\n");
			lastShot = System.currentTimeMillis();
			shotbullet++;
		}

		if (input.shoot.isPressed() && !Player.triggeredWEAPON) {
			System.out.printf("Weapon Put Away\n");
			level.removeEntity(Game.FireBall);
		}

		if (overItem == true) { // PICK UP ITEMS
			level.removeEntity(PickableItem.pickUp);
			overItem = false;
		}

		if (overCoin == true) { // PICK UP ITEMS
			level.removeEntity(PickableItem.pickUp);
			overCoin = false;
			bullets += 10;
		}

		if (Player.wardrobe == true) { // WARDROBE
			int randomcolour1 = generator.nextInt(5) + 1;
			int randomcolour2 = generator.nextInt(5) + 1;
			int randomcolour3 = generator.nextInt(5) + 1;
			level.removeEntity(Game.monster);

			Font.render("b", screen, x + 72, y + 44, Colours.get(-1, 135, -1, 000), 1);

			if (input.investigate.isPressed()) {
				level.removeEntity(Game.FireBall);
			}
			Player.colour = Colours.get(-1, 000, randomcolour1 * 100 + randomcolour2 * 10 + randomcolour3, 543);
			// Player.wardrobe = false;
		}

		if (Player.triggeredDOOR == true) {
			Font.render("ENTER", screen, x + 65, y + 37, Colours.get(-1, 135, -1, 000), 1);
			if (input.enter.isPressed()) {
				if (nowLevel == 1) {
					Game.startIndoorLevel("/levels/houses_house" + nowLevel + ".png", 75, 85);
				}
				playerIsIndoor = true;
			}
		}

		if (Player.triggeredDOOR_LEAVE == true) { // FOREST/HOUSE_LEAVE
			Font.render("LEAVE", screen, x + 65, y + 37, Colours.get(-1, 135, -1, 000), 1);
			if (input.enter.isPressed()) {
				if (nowLevel == 1) {
					Game.startLevel1("/levels/level_" + nowLevel + ".png", 505, 475);
				}

				playerIsIndoor = false;
			}
		}

		if (Player.triggeredLAVA == true) { // LAVA DAMAGE

			Player.gettingDamage = true;
			if (System.currentTimeMillis() >= lastTime) {
				lastTime = System.currentTimeMillis() + 500;
				playerHealth--;
			} else {
				Player.gettingDamage = false;
			}
		}

		if (Player.gettingDamage == false && playerHealth < 10) { // MEDIC
			if (System.currentTimeMillis() >= lastTime) {
				lastTime = System.currentTimeMillis() + 2000;
				playerHealth++;
			}
		}

		if (/* !StageTimer.run || */ playerHealth == 0) { // Time up or Dead
			Game.level = new Level("/levels/you_are_dead.png");
			Font.render("Y O U  A R E", screen, 28, 30, Colours.get(-1, 135, -1, 555), 2);
			Timer timergame = new Timer();
			TimerTask gametest = new TimerTask() {
				@Override
				public void run() {
					System.exit(1);
				}

			};

			timergame.schedule(gametest, 2000);
		}

	}

}