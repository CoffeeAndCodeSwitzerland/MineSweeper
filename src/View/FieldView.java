package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Random;

public class FieldView extends JFrame {

    private CellView[][] cellViews;
    private Random rnd;
    private double randomState;
    private double bombPercentage = 15;

    public FieldView(int fieldSize, MouseListener listener) {

        setSize(500, 500);
        setMinimumSize(new Dimension(50, 50));
        setLocationRelativeTo(null);

        this.cellViews = new CellView[fieldSize][fieldSize];
        for (int col = 0; col < fieldSize; col++) {
            for (int row = 0; row < fieldSize; row++) {
                rnd = new Random();
                this.randomState = rnd.nextDouble()*100;
                cellViews[col][row] = new CellView(listener, col, row, this.randomState, this.bombPercentage);
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
