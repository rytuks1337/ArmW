package com.rytis.armw.ui.tournaments;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rytis.armw.R;
import com.rytis.armw.dataModels.TournamentModel;
import com.rytis.armw.tournament.tournament_details_view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.myViewHolder>{

    public String getMonthNameInLithuanian(String monthNumber) {
        try {
            // Parse the month number to a Date object
            SimpleDateFormat monthParse = new SimpleDateFormat("MM", Locale.getDefault());
            Date date = monthParse.parse(monthNumber);

            // Format the Date object to get the month name in Lithuanian
            SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM", new Locale("lt"));
            return monthDisplay.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    Context context;

    ArrayList<TournamentModel.TournamentRespGetData> tournament_list;
    public HomeAdapter(Context context, ArrayList<TournamentModel.TournamentRespGetData> tournament_list) {

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
        TournamentModel.TournamentRespGetData tournaments = tournament_list.get(position);
        Integer id = tournaments.getId();
        if(tournaments.getId() != null) {
            holder.name.setText(tournaments.getPavadinimas());
            holder.date_n.setText(String.valueOf(Integer.valueOf(tournaments.getData().split("-")[2].split("T")[0])));
            holder.date_m.setText(getMonthNameInLithuanian(String.valueOf(Integer.valueOf(tournaments.getData().split("-")[1]))));
            holder.date_casing.setBackgroundColor(context.getResources().getColor(R.color.green, null));

            holder.container_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, tournament_details_view.class);

                    i.putExtra("id",tournaments.getId());
                    i.putExtra("data",tournaments.getData());
                    i.putExtra("pradzia",tournaments.getPradzia());
                    i.putExtra("pabaiga",tournaments.getPabaiga());
                    i.putExtra("aprasas",tournaments.getAprasas());
                    i.putExtra("pavadinimas",tournaments.getPavadinimas());
                    i.putExtra("organizatoriusvartotojo_id",tournaments.getOrganizatoriusvartotojo_id());
                    startActivity(context, i, null);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return tournament_list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView date_m, date_n, name;
        LinearLayout date_casing, container_layout;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            date_m = itemView.findViewById(R.id.tvMonth);
            date_n = itemView.findViewById(R.id.tvDay);
            name = itemView.findViewById(R.id.tv_tournament_name);
            date_casing = itemView.findViewById(R.id.tvLayout);
            container_layout = itemView.findViewById(R.id.layout_tournament);
        }
    }
}
