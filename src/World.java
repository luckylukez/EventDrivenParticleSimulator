import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class World extends JPanel implements ActionListener {

    private Timer timer;
    private ArrayList<Ball> balls;
    private static final Color OUTLINE_COLOR = new Color(0,0,0);
    private int visualIteration = 0;
    private double t = 0;
    private ArrayList<ArrayList<double[]>> visualData = new ArrayList<>();


    public static final double T = 100;
    public static final int DELAY_MILLIS = 10;
    public static final double DT = (double )DELAY_MILLIS/1000;
    public static final int X_PIXELS = 500;
    public static final int Y_PIXELS = 500;


    public World(ArrayList<Ball> balls){
        this.balls = balls;
        System.out.println("Creating visula data");
        createVisualData();
        System.out.println("visual data created");
        this.timer = new Timer(DELAY_MILLIS, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(OUTLINE_COLOR);
        g2d.drawLine(0,0, X_PIXELS, 0);
        g2d.drawLine(0,0,0, Y_PIXELS);
        g2d.drawLine(0,Y_PIXELS, X_PIXELS, Y_PIXELS);
        g2d.drawLine(X_PIXELS,0,X_PIXELS, Y_PIXELS);

        drawWorld(g2d);
    }

    public void drawWorld(Graphics2D g2d){
        drawOutline(g2d);
        drawState(g2d);
    }

    void drawOutline(Graphics2D g2d){
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawLine(0,0, X_PIXELS, 0);
        g2d.drawLine(0,0,0, Y_PIXELS);
        g2d.drawLine(0,Y_PIXELS, X_PIXELS, Y_PIXELS);
        g2d.drawLine(X_PIXELS,0,X_PIXELS, Y_PIXELS);
    }

    void drawState(Graphics2D g2d){


        for(int i = 0; i < this.balls.size(); i++){
            double[] data = this.visualData.get(i).get(visualIteration);
            double r = balls.get(i).getRadius();
            double x = data[1];
            double y = data[2];

            g2d.fillOval((int) (x - r), (int) (y - r), (int) (2 * r), (int) (2 * r));
        }
        this.visualIteration++;
    }

    void createVisualData(){
        for(Ball b : this.balls){
            System.out.println("creating visual data");
            this.visualData.add(b.getVisualData(DT, T));
            System.out.println("visua data created");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        repaint();
    }
}
