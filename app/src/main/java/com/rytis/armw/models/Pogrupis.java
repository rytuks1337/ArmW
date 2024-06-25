package com.rytis.armw.models;

public class Pogrupis {
    public Pogrupis(String pavadinimas, int svoris, int amzius_k, String lytis, String ranka) {
        this.pavadinimas = pavadinimas;
        this.svoris = svoris;
        this.amzius_k = amzius_k;
        this.lytis = lytis;
        this.ranka = ranka;
    }

    private String pavadinimas;
    private int svoris;
    private int amzius_k;
    private String lytis;
    private String ranka;

    public Pogrupis() {

    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public int getSvoris() {
        return svoris;
    }

    public void setSvoris(int svoris) {
        this.svoris = svoris;
    }

    public int getAmzius_k() {
        return amzius_k;
    }

    public void setAmzius_k(int amzius_k) {
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


}
