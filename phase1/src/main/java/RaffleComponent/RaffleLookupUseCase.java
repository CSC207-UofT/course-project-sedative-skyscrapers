package main.java.RaffleComponent;

import java.util.ArrayList;
import java.util.HashMap;

public class RaffleLookupUseCase {

    private final String orgRaffleId;

    public RaffleLookupUseCase(String orgRaffleId){
        this.orgRaffleId = orgRaffleId;
    }

    public static HashMap<String, ArrayList<Object>> getAllOrgRaffleInfo(){
        // returns {orgRaffleId: ArrayList<Object>} where ArrayList<Object> refers to a raffle's info
        // todo uncomment: return DataAccess.getAllOrganizerRaffles();
    }

    public ArrayList<Object> getPtcRaffleInfo(){
        // todo uncomment: ArrayList<Object> orgRaffleInfo = DataAccess.getOrganizerRaffleById(this.orgRaffleId);
        ArrayList<Object> ptcRaffleInfo = new ArrayList<>();

        // returns [name, numOfWinners, endDate]
        ptcRaffleInfo.add(orgRaffleInfo.get(0), orgRaffleInfo.get(1)), orgRaffleInfo.get(3));
        return ptcRaffleInfo;
    }
}
