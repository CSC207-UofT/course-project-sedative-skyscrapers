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

    void uploadCreatedRaffle(ArrayList<String> takenIds, String orgRaffleId, ArrayList<Object> raffleCreatedInfo);
    // update of the takenIds after an organizer raffle object is created, along with the new organizer Raffle
    // information that was just input by the used.
    // RaffleCreatedInfo format: [raffleName="raffle", numberOfWinners=2, rules="this is a string of rules",
    //        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
    //        winnerIds=ArrayList<String>]

    HashMap<String, ArrayList<Object>> getAllOrganizerRaffles();
    // gets the information of all the organizer raffles available in the DB in the form of a hashmap of
    // raffleId to raffleInformation following the format:
    // [raffleName="raffle", numberOfWinners=2, rules="this is a string of rules",
    //        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
    //        winnerIds=ArrayList<String>]


    ArrayList<Object> getOrganizerRaffleById(String orgRaffleId);
    // get the information specific to a raffle according to its id (indexing of the hashmap
    // from getAllOrganizerRaffles)

    void uploadModifiedOrgRaffle(String orgRaffleId, ArrayList<Object> raffleModifiedInfo);
    // sends updated orgRaffle information data to DB under the provided Id, at DB all data related to the raffle
    // is updated according to raffleModifiedInfo, this would be just like uploadCreatedRaffle, but some values
    // are to be replaced/added according to how the raffle was modified

    void uploadLoggedInRaffle(String ptcRaffleId, ArrayList<Object> raffleJoinedInfo);
    // sends data of newly logged in participant raffle object to the database to be stored under the raffleId provided
    // the raffleJoinedInfo has the format:
    // [raffleName="raffle", numberOfWinners=2, rules="this is a string of rules",
    //        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>]


    ArrayList<ArrayList<Object>> getAllParticipantRaffles(String raffleId);
    // get the information of all participantRaffles under the provided raffleId

    HashMap<String, ArrayList<Object>> getAllParticipantRafflesAndIDs(String raffleId);
    // get the information of all participantRaffles under the provided pure raffleId, and return a hashmap of the
    // format {participantRaffleId = "username:raffleID" : participantRaffleInfo = ArrayList<Object>}

    void uploadModifiedPtcRaffle(String ptcRaffleId, ArrayList<Object> raffleModifiedInfo);
    // sends updated ptcRaffle information data to DB under the provided Id, at DB all data related to the raffle
    // is updated according to raffleModifiedInfo, this would be just like uploadLoggedInRaffle, but some values
    // are to be replaced/added according to how the raffle was modified

    ArrayList<Object> getParticipantRaffleById(String raffleId);
    // get the information of the one participant raffle being requested through the given Id
}
