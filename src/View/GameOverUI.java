package View;

import javax.swing.*;

public class GameOverUI extends JFrame {
    private StartScreen startScreen;

    public GameOverUI() {

        setSize(100, 100);

        JLabel label = new JLabel("GameOver");
        add(label);

        setVisible(true);
    }
}
