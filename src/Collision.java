public class Collision implements Comparable<Collision>{
    private Ball[] colliders; // Can be one or two balls depending on whether it is wall collision or not.
    private int[] collisionsAtCreation;
    public final double CollisionTime;
    private String collisionType = "";
    private String wall = "";
    private boolean isEmpty = false;

    public void setEmpty() {
        isEmpty = true;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public Collision(Ball[] colliders, double CollisionTime){
        this.colliders = colliders;
        this.CollisionTime = CollisionTime;
        this.collisionsAtCreation = new int[colliders.length];
        for(int i = 0; i < colliders.length; i++) {
            collisionsAtCreation[i] = colliders[i].getCollisionCount();
        }
        if(colliders.length == 1){
            collisionType = "wall";
        }
        else{
            collisionType = "ball";
        }

    }

    public Ball[] getColliders(){
        return colliders;
    }

    public double getCollisionTime(){
        return CollisionTime;
    }

    public String getWall(){
        return this.wall;
    }

    public void setWall(String wall){
        this.wall = wall;
    }


    public boolean isRelevant(){
        for(int i = 0; i<colliders.length; i++){
            if(colliders[i].getCollisionCount() != collisionsAtCreation[i]){
                return false;
            }
        }
        return true;
    }

    public void  updateCollisionVelocities(){
        if(this.wall == ""){
            calculateBallCollision();
        }
        else{
            calculateWallCollision();
        }
    }

    public void calculateBallCollision(){
        double distance = distanceBetweenBalls();
        double sin = dy()/distance;
        double cos = dx()/distance;

        Ball b1 = colliders[0];
        Ball b2 = colliders[1];

        double vs1 = cos * b1.getVx() + sin * b1.getVy();
        double vt1 = -sin * b1.getVx() + cos * b1.getVy();

        double vs2 = cos * b2.getVx() + sin * b2.getVy();
        double vt2 = -sin * b2.getVx() + cos * b2.getVy();

        double vs1New = ((b1.getMass() - b2.getMass())*vs1 + 2*b2.getMass()*vs2)/(b1.getMass() + b2.getMass());
        double vs2New = ((b2.getMass() - b1.getMass())*vs2 + 2*b1.getMass()*vs1)/(b1.getMass() + b2.getMass());

        b1.setVx(cos * vs1New -sin * vt1);
        b1.setVy(sin * vs1New + cos * vt1);

        b2.setVx(cos * vs2New -sin * vt2);
        b2.setVy(sin * vs2New + cos * vt2);
    }

    private double dx(){
        return colliders[1].getX() - colliders[0].getX();
    }

    private double dy(){
        return colliders[1].getY() - colliders[0].getY();
    }

    private double distanceBetweenBalls(){
        return Math.sqrt(Math.pow(dx(),2) + Math.pow(dy(),2));
    }

    public void calculateWallCollision(){
        Ball collider = this.colliders[0];
        switch(this.wall){
            case "left" :
                collider.setVx(-collider.getVx());
                break;
            case "right":
                collider.setVx(-collider.getVx());
                break;
            case "upper" :
                collider.setVy(-collider.getVy());
                break;
            case "lower":
                collider.setVy(-collider.getVy());
                break;
        }
    }

    public void print(){
        System.out.println("printing collision:");
        System.out.println("Is relevant: " + isRelevant());
        for(int i = 0; i < colliders.length; i++){
            System.out.println(collisionsAtCreation[i]);
            System.out.println(colliders[i].getCollisionCount());
        }
        System.out.println("collision time: " + CollisionTime);
        System.out.println("colliders:");
        for(Ball b:colliders){
            System.out.println(b.getName());
        }
    }

    @Override
    public int compareTo(Collision c) {
        if(this.CollisionTime > c.CollisionTime){
            return 1;
        }
        else if(c.CollisionTime > this.CollisionTime){
            return -1;
        }
        else{
            return 0;
        }
        //return (int) (this.CollisionTime - c.CollisionTime);
    }
}
