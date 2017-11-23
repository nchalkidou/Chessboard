package com.nchalkidou.chessexersise;

import android.util.Log;

import com.nchalkidou.chessexersise.util.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The class of the Knight
 */
public class Knight {

    private final int x;
    private final int y;
    private int targetX;
    private int targetY;

    public Knight(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Find possible paths of knight for specific moves number to target point
     * @param targetX
     * @param targetY
     * @param movesNumber in how many moves
     * @return the list of possible paths
     */
    public List<List<Point>> getPathToTarget(int targetX, int targetY, int movesNumber) {
        this.targetX = targetX;
        this.targetY = targetY;

        List<List<Point>> paths = new ArrayList<>();


        //Todo: make it recursive

        List<Point> firstMoves = getPossibleMoves(new Point(x, y));
        for (Point firstMove : firstMoves) {

            List<Point> secondMoves = getPossibleMoves(new Point(firstMove.getX(), firstMove.getY()));
            for (Point secondMove : secondMoves) {

                List<Point> thirdMoves = getPossibleMoves(new Point(secondMove.getX(), secondMove.getY()));
                for (Point thirdMove : thirdMoves) {
                    if (isMoveEndPoint(thirdMove.getX(), thirdMove.getY())){
                        List<Point> path = new ArrayList<>();
                        path.add(firstMove);
                        path.add(secondMove);
                        path.add(thirdMove);

                        paths.add(path);
                        Log.e("test", "added");
                    }
                }
            }
        }

        return paths;

    }

    /**
     * Return the possible moves (max 8 possible) of knight from specific position
     * @param point of the knight
     * @return return the List with points / positions with possible moves
     */
    private List<Point> getPossibleMoves(Point point) {
        List<Point> moves = new ArrayList<>();
        int x = point.getX();
        int y = point.getY();

        // 1
        if (isMovePossible(x - 2, y - 1)) {
            moves.add(new Point(x - 2, y - 1));
        }
        // 2
        if (isMovePossible(x - 1, y - 2)) {
            moves.add(new Point(x - 1, y - 2));
        }
        // 3
        if (isMovePossible(x + 1, y - 2)) {
            moves.add(new Point(x + 1, y - 2));
        }
        // 4
        if (isMovePossible(x + 2, y - 1)) {
            moves.add(new Point(x + 2, y - 1));
        }
        // 5
        if (isMovePossible(x + 2, y + 1)) {
            moves.add(new Point(x + 2, y + 1));
        }
        // 6
        if (isMovePossible(x + 1, y + 2)) {
            moves.add(new Point(x + 1, y + 2));
        }
        // 7
        if (isMovePossible(x - 1, y + 2)) {
            moves.add(new Point(x - 1, y + 2));
        }
        // 8
        if (isMovePossible(x - 2, y + 1)) {
            moves.add(new Point(x - 2, y + 1));
        }

        return moves;
    }

    /**
     * Check if move is valid (in chessboard 8x8)
     * @param x coordinate of the move
     * @param y coordinate of the move
     * @return true if valid
     */
    private boolean isMovePossible(int x, int y) {
        return (x >= 0 && x < 8 && y >=0 && y < 8);
    }

    /**
     * Return true if is target point
     * @param x coordinate of point to check
     * @param y coordinate of point to check
     * @return true if equal to target
     */
    private boolean isMoveEndPoint(int x, int y) {
        return (x == targetX && y == targetY);
    }
}