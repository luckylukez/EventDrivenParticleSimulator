public class Collision implements Comparable<Collision>{
    private CollisionObject[] colliders;
    private int[] collisionsAtCreation;
    public double collisionTime;


    public Collision(CollisionObject[] colliders, double collisionTime){
        this.colliders = colliders;
        this.collisionTime = collisionTime;
        for(int i = 0; i < 2; i++) {
            collisionsAtCreation[i] = colliders[i].getCollisionCount();

        }
    }

    public CollisionObject[] getColliders(){
        return colliders;
    }

    public double getCollisionTime(){
        return collisionTime;
    }

    public boolean isRelevant(){
        for(int i = 0; i<2; i++){
            if(colliders[i].getCollisionCount() != collisionsAtCreation[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(Collision c) {
        return (int) (this.collisionTime - c.collisionTime);
    }
}
