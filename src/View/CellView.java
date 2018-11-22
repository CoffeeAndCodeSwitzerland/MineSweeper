/*
 * Copyright (c) 2018. School project
 */

package View;

import Common.CellState;
import Model.Cell;

import javax.swing.*;
import java.awt.event.MouseListener;

public class CellView extends JButton {

    private Cell cell;

    public CellView(MouseListener listener, int col, int row) {
        cell = new Cell();
        cell.setCol(col);
        cell.setRow(row);
        addMouseListener(listener);
    }

    public int getBombNeighbors() {
        return this.cell.getBombNeighbors();
    }

    public CellState getState() {
        return this.cell.getState();
    }

    public void setBombNeighbors(int bombNeighbors) {
        this.cell.setBombNeighbors(bombNeighbors);
    }

}
