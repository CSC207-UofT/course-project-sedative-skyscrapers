package main.java.UserComponent;

import main.java.DatabaseRe.AccessData;
import main.java.UserComponent.LookUpUser;

import java.sql.SQLException;
import java.util.ArrayList;

import main.java.RaffleComponent.DataAccessPoint;

/**
 * the class is reponsible for checking the username the user enters
 */
public class CheckUserUsernameUseCase {
    private DataAccessPoint dataAccess;
    private final LookUpUser userLookUp;

    public CheckUserUsernameUseCase() {
        try {
            this.dataAccess = new AccessData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.userLookUp = new LookUpUser();
    }

    /**
     * returns true if the username is already registered by other users, either participants or organizers
     */
    public boolean checkUserNameUsed(String username) {
        if (dataAccess.getTakenParticipantIds().contains(username)) {
            return true;
        } else return dataAccess.getTakenOrganizerIds().contains(username);
    }

    /**
     * returns true if the username matches the password
     */
    public boolean checkUsernameMatchPassword(String username, String password, String userType) {
        if (userType.equals("P")) {
            String pw = userLookUp.getPtcPassword(username);
            if (pw == null) {
                return false;
            }
            return pw.equals(password);
        } else if (userType.equals("O")) {
            String pw = userLookUp.getOrgPassword(username);
            if (pw == null) {
                return false;
            }
            return pw.equals(password);
        }
        return false;
    }
}
