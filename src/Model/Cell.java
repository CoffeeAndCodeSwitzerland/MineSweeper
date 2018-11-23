package Model;

import Common.CellClickState;
import Common.CellState;

public class Cell {

    private int col;
    private int row;
    private CellState state;
    private CellClickState clickState;
    private int bombNeighbors;

    private boolean isProtected;

    public Cell() {
        setClickState(CellClickState.NOT_CLICKED);
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public CellClickState getClickState() {
        return clickState;
    }

    public void setClickState(CellClickState clickState) {
        this.clickState = clickState;
    }

    public int getBombNeighbors() {
        return bombNeighbors;
    }

    public void setBombNeighbors(int bombNeighbors) {
        this.bombNeighbors = bombNeighbors;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }
}

