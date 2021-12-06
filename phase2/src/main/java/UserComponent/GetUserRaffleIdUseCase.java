package src.main.java.UserComponent;

import main.java.DatabaseRe.AccessData;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetUserRaffleIdUseCase {
    private DataAccessPoint DataAccess;

    public GetUserRaffleIdUseCase(){
        try {
            this.DataAccess = new AccessData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getPtcRaffleIdsFromDatabase(String username){
        return DataAccessPoint.getParticipantRaffleIds(username);
    }

    //TODO: convert ArrayList<String> to String[]
    public String[] getOrgRafflesIdsFromDatabase(String username){
        return DataAccessPoint.getOrganizerRaffleIds(username);
    }
}
