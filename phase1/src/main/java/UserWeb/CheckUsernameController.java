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
            String[] ptcInfo = dataExtractor.getUserDetails(username, "P");
            return Arrays.asList(ptcInfo).contains(username);
        } catch (Exception e1) {
            try {
                String[] orgInfo = dataExtractor.getUserDetails(username, "O");
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
            ptcInfo = dataExtractor.getUserDetails(username, "P");
        } catch (Exception e) {
            return false;
        }
        String correctPassword = ptcInfo[0];
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
            orgInfo = dataExtractor.getUserDetails(username, "O");
        } catch (Exception e) {
            return false;
        }
        String correctPassword = orgInfo[0];
        return password.equals(correctPassword);
    }
}
