import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.time.*;
import java.util.PriorityQueue;

import com.jom.DoubleMatrixND;
import com.jom.OptimizationProblem;


public class BouncingBalls {
    public static void main(String[] args){

        int N = 5; // number of elements in each set

        /* Create the optimization problem object */
        OptimizationProblem op = new OptimizationProblem();

        /* Add the decision variables to the problem */
        op.addDecisionVariable("x", false, new int[] { N , N , N }, 0, 1);  // name, isInteger, size , minValue, maxValue

        /* Set value for the input parameter c_{ijk} */
        op.setInputParameter("c", new DoubleMatrixND(new int [] { N , N , N} , "random"));

        /* Sets the objective function */
        op.setObjectiveFunction("minimize", "sum(x .* c)");

        /* Add the constraints */
        op.addConstraint(" sum(sum(x,3),2) <= 1");  // for each i \sum_{jk} x_{ijk} <= 1
        op.addConstraint(" sum(sum(x,3),1) <= 1");  // for each j \sum_{ik} x_{ijk} <= 1
        op.addConstraint(" sum(sum(x,2),1) <= 1");  // for each k \sum_{ij} x_{ijk} <= 1

        /* Call the solver to solve the problem */
        op.solve("glpk" ,  "solverLibraryName" , "glpk_4_47");
        if (!op.solutionIsOptimal ()) throw new RuntimeException ("An optimal solution was not found");

        /* Print the solution */
        DoubleMatrixND sol = op.getPrimalSolution("x");
        for (int c1 = 0 ; c1 < N ; c1 ++)
            for (int c2 = 0 ; c2 < N ; c2 ++)
                for (int c3 = 0 ; c3 < N ; c3 ++)
                    if (sol.get(new int [] { c1 , c2 , c3}) == 1)
                        System.out.println (c1 + " - " + c2 + " - " + c3);


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

