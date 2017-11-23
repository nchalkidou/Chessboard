package com.nchalkidou.chessexersise;

import android.arch.lifecycle.ViewModel;

import com.nchalkidou.chessexersise.util.Point;

import java.util.ArrayList;
import java.util.List;

public class ChessboardViewModel extends ViewModel {

    private List<Square> mSquares;
    private Square mStartSquare;
    private Square mTargetSquare;
    private Knight mKnight;

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
    }

    public void resetChessboard() {
        mSquares = createSquares();
        // reset start and end point
        mStartSquare = null;
        mTargetSquare = null;
        mKnight = null;
    }

    public List<List<String>> getKnightPossiblePaths() {
        List<List<String>> algebraicPaths = new ArrayList<>();

        if (mStartSquare != null && mTargetSquare != null) {
            List<List<Point>> paths = mKnight.getPathToTarget(mTargetSquare.getX(), mTargetSquare.getY(), 3);
            for (List<Point> path : paths) {
                List<String> algebraicPath = new ArrayList<>();
                algebraicPath.add(mStartSquare.getIdentifierName());
                for (Point position : path) {
                    int index = position.getY() * 8 + position.getX();
                    algebraicPath.add(mSquares.get(index).getIdentifierName());
                }
                algebraicPaths.add(algebraicPath);
            }
        }

        return algebraicPaths;
    }

    /**
     * Create 64(8x8) squares and set white and black squares
     * @return return the list of the created squares
     */
    private List<Square> createSquares() {
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
