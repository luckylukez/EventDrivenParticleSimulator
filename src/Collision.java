public class Collision implements Comparable<Collision>{
    private Ball[] balls;
    private int[] collisionsAtCreation;
    private int amountOfBalls;
    private int collisionType;
    public double collisionTime;

    public Collision(Ball[] balls, int collisionType, double timeToCollision){
        this.collisionTime = System.currentTimeMillis() + timeToCollision;
        this.balls = balls;
        this.amountOfBalls = balls.length;
        this.collisionType = collisionType;
        
        for(int i = 0; i < amountOfBalls; i++){
            collisionsAtCreation[i] = balls[i].getCollisionCount();
        }
        this.collisionTime = timeToCollision;
    }

    public Ball[] balls(){
        return balls;
    }

    public boolean isRelevant(){
        boolean isRelevant = true;
        for(int i = 0; i < amountOfBalls; i++){
            if( balls[i].getCollisionCount() != collisionsAtCreation[i]){
                isRelevant = false;
            }
        }
        return isRelevant;
    }
    // returns type of collision
    // -1 for wall collisions along the x-axis
    // 1 for wall collisions along the y-axis
    // 0 for collisions between balls
    public int collisionType(){
        return collisionType;
    }

    @Override
    public int compareTo(Collision c) {
        return (int) (this.collisionTime - c.collisionTime);
    }
}
