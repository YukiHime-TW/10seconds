package pr.dawe.game;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import pr.dawe.game.entities.Mob;
import pr.dawe.game.entities.NPC;
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
	private int nowLevel = 1;
	public static boolean overItem = false;
	public static boolean overCoin = false;

	private int green = Colours.get(-1, 555, 141, 400);
	private int blue = Colours.get(-1, 555, 115, 400);
	private int orange = Colours.get(-1, 555, 542, 400);
	private int red = Colours.get(-1, 555, 500, 400);
	private int black = Colours.get(-1, 555, 000, 400);

	Timer weaponTimer = new Timer();

	private static int playerHealth;
	private int playerMaxHealth;
	static int bullets = 0;
	public static int shotbullet = 0;

	public GameEvents(int health) {
		this.playerMaxHealth = health;
		this.playerHealth = playerMaxHealth;
		Player.hp = health;
	}

	public static int getPlayerHealth() {
		return playerHealth;
	}

	public static void setPlayerHealth(int health) {
		playerHealth = health;
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
	}

	public void renderPlayerEvents(Screen screen, int x, int y, InputHandler input, Player player, final Level level,
			NPC enemy) {

		boolean firstTrigger = false;

		if (input.shoot.isPressed() || Player.triggeredWEAPON) { // Take out WEAPON
			// if (Player.triggeredWEAPON) {
			Game.FireBall = new Weapon(level, Screen.xOffset + 73, Screen.yOffset + 57, "Sword", 1);
			level.addWeaponEntity(Game.FireBall);
			level.removeUselessWeapon();
			Player.triggeredWEAPON = false;
			// }
			if (player.touchMon.intersects(enemy.touchPlayer)) {
				if (player.touchMon.intersects(enemy.touchPlayer)) {
					System.out.print("Player Attack!\n");
					Game.FireBall.playerAttack(enemy);
					System.out.print("Monster Attack!\n");
					enemy.monAttack(player);
				}

			}
		}

		if (!input.shoot.isPressed()) { // Put Away WEAPON
			// if (Player.triggeredWEAPON) {
			level.removeWeaponEntity();
			Player.triggeredWEAPON = true;
			// }
			if (enemy.touchPlayer.intersects(player.touchMon)) {
				if (enemy.touchPlayer.intersects(player.touchMon)) {
					System.out.print("Monster Attack!\n");
					enemy.monAttack(player);
				}
			}
		}

		/*
		 * if (input.shoot.isPressed()) { // Take out WEAPON if (Player.triggeredWEAPON)
		 * { System.out.printf("Weapon Taken Out\n"); Game.FireBall = new Weapon(level,
		 * Screen.xOffset + 73, Screen.yOffset + 57, "Sword");
		 * level.addWeaponEntity(Game.FireBall); level.removeUselessWeapon();
		 * Player.triggeredWEAPON = false; } }
		 */

		/*
		 * if (input.weaponDes.isPressed()) { // Put away WEAPON if
		 * (!Player.triggeredWEAPON) { System.out.printf("Weapon Put Away\n");
		 * level.removeWeaponEntity(); Player.triggeredWEAPON = true; } }
		 */

		if (level.monEntities.size() == 0) { // If all the monster are dead
			if (nowLevel == 1) {
				nowLevel++;
				Game.startLevel2(505, 475);
			} else if (nowLevel == 2) {
				nowLevel++;
				Game.startLevel3(505, 475);
			} else if (nowLevel == 3) {
				nowLevel++;
				Game.startLevel4(505, 475);
			}
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

		/*
		 * if (Player.wardrobe == true) { // WARDROBE int randomcolour1 =
		 * generator.nextInt(5) + 1; int randomcolour2 = generator.nextInt(5) + 1; int
		 * randomcolour3 = generator.nextInt(5) + 1; level.removeEntity(Game.monster);
		 * 
		 * Font.render("b", screen, x + 72, y + 44, Colours.get(-1, 135, -1, 000), 1);
		 * 
		 * if (input.investigate.isPressed()) { level.removeEntity(Game.FireBall); }
		 * Player.colour = Colours.get(-1, 000, randomcolour1 * 100 + randomcolour2 * 10
		 * + randomcolour3, 543); // Player.wardrobe = false; }
		 */

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
					Game.startLevel1(505, 475);
				} else if (nowLevel == 2) {
					Game.startLevel2(505, 475);
				} else if (nowLevel == 3) {
					Game.startLevel3(505, 475);
				}

				playerIsIndoor = false;
			}
		}

		if (Player.triggeredLAVA == true) { // LAVA DAMAGE

			Player.gettingDamage = true;
			if (System.currentTimeMillis() >= lastTime) {
				lastTime = System.currentTimeMillis() + 500;
				playerHealth--;
				player.hp--;
			} else {
				Player.gettingDamage = false;
			}
		}

		if (Player.gettingDamage == false && playerHealth < playerMaxHealth) { // MEDIC
			if (System.currentTimeMillis() >= lastTime) {
				lastTime = System.currentTimeMillis() + 2000;
				playerHealth++;
				player.hp++;
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

	class Damage extends Thread {
		public int midTime = 500;

		@Override
		public void run() {
			Timer damageSlow = new Timer();

			damageSlow.schedule(gametest, 1000);
		}

		public TimerTask gametest = new TimerTask() {
			@Override
			public void run() {
				if (midTime > 0) {
					midTime--;
					System.out.printf("DAMAGE = %d\n", midTime + 1);
				}
			}

		};

	}

}