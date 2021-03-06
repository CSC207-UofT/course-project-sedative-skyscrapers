package main.java.RaffleComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.ProvideData;
import main.java.Helpers.UseCaseDateFormatter;
import main.java.DatabaseRe.DataAccessPoint;
import main.java.DatabaseRe.DataProviderPoint;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

// the use case for when a raffle organizer wants to decide the winners of a raffle
public class RaffleWinnerGeneratorUseCase {

    private ArrayList<Object> orgRaffleInfo;
    private OrganizerRaffleEntity orgRaffle;
    private DataAccessPoint dataAccess;
    private DataProviderPoint dataUploader;
    private final ArrayList<String> validParticipantIds;

    /**
     * Constructor for the use case handling the event of generating a list of winning participants within a raffle
     *
     * @param raffleId reference to the raffle object to which the winnerIdList is to be generated for
     */
    public RaffleWinnerGeneratorUseCase(String raffleId) {

        try {
            this.dataAccess = new AccessData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.dataUploader = new ProvideData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.orgRaffleInfo = this.dataAccess.getOrganizerRaffleById(raffleId);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        String date = this.orgRaffleInfo.get(3).toString();
        int[] dateData = UseCaseDateFormatter.formatDateIntoStrings(date);
        this.orgRaffle = new OrganizerRaffleEntity(this.orgRaffleInfo.get(0).toString(),
                Integer.parseInt(this.orgRaffleInfo.get(1).toString()),
                LocalDate.of(dateData[0], dateData[1], dateData[2]),
                this.orgRaffleInfo.get(7).toString());
        this.orgRaffle.setRaffleId(raffleId);
        this.orgRaffle.setRaffleRules((String) orgRaffleInfo.get(2));
        this.orgRaffle.setTaskIdList((ArrayList<String>) orgRaffleInfo.get(4));
        // no winners set yet

        this.validParticipantIds = this.dataAccess.getValidParticipants(this.orgRaffle.getRaffleId());

    }

    /**
     * Executes the processes to generate, return and store this.orgRaffle 's winners
     *
     * @return the boolean representing whether the raffleWinners have been declared and stored,
     * while storing the selected winners in this.orgRaffle.winnerIdList
     */
    public boolean updateRaffleWinners() {
        // for now any participant can be selected as a winner, phase2 this will be updated to only valid ones
        if (!this.generateWinners().isEmpty()) {
            this.orgRaffle.setWinnerList(this.generateWinners());
            try {
                this.dataUploader.addWinnersToRaffle(this.orgRaffle.getRaffleId(), this.orgRaffle.getWinnerList());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        // else
        return false;
    }

    /**
     * Generates the winning participants from the list of valid participants of this.orgRaffle and
     * updates the database accordingly
     *
     * @return the arraylist of strings consisting of the winning participants of this raffle
     */
    private ArrayList<String> generateWinners() {
        int i;
        ArrayList<String> winnersSoFar = new ArrayList<>();
        ArrayList<Integer> winningNumsSoFar = new ArrayList<>();

        // check amount of winners to calculate
        int winningEntriesToCalculate = Math.min(this.validParticipantIds.size(),
                this.orgRaffle.getNumberOfWinners());

        for (i = 0; i < winningEntriesToCalculate; i++) {
            int winningEntry = calculateWinningEntry(winningNumsSoFar);
            winningNumsSoFar.add(winningEntry);
            winnersSoFar.add(this.validParticipantIds.get(winningEntry));  // winningEntry is the index
        }
        return winnersSoFar;  // returns arrayList of userId strings
    }

    /**
     * Generates the indexes referring to the winning participants within the valid participants of this.orgRaffle
     *
     * @param winningNumsSoFar the indexes who have already been identified as winners (to not be repeated)
     * @return a single index referring to the winning entry
     */
    private int calculateWinningEntry(ArrayList<Integer> winningNumsSoFar) {

        int winningEntry = (int) (this.orgRaffle.getParticipantIdList().size() * Math.random());

        while (winningNumsSoFar.contains(winningEntry)) {
            winningEntry = (int) (this.orgRaffle.getParticipantIdList().size() * Math.random());  // recalculate
        }

        return winningEntry;
    }

    // for testing purposes only

    public void setOrgRaffle(OrganizerRaffleEntity orgRaffle) {
        this.orgRaffle = orgRaffle;
    }

    public OrganizerRaffleEntity getOrgRaffle() {
        return this.orgRaffle;
    }
}
