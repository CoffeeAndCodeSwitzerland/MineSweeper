package View;

import Controller.FieldController;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class StartScreen extends JFrame {

    private int fieldSize;

    public StartScreen() {
        setLayout(new GridLayout(3, 1));
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Willkommen zu Minesweeper! \nLinksklick zum Aufdecken, Rechtsklick zum Schützen einer Zelle", SwingConstants.CENTER);
        add(label);

        JFormattedTextField textField = new JFormattedTextField(NumberFormat.getNumberInstance());
        textField.setSize(new Dimension(50, 50));
        textField.setValue(10d);
        textField.setColumns(10);
        add(textField);

        JButton button = new JButton("Zum Spiel");
        add(button);
        button.addActionListener(e -> {
            try {
                if (Integer.parseInt(textField.getText()) < 3 || Integer.parseInt(textField.getText()) > 200) {
                    throw new Exception();
                }
                setFieldSize(Integer.parseInt(textField.getText()));
                new FieldController(getFieldSize());
                this.setVisible(false);
            } catch (Exception ex) {
                textField.setText("");
                JOptionPane.showMessageDialog(null, "Schlingel du! Max=200, Min=3 (setze Werte zurück..)");
                textField.setText("");
            }
        });

        setVisible(true);
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }

}
