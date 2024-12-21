package com.rytis.armw.ui.bracket;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BracketMatchModel {
    private int id;

    @SerializedName("dalyvio_ID")
    private Integer player1Id; // Nullable

    @SerializedName("dalyvio2_ID")
    private Integer player2Id; // Nullable

    @SerializedName("laimetojoDalyvio_ID")
    private Integer winnerId; // Nullable

    @SerializedName("pralaimetoDalyvio_ID")
    private Integer loserId; // Nullable

    @SerializedName("dalyvioN")
    private String playerName; // Nullable

    @SerializedName("dalyvio2N")
    private String playerName2; // Nullable

    private int round;

    private String status;

    public int getId() {
        return id;
    }

    public Integer getPlayer1Id() {
        return player1Id;
    }

    public Integer getPlayer2Id() {
        return player2Id;
    }
    public String getPlayer1() {
        return playerName;
    }
    public String getPlayer2() {
        if(playerName2 == null && winnerId != null){
            return "";
        }
        return playerName2;
    }
    public String getWinner() {
        if (winnerId == null) {
            return null;
        }
        if (winnerId.equals(player1Id)) {
            return playerName;
        } else if (winnerId.equals(player2Id)) {
            return playerName2;
        }
        return null;
    }

    public Integer getWinnerId() {
        return winnerId;
    }

    public Integer getLoserId() {
        return loserId;
    }

    public int getRound() {
        return this.round;
    }

    public String getStatus() {
        return this.status;
    }
}