package pr.dawe.game.gfx;

import javax.swing.JFrame;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;

public class StageTimer {
	Timer timerGame = new Timer();
	public static boolean run = true;
	public static long midTime = 0; // second

	public TimerTask gametest = new TimerTask() {
		@Override
		public void run() {
			if (midTime > 0) {
				midTime--;
				System.out.printf("midTime = %d\n", midTime + 1);
			} else {
				stop();
			}
		}

	};

	public StageTimer(JFrame gf, int stageTime) {
		midTime = stageTime;
	}

	public void TimeGame() {
		run = true;
		System.out.println("Start Time");
		timerGame.schedule(gametest, 1000, 1000);
	}

	public void stop() {
		System.out.println("Time's Up");
		timerGame.cancel();
		timerGame.purge();
		run = false;
	}

	public void pause() {
		System.out.println("Time's paused");
		timerGame.cancel();
		timerGame.schedule(gametest, 1000, 1000);
	}

	public void resume() {
		System.out.println("Time resume");
		this.timerGame = new Timer();
		timerGame.schedule(gametest, 0, 1000);
	}

}