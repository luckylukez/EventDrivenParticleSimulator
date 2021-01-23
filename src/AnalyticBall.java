public class AnalyticBall {
    private double r;               // Ball radius
    private int collisionCount;     // Number of preious collisions
    private double speed;           // Speed at which curve runns

    /* Getters for field varables */
    public int getCollisionCount() { return this.collisionCount; }
    public double getRadius() { return this.r; }
    public double getSpeed() { return this.speed; }

    /* Setters for field variables */
    public void setCollisionCount(int c) { this.collisionCount = c; }
    public void setRadius(double r) { this.r = r; }
    public void setSpeed(double v) { this.speed = v; }

    /* Updaters for field variables with natural successor */
    public void updateCollisionCount() { this.setCollisionCount( this.getCollisionCount() + 1 ); }

    

}
