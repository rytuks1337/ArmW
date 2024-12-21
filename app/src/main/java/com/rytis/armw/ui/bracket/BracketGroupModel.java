package com.rytis.armw.ui.bracket;

import java.util.List;

public class BracketGroupModel {
    private List<List<BracketMatchModel>> losB; // Losers' Bracket
    private List<List<BracketMatchModel>> winB; // Winners' Bracket

    public List<List<BracketMatchModel>> getLosB() {
        return losB;
    }

    public List<List<BracketMatchModel>> getWinB() {
        return winB;
    }
}