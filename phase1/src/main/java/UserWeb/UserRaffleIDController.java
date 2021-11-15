package main.java.UserWeb;

import java.util.ArrayList;
import java.util.HashMap;

public class UserRaffleIDController {
    private HashMap<String, ArrayList<String>> participantToRaffleID;
    private HashMap<String, String> organizerToRaffleID;

    //TODO: implement constructor
    UserRaffleIDController() {}

    /**
     * Get a list of raffleIDs of the raffles that the participant is a part of
     * @param username participant username
     * @return a list of raffleIDs
     */
    public ArrayList<String> getParticipantRaffleID(String username) {
        return participantToRaffleID.get(username);
    }

    /**
     * Get the raffleID of the raffle that the organizer created
     * @param username organizer username
     * @return a raffleID String
     */
    public String getOrganizerRaffleID(String username) {
        return organizerToRaffleID.get(username);
    }

    //TODO: implement addParticipantRaffleID
    /**
     * Upload the raffleID of the raffle the participant joined to database
     * @param username participant username
     * @param raffleID the raffleID of the raffle the participant joined
     */
    public void addRaffleIDToParticipant(String username, ArrayList<String> raffleID) {

    }

    //TODO: implement addOrganizerRaffleID
    /**
     * Upload the raffleID of the raffle the organizer created to database
     * @param username organizer username
     * @param raffleID the raffleID of the raffle the organizer created
     */
    public void addRaffleIDToOrganizer(String username, ArrayList<String> raffleID) {

    }
}
