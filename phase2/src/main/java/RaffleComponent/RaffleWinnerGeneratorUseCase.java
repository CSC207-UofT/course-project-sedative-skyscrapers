package main.java.RaffleComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.DataAccessPoint;
import main.java.DatabaseRe.DataProviderPoint;
import main.java.DatabaseRe.ProvideData;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// the use case for when a raffle organizer wants to decide the winners of a raffle
public class RaffleWinnerGeneratorUseCase {

    private ArrayList<Object> orgRaffleInfo;
    private OrganizerRaffleEntity orgRaffle;
//    private PackageRaffleEntityInstance dataPackager;
    private DataAccessPoint dataAccess;
    private DataProviderPoint dataUploader;
    private final ArrayList<String> validParticipantIds;

    /**
     * Constructor for the use case handling the event of generating a list of winning participants within a raffle
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

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.orgRaffle = new OrganizerRaffleEntity((String)this.orgRaffleInfo.get(0),
                Integer.parseInt(this.orgRaffleInfo.get(1).toString()),
                LocalDate.parse(this.orgRaffleInfo.get(3).toString(),dtf),
                (String)this.orgRaffleInfo.get(6));
        this.orgRaffle.setRaffleId(raffleId);
        this.orgRaffle.setRaffleRules((String)this.orgRaffleInfo.get(2));
        this.orgRaffle.setTaskIdList((ArrayList<String>) this.orgRaffleInfo.get(4));
        this.orgRaffle.setParticipantIdList((ArrayList<String>) this.orgRaffleInfo.get(5));
        // no winners set yet

        this.validParticipantIds = this.dataAccess.getValidParticipants(this.orgRaffle.getRaffleId());

    }

    /**
     * Executes the processes to generate, return and store this.orgRaffle 's winners
     * @return the updated winnerIdList of this.orgRaffle
     */
    public boolean updateRaffleWinners(){
        // for now any participant can be selected as a winner, phase2 this will be updated to only valid ones
        if (!this.generateWinners().isEmpty()){
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
     * @return the arraylist of strings consisting of the winning participants of this raffle
     */
    public ArrayList<String> generateWinners(){
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
        //System.out.println("winnersSoFar"+winnersSoFar.get(0));
        // upload results to database
//        try {
//            this.dataUploader.uploadRaffleWinners(this.orgRaffle.getRaffleId(), winnersSoFar);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return winnersSoFar;  // returns arrayList of userId strings
    }

    /**
     * Generates the indexes referring to the winning participants within the valid participants of this.orgRaffle
     * @param winningNumsSoFar the indexes who have already been identified as winners (to not be repeated)
     * @return a single index referring to the winning entry
     */
    public int calculateWinningEntry(ArrayList<Integer> winningNumsSoFar){

        int winningEntry = (int)(this.orgRaffle.getParticipantIdList().size() * Math.random());

        while (winningNumsSoFar.contains(winningEntry)){
            winningEntry = (int)(this.orgRaffle.getParticipantIdList().size() * Math.random());  // recalculate
        }

        return winningEntry;
    }

    // for testing purposes only

    public void setOrgRaffle(OrganizerRaffleEntity orgRaffle){
        this.orgRaffle = orgRaffle;
    }

    public OrganizerRaffleEntity getOrgRaffle(){
        return this.orgRaffle;
    }
}
