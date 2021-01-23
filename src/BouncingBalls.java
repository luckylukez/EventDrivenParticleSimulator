import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.time.*;
import java.util.PriorityQueue;

public class BouncingBalls {
    public static void main(String[] args){

        ArrayList<Ball> balls = new ArrayList<>();
        // Create balls
        for(int i = (int) Ball.r + 5; i < World.X_PIXELS - (int) Ball.r - 5; i = i+(int)(2*Ball.r)){
                    for(int j = (int) Ball.r + 5; j < World.Y_PIXELS - (int) Ball.r - 5; j = j+(int)(2*Ball.r)){
                        balls.add(new Ball(i,j));
            }
        }



        // Start graphics generation on different thread
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                World world = new World(balls);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1;
                gbc.weighty = 1;
                gbc.fill = GridBagConstraints.BOTH;

                Frame frame = new Frame();
                frame.add(world, gbc);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        /*
        // Create collisions queue
        //TODO: check if -1 is needed?
        PriorityQueue<Collision> collisions = new PriorityQueue<>();
        for (int i = 0; i < balls.size(); i++){ //-1 because last ball has no ball left to calculate collision with
            Ball b1 = balls.get(i);
            for(int j = i+1; j < balls.size(); j++ ){
                Ball b2 = balls.get(j);
                //Collision c = b1.calculateCollision(b2);
                //collisions.add(c);
            }
            collisions.add(b1.calculateWallCollision());
        }
        */
        //####################
        //MAIN LOOP
        //####################

        while(true){
            for (Ball b: balls){
                b.move(0.5);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
