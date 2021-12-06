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

    public String[] getOrgRafflesIdsFromDatabase(String username){
        ArrayList<String> orgRafflesIdsFromDatabase = DataAccessPoint.getOrganizerRaffleIds(username)
        int orgRaffleIdSize = orgRafflesIdsFromDatabase.size();
        String[] orgRaffleIds = new String[orgRaffleIdSize];
        for (int i = 0; i < orgRaffleIdSize; i++){
            orgRaffleIds[i] = orgRafflesIdsFromDatabase.get(i);
        }
        return orgRaffleIds;
    }
}
