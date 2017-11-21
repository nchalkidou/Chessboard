package com.nchalkidou.chessexersise;

import static com.nchalkidou.chessexersise.utils.Utils.VERTICAL_FILES_LABELS;

/**
 * Squares of the chess board
 */
public class Square {

    private int coordX;
    private int coordY;
    private boolean isBlack;

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public void setBlack() {
        isBlack = true;
    }

    public void setWhite() {
        isBlack = false;
    }

    public String getVerticalFile() {
        return VERTICAL_FILES_LABELS[coordX];
    }

    public String getHorizontalRank() {
        return String.valueOf(8-getCoordY());
    }

    @Override
    public String toString() {
        return getVerticalFile() + getHorizontalRank() + ", X: " + coordX + ", Y: " + coordY + ", " + (isBlack ? "black" : "white");
    }
}
