package main.java.UserComponent;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckUsernameController {
    private ArrayList<String> usernamePool;
    private HashMap<String, String> participantToPassword;
    private HashMap<String, String> organizerToPassword;

    //TODO: implement constructor
    //construct CheckUserController using database
    public CheckUsernameController() {}

    /**
     * Check whether a username is already registered
     * @param username username that a user trying to register
     * @return false if the username is not registered yet, true if the username is already used
     */
    public boolean userNameUsed(String username) {
        return usernamePool.contains(username);
    }

    /**
     * Check whether the participant username is already registered and match the password
     * @param username username of the participant
     * @param password password of the organizer
     * @return true if the username is registered as a participant and matches the password, false otherwise
     */
    public boolean participantUsernameMatchPassword(String username, String password) {
        if (participantToPassword.containsKey(username)) {
            return participantToPassword.get(username).equals(password);
        } else {
            return false;
        }
    }

    /**
     * Check whether the organizer username is already registered and match the password
     * @param username username of the organizer
     * @param password password of the organizer
     * @return true if the username is registered as an organizer and matches the password, false otherwise
     */
    public boolean organizerUsernameMatchPassword(String username, String password) {
        if (organizerToPassword.containsKey(username)) {
            return organizerToPassword.get(username).equals(password);
        } else {
            return false;
        }
    }
}
