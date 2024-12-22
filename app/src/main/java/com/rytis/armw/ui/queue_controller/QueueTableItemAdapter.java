package com.rytis.armw.ui.queue_controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rytis.armw.R;
import com.rytis.armw.ui.bracket.BracketMatchAdapter;
import com.rytis.armw.ui.bracket.BracketMatchModel;

import java.util.List;

public class QueueTableItemAdapter extends RecyclerView.Adapter<QueueTableItemAdapter.QueueTableViewHolder> {
    private final List<Queue_Table.GroupQueue.MatchQueue> matches;
    private final Boolean withoutWinners;

    public QueueTableItemAdapter(List<Queue_Table.GroupQueue.MatchQueue> matches, Boolean withoutWinners){
        this.matches = matches;
        this.withoutWinners = withoutWinners;
    }

    @Override
    public QueueTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matchvs_item, parent, false);
        return new QueueTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueueTableViewHolder holder, int position) {
        Queue_Table.GroupQueue.MatchQueue match = matches.get(position);
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

    public static class QueueTableViewHolder extends RecyclerView.ViewHolder {
        TextView player1TextView, player2TextView, winnerTextView;

        public QueueTableViewHolder(View itemView) {
            super(itemView);
            player1TextView = itemView.findViewById(R.id.text_player1);
            player2TextView = itemView.findViewById(R.id.text_player2);
            winnerTextView = itemView.findViewById(R.id.text_winner);

        }
    }
}
