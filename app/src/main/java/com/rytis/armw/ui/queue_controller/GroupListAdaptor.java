package com.rytis.armw.ui.queue_controller;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rytis.armw.R;
import com.rytis.armw.models.Grupe;
import com.rytis.armw.ui.bracket.BracketActivity;

import java.util.List;

public class GroupListAdaptor extends RecyclerView.Adapter<GroupListAdaptor.MyGroupListViewHolder> {

    Context context;
    List<Grupe> grupeList;

    public GroupListAdaptor(Context context, List<Grupe> grupeList) {
        this.context = context;
        this.grupeList = grupeList;
    }

    @NonNull
    @Override
    public MyGroupListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        return new MyGroupListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGroupListViewHolder holder, int position) {
        Grupe group = grupeList.get(position);
        holder.groupNameTextView.setText(group.getPavadinimas());

        String details = group.returnGrupe();
        holder.groupDetailsTextView.setText(details);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BracketActivity.class);
            intent.putExtra("tournamentId", group.getTournamentId());
            intent.putExtra("groupId", group.getId());
            startActivity(context,intent,null);
        });
    }

    @Override
    public int getItemCount() {
        return grupeList.size();
    }

    public static class MyGroupListViewHolder extends RecyclerView.ViewHolder {

        TextView groupNameTextView;
        TextView groupDetailsTextView;

        public MyGroupListViewHolder(@NonNull View itemView) {
            super(itemView);
            groupNameTextView = itemView.findViewById(R.id.groupNameTextView);
            groupDetailsTextView = itemView.findViewById(R.id.groupDetailsTextView);
        }
    }
}
