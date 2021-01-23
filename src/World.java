import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class World extends JPanel implements ActionListener {

    private Timer timer;
    private ArrayList<Ball> balls;
    private static final Color OUTLINE_COLOR = new Color(0,0,0);

    public static final int DELAY_MILLIS = 10;
    public static final int X_PIXELS = 500;
    public static final int Y_PIXELS = 500;


    public World(ArrayList<Ball> balls){
        this.timer = new Timer(DELAY_MILLIS, this);
        timer.start();

        balls = this.balls = balls;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        /*
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawLine(0,0, X_PIXELS, 0);
        g2d.drawLine(0,0,0, Y_PIXELS);
        g2d.drawLine(0,Y_PIXELS, X_PIXELS, Y_PIXELS);
        g2d.drawLine(X_PIXELS,0,X_PIXELS, Y_PIXELS);

        g2d.fillOval((int) 0, (int) 0, (int) 20, (int) 20);
        */
        drawWorld(g2d);
    }

    public void drawWorld(Graphics2D g2d){
        drawOutline(g2d);
        drawBalls(g2d);
    }

    void drawOutline(Graphics2D g2d){
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawLine(0,0, X_PIXELS, 0);
        g2d.drawLine(0,0,0, Y_PIXELS);
        g2d.drawLine(0,Y_PIXELS, X_PIXELS, Y_PIXELS);
        g2d.drawLine(X_PIXELS,0,X_PIXELS, Y_PIXELS);
    }

    void drawBalls(Graphics2D g2d){
        for(Ball b: balls){
            b.draw(g2d);
            //b.drawTrack(g2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        repaint();
    }
}
