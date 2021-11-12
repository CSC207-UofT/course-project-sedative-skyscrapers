package main.java.RaffleComponent;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrganizerRaffleEntity extends RaffleEntity {
    private ArrayList<String> participantIdList;
    private ArrayList<String> winnerIdList;

    public OrganizerRaffleEntity(String raffleName, int numberOfWinners, LocalDate endDate){
        super(raffleName, numberOfWinners, endDate);
        // participantList and winnerList not yet initialized, but rather gotten from use case
        this.participantIdList = new ArrayList<String>();
        this.winnerIdList = new ArrayList<String>();
    }

    public ArrayList<String> getParticipantIdList() {
        return participantIdList;
    }

    public ArrayList<String> getWinnerList() {
        return winnerIdList;
    }

    public void setWinnerList(ArrayList<String> winnerList) {
        this.winnerIdList = winnerList;
    }

    public void setParticipantIdList(ArrayList<String> participantIdList) {
        this.participantIdList = participantIdList;
    }
}
