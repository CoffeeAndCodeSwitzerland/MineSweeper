package View;

import Controller.FieldController;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JFrame {

    private boolean firstStart;

    public StartScreen(boolean isFirstStart) {
        setFirstStart(isFirstStart);
        //on first start show message where to delete the db
        if (isFirstStart()) {
            JOptionPane.showMessageDialog(null,
                    "WILLKOMMEN ZU MINESWEEPER\n" +
                            "\n" +
                            "- Um die vorhandenen \"Rekorddaten\" zu löschen, gehen Sie ins Verzeichnis 'C:\\Users\\ihrbenutzer\\' " +
                            "\nund löschen dort die Datei \"Minesweeper.db\"\n\n" +
                            "(ACHTUNG: kann nicht rückgängig gemacht werden!)\n");
        }

        setLayout(new GridLayout(3, 1));
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Willkommen zu Minesweeper");

        JLabel label = new JLabel("Willkommen zu Minesweeper! \nLinksklick zum Aufdecken, Rechtsklick zum Schützen einer Zelle", SwingConstants.CENTER);
        add(label);

        JTextField textField = new JTextField("Hier Feldgrösse eingeben");
        textField.setSize(new Dimension(50, 50));
        textField.setColumns(10);
        add(textField);

        JButton button = new JButton("Zum Spiel");
        button.addActionListener(e -> {
            try {
                if (Integer.parseInt(textField.getText()) < 3 || Integer.parseInt(textField.getText()) > 200) {
                    throw new Exception();
                }
                new FieldController(Integer.parseInt(textField.getText()));
                this.setVisible(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten! (Maximum Grösse = 200, Minimum Grösse =3) setze Werte zurück...");
                textField.setText("");
            }
        });
        add(button);

        setVisible(true);
    }

    public boolean isFirstStart() {
        return firstStart;
    }

    public void setFirstStart(boolean firstStart) {
        this.firstStart = firstStart;
    }

}
