package com.nchalkidou.chessexersise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChessSquaresAdapter.ChessSquaresAdapterOnClickHandler {

    private RecyclerView chessboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init / create the chessboard
        createChessboard();
    }

    @Override
    public void onClick(Square square) {
        Toast.makeText(this, square.toString(), Toast.LENGTH_LONG).show();
    }

    /**
     * Init the RecyclerView - Chessboard and populate it with squares
     */
    private void createChessboard() {
        chessboard = (RecyclerView) findViewById(R.id.chessboard);
        chessboard.setLayoutManager(new GridLayoutManager(this, 8));
        chessboard.setHasFixedSize(true);

        // create the adapter and populate with squares
        ChessSquaresAdapter adapter = new ChessSquaresAdapter(this, this);
        adapter.updateChessboard(getChessboardSquares());

        chessboard.setAdapter(adapter);
    }

    /**
     * Create 64(8x8) squares and set white and black squares
     * @return return the list of the created squares
     */
    private List<Square> getChessboardSquares() {
        List<Square> squares = new ArrayList<>();

        for (int y=0; y<8; y++) {
            for (int x=0; x<8; x++) {
                Square square = new Square();
                square.setCoordX(x);
                square.setCoordY(y);
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
