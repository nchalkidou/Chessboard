package com.nchalkidou.chessexersise;

import android.util.Log;

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
    public List<List<int[]>> getPathToTarget(int targetX, int targetY, int movesNumber) {
        this.targetX = targetX;
        this.targetY = targetY;

        List<List<int[]>> paths = new ArrayList<>();


        //getPath(paths);

        List<int[]> firstMoves = getPossibleMoves();
        for (int[] firstMove : firstMoves) {

            List<int[]> secondMoves = getPossibleMoves(firstMove[0], firstMove[1]);
            for (int[] secondMove : secondMoves) {

                List<int[]> thirdMoves = getPossibleMoves(secondMove[0], secondMove[1]);
                for (int[] thirdMove : thirdMoves) {
                    if (isMoveEndPoint(thirdMove[0], thirdMove[1])){
                        List<int[]> path = new ArrayList<>();
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

   /* public void getPath(List<int[]> path, int movesNumber) {
        if (movesNumber == 0) {
            // todo: add to paths if and point
            return;
        }
        for (int[] move : path) {

        }
        List<int[]> stepMove = getPossibleMoves()

    }*/

    /**
     * Return the possible moves of knight from initial position
     * @return
     */
    private List<int[]> getPossibleMoves() {
        return getPossibleMoves(x, y);
    }

    /**
     * Return the possible moves (max 8 possible) of knight from specific position
     * @param fromX
     * @param fromY
     * @return
     */
    private List<int[]> getPossibleMoves(int fromX, int fromY) {
        List<int[]> moves = new ArrayList<>();

        // 1
        if (isMovePossible(fromX - 2, fromY - 1)) {
            int move[] = {fromX - 2, fromY - 1};
            moves.add(move);
        }
        // 2
        if (isMovePossible(fromX - 1, fromY - 2)) {
            int move[] = {fromX - 1, fromY - 2};
            moves.add(move);
        }
        // 3
        if (isMovePossible(fromX + 1, fromY - 2)) {
            int move[] = {fromX + 1, fromY - 2};
            moves.add(move);
        }
        // 4
        if (isMovePossible(fromX + 2, fromY - 1)) {
            int move[] = {fromX + 2, fromY - 1};
            moves.add(move);
        }
        // 5
        if (isMovePossible(fromX + 2, fromY + 1)) {
            int move[] = {fromX + 2, fromY + 1};
            moves.add(move);
        }
        // 6
        if (isMovePossible(fromX + 1, fromY + 2)) {
            int move[] = {fromX + 1, fromY + 2};
            moves.add(move);
        }
        // 7
        if (isMovePossible(fromX - 1, fromY + 2)) {
            int move[] = {fromX - 1, fromY + 2};
            moves.add(move);
        }
        // 8
        if (isMovePossible(fromX - 2, fromY + 1)) {
            int move[] = {fromX - 2, fromY + 1};
            moves.add(move);
        }

        return moves;
    }

    /**
     * Check if move is valid (in chessboard 8x8)
     * @param x
     * @param y
     * @return true if valid
     */
    private boolean isMovePossible(int x, int y) {
        return (x >= 0 && x < 8 && y >=0 && y < 8);
    }

    /**
     * Return true if is target point
     * @param x
     * @param y
     * @return true if equal to target
     */
    private boolean isMoveEndPoint(int x, int y) {
        return (x == targetX && y == targetY);
    }
}