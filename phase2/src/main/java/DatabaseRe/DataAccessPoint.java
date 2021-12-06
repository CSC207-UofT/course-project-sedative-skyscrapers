package main.java.DatabaseRe;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface DataAccessPoint {
    /* the interface detailing the methods implemented at the 4th layer, such methods are to be accessed
     by the use cases (directly dependent on this interface) to get the needed information from the database
     */

    ArrayList<String> getTakenRaffleIds() throws SQLException;
    // NOTED
    // obtain data from DB about ids that are already under use (Already implemented)

    ArrayList<Object> getOrganizerRaffleById(String orgRaffleId) throws SQLException, ParseException;
    // NOTED
    // get the information specific to a raffle according to its id (indexing of the hashmap
    // from getAllOrganizerRaffles)
    // [raffleName="raffle", numberOfWinners=2, rules="this is a string of rules",
    //        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
    //        winnerIds=ArrayList<String>]

    ArrayList<Object> getParticipantRaffleById(String ptcRaffleID) throws SQLException, ParseException;
    // trim raffleID
    // Hence, NOTED
    // Returns the list of participants in that raffle  [raffleName, numOfWinners, rules, endDate, taskIdList]

    ArrayList<String> getTaskById(String taskId);
    // NOTED

    ArrayList<String> getParticipantInfo(String ptcUsername);
    // NOTED
    // Return the information about the participant

    ArrayList<String> getOrganizerInfo(String orgUsername);
    // NOTED

    ArrayList<String> getParticipantRaffleIds(String ptcUsername);
    // NOTED AS: returns the RaffleIds in which Participant is enrolled in
    // "participantusername:raffleID"

    ArrayList<String> getOrganizerRaffleIds(String orgUsername);
    // NOTED AS: returns the raffleIDs of raffles which are created by the Organizer with orgusername
    // todo these are the new ones from nov 23, the rest still need to be implemented though
    // "raffleID"

    boolean hasCompletedTask(String ptcRaffleId, String userName, String taskId) throws SQLException;
    // NOTED AS: <userName> has to be a <ptcusername>. Returns whether the ptc has completed the task in the raffle
    // return whether the specified task has the "complete" status

    ArrayList<String> getTakenOrganizerIds();
//     output: all organizer IDs stored

    ArrayList<String> getTakenParticipantIds();
//     output: all participant IDs stored

    ArrayList<String> getValidParticipants (String orgRaffleId);
    // returns arrayList of the ids of all participants who have completed all of the tasks of this specific raffle,
    // I could pass you the raffle's taskIds too if you need them. This method was implemented for phase1 already,
    // I just oversaw it when looking at the interface, but it shouldnt be too bad using phase1's implementation as
    // a guide (inside dataExtractor)
//
}

