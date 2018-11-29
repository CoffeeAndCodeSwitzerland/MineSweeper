package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GameOverUI extends JFrame {

    public GameOverUI () throws URISyntaxException {

        final URI uri = new URI("https://youtu.be/eF-fyn-WfIs?t=55");
        class OpenUrlAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                open(uri);
                System.exit(0);
            }
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        JButton button = new JButton();
        button.setText("<HTML><FONT color=\"#FF0000\"><U>GAMEOVER (click)</U></FONT>");
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setBackground(Color.WHITE);
        button.setToolTipText(uri.toString());
        button.addActionListener(new OpenUrlAction());
        container.add(button);
        setVisible(true);
    }

    private static void open(URI uri) {
        if (Desktop.isDesktopSupported())
        {
            try
            {
                Desktop.getDesktop().browse(uri);
            }
            catch (IOException ignored){}
        } else {
            System.exit(0);
        }
    }
}
