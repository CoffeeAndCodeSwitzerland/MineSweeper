package View;

import javax.swing.*;
import java.awt.*;

public class SuccessUI extends JFrame {
    public SuccessUI() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout());

        JLabel label = new JLabel("Du hast gewonnen!!");
        add(label);

        setVisible(true);
    }
}
