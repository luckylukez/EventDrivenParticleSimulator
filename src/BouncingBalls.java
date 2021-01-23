import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.time.*;
import java.util.PriorityQueue;

public class BouncingBalls {
    public static void main(String[] args){

        ArrayList<CollisionObject> balls = new ArrayList<>();
        // TODO: create balls



        //TODO: Create collision objects for the walls
        CollisionObject leftWall = new CollisionObject();
        CollisionObject rightWall = new CollisionObject();
        CollisionObject upperWall = new CollisionObject();
        CollisionObject lowerWall = new CollisionObject();

        ArrayList<CollisionObject> walls = new ArrayList<>();
        walls.add(leftWall);
        walls.add(rightWall);
        walls.add(upperWall);
        walls.add(lowerWall);

        // Create collisions queue
        PriorityQueue<Collision> collisions = new PriorityQueue<>();
        for (int i = 0; i < balls.size(); i++){ //-1 because last ball has no ball left to calculate collision with
            CollisionObject b1 = balls.get(i);
            for(int j = i+1; j < balls.size(); j++ ){
                CollisionObject b2 = balls.get(j);
                Collision c = b1.calculateCollision(b2);
                collisions.add(c);
            }
            collisions.add(b1.calculateWallCollision());
        }

        //####################
        //MAIN LOOP
        //####################
        double T = 100;
        while(collisions.peek().getCollisionTime() < T){
            Collision nextCollision = collisions.poll();

            if(nextCollision.isRelevant()){
                CollisionObject[] colliders = nextCollision.getColliders();
                double t = nextCollision.getCollisionTime();
                for(CollisionObject obj: colliders){
                    //TODO: calculate new path functions
                    // save time and function data
                }
            }
        }
    }
}

