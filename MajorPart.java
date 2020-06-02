package bin.tenseconds.major;

import java.util.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MajorPart extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public boolean running = false;

    public int tickCount = 0;

    public static Level level;

    public MajorPart() {

        // 處理圖片用的
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        JFrame jframe = new JFrame("時間勇者:Take A Breath");

        jframe.setIconImage(toolkit.getImage("src/icon.png"));

        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);

        jframe.setLocationRelativeTo(null);

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jframe.setLayout(new BorderLayout());

        jframe.add(this, BorderLayout.CENTER);

        jframe.pack();

        jframe.setVisible(true);

    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000 / 60D;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

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
                System.out.println("" + ticks + " ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;
            }
        }
    }

    public void tick() {
		tickCount++;
		level.tick();
	}

    public static void main(String[] args) {
        new MajorPart().start();
    }
}