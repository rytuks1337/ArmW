package com.rytis.armw.models;

public class Tournament {



    public String name;
    public String date_day;
    public String date_month;
    public int date_n;


    public Tournament(String date_month, String date_day, String name) {
        this.date_month = date_month;
        this.date_day = date_day;
        this.name = name;
    }



}
