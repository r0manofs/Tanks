import javax.swing.*;
import java.awt.*;

public class Tanks extends JPanel {
    final int BF_WIDTH = 576;
    final int BF_HEIGHT = 576;
    final int OBJECT_SIZE = 64;

    String[][] objects = {
            {"B", "B", "B", "B", "B", "W", "W", "B", "B"},
            {"B", "G", "G", "G", "G", "G", "G", "G", "B"},
            {"B", "G", "G", "G", "G", "G", "G", "G", "B"},
            {"B", "G", "B", "G", "G", "B", "B", "G", "B"},
            {"B", "G", "B", "G", "G", "W", "W", "G", "B"},
            {"B", "G", "B", "G", "G", "B", "B", "G", "B"},
            {"B", "G", "G", "G", "G", "G", "G", "G", "B"},
            {"B", "G", "G", "G", "G", "G", "G", "G", "B"},
            {"B", "B", "B", "B", "B", "W", "W", "B", "B"}
    };
    Direction direction = Direction.Left;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    int tankX = 256;
    int tankY = 256;
    int bulletX;
    int bulletY;

    void move(Direction direction) throws InterruptedException {
        setDirection(direction);
        //don't leave the field
        while (tankY != BF_HEIGHT - OBJECT_SIZE - 32 && tankX != 0
                && tankY != 0 && tankX != BF_WIDTH - OBJECT_SIZE) {
            switch (direction) {
                case Up -> tankY--;
                case Down -> tankY++;
                case Left -> tankX--;
                case Right -> tankX++;
            }
            Thread.sleep(10);
            repaint();
        }
    }

    void run(Direction direction) throws InterruptedException {
            //fire(direction);
            //move(direction);
        while (tankX!=0)
            fire(Direction.Left);

    }

    void fire(Direction direction) throws InterruptedException {
        //bulletPlace
        bulletX = tankX + 25;
        bulletY = tankY + 25;

        while (bulletY < BF_HEIGHT - 44 && bulletX > 0 &&
                bulletY > 0 && bulletX < BF_WIDTH - 16) {
            switch (direction) {
                case Up -> bulletY -= 1;
                case Down -> bulletY += 1;
                case Left -> bulletX -= 1;
                case Right -> bulletX += 1;
            }
            Thread.sleep(4);
            repaint();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Tanks tank = new Tanks();
        tank.run(Direction.Down);

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
        //Tank's direction descript and visual
        super.paintComponent(g);

        for (int y = 0; y < objects.length; y++) {
            for (int x = 0; x < objects.length; x++) {
                switch (objects[y][x]) {
                    case "B" -> g.setColor(new Color(137, 63, 69));
                    case "W" -> g.setColor(new Color(96, 197, 252));
                    case "G" -> g.setColor(new Color(158, 97, 13));
                }
                g.fillRect(x * OBJECT_SIZE, y * OBJECT_SIZE, OBJECT_SIZE, OBJECT_SIZE);
            }
        }

        g.setColor(Color.BLACK);
        g.fillRect(tankX, tankY, OBJECT_SIZE, OBJECT_SIZE);
        g.setColor(Color.RED);
        if (direction == Direction.Up)
            g.fillRect(tankX + 24, tankY, 16, 32);
        else if (direction == Direction.Down)
            g.fillRect(tankX + 24, tankY + 32, 16, 32);
        else if (direction == Direction.Left)
            g.fillRect(tankX, tankY + 24, 32, 16);
        else if (direction == Direction.Right)
            g.fillRect(tankX + 32, tankY + 24, 32, 16);

        //Bullets
        g.setColor(Color.RED);
        g.fillRect(bulletX, bulletY, 14, 14);

        //Tower
       /* g.setColor(Color.RED);
        g.fillRect(tankX+16,tankY+16,32,32);*/
    }
}
