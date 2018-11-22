
package Controller;

import Common.CellState;
import View.CellView;
import View.FieldView;

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
                reveal((CellView) e.getSource());
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

    public void setNeighbors() {
        int bombCounter = 0;
        CellView[][] field = view.getCellViews();
        //FOREACH CELL
        for (int col = 0; col < field.length; col++) {
            for (int row = 0; row < field.length; row++) {
                //FOREACH CELL IN 9 FIELD
                for (int x = -1; x < 1; x++) {
                    for (int y = -1; y < 1; y++) {
                        if (field[col+x][row+y].getState() == CellState.BOMB) {
                            bombCounter++;
                        }
                    }
                }
                //
                if (field[col][row].getState() != CellState.BOMB) {
                    field[col][row].setBombNeighbors(bombCounter);
                    //----------------------------------
                    field[col][row].setText(String.valueOf(bombCounter));
                    //----------------------------------
                }
                bombCounter = 0;
            }
        }
        //
    }

    public void reveal(CellView sourceCell) {
        CellView[][] field = view.getCellViews();
        if (sourceCell.getBombNeighbors() != 0) {
            //show number
        } else {
            //FILL
        }


        for (int col = -1; col < 1; col++) {
            for (int row = -1; row < 1; row++) {

            }
        }
        for (int col = 0; col < field.length; col++) {
            for (int row = 0; row < field.length; row++) {

            }
        }
    }
}
