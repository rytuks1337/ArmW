package com.rytis.armw.dataModels;

public class TournamentModel {

    public static class TournamentRespGetData {

        private String data;
        private String pradzia;
        private String pabaiga;
        private String aprasas;
        private Integer id;
        private String pavadinimas;
        private Integer organizatoriusvartotojo_id;

        public TournamentRespGetData(Integer id, String pavadinimas, String data, String pradzia, String pabaiga, String aprasas, Integer organizatoriusvartotojo_id) {
            this.data = data;
            this.pradzia = pradzia;
            this.pabaiga = pabaiga;
            this.aprasas = aprasas;
            this.id = id;
            this.pavadinimas = pavadinimas;
            this.organizatoriusvartotojo_id = organizatoriusvartotojo_id;
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

        public String getAprasas() {
            return aprasas;
        }

        public void setAprasas(String aprasas) {
            this.aprasas = aprasas;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPavadinimas() {
            return pavadinimas;
        }

        public void setPavadinimas(String pavadinimas) {
            this.pavadinimas = pavadinimas;
        }

        public Integer getOrganizatoriusvartotojo_id() {
            return organizatoriusvartotojo_id;
        }

        public void setOrganizatoriusvartotojo_id(Integer organizatoriusvartotojo_id) {
            this.organizatoriusvartotojo_id = organizatoriusvartotojo_id;
        }
    }


}
