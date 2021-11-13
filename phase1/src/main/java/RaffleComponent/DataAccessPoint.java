package main.java.RaffleComponent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public interface DataAccessPoint {
    /* the interface detailing the methods implemented at the 4th layer, such methods are to be accessed
     by the use cases (directly dependent on this interface) to get the needed information from the database
     */

    ArrayList<String> getTakenRaffleIds();
    // obtain data from DB about ids that are already under use

    void uploadCreatedRaffle(ArrayList<String> takenIds, OrganizerRaffleEntity raffleCreated);
    // sends data of new created raffle to DB

    HashMap<String, ArrayList<Object>> getAllOrganizerRaffles();
    // gets the information of all the organizer raffles available in the DB

    ArrayList<Object> getOrganizerRaffleById(String orgRaffleId);
    // get the information specific to a raffle according to its id

    void uploadModifiedOrgRaffle(OrganizerRaffleEntity raffleModified);
    // sends updated raffle information data to DB, at DB all data related to the raffle is updated according
    // to raffleModified

    void uploadLoggedInRaffle(RaffleEntity raffleLoggedIn);
    // sends data of newly logged in raffle to DB

    ArrayList<ArrayList<Object>> getAllParticipantRaffles(String raffleId);
    // get the information of all participantRaffles under the provided raffleId

    void uploadModifiedPtcRaffle(RaffleEntity raffleModified);
    // sends updated raffle information data to DB, at DB all data related to the raffle is updated according
    // to raffleModified

    ArrayList<Object> getParticipantRaffle(String raffleId);
    // get the information of the one raffle the single user at the moment is making use of
}
