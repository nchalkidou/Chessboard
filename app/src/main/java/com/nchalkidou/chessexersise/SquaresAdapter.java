package com.nchalkidou.chessexersise;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nchalkidou.chessexersise.databinding.SquareBinding;

import java.util.List;

public class SquaresAdapter extends RecyclerView.Adapter<SquaresAdapter.SquareViewHolder> {

    private final Context mContext;
    private List<Square> squares;

    final private SquaresAdapterOnClickHandler mClickHandler;

    /**
     * Interface to receive clicks
     */
    public interface SquaresAdapterOnClickHandler {
        void onClick(int position);
    }

    public SquaresAdapter(@NonNull Context context, SquaresAdapterOnClickHandler clickHandler) {
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
        // set start or target
        if (square.isStartPoint()) {
            holder.mBinding.startSquareLabel.setText("S");
        } else {
            holder.mBinding.startSquareLabel.setText("");
        }
        if (square.isTargetPoint()) {
            holder.mBinding.targetSquareLabel.setText("T");
        } else {
            holder.mBinding.targetSquareLabel.setText("");
            holder.mBinding.startSquareLabel.setGravity(Gravity.CENTER);
            holder.mBinding.targetSquareLabel.setGravity(Gravity.CENTER);
        }
        if (square.isStartPoint() && square.isTargetPoint()){
            holder.mBinding.startSquareLabel.setGravity(Gravity.LEFT);
            holder.mBinding.targetSquareLabel.setGravity(Gravity.RIGHT);
        }

        // set color of square and label
        if(square.isBlack()) {
            holder.mBinding.square.setBackgroundResource(R.color.colorBlackSquare);
            holder.mBinding.startSquareLabel.setTextColor(mContext.getResources().getColor(R.color.colorWhiteSquare));
            holder.mBinding.targetSquareLabel.setTextColor(mContext.getResources().getColor(R.color.colorWhiteSquare));
        }else {
            holder.mBinding.square.setBackgroundResource(R.color.colorWhiteSquare);
            holder.mBinding.startSquareLabel.setTextColor(mContext.getResources().getColor(R.color.colorBlackSquare));
            holder.mBinding.targetSquareLabel.setTextColor(mContext.getResources().getColor(R.color.colorBlackSquare));
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
            mClickHandler.onClick(getAdapterPosition());
        }
    }
}
