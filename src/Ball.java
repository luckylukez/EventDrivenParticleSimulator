import java.awt.*;
import java.util.Random;

public class Ball {
    private double x, y, vx, vy;
    private int collisionCount;
    private boolean isWall;
    private static Random random = new Random();


    public static final double r = 20;

    public Ball(double x, double y){
        this.x  = x;
        this.y = y;

        Random random = new Random();
        double theta = random.nextDouble() * 2*Math.PI;
        vx = Math.cos(theta);
        vy = Math.sin(theta);

        this.collisionCount = 0;
    }


    public void move(double dt){
        if ((x + vx*dt < r) || (x + vx*dt > World.X_PIXELS - r)) { vx = -vx; }
        if ((y + vy*dt < r) || (y + vy*dt > World.Y_PIXELS - r)) { vy = -vy; }
        x = x + vx*dt;
        y = y + vy*dt;
    }

    public void draw(Graphics2D g2d){
        g2d.fillOval((int) (x-r), (int) (y-r), (int) (2*r), (int) (2*r));
    }

    public void updatePosition(){
        //TODO: update position
    }

    public void updateVelocities(){
        //TODO: update velocities
        collisionCount++;
    }

    public static int getRandomInt(int min, int max){
        return random.nextInt(max - min + 1) + min;
    }

    public int getCollisionCount() {
        return collisionCount;
    }

    //public Collision calculateCollision(Ball that){


    //}

    public Collision calculateWallCollision(){
        double xTimeToCollision;
        double yTimeToCollision;

        if(vx > 0) {
            xTimeToCollision = (World.X_PIXELS - this.x) / vx;
        }
        else {
            xTimeToCollision = (World.X_PIXELS - this.x) / vx;
        }
        if(vy > 0) {
            yTimeToCollision = (World.Y_PIXELS - this.y) / vy;
        }
        else {
            yTimeToCollision = (World.Y_PIXELS - this.y) / vy;
        }

        int collisionType;
        if(xTimeToCollision < yTimeToCollision){
            collisionType = -1;
        }
        else{
            collisionType = 1;
        }
        return new Collision(new Ball[]{this}, collisionType, xTimeToCollision);
    }
}
