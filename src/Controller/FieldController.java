package Controller;

import Common.CellClickState;
import Common.CellState;
import Model.Game;
import View.CellView;
import View.FieldView;
import View.GameOverUI;
import View.SuccessUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;

public class FieldController {

    private FieldView view;
    private Game db;

    public FieldController(int fieldSize) {
        db = new Game();
        db.setStartDate(new Date());

        view = new FieldView(fieldSize, new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CellView sourceCell = (CellView) e.getSource();
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (!sourceCell.isProtected()) {
                        try {
                            reveal(sourceCell);
                        } catch (URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                    }
                } else {
                    if (sourceCell.getClickState() != CellClickState.CLICKED) {
                        sourceCell.protect();
                    }
                }
                checkGameState();
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

    private void reveal(CellView sourceCell) throws URISyntaxException {
        if (sourceCell.getState() != CellState.BOMB) {
            sourceCell.reveal();
            if (sourceCell.getBombNeighbors() == 0) {
                fillNoBombNeighbors(sourceCell.getCol(), sourceCell.getRow());
            }
        } else {
            view.setVisible(false);
            new GameOverUI();
            System.out.println("gameOver");
        }
    }

    private void fillNoBombNeighbors(int xPos, int yPos) {
        CellView[][] field = view.getCellViews();
        for (int col = -1; col <= 1; col++) {
            for (int row = -1; row <= 1; row++) {
                try {
                    if (field[xPos+col][yPos+row].getClickState() != CellClickState.CLICKED) {
                        reveal(field[xPos+col][yPos+row]);
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {} catch (URISyntaxException e) {
                    //sum expl0sion ät se end ^^
                    e.printStackTrace();
                }
            }
        }
    }

    private void checkGameState() {
        double success = 0;
        CellView[][] field = view.getCellViews();
        for (int col = 0; col < field.length; col++) {
            for (int row = 0; row < field.length; row++) {
                if (field[col][row].getClickState() == CellClickState.CLICKED || field[col][row].getState() == CellState.BOMB) {
                    success++;
                } else return;
            }
        }
        if (success == Math.pow(field.length, 2)) {
            this.view.setVisible(false);
            new SuccessUI(db);
        }
    }
}
