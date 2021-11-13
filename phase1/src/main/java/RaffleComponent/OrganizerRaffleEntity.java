package main.java.RaffleComponent;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrganizerRaffleEntity extends RaffleEntity {
    private ArrayList<String> participantIdList;
    private ArrayList<String> winnerIdList;

    public OrganizerRaffleEntity(String raffleName, int numberOfWinners, LocalDate endDate){
        super(raffleName, numberOfWinners, endDate);
        // participantList and winnerList not yet initialized, but rather gotten from use case
        this.participantIdList = new ArrayList<>();
        this.winnerIdList = new ArrayList<>();
    }

    @Override
    public String toString(){
        String generalInfo = "Raffle Name: " + this.getRaffleName() + "\nRaffle ID: " + this.getRaffleId() +
                "\nRaffle Creator: " + "\nEnding Date: " + this.getFormattedEndDate() + "\nNumber of Winners: "
                + this.getNumberOfWinners();

        StringBuilder taskListStr = new StringBuilder();
        int i;
        for (i = 0; i < this.getTaskIdList().size(); i++){
            taskListStr.append("[").append(i).append("]").append(this.getTaskIdList().get(i)).append("\n");
        }

        StringBuilder ptcListStr = new StringBuilder();
        int j;
        for (j = 0; j < this.participantIdList.size(); j++){
            ptcListStr.append("[").append(j).append("]").append(this.participantIdList.get(j)).append("\n");
        }

        StringBuilder winnerListStr = new StringBuilder();
        int k;
        for (k = 0; k < this.winnerIdList.size(); k++){
            winnerListStr.append("[").append(j).append("]").append(this.winnerIdList.get(j)).append("\n");
        }

        return generalInfo + "\nTasks:\n" + taskListStr + "\nParticipants:\n" + ptcListStr +
                "\nWinners:\n" + winnerListStr;

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
