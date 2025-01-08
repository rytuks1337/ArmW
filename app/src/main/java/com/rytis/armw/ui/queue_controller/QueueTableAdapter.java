package com.rytis.armw.ui.queue_controller;

import static android.app.Activity.RESULT_OK;
import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rytis.armw.R;
import com.rytis.armw.ui.tournaments.control.judge.RefControl;

import java.util.List;
import java.util.Objects;

public class QueueTableAdapter extends RecyclerView.Adapter<QueueTableAdapter.QueueTableViewHolder> {

    private List<Queue_Table> tables;
    private RefreshListener refreshListener;
    private ActivityResultLauncher<Intent> resultLauncher;//For result from created referee management activity
    private WresQueue parent;

    public QueueTableAdapter(List<Queue_Table> tables, RefreshListener refreshListener, WresQueue parent) {
        this.refreshListener = refreshListener;
        this.tables = tables;
        this.parent = parent;
        resultLauncher = this.parent.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            refreshListener.forceRefresh();
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    String resultValue = data.getStringExtra("instructions");
                    if(Objects.equals(resultValue, "refresh")){
                        //Open same table for modification.
                        refreshListener.forceRefresh();
                    }else{
                        refreshListener.onRefreshToggle(true);
                    }
                }
            }
            refreshListener.onRefreshToggle(true);
        });
    }

    @NonNull
    @Override
    public QueueTableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.queue_table_item , parent, false);
        return new QueueTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueueTableViewHolder holder, int position) {
        Queue_Table table = tables.get(position);
        System.out.println(table.getId());
        holder.tableName.setText("Stalas "+ (1+table.getTableNumber()));
        holder.queue_round.setText("Raundas: " + (1+table.getCurrentMatch().getRound()));
        holder.group_name.setText(table.getCurrentMatch().getGroupName());

        // Setup nested RecyclerView
        QueueTableItemAdapter itemAdapter = new QueueTableItemAdapter(table.getCurrentMatch().getMatches(),true);
        holder.recyclerView.setAdapter(itemAdapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));

        holder.itemView.setOnClickListener(v -> {
            refreshListener.onRefreshToggle(false);
            Intent intent = new Intent(holder.itemView.getContext(), RefControl.class);
            intent.putExtra("id", table.getCurrentMatch().getMatches().get(0).getId());
            intent.putExtra("dalyvio_id", table.getCurrentMatch().getMatches().get(0).getPlayer1Id());
            intent.putExtra("dalyvio2_id", table.getCurrentMatch().getMatches().get(0).getPlayer2Id());
            intent.putExtra("dalyvio1_name", table.getCurrentMatch().getMatches().get(0).getPlayer1());
            intent.putExtra("dalyvio2_name", table.getCurrentMatch().getMatches().get(0).getPlayer2());
            intent.putExtra("tournamentID", table.getTournamentId());
            intent.putExtra("round", table.getCurrentMatch().getRound());
            resultLauncher.launch(intent);
        });


    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public static class QueueTableViewHolder extends RecyclerView.ViewHolder {
         TextView tableName;
         TextView queue_round;
         TextView group_name;
         RecyclerView recyclerView;

        public QueueTableViewHolder(@NonNull View itemView) {
            super(itemView);
            tableName = itemView.findViewById(R.id.queue_table_name);
            recyclerView = itemView.findViewById(R.id.recycler_view_queue_table);
            queue_round = itemView.findViewById(R.id.queue_round);
            group_name = itemView.findViewById(R.id.queue_table_group_name);
        }
    }
}