package com.nchalkidou.chessexersise;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChessboardViewModelTest {

    private ChessboardViewModel chessboardViewModel;

    @Before
    public void setupViewModel() {
        chessboardViewModel = new ChessboardViewModel();
    }

    @Test
    public void setupChessboardSquares() throws Exception {
        List<Square> squares = chessboardViewModel.createSquares();
        assertEquals(squares.size(), 8*8);
    }

    @Test
    public void setStartPosition() {
        List<Square> squares = chessboardViewModel.getSquares();

        chessboardViewModel.setStartSquare(4);
        assertTrue(squares.get(4).isStartPoint());
        assertTrue(chessboardViewModel.getStartSquare().isStartPoint());
    }

    @Test
    public void setTargetPosition() {
        List<Square> squares = chessboardViewModel.getSquares();

        chessboardViewModel.setTargetSquare(7);
        assertTrue(squares.get(7).isTargetPoint());
        assertTrue(chessboardViewModel.getTargetSquare().isTargetPoint());
    }

    @Test
    public void getKnightPossiblePaths_isCorrect() {
        // check e8 to h8
        chessboardViewModel.setStartSquare(4);
        chessboardViewModel.setTargetSquare(7);
        List<List<String>> paths = chessboardViewModel.getKnightPossiblePaths();
        assertEquals(paths.get(0).get(0), "e8");
        assertEquals(paths.get(0).get(1), "d6");
        assertEquals(paths.get(0).get(2), "f7");
        assertEquals(paths.get(0).get(3), "h8");

        // check with not possible paths (a8 to a8)
        chessboardViewModel.setStartSquare(0);
        chessboardViewModel.setTargetSquare(0);
        paths = chessboardViewModel.getKnightPossiblePaths();
        assertTrue(paths.isEmpty());

        // check with not possible paths (a8 to h1)
        chessboardViewModel.setStartSquare(0);
        chessboardViewModel.setTargetSquare(63);
        paths = chessboardViewModel.getKnightPossiblePaths();
        assertTrue(paths.isEmpty());

        // check a8 to c7
        chessboardViewModel.setStartSquare(0);
        chessboardViewModel.setTargetSquare(10);
        paths = chessboardViewModel.getKnightPossiblePaths();

        String[][] testPoints = {
                {"a8", "c7", "a8", "c7"},
                {"a8", "c7", "e8", "c7"},
                {"a8", "c7", "e6", "c7"},
                {"a8", "c7", "d5", "c7"},
                {"a8", "c7", "b5", "c7"},
                {"a8", "c7", "a6", "c7"},
                {"a8", "b6", "a8", "c7"},
                {"a8", "b6", "d5", "c7"}};

        for (int i=0; i<paths.size(); i++) {
            List<String> path = paths.get(i);
            for (int j=0; j<path.size(); j++) {
                assertEquals(path.get(j), testPoints[i][j]);
            }
        }
    }
}
