package bin.tenseconds.interactive;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import bin.tenseconds.major.MajorPart;

public class InputHandler implements KeyListener {

    public static int lookDir;

    public InputHandler(MajorPart game) {
        game.addKeyListener(this);
    }

    public class Key {
        private boolean pressed = false;

        public boolean isPressed() {
            return pressed;
        }

        public void toggle(boolean isPressed) {
            pressed = isPressed;
        }
    }

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();

    public void keyPressed(KeyEvent e) {
        toggleKey(e.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent e) {
        toggleKey(e.getKeyCode(), false);
    }

    public void keyTyped(KeyEvent e) {

    }

    public void toggleKey(int keyCode, boolean isPressed) {
        // MOVEMENT
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            lookDir = 0;
            up.toggle(isPressed);
            System.out.println("UP is Pressed");
        }

        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            lookDir = 1;
            down.toggle(isPressed);
            System.out.println("DOWN is Pressed");
        }

        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            lookDir = 2;
            left.toggle(isPressed);
            System.out.println("LEFT is Pressed");
        }

        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            lookDir = 3;
            right.toggle(isPressed);
            System.out.println("RIGHT is Pressed");
        }
    }
}