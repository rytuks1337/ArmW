package com.rytis.armw.ui.tournaments;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.rytis.armw.R;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.auth.OnActionSuccessListener;
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
import java.util.TimerTask;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.myViewHolder>{
    private java.util.Timer timer;
    private TimerTask timerTask;
    private OnActionSuccessListener onChoiceSuccessListener;
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
        if (context instanceof OnActionSuccessListener) {
            onChoiceSuccessListener = (OnActionSuccessListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnLoginSuccessListener");
        }
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
        Retrofit_Pre preRetrofit = new Retrofit_Pre(context);

        Integer id = tournaments.getId();
        if(tournaments.getId() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(preRetrofit.getBaseUrl()+
                            "api/"+tournaments.getFilepath()) // Image loading with glide.
                    .into(holder.tournamentImage); // load into the holders imageview.
            holder.name.setText(tournaments.getPavadinimas());
            String status = tournaments.getStatus();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

            // Parse the string to a LocalDateTime object
            LocalDateTime parsedDate = LocalDateTime.parse(tournaments.getData(), formatter);

            // Get the current date and time
            if(status.equals("INIT")){
                holder.additional_info.setText(R.string.not_started);
                holder.date_casing.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.tournament_gradient_background_yellow));
            }
            LocalDateTime today = LocalDateTime.now();
            if(today.isBefore(parsedDate) && status.equals("SETUP")){
                Duration duration = Duration.between(today, parsedDate);
                long days = duration.toDays();
                long hours = duration.toHours() % 24;
                long minutes = duration.toMinutes() % 60;
                holder.additional_info.setText(String.format(Locale.getDefault(),"Time left: %02d Days, %02d:%02d", days, hours, minutes));
                holder.date_casing.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.tournament_gradient_background_green));
            }
            if(status.equals("REGISTER")){
                holder.date_casing.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.tournament_gradient_background_red));
            }
            if(status.equals("IN_PROCCESS")){
                holder.additional_info.setText(R.string.currently_in_progress);
                holder.date_casing.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.tournament_gradient_background_blue));
            }
            if(status.equals("FINISHED")){
                holder.additional_info.setText(R.string.has_ended);
                holder.date_casing.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.tournament_gradient_background_grey));
            }

            String number_of_days = String.valueOf(parsedDate.getDayOfMonth());
            if(number_of_days.length() == 1){
                holder.date_number.setText("0"+number_of_days);
            }else{
                holder.date_number.setText(number_of_days);
            }
            holder.date_month.setText(getMonthNameInLithuanian(String.valueOf(Integer.valueOf(tournaments.getData().split("-")[1]))).substring(0,3).toUpperCase(Locale.getDefault()));


            holder.container_layout.setOnClickListener(v -> {
                Intent i = new Intent(context, tournament_details_view.class);

                i.putExtra("id",tournaments.getId());
                i.putExtra("status",tournaments.getStatus());
                i.putExtra("data",tournaments.getData());
                i.putExtra("filepath",tournaments.getFilepath());
                i.putExtra("pabaiga",tournaments.getPabaiga());
                i.putExtra("lokacija",tournaments.getLokacija());
                i.putExtra("aprasas",tournaments.getAprasas());
                i.putExtra("pavadinimas",tournaments.getPavadinimas());
                //startActivity(context, i, null);
                onChoiceSuccessListener.onChoiceSuccess(i);

            });
        }

    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        startTimer();
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        stopTimer();
    }

    private void startTimer() {
        timer = new java.util.Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                // Update UI on the main thread
                ((Activity) context).runOnUiThread(() -> notifyDataSetChanged());
            }
        };
        timer.schedule(timerTask, 0, 60000); // Update every 60 seconds
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public int getItemCount() {
        return tournament_list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView date_month, date_number, name, additional_info;
        LinearLayout date_casing;
        ImageView tournamentImage;
        MaterialCardView container_layout;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            date_month = itemView.findViewById(R.id.tvMonth);
            date_number = itemView.findViewById(R.id.tvDay);
            name = itemView.findViewById(R.id.tv_tournament_name);
            date_casing = itemView.findViewById(R.id.tvLayout);
            additional_info = itemView.findViewById(R.id.tv_additional_information);
            container_layout = itemView.findViewById(R.id.layout_tournament);
            tournamentImage = itemView.findViewById(R.id.tournament_image);
        }
    }

}
