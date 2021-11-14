package main.java.UserComponent;

import java.util.ArrayList;
import java.util.HashMap;

public class UserRaffleIDController {
    private HashMap<String, ArrayList<String>> participantToRaffleID;
    private HashMap<String, String> organizerToRaffleID;

    //TODO: implement constructor
    //construct GetUserRaffleIDController using the data participantUsername, participantRaffleID,
    // organizerUsername, and organizerRaffleID from database
    UserRaffleIDController(String participantUsername, String participantRaffleID,
                           String organizerUsername, String organizerRaffleID) {}

    //I think you make a controller along with a use case to add a new RaffleId to the list of raffleIds of a user,
    // and the systemManager takes care of delivering the raffleId to that controller
    //TODO: implement getParticipantRaffleID
    /*
    Return a list of raffleID of the raffles the participant is a part of by inputting the username of the participant.
     */
    public ArrayList<String> getParticipantRaffleID(String username) {

    }

    //TODO: implement getOrganizerRaffleID
    /*
    Return a list of raffleID of the active raffles the organizer created by inputting the username of the organizer.
     */
    public String getOrganizerRaffleID(String userName) {

    }

    //TODO: implement addParticipantRaffleID to associate the list of raffleID to the participant
    public void addRaffleIDToParticipant(String username, ArrayList<String> raffleID) {

    }

    //TODO: implement addOrganizerRaffleID to associate the list of raffleID to the organizer
    public void addRaffleIDToOrganizer(String username, ArrayList<String> raffleID) {

    }
}
