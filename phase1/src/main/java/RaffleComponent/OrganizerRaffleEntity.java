package main.java.RaffleComponent;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrganizerRaffleEntity extends RaffleEntity {
    private ArrayList<String> participantIdList;
    private ArrayList<String> winnerIdList;
    private final String orgUsername;

    /**
     * Constructor initializing a new OrganizerRaffleEntity according to the inputs
     * @param raffleName the name to be given to this raffle
     * @param numberOfWinners the number of participants to be able to win this raffle
     * @param endDate the day this raffle ends
     * @param username the username of the organizer who has created this instance of OrganizerRaffleEntity
     */
    public OrganizerRaffleEntity(String raffleName, int numberOfWinners, LocalDate endDate, String username){
        super(raffleName, numberOfWinners, endDate);

        // participantList and winnerList not yet initialized, but rather gotten from use case
        this.participantIdList = new ArrayList<>();
        this.winnerIdList = new ArrayList<>();
        this.orgUsername = username;
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

    // getters and setters

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
