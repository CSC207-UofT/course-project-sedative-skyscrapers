package main.java.UserWeb;

import main.java.database.DataExtractor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckUsernameController {
    private DataExtractor dataExtractor;

    public CheckUsernameController() {
        try {
            this.dataExtractor = new DataExtractor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check whether a username is already registered
     * @param username username that a user trying to register
     * @return false if the username is not registered yet, true if the username is already used
     */
    public boolean userNameUsed(String username) {
        try {
            String[] ptcInfo = dataExtractor.getParticipantDetails(username);
            return Arrays.asList(ptcInfo).contains(username);
        } catch (Exception e1) {
            try {
                String[] orgInfo = dataExtractor.getOrganizerDetails(username);
                return Arrays.asList(orgInfo).contains(username);
            } catch (Exception e2) {
                return false;
            }
        }
    }

    /**
     * Check whether the participant username is already registered and match the password
     * @param username username of the participant
     * @param password password of the organizer
     * @return true if the username is registered as a participant and matches the password, false otherwise
     */
    public boolean participantUsernameMatchPassword(String username, String password) {
        String[] ptcInfo;
        try {
            ptcInfo = dataExtractor.getParticipantDetails(username);
        } catch (Exception e) {
            return false;
        }
        String correctPassword = ptcInfo[1];
        return password.equals(correctPassword);
    }

    /**
     * Check whether the organizer username is already registered and match the password
     * @param username username of the organizer
     * @param password password of the organizer
     * @return true if the username is registered as an organizer and matches the password, false otherwise
     */
    public boolean organizerUsernameMatchPassword(String username, String password) {
        String[] orgInfo;
        try {
            orgInfo = dataExtractor.getOrganizerDetails(username);
        } catch (Exception e) {
            return false;
        }
        String correctPassword = orgInfo[1];
        System.out.println(correctPassword);
        return password.equals(correctPassword);
    }
}
