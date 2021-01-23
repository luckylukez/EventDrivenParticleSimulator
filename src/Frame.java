import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Frame extends JFrame{

    public Frame() {
        initUI();
    }

    private void initUI() {
        setTitle("Balls");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(new Color(0, 255, 255));
        int borderWidth = 20;
        content.setBorder(new EmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));
        content.setPreferredSize(new Dimension(World.X_PIXELS+2*borderWidth+1, World.Y_PIXELS+2*borderWidth+1));
        setContentPane(content);
    }
}
