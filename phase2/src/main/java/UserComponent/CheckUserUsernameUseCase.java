package main.java.UserComponent;

import main.java.DatabaseRe.AccessData;
import main.java.UserComponent.LookUpUser;
import java.sql.SQLException;
import java.util.ArrayList;

public class CheckUserUsernameUseCase {
    private DataAccessPoint dataAccess;
    private final LookUpUser userLookUp;

    public CheckUserUsernameUseCase(){
        try {
            this.dataAccess = new AccessData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.userLookUp = new LookUpUser();
    }

    public boolean checkUserNameUsed(String username){
        if (dataAccess.getParticipantInfo(username)){
            return true;
        } else return dataAccess.getOrganizerInfo(username);
    }

    public boolean checkPtcUsernameMatchPassword(String username, String password){
        return userLookUp.getPtcPassword(username).equals(password);
    }

    public boolean checkOrgUsernameMatchPassword(String username, String password){
        return userLookUp.getOrgPassword(username).equals(password);
    }
}
