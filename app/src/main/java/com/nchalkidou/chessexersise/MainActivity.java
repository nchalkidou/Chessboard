package com.nchalkidou.chessexersise;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SquaresAdapter.SquaresAdapterOnClickHandler {

    public RecyclerView mChessboardView;
    private SquaresAdapter mSquaresAdapter;
    private ChessboardViewModel mChessboardViewModel;

    private TextView startPointValue, targetPointValue, pathsValue;

    private ListView pathsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create of get the instance of the view model
        mChessboardViewModel = ViewModelProviders.of(this).get(ChessboardViewModel.class);

        startPointValue = (TextView) findViewById(R.id.startPointValue);
        targetPointValue = (TextView) findViewById(R.id.targetPointValue);
        pathsValue = (TextView) findViewById(R.id.pathsValue);
        pathsList = (ListView) findViewById(R.id.pathsList);

        // Init / create the mChessboard
        createChessboard();
        displayStartPosition();
        displayTargetPosition();
        showKnightPossiblePaths();
    }

    @Override
    public void onClick(int position) {

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
     * Show the possible paths in list view
     */
    private void showKnightPossiblePaths() {
        String[] paths = mChessboardViewModel.getKnightPossiblePathsArray();

        if (paths != null && paths.length > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    paths);
            pathsList.setAdapter(adapter);
            // todo: maybe add animated paths on click
            pathsList.setVisibility(View.VISIBLE);
            pathsValue.setVisibility(View.INVISIBLE);
        } else {
            pathsValue.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Reset the selected positions
     */
    public void reset(View view) {
        // reset labels
        startPointValue.setText("");
        targetPointValue.setText("");
        pathsValue.setVisibility(View.INVISIBLE);
        pathsList.setVisibility(View.INVISIBLE);

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
