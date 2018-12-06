package View;

import javax.swing.*;
import java.awt.*;

public class GameOverUI extends JFrame {
    public GameOverUI () {
        setLayout(new GridLayout(3,1));

        setSize(300, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Game over ;(");

        JLabel gameOver = new JLabel("Game Over", SwingConstants.CENTER);
        add(gameOver);

        add(new JLabel("Noch einmal spielen?", SwingConstants.CENTER));

        JButton btnContinue = new JButton("Noch einmal");
        btnContinue.addActionListener(e -> {
            setVisible(false);
            new StartScreen(false);
        });
        add(btnContinue);

        JButton btnExit = new JButton("Spiel beenden");
        btnExit.addActionListener(e -> System.exit(0));
        add(btnExit);

        setVisible(true);
    }
}
