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

public class MajorPart extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public boolean running = false;

    public MajorPart(){
        Frame major = new Frame();
    }

    public synchronized void start() {
		running = true;
		new Thread(this).start();
    }
    
    @Override
    public void run(){

    }
    public static void main(String[] args) {
        new MajorPart();
    }
}