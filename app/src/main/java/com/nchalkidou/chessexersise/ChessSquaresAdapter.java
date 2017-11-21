package com.nchalkidou.chessexersise;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nchalkidou.chessexersise.databinding.SquareBinding;

import java.util.List;

public class ChessSquaresAdapter extends RecyclerView.Adapter<ChessSquaresAdapter.SquareViewHolder> {

    private final Context mContext;
    private List<Square> squares;

    final private ChessSquaresAdapterOnClickHandler mClickHandler;

    /**
     * Interface to receive clicks
     */
    public interface ChessSquaresAdapterOnClickHandler {
        void onClick(Square square);
    }

    public ChessSquaresAdapter(@NonNull Context context, ChessSquaresAdapterOnClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
    }

    /**
     * Update the list of squares in adapter
     * @param squares List of squares for chessboard
     */
    public void updateChessboard(List<Square> squares) {
        this.squares = squares;
        notifyDataSetChanged();
    }

    @Override
    public SquareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SquareBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.square, parent, false);
        return new SquareViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(SquareViewHolder holder, int position) {
        Square square = squares.get(position);
        if(square.isBlack()) {
            holder.mBinding.square.setBackgroundResource(R.color.colorBlackSquare);
        }else {
            holder.mBinding.square.setBackgroundResource(R.color.colorWhiteSquare);
        }
    }

    @Override
    public int getItemCount() {
        return (squares == null) ? 0 : squares.size();
    }

    /**
     * ViewHolder of Square
     */
    class SquareViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private SquareBinding mBinding;

        public SquareViewHolder(SquareBinding binding) {
            this(binding.getRoot());
            mBinding = binding;

            // set onOclick listener
            mBinding.getRoot().setOnClickListener(this);
        }

        public SquareViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {
            Square clickedSquare = squares.get(getAdapterPosition());
            mClickHandler.onClick(clickedSquare);
        }
    }
}
