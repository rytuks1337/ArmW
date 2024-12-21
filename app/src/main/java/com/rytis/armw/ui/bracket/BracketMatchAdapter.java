package com.rytis.armw.ui.bracket;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rytis.armw.R;

import java.util.ArrayList;
import java.util.List;

public class BracketMatchAdapter extends RecyclerView.Adapter<BracketMatchAdapter.MatchViewHolder> {
    private final List<BracketMatchModel> matches;
    private final Boolean withoutWinners;

    public BracketMatchAdapter(List<BracketMatchModel> matches, Boolean withoutWinners) {
        this.matches = matches;
        this.withoutWinners = withoutWinners;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matchvs_item, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {
        BracketMatchModel match = matches.get(position);
        holder.player1TextView.setText(match.getPlayer1());
        holder.player2TextView.setText(match.getPlayer2());
        if (withoutWinners) {
            holder.winnerTextView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        TextView player1TextView, player2TextView, winnerTextView;

        public MatchViewHolder(View itemView) {
            super(itemView);
            player1TextView = itemView.findViewById(R.id.text_player1);
            player2TextView = itemView.findViewById(R.id.text_player2);
            winnerTextView = itemView.findViewById(R.id.text_winner);

        }
    }
}