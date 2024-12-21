package com.rytis.armw.ui.queue_controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rytis.armw.R;
import com.rytis.armw.ui.bracket.BracketMatchAdapter;
import com.rytis.armw.ui.bracket.BracketMatchModel;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class QueueTableAdapter extends RecyclerView.Adapter<QueueTableAdapter.QueueTableViewHolder> {

    private List<QueueModel.Queue_Table> tables;

    public QueueTableAdapter(List<QueueModel.Queue_Table> tables) {

        this.tables = tables;
    }

    @NonNull
    @Override
    public QueueTableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.queue_table_item , parent, false);
        return new QueueTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueueTableViewHolder holder, int position) {
        QueueModel.Queue_Table table = tables.get(position);

        holder.tableName.setText("Stalas"+ (1+table.getTableNumber()));
        holder.queue_round.setText("Roundas: " + table.getCurrentMatch().getRound());

        // Setup nested RecyclerView
        BracketMatchAdapter itemAdapter = new BracketMatchAdapter(table.getMatches(),true);
        holder.recyclerView.setAdapter(itemAdapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public static class QueueTableViewHolder extends RecyclerView.ViewHolder {
         TextView tableName;
         TextView queue_round;
         RecyclerView recyclerView;

        public QueueTableViewHolder(@NonNull View itemView) {
            super(itemView);
            tableName = itemView.findViewById(R.id.queue_table_name);
            recyclerView = itemView.findViewById(R.id.recycler_view_queue_table);
            queue_round = itemView.findViewById(R.id.queue_round);
        }
    }
}