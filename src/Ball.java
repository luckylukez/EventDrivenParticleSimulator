import java.awt.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Ball{
    private double r;                       // Ball radius
    private double m;                       // Ball mass
    private int collisionCount = 0;             // Number of preious collisions
    private double speed;                   // Speed at which curve runns
    private double x, y, vx, vy;
    private ArrayList<double[]> data = new ArrayList<>();
    private String name;

    public Ball(String name, double radius, double x, double y, double vx, double vy) {
        this.name = name;
        this.r = radius;
        this.m = r*r;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;

        double[] initData = {0,x,y,vx,vy};
        data.add(initData);
    }

    public void incrementCollisionCount(){
        this.collisionCount++;
    }

    /* Getters for field varables */
    public int getCollisionCount() { return this.collisionCount; }
    public double getRadius() { return this.r; }
    public double getMass() { return this.m; }
    public double getSpeed() { return this.speed; }
    public double getX(){return this.x;}
    public double getY(){return this.y;}
    public double getVx(){return this.vx;}
    public double getVy(){return this.vy;}


    /* Setters for field variables */
    public void setRadius(double r) { this.r = r; }
    public void setMass(double m) { this.m = m; }
    public void setSpeed(double v) { this.speed = v; }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setVx(double vx) {
        this.vx = vx;
    }
    public void setVy(double vy) {
        this.vy = vy;
    }
    /* Updaters for field variables with natural successor */

    public void draw(Graphics2D g2d, double t) {
        // g2d.fillOval((int) (x - this.r), (int) (y - this.r), (int) (2 * this.r), (int) (2 * this.r));
    }

    public ArrayList<double[]> getData() {
        return data;
    }

    public void saveData(double t){
        double[] dataPoint ={t,x,y,vx,vy};
        data.add(dataPoint);
    }

    /*public ArrayList<Collision> getNewCollisions(ArrayList<Ball> balls, double t){
        ArrayList<Collision> newCollisions = new ArrayList<>();
        newCollisions = getNewBallCollisions(balls,t);
        Collision nextWallCollision = getNextWallCollision(t);
        newCollisions.add(nextWallCollision);
        return newCollisions;
    }*/

    public Collision getNewBallCollision(Ball that, double t){

        double dt = calculateCollisionTime(that);
        if(dt == -1){
            return null;
        }
        double collisionTime = t + dt;
        Ball[] colliders = {this, that};
        Collision c = new Collision(colliders, collisionTime);
        return c;
    }

    public String getName() {
        return name;
    }

    public Collision getNextWallCollision(double t){
        double dtx, dty;
        String xWall, yWall;
        if(vx > 0) {
            dtx = (World.X_PIXELS - this.x - this.r) / vx;
            xWall = "right";
        }
        else if(vx < 0){
            dtx = -(this.x - this.r)/vx;
            xWall = "left";
        }
        else{
            dtx = Double.POSITIVE_INFINITY;
            xWall = "";
        }
        if(vy > 0) {
            dty = (World.Y_PIXELS - this.y - this.r ) / vy;
            yWall = "lower";
        }
        else if(vy < 0){
            dty = -(this.y - this.r)/vy;
            yWall = "upper";
        }
        else{
            dty = Double.POSITIVE_INFINITY;
            yWall = "";
        }
        System.out.println("dtx: " + dtx);
        System.out.println("dty: " + dty);

        Collision c;
        Ball[] colliders = {this};
        if(dtx <= dty){
            double collisionTime = t + dtx;
            c = new Collision(colliders, collisionTime);
            c.setWall(xWall);
        }
        else{
            double collisionTime = t + dty;
            c = new Collision(colliders, collisionTime);
            c.setWall(yWall);
        }
        return c;
    }

    private double calculateCollisionTime(Ball that){
        //TODO: calculate collisionTime for this and that.
        double x1 = this.x;
        double y1 = this.y;
        double x11 = this.vx;
        double y11 = this.vy;

        double x2 = that.getX();
        double y2 = that.getY();
        double x22 = that.getVx();
        double y22 = that.getVy();

        double r1 = this.r;
        double r2 = that.getRadius();

        //t (t (x11^2 + x22 (x22 - 2 x11) + y11 (y11 - 2 y22) + y22^2) + 2 x1 x11 + x22 (2 x2 - 2 x1) - 2 x11 x2 + y1 (2 y11 - 2 y22) - 2 y11 y2 + 2 y2 y22) + x1 (x1 - 2 x2) + x2^2 + y1 (y1 - 2 y2) + y2^2

        double a = x11*x11 + x22*(x22 - 2*x11) + y11*(y11 - 2*y22) + y22*y22;
        double b = 2*x1*x11 + x22*(2*x2 - 2*x1) - 2*x11*x2 + y1*(2*y11 - 2*y22) - 2*y11*y2 + 2*y2*y22;
        double c = x1*(x1 - 2*x2) + x2*x2 + y1*(y1 - 2*y2) + y2*y2 - (r1+r2)*(r1+r2);

        double result = b * b - 4.0 * a * c;

        if (result > 0.0) {
            double deltaT1 = (-b - Math.pow(result, 0.5)) / (2.0 * a);
            double deltaT2 = (-b + Math.pow(result, 0.5)) / (2.0 * a);
            //System.out.println("The roots are " + r1 + " and " + r2);
            //Den senare lösningen borde aldrig hända eftersom det innebär att bollarna är inne i varandra.
            if(deltaT1 >= 0 && deltaT2 >= 0) {
                System.out.println("Returning double solution dt = " + deltaT1);
                return deltaT1;
            }
            else if(deltaT1 < 0 && deltaT2 >= 0){
                try{
                    throw new Exception("Balls moved into each other");
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                return -1;
            }
        }
        else if (result == 0.0) {
            double deltaT = -b / (2.0 * a);
            if(deltaT >= 0){
                System.out.println("returnning single solution dt = " + deltaT);
                return deltaT;
            }
            return -1;
            //System.out.println("The root is " + r1);
        }
        else {
            //paths are parallel
            return -1;
            //System.out.println("The equation has no real roots.");
        }
    return -1;
    }

    public void updatePosition(double dt){
        x += dt*vx;
        y += dt*vy;
    }

    public void printState(){
        System.out.println("######################################################################################");
        System.out.println("State of ball " + name );
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("vx " + vx);
        System.out.println("vy " + vy);
    }

    public ArrayList<double[]> getVisualData(double dt, double T){
        ArrayList<double[]> visualData = new ArrayList<>();
        double t = 0;
        int iteration = 0;


        while(iteration < data.size()){
            double[] data = this.data.get(iteration);
            double lastCollisionTime = data[0];

            double nextCollisionTime;
            if(iteration == this.data.size()-1) {
                nextCollisionTime = T;
            }
            else{
                double[] nextData = this.data.get(iteration+1);
                nextCollisionTime = nextData[0];
            }

            double x = data[1];
            double y = data[2];
            double vx = data[3];
            double vy = data[4];

            while(t < nextCollisionTime){
                double timeSinceCollision = t - lastCollisionTime;
                double[] newPoint = {t,x+vx*timeSinceCollision,y+vy*timeSinceCollision};
                visualData.add(newPoint);
                t += dt;
                //System.out.println("time: " + t + "lastCollision: " + lastCollisionTime + "nextCollision: " + lastCollisionTime);
            }
            iteration++;
        }
        return(visualData);
    }
}
