

public class AnalyticBall extends CollisionObject {
    private double r;                       // Ball radius
    private double m;                       // Ball mass
    private int collisionCount;             // Number of preious collisions
    private double speed;                   // Speed at which curve runns
        
    public AnalyticBall(double radius, double mass, int collisionCount, double speed, AnalyticPath<Double, Double[]> fn) {
        super(fn);
        this.r = radius;
        this.m = mass;
        this.collisionCount = collisionCount;
        this.speed = speed;
    }

    /* Getters for field varables */
    public int getCollisionCount() { return this.collisionCount; }
    public double getRadius() { return this.r; }
    public double getMass() { return this.m; }
    public double getSpeed() { return this.speed; }

    /* Setters for field variables */
    public void setCollisionCount(int c) { this.collisionCount = c; }
    public void setRadius(double r) { this.r = r; }
    public void setMass(double m) { this.m = m; }
    public void setSpeed(double v) { this.speed = v; }

    /* Updaters for field variables with natural successor */
    public void updateCollisionCount() { this.setCollisionCount( this.getCollisionCount() + 1 ); }

}
