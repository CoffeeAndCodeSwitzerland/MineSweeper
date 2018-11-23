/*
 * Copyright (c) 2018. School project
 */

package View;

import Common.CellClickState;
import Common.CellState;
import Model.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class CellView extends JButton {

    private Cell cell;

    public CellView(MouseListener listener, int col, int row, double rndNumber, double bombPercentage) {
        cell = new Cell();
        cell.setCol(col);
        cell.setRow(row);
        if (rndNumber <= bombPercentage) {
            this.cell.setState(CellState.BOMB);
        } else {
            this.cell.setState(CellState.DEFAULT);
        }
        addMouseListener(listener);
    }

    public void reveal() {
        setBackground(Color.gray);
        if (this.cell.getBombNeighbors() != 0) {
            setText(String.valueOf(this.cell.getBombNeighbors()));
        }
        this.cell.setClickState(CellClickState.CLICKED);
    }

    public void protect() {
        this.cell.setProtected(!this.cell.isProtected());
        System.out.println(this.cell.isProtected());
        if (this.cell.isProtected()) {
            setBackground(Color.orange);
        } else {
            if (this.getState() != CellState.BOMB) {
                setBackground(Color.lightGray);
            } else {
                setBackground(Color.black);
            }
        }
    }

    public boolean isProtected() {
        return this.cell.isProtected();
    }

    public void setClickState(CellClickState state) {
        this.cell.setClickState(state);
    }

    public int getCol() {
        return this.cell.getCol();
    }

    public int getRow() {
        return this.cell.getRow();
    }

    public int getBombNeighbors() {
        return this.cell.getBombNeighbors();
    }

    public CellState getState() {
        return this.cell.getState();
    }

    public CellClickState getClickState() {
        return this.cell.getClickState();
    }

    public void setBombNeighbors(int bombNeighbors) {
        this.cell.setBombNeighbors(bombNeighbors);
    }

}
