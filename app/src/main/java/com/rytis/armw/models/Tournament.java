package com.rytis.armw.models;

public class Tournament {



    public String name;

    public Tournament(String name, String date, String status, String time_to, String description, Integer ID) {
        this.id = ID;
        this.name = name;
        this.date = date;
        this.status = status;
        this.time_to = time_to;
        this.description = description;

    }

    public String date;
    public String status, time_to ;
    public String description;
    public Integer id;

}
