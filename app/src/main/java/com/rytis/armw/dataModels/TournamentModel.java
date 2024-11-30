package com.rytis.armw.dataModels;

import java.util.ArrayList;
import java.util.List;

public class TournamentModel {

    public static class TournamentRespGetData {

        public TournamentRespGetData() {
            this.result = new ArrayList<>();
        }
        private List<TournamentData> result;

        public List<TournamentData> getResult() {
            return this.result;
        }

        public static class TournamentData {
            private final Integer id;
            private final String status;
            private final String pavadinimas;
            private final String data;
            private final Integer pabaiga;
            private final String lokacija;
            private final Integer stalu_sk;
            private final String aprasas;

            public TournamentData(Integer id, String status, String pavadinimas, String data, Integer pabaiga, String lokacija, Integer stalu_sk, String aprasas) {
                this.id = id;
                this.status = status;
                this.pavadinimas = pavadinimas;
                this.data = data;
                this.pabaiga = pabaiga;
                this.lokacija = lokacija;
                this.stalu_sk = stalu_sk;
                this.aprasas = aprasas;
            }

            public String getStatus() {
                return status;
            }

            public Integer getId() {
                return id;
            }

            public String getPavadinimas() {
                return pavadinimas;
            }

            public String getData() {
                return data;
            }

            public Integer getPabaiga() {
                return pabaiga;
            }

            public String getLokacija() {
                return lokacija;
            }

            public Integer getStalu_sk() {
                return stalu_sk;
            }

            public String getAprasas() {
                return aprasas;
            }

        }

    }


}
