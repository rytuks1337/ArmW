package com.rytis.armw.ui.queue_controller;

import com.google.gson.annotations.SerializedName;
import com.rytis.armw.ui.bracket.BracketMatchModel;

import java.util.ArrayList;
import java.util.List;

public class QueueModel {

    private ArrayList<Queue_Table> tables;

    public QueueModel(ArrayList<Queue_Table> tables) {
        this.tables = tables;
    }

    public ArrayList<Queue_Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Queue_Table> tables) {
        this.tables = tables;
    }


    public int size() {
        return tables.size();
    }

    public static class Queue_Table {

        private int id;

        private int nr;

        private List<BracketMatchModel> lenkimo_id;

        private BracketMatchModel dabartinisLenkimoGrupesID;

        public Queue_Table(int id, int nr, List<BracketMatchModel> lenkimo_id, BracketMatchModel dabartinisLenkimoGrupesID) {
            this.id = id;
            this.nr = nr;
            this.lenkimo_id = lenkimo_id;
            this.dabartinisLenkimoGrupesID = dabartinisLenkimoGrupesID;
        }

        public int getId() {
            return id;
        }

        public int getTableNumber() {
            return nr;
        }

        public List<BracketMatchModel> getMatches() {
            return lenkimo_id;
        }

        public BracketMatchModel getCurrentMatch() {
            return dabartinisLenkimoGrupesID;
        }

        public void setTableNumber(int tableNumber) {
            this.nr = tableNumber;
        }

        public void setMatches(List<BracketMatchModel> matches) {
            this.lenkimo_id = matches;
        }
    }
}
