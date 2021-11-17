package main.java.UserWeb;

import main.java.database.DataExtractor;
import main.java.database.JoinUserToRaffle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class UserRaffleIDController {
    private DataExtractor dataExtractor;
    private JoinUserToRaffle joinUserToRaffle;

    public UserRaffleIDController() {
        try {
            this.dataExtractor = new DataExtractor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.joinUserToRaffle = new JoinUserToRaffle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a list of raffleIDs of the raffles that the participant is a part of
     * @param username participant username
     * @return a list of raffleIDs
     */
    public ArrayList<String> getParticipantRaffleID(String username) {
        try {
            return dataExtractor.getParticipantRaffleId(username);
        }
        catch(IOException e)
        {
            return new ArrayList<String>();
        }
    }

    /**
     * Get the raffleID of the raffle that the organizer created
     * @param username organizer username
     * @return a raffleID String
     */
    public String[] getOrganizerRaffleID(String username) {
        return dataExtractor.getOrganizerRaffleId(username);
    }

    /**
     * Upload the raffleID of the raffle the participant joined to database
     * @param username participant username
     * @param raffleID the raffleID of the raffle the participant joined
     */
    public void addRaffleIDToParticipant(String username, String raffleID) {
        try {
            joinUserToRaffle.joinUserToRaffle(raffleID, username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
