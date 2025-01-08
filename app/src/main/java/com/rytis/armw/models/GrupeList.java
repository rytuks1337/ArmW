package com.rytis.armw.models;

import java.util.ArrayList;
import java.util.List;

public class GrupeList {

    //array of Grupe
    private List<Grupe> data;

    public List<Grupe> getGrupes() {
        return data;
    }
    public List<GrupeBasic> getGrupesBasic() {
        //From all of the grupe get non-repeated groups (separated by pavadinimas and lytis)
        List<GrupeBasic> grupesBasic = new ArrayList<>();
        for (Grupe item : data) {
            boolean found = false;
            for (GrupeBasic grupeBasic : grupesBasic) {
                if (grupeBasic.pavadinimas.equals(item.getPavadinimas()) && grupeBasic.lytis.equals(item.getLytis())) {
                    found = true;
                    grupeBasic.addGrupes(item);
                    break;
                }
            }
            if (!found) {
                GrupeBasic grupeBasic = new GrupeBasic();
                grupeBasic.pavadinimas = item.getPavadinimas();
                grupeBasic.lytis = item.getLytis();
                grupesBasic.add(grupeBasic);
                grupeBasic.addGrupes(item);
            }
        }
        return grupesBasic;
    }

    public void setGrupes(List<Grupe> grupes) {
        this.data = grupes;
    }

    public static class GrupeBasic{
        private String pavadinimas;
        private String lytis;
        private List<Grupe> data = new ArrayList<>();
        public void addGrupes(Grupe item){
            data.add(item);
        }
        public List<Grupe> getGrupes(){
            return data;
        }
        public String getPavadinimas(){
            return pavadinimas;
        }
        public String getLytis(){
            return lytis;
        }
    }
}
