package main.java.UserComponent;

import main.java.DatabaseRe.AccessData;

import java.sql.SQLException;
import java.util.ArrayList;
import main.java.RaffleComponent.DataAccessPoint;

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
        return this.DataAccess.getParticipantRaffleIds(username);
    }

    public String[] getOrgRafflesIdsFromDatabase(String username){
        ArrayList<String> orgRafflesIdsFromDatabase = this.DataAccess.getOrganizerRaffleIds(username);
        int orgRaffleIdSize = orgRafflesIdsFromDatabase.size();
        String[] orgRaffleIds = new String[orgRaffleIdSize];
        for (int i = 0; i < orgRaffleIdSize; i++){
            orgRaffleIds[i] = orgRafflesIdsFromDatabase.get(i);
        }
        return orgRaffleIds;
    }
}
