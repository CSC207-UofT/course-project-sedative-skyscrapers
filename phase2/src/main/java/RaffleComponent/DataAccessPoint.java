package main.java.RaffleComponent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public interface DataAccessPoint {
    /* the interface detailing the methods implemented at the 4th layer, such methods are to be accessed
     by the use cases (directly dependent on this interface) to get the needed information from the database
     */

    ArrayList<String> getTakenRaffleIds();
    // obtain data from DB about ids that are already under use (Already implemented)



    HashMap<String, ArrayList<Object>> getAllOrganizerRaffles();
    // gets the information of all the organizer raffles available in the DB in the form of a hashmap of
    // raffleId to raffleInformation following the format:
    // [raffleName="raffle", numberOfWinners=2, rules="this is a string of rules",
    //        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
    //        winnerIds=ArrayList<String>]

    ArrayList<Object> getOrganizerRaffleById(String orgRaffleId);
    // get the information specific to a raffle according to its id (indexing of the hashmap
    // from getAllOrganizerRaffles)

    ArrayList<ArrayList<Object>> getAllParticipantRaffles(String raffleId);
    // get the information of all participantRaffles under the provided raffleId

    HashMap<String, ArrayList<Object>> getAllParticipantRafflesAndIDs(String raffleId);
    // get the information of all participantRaffles under the provided pure raffleId, and return a hashmap of the
    // format {participantRaffleId = "username:raffleID" : participantRaffleInfo = ArrayList<Object>

    ArrayList<Object> getParticipantRaffleById(String raffleId);
    // get the information of the one participant raffle being requested through the given Id

    ArrayList<String> getTaskById(String taskId);

    ArrayList<String> getParticipantInfo(String ptcUsername);

    ArrayList<String> getOrganizerInfo(String orgUsername);

    ArrayList<String> getParticipantRaffleIds(String ptcUsername);

    String getOrganizerRaffleIds(String orgUsername);

    // todo
    ArrayList<String> getValidParticipants(String orgRaffleId);
    // return an arraylist of the userIds of all participants inside the orgRaffle
    // denoted by orgRaffleId who have all of their task states set to complete
    // note this was already implemented, we would just need to translate it to SQL

}
