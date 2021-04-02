import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class Frame extends JFrame{
    private ArrayList<Ball> balls;

    public Frame(ArrayList<Ball> balls) {
        this.balls = balls;
        initUI();
    }

    private void initUI() {

        setTitle("Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(new Color(0, 255, 255));
        int borderWidth = 20;
        content.setBorder(new EmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));
        setContentPane(content);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        content.add(new World(balls), gbc);
        content.setPreferredSize(new Dimension(World.X_PIXELS+2*borderWidth+1, World.Y_PIXELS+2*borderWidth+1));
        pack();
        setTitle("Balls");



        setLocationRelativeTo(null);

        /*setTitle("Balls");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(new Color(0, 255, 255));
        int borderWidth = 20;
        content.setBorder(new EmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));
        content.setPreferredSize(new Dimension(World.X_PIXELS+2*borderWidth+1, World.Y_PIXELS+2*borderWidth+1));
        setContentPane(content);*/
    }
}
