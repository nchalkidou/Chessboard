package com.nchalkidou.chessexersise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ChessSquaresAdapter.ChessSquaresAdapterOnClickHandler {

    private RecyclerView mChessboardView;
    private ChessSquaresAdapter mSquaresAdapter;
    private Chessboard mChessboard;

    private TextView startPointValue, targetPointValue, pathsValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startPointValue = (TextView) findViewById(R.id.startPointValue);
        targetPointValue = (TextView) findViewById(R.id.targetPointValue);
        pathsValue = (TextView) findViewById(R.id.pathsValue);

        // Init / create the mChessboard
        createChessboard();
    }

    @Override
    public void onClick(int position) {
        Square square = mChessboard.getSquares().get(position);

        if (!mChessboard.hasStartSquare()) {
            mChessboard.setStartSquare(position);
            mSquaresAdapter.updateChessboard(mChessboard.getSquares());

            startPointValue.setText(square.getIdentifierName());
        } else if (!mChessboard.hasEndSquare()) {
            mChessboard.setEndSquare(position);
            mSquaresAdapter.updateChessboard(mChessboard.getSquares());

            targetPointValue.setText(square.getIdentifierName());

            // trigger on target square selected
            showKnightPossiblePaths();
        }
    }

    /**
     * Show the possible paths
     */
    private void showKnightPossiblePaths() {
        List<List<String>> paths = mChessboard.getKnightPossiblePaths();

        if (paths != null && !paths.isEmpty()){
            int countPath = 1;

            for (List<String> path : paths) {
                String pathString = countPath + ". \t";

                for (int i=0; i<path.size(); i++) {
                    if (path.size()-1 == i)
                        pathString += path.get(i);
                    else
                        pathString += path.get(i) +" - ";
                }

                pathsValue.append(pathString + "\n");
                countPath++;
            }
        } else {
            pathsValue.setText(this.getResources().getString(R.string.noPathsAvailable));
        }
    }

    /**
     * Reset the selected positions
     */
    public void reset(View view) {
        // reset labels
        startPointValue.setText("");
        targetPointValue.setText("");
        pathsValue.setText("");

        // reset chessboard squares
        mChessboard.resetChessboard();
        mSquaresAdapter.updateChessboard(mChessboard.getSquares());
    }

    /**
     * Init the RecyclerView - Chessboard and populate it with squares
     */
    private void createChessboard() {
        // RecyclerView - chessboard view
        mChessboardView = (RecyclerView) findViewById(R.id.chessboard);
        mChessboardView.setLayoutManager(new GridLayoutManager(this, 8));
        mChessboardView.setHasFixedSize(true);

        // chessboard
        mChessboard = new Chessboard();

        // create the adapter and populate with squares
        mSquaresAdapter = new ChessSquaresAdapter(this, this);
        mSquaresAdapter.updateChessboard(mChessboard.getSquares());

        mChessboardView.setAdapter(mSquaresAdapter);
    }


}
