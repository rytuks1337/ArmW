package com.rytis.armw.ui.queue_controller;

import com.google.gson.annotations.SerializedName;
import com.rytis.armw.ui.bracket.BracketMatchModel;

import java.util.ArrayList;
import java.util.List;

public class Queue_Table {

        private int id;

        private int nr;

        private final GroupQueue dabartinisLenkimoGrupesID;

        public Queue_Table(int id, int nr, GroupQueue dabartinisLenkimoGrupesID) {
            this.id = id;
            this.nr = nr;
            this.dabartinisLenkimoGrupesID = dabartinisLenkimoGrupesID;
        }

        public int getId() {
            return id;
        }

        public int getTableNumber() {
            return nr;
        }

        public GroupQueue getCurrentMatch() {
            return dabartinisLenkimoGrupesID;
        }

        public void setTableNumber(int tableNumber) {
            this.nr = tableNumber;
        }

        public static class GroupQueue{
            private int id;
            private int turnyro_ID;
            private String pavadinimas;
            private String svoris;
            private String amzius;
            private String lytis;
            private String ranka;
            private List<MatchQueue> lenkimo_tvarka;
            private int raundas;

            public int getId() {
                return id;
            }

            public int getTournamentId() {
                return turnyro_ID;
            }

            public String getName() {
                return pavadinimas;
            }
            public String getWeight() {
                return svoris;
            }
            public String getAge() {
                return amzius;
            }
            public String getGender() {
                return lytis;
            }
            public String getHand() {
                return ranka;
            }
            public List<MatchQueue> getMatches() {
                return lenkimo_tvarka;
            }
            public int getRound() {
                return raundas;
            }

            public static class MatchQueue{
                private int id;

                @SerializedName("dalyvio_ID")
                private Integer player1Id;

                @SerializedName("dalyvio2_ID")
                private Integer player2Id;

                @SerializedName("laimetojoDalyvio_ID")
                private Integer winnerId;

                @SerializedName("pralaimetoDalyvio_ID")
                private Integer loserId;

                @SerializedName("dalyvio_name")
                private String playerName;

                @SerializedName("dalyvio2_name")
                private String playerName2;

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
        }
}
