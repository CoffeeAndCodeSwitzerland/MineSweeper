
package Controller;

import Common.CellClickState;
import Common.CellState;
import View.CellView;
import View.FieldView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldController {

    private FieldView view;
    private int fieldSize;

    public FieldController(int fieldSize) {
        this.fieldSize = fieldSize;
        view = new FieldView(this.fieldSize, new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    reveal((CellView) e.getSource());
                } else {
                    //PROTECTCELL
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        setNeighbors();
    }

    private void setNeighbors() {
        CellView[][] field = view.getCellViews();
        int bombCounter = 0;
        //FOR EVERY CELL
        for (int col = 0; col < field.length; col++) {
            for (int row = 0; row < field.length; row++) {
                //CHECK FIELD OF 9
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        try {
                            if (i == 0 && j == 0) {}
                            else if (field[col + i][row + j].getState() == CellState.BOMB) {
                                bombCounter++;
                            }
                        } catch (ArrayIndexOutOfBoundsException aoobex) {
                        }
                    }
                }
                //END CHECK FIELD OF 9
                if (field[col][row].getState() != CellState.BOMB) {
                    field[col][row].setBombNeighbors(bombCounter);
                } else {
                    field[col][row].setBackground(Color.black);
                }
                bombCounter = 0;
            }
        }
        //END FIELD
    }

    private void reveal(CellView sourceCell) {
        sourceCell.reveal();
        if (sourceCell.getBombNeighbors() == 0) {
            fillNoBombNeighbors(sourceCell.getCol(), sourceCell.getRow());
        }
    }

    private void fillNoBombNeighbors(int xPos, int yPos) {
        CellView[][] field = view.getCellViews();
        for (int col = -1; col <= 1; col++) {
            for (int row = -1; row <= 1; row++) {
                try {
                    if (field[xPos+col][yPos+row].getState() != CellState.BOMB && field[xPos+col][yPos+row].getClickState() != CellClickState.CLICKED) {
                        reveal(field[xPos+col][yPos+row]);
                    }
                } catch (ArrayIndexOutOfBoundsException aioobex) {}
            }
        }
    }
}
