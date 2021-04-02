import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


public class BouncingBalls {
    public static void main(String[] args){

        //Create Balls and set start conditions.
        ArrayList<Ball> balls = new ArrayList<>();
        for(int i = 1; i < 30; i++){
            for(int j = 1; j < 30; j++){
                balls.add(new Ball(Integer.toString(i) + Integer.toString(j),(int) ((Math.random() * 5)+2),20+15*i, 20+15*j, (int) ((Math.random() * 400)-200), (int) ((Math.random() * 600)-300)));
            }
        }

        // Create collisions queue
        PriorityQueue<Collision> collisions = new PriorityQueue<>();

        for (int i = 0; i < balls.size(); i++){
            Ball b1 = balls.get(i);
            for(int j = i+1; j < balls.size(); j++ ) {
                Ball b2 = balls.get(j);
                Collision newBallCollision = b1.getNewBallCollision(b2, 0);
                if(newBallCollision != null){
                    collisions.add(newBallCollision);
                }
            }
            Collision nextWallCollision = b1.getNextWallCollision(0);
            collisions.add(nextWallCollision);
        }

        //####################
        //MAIN LOOP
        //####################

        double T = World.T;
        double t = 0;

        while(t < T){
            // Choose next collision
            Collision nextCollision = collisions.poll();
            if(nextCollision.isRelevant()){
                double tNext = nextCollision.getCollisionTime();
                double dt = tNext - t;
                t += dt; //move time to next collision

                // End if next collision is after time limit
                if(t >= T){
                    break;
                }
                // Update ball positions to next Collision time
                for(Ball b : balls){
                    b.updatePosition(dt);
                }
                Ball[] colliders = nextCollision.getColliders();
                // update velocities for colliders
                nextCollision.updateCollisionVelocities();

                for(Ball ball : colliders) {
                    ball.incrementCollisionCount();
                    ball.saveData(t);
                }

                //printBallStates(balls, t, iteration);

                for(int i = 0; i < colliders.length; i++){
                    Ball b1 = colliders[i];
                    for(int j = 0; j < balls.size(); j++ ) {
                        Ball b2 = balls.get(j);
                        if(Arrays.asList(colliders).contains(b2)){
                            continue;
                        }
                        Collision newBallCollision = b1.getNewBallCollision(b2, t);
                        if(newBallCollision != null){
                            collisions.add(newBallCollision);
                        }
                    }
                    Collision nextWallCollision = b1.getNextWallCollision(t);
                    collisions.add(nextWallCollision);
                }
            }
        }
        System.out.println("calculateions done");
        //TODO: print data

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Frame frame = new Frame(balls);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });



    }

    public static void printBallStates(ArrayList<Ball> balls, double t, int iteration){
        System.out.println("#######################################################################################");
        System.out.println("iteration: " + iteration + ", time: " + t);
        System.out.println("#######################################################################################");
        System.out.println("PRINTING BALL STATES: ");
        for(Ball b : balls){
            b.printState();
        }
    }
}

