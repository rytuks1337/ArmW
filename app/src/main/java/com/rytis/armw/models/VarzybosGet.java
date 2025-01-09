package com.rytis.armw.models;

import java.util.List;

public class VarzybosGet {
    private int id;
    private String pavadinimas;
    private String status;
    private String data;
    private String pabaiga;
    private String lokacija;
    private int stalu_sk;
    private String aprasas;

    public VarzybosGet(int id, String pavadinimas, String data, String pradzia, String pabaiga, String filepath, String lokacija, int stalu_sk, String status, String aprasas, List<Grupe> pogrupis_sarasas) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.data = data;
        this.pabaiga = pabaiga;
        this.lokacija = lokacija;
        this.stalu_sk = stalu_sk;
        this.aprasas = aprasas;
        this.status = status;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { this.status = status; }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

}
