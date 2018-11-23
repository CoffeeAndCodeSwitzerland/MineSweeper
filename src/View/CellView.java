package View;

import Common.CellClickState;
import Common.CellState;
import Model.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.IOException;

public class CellView extends JButton {

    private Cell cell;

    CellView(MouseListener listener, int col, int row, double rndNumber, double bombPercentage) {
        setBackground(Color.lightGray);
        this.cell = new Cell();
        this.setCol(col);
        this.setRow(row);
        if (rndNumber <= bombPercentage) {
            this.setState(CellState.BOMB);
        } else {
            this.setState(CellState.DEFAULT);
        }
        addMouseListener(listener);
    }

    public void reveal() {
        setBackground(Color.gray);
        if (this.getBombNeighbors() != 0) {
            setText(String.valueOf(this.getBombNeighbors()));
        }
        this.setClickState(CellClickState.CLICKED);
    }

    public void protect() {
        this.setProtected(!this.isProtected());
        if (this.isProtected()) {
            setBackground(Color.red);
        } else {
            if (this.getState() != CellState.BOMB) {
                setBackground(Color.lightGray);
            }
        }
    }

    public boolean isProtected() {
        return this.cell.isProtected();
    }

    public void setProtected(boolean isProtected) {
        this.cell.setProtected(isProtected);
    }

    public int getCol() {
        return this.cell.getCol();
    }

    public void setCol(int col) {
        this.cell.setCol(col);
    }

    public int getRow() {
        return this.cell.getRow();
    }

    public void setRow(int row) {
        this.cell.setRow(row);
    }

    public int getBombNeighbors() {
        return this.cell.getBombNeighbors();
    }

    public CellState getState() {
        return this.cell.getState();
    }

    public void setState(CellState state) {
        this.cell.setState(state);
    }

    public CellClickState getClickState() {
        return this.cell.getClickState();
    }

    public void setClickState(CellClickState clickState) {
        this.cell.setClickState(clickState);
    }

    public void setBombNeighbors(int bombNeighbors) {
        this.cell.setBombNeighbors(bombNeighbors);
    }

}
