import javax.swing.*;
import java.awt.*;


public class Tanks extends JPanel {
    final int BF_WIDTH = 576;
    final int BF_HEIGHT = 576;
    Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    int x = 256;
    int y = 256;
    final int BULLET_X = 320;
    final int BULLET_Y = 320;


    void move(Direction direction) throws InterruptedException {
        setDirection(direction);

        switch (direction) {
            case Up -> y--;
            case Down -> y++;
            case Left -> x--;
            case Right -> x++;
        }
        Thread.sleep(10);
        repaint();
    }

    void run(Direction direction) throws InterruptedException {
        //don't leave range of battlefield
        while (y != BF_HEIGHT - 64 - 32 && x != 0
                && y != 0 && x != BF_WIDTH - 64)
            move(direction);
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
        frame.setLocationRelativeTo(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //Описание танка и разворот в разные стороны
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 64, 64);

        g.setColor(Color.RED);
        if (direction == Direction.Up)
            g.fillRect(x + 25, y, 16, 32);
        else if (direction == Direction.Down)
            g.fillRect(x + 25, y + 32, 16, 32);
        else if (direction == Direction.Left)
            g.fillRect(x, y + 25, 32, 16);
        else if (direction == Direction.Right)
            g.fillRect(x + 32, y + 25, 32, 16);

        //Bullets
        g.setColor(Color.RED);
        g.fillRect(BULLET_X,BULLET_Y,15,15);
    }
}
