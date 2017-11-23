package com.nchalkidou.chessexersise;

import com.nchalkidou.chessexersise.util.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
     * @param targetX of target point
     * @param targetY of target point
     * @param movesNumber in how many moves
     * @return the list of possible paths
     */
    public List<List<Point>> getPathToTarget(int targetX, int targetY, int movesNumber) {
        this.targetX = targetX;
        this.targetY = targetY;

        List<List<Point>> paths = new ArrayList<>();
        Queue<List<Point>> queuePaths = new LinkedList<>();

        // add the first position /start
        List<Point> path = new ArrayList<>();
        path.add(new Point(x, y));
        queuePaths.add(path);

        while(!queuePaths.isEmpty()) {
            List<Point> queuePath = queuePaths.remove();
            // if all steps done check if target achieved
            if (queuePath.size() == movesNumber+1) {
                Point checkPoint = queuePath.get(queuePath.size()-1);
                if (isMoveTargetPoint(checkPoint.getX(), checkPoint.getY())) {
                    paths.add(queuePath);
                }
            } else {
                Point lastPoint = queuePath.get(queuePath.size()-1);
                List<Point> possibleMoves = getPossibleMoves(lastPoint);

                for (Point possibleMove : possibleMoves) {
                    List<Point> newPath = new ArrayList<>();
                    newPath.addAll(queuePath);
                    newPath.add(possibleMove);
                    queuePaths.add(newPath);
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
    private boolean isMoveTargetPoint(int x, int y) {
        return (x == targetX && y == targetY);
    }
}