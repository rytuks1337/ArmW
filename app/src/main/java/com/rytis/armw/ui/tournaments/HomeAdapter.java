package com.rytis.armw.ui.tournaments;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.rytis.armw.R;
import com.rytis.armw.dataModels.TournamentModel;
import com.rytis.armw.tournament.tournament_details_view;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.threeten.bp.Duration;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import com.jakewharton.threetenabp.AndroidThreeTen;

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

    ArrayList<TournamentModel.TournamentRespGetData.TournamentData> tournament_list;
    public HomeAdapter(Context context, ArrayList<TournamentModel.TournamentRespGetData.TournamentData> tournament_list) {

        this.context = context;
        this.tournament_list=tournament_list;
        AndroidThreeTen.init(context);

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.fragment_tournament_model, parent, false);



        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        TournamentModel.TournamentRespGetData.TournamentData tournaments = tournament_list.get(position);
        Integer id = tournaments.getId();
        if(tournaments.getId() != null) {
            holder.name.setText(tournaments.getPavadinimas());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

            // Parse the string to a LocalDateTime object
            LocalDateTime parsedDate = LocalDateTime.parse(tournaments.getData(), formatter);

            // Get the current date and time
            LocalDateTime today = LocalDateTime.now();
            if(today.isBefore(parsedDate)){
                Duration duration = Duration.between(today, parsedDate);
                long days = duration.toDays();
                long hours = duration.toHours() % 24;
                long minutes = duration.toMinutes() % 60;
                long seconds = duration.getSeconds() % 60;
                holder.additional_info.setText(String.format(Locale.getDefault(),"Time left: %02d:%02d:%02d:%02d", days, hours, minutes, seconds));
            }else{
                holder.additional_info.setText("Tournament has started");
            }



            holder.date_number.setText(String.valueOf(Integer.valueOf(tournaments.getData().split("-")[2].split("T")[0])));
            holder.date_month.setText(getMonthNameInLithuanian(String.valueOf(Integer.valueOf(tournaments.getData().split("-")[1]))).substring(0,3).toUpperCase(Locale.getDefault()));
            //holder.date_casing.setBackgroundColor(context.getResources().getColor(R.color.green, null));
            //holder.date_casing.seet(context.getResources().getColor(R.color.green, null));

            holder.container_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, tournament_details_view.class);

                    i.putExtra("id",tournaments.getId());
                    i.putExtra("data",tournaments.getData());
                    i.putExtra("pabaiga",tournaments.getPabaiga());
                    i.putExtra("lokacija",tournaments.getLokacija());
                    i.putExtra("aprasas",tournaments.getAprasas());
                    i.putExtra("pavadinimas",tournaments.getPavadinimas());
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

        TextView date_month, date_number, name, additional_info;
        LinearLayout date_casing;
        MaterialCardView container_layout;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            date_month = itemView.findViewById(R.id.tvMonth);
            date_number = itemView.findViewById(R.id.tvDay);
            name = itemView.findViewById(R.id.tv_tournament_name);
            date_casing = itemView.findViewById(R.id.tvLayout);
            additional_info = itemView.findViewById(R.id.tv_additional_information);
            container_layout = itemView.findViewById(R.id.layout_tournament);
        }
    }
}
