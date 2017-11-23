package com.nchalkidou.chessexersise;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SquaresAdapter.SquaresAdapterOnClickHandler {

    public RecyclerView mChessboardView;
    private SquaresAdapter mSquaresAdapter;
    private ChessboardViewModel mChessboardViewModel;

    private TextView startPointValue, targetPointValue, pathsValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create of get the instance of the view model
        mChessboardViewModel = ViewModelProviders.of(this).get(ChessboardViewModel.class);

        startPointValue = (TextView) findViewById(R.id.startPointValue);
        targetPointValue = (TextView) findViewById(R.id.targetPointValue);
        pathsValue = (TextView) findViewById(R.id.pathsValue);

        // Init / create the mChessboard
        createChessboard();
        displayStartPosition();
        displayTargetPosition();
        showKnightPossiblePaths();
    }

    @Override
    public void onClick(int position) {
        Square square = mChessboardViewModel.getSquares().get(position);

        if (mChessboardViewModel.getStartSquare() == null) {
            mChessboardViewModel.setStartSquare(position);
            mSquaresAdapter.updateChessboard(mChessboardViewModel.getSquares());

            displayStartPosition();
        } else if (mChessboardViewModel.getTargetSquare() == null) {
            mChessboardViewModel.setTargetSquare(position);
            mSquaresAdapter.updateChessboard(mChessboardViewModel.getSquares());

            displayTargetPosition();

            // trigger on target square selected
            showKnightPossiblePaths();
        }
    }

    private void displayStartPosition() {
        if (mChessboardViewModel.getStartSquare() != null) {
            startPointValue.setText(mChessboardViewModel.getStartSquare().getIdentifierName());
        }
    }

    private void displayTargetPosition() {
        if (mChessboardViewModel.getTargetSquare() != null) {
            targetPointValue.setText(mChessboardViewModel.getTargetSquare().getIdentifierName());
        }
    }

    /**
     * Show the possible paths
     */
    private void showKnightPossiblePaths() {
        List<List<String>> paths = mChessboardViewModel.getKnightPossiblePaths();

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
        mChessboardViewModel.resetChessboard();
        mSquaresAdapter.updateChessboard(mChessboardViewModel.getSquares());
    }

    /**
     * Init the RecyclerView - Chessboard and populate it with squares
     */
    private void createChessboard() {
        // RecyclerView - chessboard view
        mChessboardView = (RecyclerView) findViewById(R.id.chessboard);
        mChessboardView.setLayoutManager(new GridLayoutManager(this, 8));
        mChessboardView.setHasFixedSize(true);

        // create the adapter and populate with squares
        mSquaresAdapter = new SquaresAdapter(this, this);
        mSquaresAdapter.updateChessboard(mChessboardViewModel.getSquares());

        mChessboardView.setAdapter(mSquaresAdapter);
    }


}
