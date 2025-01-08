package com.rytis.armw.ui.queue_controller;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rytis.armw.R;
import com.rytis.armw.models.Grupe;
import com.rytis.armw.models.GrupeList;
import com.rytis.armw.ui.bracket.BracketActivity;

import java.util.ArrayList;
import java.util.List;

public class GroupBasicListAdaptor extends RecyclerView.Adapter<GroupBasicListAdaptor.GroupBasicListViewHolder> {

    Context context;
    List<GrupeList.GrupeBasic> grupeBasicList;

    public GroupBasicListAdaptor(Context context, List<GrupeList.GrupeBasic> grupeList) {
        this.context = context;
        this.grupeBasicList = grupeList;
    }

    @NonNull
    @Override
    public GroupBasicListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        return new GroupBasicListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupBasicListViewHolder holder, int position) {
        GrupeList.GrupeBasic group = grupeBasicList.get(position);
        ArrayList<Grupe> grupes = new ArrayList<>(group.getGrupes());
        holder.groupNameTextView.setText(group.getPavadinimas());

        String details = group.getLytis();
        holder.groupDetailsTextView.setText(details);
        holder.bracketDownloadButton.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Kategorijos2f.class);
            intent.putExtra("tournamentList", grupes);

            startActivity(context,intent,null);
        });
    }

    @Override
    public int getItemCount() {
        return grupeBasicList.size();
    }

    public static class GroupBasicListViewHolder extends RecyclerView.ViewHolder {

        TextView groupNameTextView;
        TextView groupDetailsTextView;
        ImageView bracketDownloadButton;

        public GroupBasicListViewHolder(@NonNull View itemView) {
            super(itemView);
            groupNameTextView = itemView.findViewById(R.id.groupNameTextView);
            groupDetailsTextView = itemView.findViewById(R.id.groupDetailsTextView);
            bracketDownloadButton = itemView.findViewById(R.id.imageDownloadBracket);
        }
    }
}
