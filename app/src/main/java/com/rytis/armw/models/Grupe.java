package com.rytis.armw.models;

public class Grupe {

    private String pavadinimas;
    private int id;
    private String svoris;
    private String amzius_k;
    private String lytis;
    private String ranka;
    private int turnyro_ID;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getTournamentId(){
        return turnyro_ID;
    }

    public void setTournamentId(int turnyro_ID){
        this.turnyro_ID = turnyro_ID;
    }


    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public String getSvoris() {
        return svoris;
    }

    public void setSvoris(String svoris) {
        this.svoris = svoris;
    }

    public String getAmzius_k() {
        return amzius_k;
    }

    public void setAmzius_k(String amzius_k) {
        this.amzius_k = amzius_k;
    }

    public String getLytis() {
        return lytis;
    }

    public void setLytis(String lytis) {
        this.lytis = lytis;
    }

    public String getRanka() {
        return ranka;
    }

    public void setRanka(String ranka) {
        this.ranka = ranka;
    }

    public String returnGrupe(){
        String name;
        String amzius = getAmzius_k();
        if(amzius!=null){
            return lytis + " " + amzius + " " + svoris + " " + ranka;
        }
        return lytis + " " + svoris + " " + ranka;
    }


}
