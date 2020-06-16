package pr.dawe.game.entities;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import pr.dawe.game.GameEvents;
import pr.dawe.game.gfx.Colours;
import pr.dawe.game.gfx.Screen;
import pr.dawe.game.level.Level;

public class NPC extends Mob {

	private int colour = Colours.get(-1, 19, 254, 23);
	private int scale = 1;
	public boolean isMon = true;
	protected boolean isSwimming = false;
	private int tickCount;
	Random generator = new Random();
	private int move;
	public Dimension d = new Dimension(12, 16);
	public Point monLeft;
	public Rectangle touchPlayer;

	public NPC(Level level, int x, int y, int colour, String name, int speed , int hp ,int attack) {
		super(level, name, x, y, speed, hp, attack);
		this.colour = colour;
		monLeft = new Point(x - 18, y - 18);
		touchPlayer = new Rectangle(monLeft, d);
	}
	

	public void tick() {
		int xa = 0;
		int ya = 0;
		move = generator.nextInt(50) + 1;

		if (move == 50)
			ya--;
		if (!hasCollided(xa, ya))

			if (move == 40)
				ya++;
		if (!hasCollided(xa, ya))

			if (move == 30)
				xa--;
		if (!hasCollided(xa, ya))

			if (move == 20)
				xa++;
		if (!hasCollided(xa, ya))

			if (xa != 0 || ya != 0) {
				move(xa, ya);
				monLeft.setLocation(x - 18, y - 18);
				touchPlayer.setLocation(monLeft);
				isMoving = true;
			} else {
				isMoving = false;
			}
		if (level.getTile(this.x >> 3, this.y >> 3).getId() == 3) {
			isSwimming = true;

		}
		if (isSwimming && level.getTile(this.x >> 3, this.y >> 3).getId() != 3) {
			isSwimming = false;
		}
		tickCount++;

	}

	public void render(Screen screen) {
		int xTile = 0;
		int yTile = 25;
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;

		if (movingDir == 1) {
			xTile += 2;
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
		}
		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;

		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, colour, flipTop, scale);
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, xTile + 1 + yTile * 32, colour, flipTop,
				scale);

		if (!isSwimming) {
			screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, colour,
					flipBottom, scale);
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier,
					(xTile + 1) + (yTile + 1) * 32, colour, flipBottom, scale);

		}
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}

		return false;
	}

	public boolean getIsMon() {
		return isMon;
	}


	public void monAttack(Player player) {
		GameEvents.setPlayerHealth(GameEvents.getPlayerHealth() - force);
	}
}
