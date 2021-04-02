import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.time.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

import com.jom.DoubleMatrixND;
import com.jom.OptimizationProblem;


public class BouncingBalls {
    public static void main(String[] args){




        ArrayList<Ball> balls = new ArrayList<>();


        for(int i = 1; i < 4; i++){
            for(int j = 1; j < 4; j++){
                balls.add(new Ball(Integer.toString(i) + Integer.toString(j),30,100+100*i, 100+100*j, (int) ((Math.random() * 600)-300), (int) ((Math.random() * 600)-300)));
            }
        }

        //balls.add(new Ball("b1", 50, 60, 60, 200, 0));
       //balls.add(new Ball("b2",70,300,80, -200, 100));
        //balls.add(new Ball("b3",40,300+10,60, -100, 0));
        System.out.println("INITIAL BALLSTATE: ");
        printBallStates(balls,0, 0);



        // Create collisions queue
        PriorityQueue<Collision> collisions = new PriorityQueue<>();
        System.out.println("######################################################################");
        System.out.println("NEW COLLISIONS:");
        for (int i = 0; i < balls.size(); i++){
            Ball b1 = balls.get(i);
            for(int j = i+1; j < balls.size(); j++ ) {
                Ball b2 = balls.get(j);
                Collision newBallCollision = b1.getNewBallCollision(b2, 0);
                if(newBallCollision != null){
                    collisions.add(newBallCollision);
                    newBallCollision.print();
                }
            }
            Collision nextWallCollision = b1.getNextWallCollision(0);
            collisions.add(nextWallCollision);
            nextWallCollision.print();
        }
        System.out.println("######################################################################");
        System.out.println("PRINTING PQ: ");
        for(Collision c: collisions){
            c.print();
        }

        //####################
        //MAIN LOOP
        //####################
        double T = World.T;
        double t = 0;
        int iteration = 0;
        while(t < T){
            // Choose next collision
            System.out.println("######################################################################");
            System.out.println("PRINTING PQ: ");
            for(Collision c: collisions){
                c.print();
            }
            Collision nextCollision = collisions.poll();
            if(nextCollision.isRelevant()){
                System.out.println("##############################################################");
                System.out.println("NEXT COLLISION:");
                nextCollision.print();

                System.out.println("t: " + t);
                double tNext = nextCollision.getCollisionTime();
                System.out.println("tNext: " + tNext);
                double dt = tNext - t;
                System.out.println("dt: " + dt);
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

                iteration++;
                for(Ball ball : colliders) {
                    ball.incrementCollisionCount();
                    ball.saveData(t);
                }

                printBallStates(balls, t, iteration);

                System.out.println("######################################################################");
                System.out.println("NEW COLLISIONS:");
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
                            newBallCollision.print();
                        }
                    }
                    Collision nextWallCollision = b1.getNextWallCollision(t);
                    collisions.add(nextWallCollision);
                    nextWallCollision.print();
                }
            }
        }
        System.out.println("calculateions done");
        //TODO: print data

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                System.out.println("creating frame");
                Frame frame = new Frame(balls);
                System.out.println("frame initialised");
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                System.out.println("frame created");
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

