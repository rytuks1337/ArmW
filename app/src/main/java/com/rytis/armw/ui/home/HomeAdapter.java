package com.rytis.armw.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rytis.armw.R;
import com.rytis.armw.models.Tournament;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.myViewHolder>{

    Context context;
    ArrayList<Tournament> tournament_list;
    public HomeAdapter(Context context, ArrayList<Tournament> tournament_list) {

        this.context = context;
        this.tournament_list=tournament_list;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.fragment_tournament_model, parent, false);



        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Tournament tournaments = tournament_list.get(position);
        holder.name.setText(tournaments.name);
        holder.date_n.setText(tournaments.date_day);
        holder.date_m.setText(tournaments.date_month);
        holder.date_casing.setBackgroundColor(context.getResources().getColor(R.color.green, null));

    }

    @Override
    public int getItemCount() {
        return tournament_list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView date_m, date_n, name;
        LinearLayout date_casing;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            date_m = itemView.findViewById(R.id.tvMonth);
            date_n = itemView.findViewById(R.id.tvDay);
            name = itemView.findViewById(R.id.tv_tournament_name);
            date_casing = itemView.findViewById(R.id.tvLayout);
        }
    }
}
