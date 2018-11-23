package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Random;

public class FieldView extends JFrame {

    private CellView[][] cellViews;

    public FieldView(int fieldSize, MouseListener listener) {

        setSize(500, 500);
        setMinimumSize(new Dimension(50, 50));
        setLocationRelativeTo(null);

        this.cellViews = new CellView[fieldSize][fieldSize];
        for (int col = 0; col < fieldSize; col++) {
            for (int row = 0; row < fieldSize; row++) {
                Random rnd = new Random();
                double randomState = rnd.nextDouble() * 100;
                double bombPercentage = 0;
                cellViews[col][row] = new CellView(listener, col, row, randomState, bombPercentage);
                add(cellViews[col][row]);
            }
        }

        setLayout(new GridLayout(fieldSize, fieldSize));

        setVisible(true);
    }

    public CellView[][] getCellViews() {
        return cellViews;
    }

}
