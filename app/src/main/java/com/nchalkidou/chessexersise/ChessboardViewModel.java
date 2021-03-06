package com.nchalkidou.chessexersise;

import android.arch.lifecycle.ViewModel;

import com.nchalkidou.chessexersise.util.Point;

import java.util.ArrayList;
import java.util.List;

public class ChessboardViewModel extends ViewModel {

    private static final int KNIGHT_MOVES = 3;
    private List<Square> mSquares;
    private Square mStartSquare;
    private Square mTargetSquare;
    private Knight mKnight;
    private List<List<Point>> mPossiblePaths;

    public static final String[] VERTICAL_FILES_LABELS = {"a", "b", "c", "d", "e", "f", "g", "h"};

    public ChessboardViewModel() {
        mSquares = createSquares();
    }

    public List<Square> getSquares() {
        return mSquares;
    }

    public Square getStartSquare() {
        return mStartSquare;
    }

    public void setStartSquare(int position) {
        Square square = mSquares.get(position);
        square.setStartPoint();
        mStartSquare = square;

        mKnight = new Knight(square.getX(), square.getY());
    }

    public Square getTargetSquare() {
        return mTargetSquare;
    }

    public void setTargetSquare(int position) {
        Square square = mSquares.get(position);
        square.setTargetPoint();
        mTargetSquare = square;

        mPossiblePaths = mKnight.getPathToTarget(mTargetSquare.getX(), mTargetSquare.getY(), KNIGHT_MOVES);
    }

    public void resetChessboard() {
        mSquares = createSquares();
        // reset start and end point
        mStartSquare = null;
        mTargetSquare = null;
        mKnight = null;
        mPossiblePaths = null;
    }

    public String[] getKnightPossiblePathsArray() {
        if (mPossiblePaths == null) return null;

        String[] paths = new String[mPossiblePaths.size()];
        for (int i = 0; i < mPossiblePaths.size(); i++) {
            List<Point> possiblePath = mPossiblePaths.get(i);
            String path = "";

            for (int j = 0; j < possiblePath.size(); j++) {
                Point position = possiblePath.get(j);
                int index = position.getY() * 8 + position.getX();
                path = path + mSquares.get(index).getIdentifierName();

                if (possiblePath.size() - 1 > j)
                    path = path + " - ";
            }
            paths[i] = path;
        }
        return paths;
    }

    /**
     * Create 64(8x8) squares and set white and black squares
     * @return return the list of the created squares
     */
    public List<Square> createSquares() {
        List<Square> squares = new ArrayList<>();

        for (int y=0; y<8; y++) {
            for (int x=0; x<8; x++) {
                Square square = new Square();
                square.setX(x);
                square.setY(y);
                // if x+y sum is even, set white else if odd set it black
                if ((x+y) % 2 == 0) {
                    square.setWhite();
                } else {
                    square.setBlack();
                }
                squares.add(square);
            }
        }
        return squares;
    }
}
