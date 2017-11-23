package com.nchalkidou.chessexersise;

import static com.nchalkidou.chessexersise.ChessboardViewModel.VERTICAL_FILES_LABELS;

/**
 * Squares of the chess board
 */
public class Square {

    private int x;
    private int y;
    private boolean isBlack;
    private boolean isStartPoint;
    private boolean isTargetPoint;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public boolean isStartPoint() {
        return isStartPoint;
    }

    public void setStartPoint() {
        isStartPoint = true;
    }

    public boolean isTargetPoint() {
        return isTargetPoint;
    }

    public void setTargetPoint() {
        isTargetPoint = true;
    }

    public String getVerticalFile() {
        return VERTICAL_FILES_LABELS[x];
    }

    public String getHorizontalRank() {
        return String.valueOf(8-getY());
    }

    public String getIdentifierName() {
        return getVerticalFile() + getHorizontalRank();
    }

    @Override
    public String toString() {
        return getIdentifierName() + ", X: " + x + ", Y: " + y + ", " + (isBlack ? "black" : "white");
    }
}
