package com.rytis.armw.models;

import java.util.List;

public class Varzybos {

    private String pavadinimas;

    public Varzybos(String pavadinimas, String data, String pradzia, String pabaiga, String lokacija, int stalu_sk, String aprasas, List<Pogrupis> pogrupis_sarasas) {
        this.pavadinimas = pavadinimas;
        this.data = data;
        this.pradzia = pradzia;
        this.pabaiga = pabaiga;
        this.lokacija = lokacija;
        this.stalu_sk = stalu_sk;
        this.aprasas = aprasas;
        this.pogrupis_sarasas = pogrupis_sarasas;
    }

    public Varzybos() {

    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPradzia() {
        return pradzia;
    }

    public void setPradzia(String pradzia) {
        this.pradzia = pradzia;
    }

    public String getPabaiga() {
        return pabaiga;
    }

    public void setPabaiga(String pabaiga) {
        this.pabaiga = pabaiga;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public int getStalu_sk() {
        return stalu_sk;
    }

    public void setStalu_sk(int stalu_sk) {
        this.stalu_sk = stalu_sk;
    }

    public String getAprasas() {
        return aprasas;
    }

    public void setAprasas(String aprasas) {
        this.aprasas = aprasas;
    }

    public List<Pogrupis> getPogrupis_sarasas() {
        return pogrupis_sarasas;
    }

    public void setPogrupis_sarasas(List<Pogrupis> pogrupis_sarasas) {
        this.pogrupis_sarasas = pogrupis_sarasas;
    }

    private String data;
    private String pradzia;
    private String pabaiga;
    private String lokacija;
    private int stalu_sk;
    private String aprasas;
    private List<Pogrupis> pogrupis_sarasas;
}
