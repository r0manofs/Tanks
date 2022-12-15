import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class Tanks extends JPanel {
    //1-Up, 2-Down, 3-Left, 4-Right

    final int BF_WIDTH =576;
    final int BF_HEIGHT =576;
    int direction = 2;

    int x = 256;
    int y = 256;

    void moveUp() throws InterruptedException {
        direction = 1;
        while (y != 0) {
            y--;
            Thread.sleep(10);
            repaint();
        }
    }

    void moveDown() throws InterruptedException {
        direction = 2;
        while (y != BF_HEIGHT-64-32) {
            y++;
            Thread.sleep(10);
            repaint();
        }
    }

    void moveLeft() throws InterruptedException {
        direction = 3;
        while (x != 0) {
            x--;
            Thread.sleep(10);
            repaint();
        }
    }

    void moveRight() throws InterruptedException {
        direction = 4;
        while (x != BF_WIDTH - 64) {
            x++;
            Thread.sleep(10);
            repaint();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Tanks tank = new Tanks();
        tank.moveDown();
    }

    Tanks() {
        JFrame frame = new JFrame("TANKS");
        frame.setMinimumSize(new Dimension(BF_WIDTH, BF_HEIGHT));
        frame.getContentPane().add(this);
        frame.setLocation(0, 0);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //Описание танка и разворот в разные стороны
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 64, 64);

        g.setColor(Color.RED);
        if (direction == 1)
            g.fillRect(x + 25, y, 16, 32);
        else if (direction == 2)
            g.fillRect(x + 25, y + 32, 16, 32);
        else if (direction == 3)
            g.fillRect(x, y + 25, 32, 16);
        else if (direction == 4)
            g.fillRect(x + 32, y + 25, 32, 16);
    }
}
