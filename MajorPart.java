package bin.tenseconds.major;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.Toolkit;

public class MajorPart extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public boolean running = false;

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

    }

    public static void main(String[] args) {
        new MajorPart().start();
    }
}