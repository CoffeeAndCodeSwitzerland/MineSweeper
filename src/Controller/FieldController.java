package Controller;

import Common.CellClickState;
import Common.CellState;
import Model.Database;
import Model.GameTime;
import View.CellView;
import View.FieldView;
import View.GameOverUI;
import View.SuccessUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Date;

/**
 * Class FieldController
 * Controls the interaction with the field's members (cells)
 */
public class FieldController {
    //database to save time high score
    private Database db = new Database();

    private FieldView view;

    //class to save playing time
    private GameTime time;

    public FieldController(int fieldSize) {
        //connect to database
        db.connect();
        db.createTable();

        time = new GameTime(new Date());

        //add the mouselistener to each cell so the FieldController can control everything
        view = new FieldView(fieldSize, new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CellView sourceCell = (CellView) e.getSource();
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (!sourceCell.isProtected()) {
                        try {
                            //reveal cells (from source cell)
                            reveal(sourceCell);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    if (sourceCell.getClickState() != CellClickState.CLICKED) {
                        sourceCell.protect();
                    }
                }
                try {
                    checkGameState();
                } catch (SQLException e1) {
                    e1.printStackTrace();
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

    /**
     * method to set the bomb neighbors of each cell in a range of a 9-field
     *
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *
     * e.g. set neighbors of cell 5
     *
     */
    private void setNeighbors() {
        CellView[][] field = view.getCellViews();
        int bombCounter = 0;
        //for every cell
        for (int col = 0; col < field.length; col++) {
            for (int row = 0; row < field.length; row++) {
                //check field of 9
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        try {
                            if (i == 0 && j == 0) {}
                            else if (field[col + i][row + j].getState() == CellState.BOMB) {
                                bombCounter++;
                            }
                        } catch (ArrayIndexOutOfBoundsException ignored) {}
                    }
                }
                //end check field of 9
                if (field[col][row].getState() != CellState.BOMB) {
                    field[col][row].setBombNeighbors(bombCounter);
                }
                bombCounter = 0;
            }
        }
        //end field
    }

    /**
     * reveal cell state
     * @param sourceCell
     */
    private void reveal(CellView sourceCell) {
        if (sourceCell.getState() != CellState.BOMB) {
            sourceCell.reveal();
            if (sourceCell.getBombNeighbors() == 0) {
                fillNoBombNeighbors(sourceCell.getCol(), sourceCell.getRow());
            }
        } else {
            view.setVisible(false);
            new GameOverUI();
        }
    }

    /**
     * fills the neighbors with no bombs in their "9 field" recursively, if there
     * is no bomb neighbor besides
     *
     * @param xPos
     * @param yPos
     */
    private void fillNoBombNeighbors(int xPos, int yPos) {
        CellView[][] field = view.getCellViews();
        for (int col = -1; col <= 1; col++) {
            for (int row = -1; row <= 1; row++) {
                try {
                    if (field[xPos+col][yPos+row].getClickState() != CellClickState.CLICKED) {
                        reveal(field[xPos+col][yPos+row]);
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
    }

    /**
     * if any cell is marked as a bomb or clicked,
     * the time is won
     * @throws SQLException
     */
    private void checkGameState() throws SQLException {
        double success = 0;
        CellView[][] field = view.getCellViews();
        for (int col = 0; col < field.length; col++) {
            for (int row = 0; row < field.length; row++) {
                //check if each field is either click or it's a bomb and it's protected
                if (field[col][row].getClickState() == CellClickState.CLICKED || field[col][row].getState() == CellState.BOMB && field[col][row].isProtected()) {
                    success++;
                } else return;
            }
        }
        if (success == Math.pow(field.length, 2)) {
            this.view.setVisible(false);
            new SuccessUI(this.time, this.db, this.view.getCellViews().length);
        }
    }
}
