package main.java.RaffleComponent;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class representing an OrganizerRaffle object, which can be created by Organizers only.
 */
public class OrganizerRaffleEntity {

    private final String raffleName;
    private String raffleId;
    private final int numberOfWinners;
    private String raffleRules;
    private LocalDate endDate;
    private ArrayList<String> taskIdList;
    private ArrayList<String> participantIdList;
    private ArrayList<String> winnerIdList;
    private String orgUsername;

    /**
     * Constructor initializing a new OrganizerRaffleEntity according to the inputs
     *
     * @param raffleName      the name to be given to this raffle
     * @param numberOfWinners the number of participants to be able to win this raffle
     * @param endDate         the day this raffle ends
     * @param orgUsername     the username of the organizer who has created this instance of OrganizerRaffleEntity
     */
    public OrganizerRaffleEntity(String raffleName, int numberOfWinners, LocalDate endDate, String orgUsername) {
        this.raffleName = raffleName;
        this.numberOfWinners = numberOfWinners;
        this.endDate = endDate;
        this.taskIdList = new ArrayList<>();

        // participantList and winnerList not yet initialized, but rather gotten from use case
        this.participantIdList = new ArrayList<>();
        this.winnerIdList = new ArrayList<>();
        this.orgUsername = orgUsername;
    }

    @Override
    public String toString() {
        String generalInfo = "Raffle Name: " + this.getRaffleName() + "\nRaffle ID: " + this.getRaffleId() +
                "\nRaffle Creator: " + "\nEnding Date: " + this.getFormattedEndDate() + "\nNumber of Winners: "
                + this.getNumberOfWinners();

        StringBuilder taskListStr = new StringBuilder();
        int i;
        for (i = 0; i < this.getTaskIdList().size(); i++) {
            taskListStr.append("[").append(i).append("]").append(this.getTaskIdList().get(i)).append("\n");
        }

        StringBuilder ptcListStr = new StringBuilder();
        int j;
        for (j = 0; j < this.participantIdList.size(); j++) {
            ptcListStr.append("[").append(j).append("]").append(this.participantIdList.get(j)).append("\n");
        }

        StringBuilder winnerListStr = new StringBuilder();
        int k;
        for (k = 0; k < this.winnerIdList.size(); k++) {
            winnerListStr.append("[").append(j).append("]").append(this.winnerIdList.get(j)).append("\n");
        }

        return generalInfo + "\nTasks:\n" + taskListStr + "\nParticipants:\n" + ptcListStr +
                "\nWinners:\n" + winnerListStr;

    }

    // getters and setters

    public String getRaffleId() {
        return raffleId;
    }

    public void setRaffleId(String raffleId) {
        this.raffleId = raffleId;
    }


    public String getRaffleName() {
        return raffleName;
    }

    public int getNumberOfWinners() {
        return numberOfWinners;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getFormattedEndDate() {
        int year = this.endDate.getYear();
        int month = this.endDate.getMonthValue();
        int day = this.endDate.getDayOfMonth();

        return year + "/" + month + "/" + day;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRaffleRules() {
        return raffleRules;
    }

    public void setRaffleRules(String raffleRules) {
        this.raffleRules = raffleRules;
    }


    public ArrayList<String> getTaskIdList() {
        return taskIdList;
    }

    public void setTaskIdList(ArrayList<String> taskIdList) {
        this.taskIdList = taskIdList;
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

    public void setOrgUsername(String orgUsername) {
        this.orgUsername = orgUsername;
    }

    public String getOrgUsername() {
        return orgUsername;
    }
}
