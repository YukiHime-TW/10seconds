package pr.dawe.game.gfx;

import javax.swing.JFrame;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;

public class PkbTimer {
	Timer timergame = new Timer();
	public int num = 0, t;
	public Timer time2;
	public boolean run = true;
	public long midTime = 20000; // second*100
	public long hh = 0, mm = 0, ss = 0;

	public PkbTimer(JFrame gf) {

	}

	public void TimeGame() {
		// System.out.println("Time");
		TimerTask gametest = new TimerTask() {
			@Override
			public void run() {
				if (midTime > 0) {
					midTime--;
					hh = midTime / 100 / 60 % 60;
					mm = midTime / 100 % 60;
					ss = midTime % 100;
					Toolkit.getDefaultToolkit().beep();
				} else
					timergame.purge();

			}

		};

		timergame.schedule(gametest, 1000);
		run = false;
	}

}