package pr.dawe.game.gfx;

import javax.swing.JFrame;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;

public class StageTimer {
	Timer timergame = new Timer();
	public static boolean run = true;
	public static long midTime = 0; // second

	public StageTimer(JFrame gf, int stageTime) {
		midTime = stageTime;
	}

	public void TimeGame() {
		run = true;
		System.out.println("Time");
		TimerTask gametest = new TimerTask() {
			@Override
			public void run() {
				if (midTime > 0) {
					midTime--;
					System.out.printf("%d\n", midTime);
				} else {
					stop();
				}
			}

		};

		timergame.schedule(gametest, 1000, 1000);
	}

	public void stop() {
		timergame.cancel();
		timergame.purge();
		run = false;
	}

}