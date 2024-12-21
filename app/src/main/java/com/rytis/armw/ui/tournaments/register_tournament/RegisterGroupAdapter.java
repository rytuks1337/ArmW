package com.rytis.armw.ui.tournaments.register_tournament;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rytis.armw.R;
import com.rytis.armw.models.Grupe;

import java.util.Collections;
import java.util.List;

public class RegisterGroupAdapter extends RecyclerView.Adapter<RegisterGroupAdapter.GroupViewHolder> implements SimpleItemTouchCallback.ItemTouchHelperAdapter {

    private List<Grupe> groupList;

    public RegisterGroupAdapter(List<Grupe> groupList) {
        this.groupList = groupList;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        Grupe group = groupList.get(position);
        holder.groupNameTextView.setText(group.getPavadinimas());

        String details = group.returnGrupe();
        holder.groupDetailsTextView.setText(details);
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder {
        public TextView groupNameTextView;
        public TextView groupDetailsTextView;

        public GroupViewHolder(View itemView) {
            super(itemView);
            groupNameTextView = itemView.findViewById(R.id.groupNameTextView);
            groupDetailsTextView = itemView.findViewById(R.id.groupDetailsTextView);
        }
    }
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(groupList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(groupList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }
}
