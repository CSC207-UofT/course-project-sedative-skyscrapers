package main.java.RaffleComponent;

import java.util.ArrayList;
import java.util.HashMap;

public class RaffleLookupUseCase {

//    private final String orgRaffleId;
    private final int PTC_DATA_SIZE = 5;
    private final int ORG_DATA_SIZE = 7;

    public RaffleLookupUseCase(){

    }

    public static HashMap<String, ArrayList<Object>> getAllOrgRaffleInfo(){
        // returns {orgRaffleId: ArrayList<Object>} where ArrayList<Object> refers to a raffle's info
        // todo uncomment: return DataAccess.getAllOrganizerRaffles();
    }

    public ArrayList<Object> getPtcRaffleInfo(String ptcRaffleId){
        // todo uncomment: ArrayList<Object> ptcRaffleInfo = DataAccess.getParticipantRaffleById(this.ptcRaffleId);
        ArrayList<Object> ptcRaffleInfo = new ArrayList<>();

        // returns [name, numOfWinners, endDate]
        int i;
        for (i = 0; i < this.PTC_DATA_SIZE; i++){
            ptcRaffleInfo.add(ptcRaffleInfo.get(i));
        }
        return ptcRaffleInfo;
        // this is for the 5 for ptcRaffle
    }

    public ArrayList<Object> getOrgRaffleInfo(String orgRaffleId){
        // todo uncomment: ArrayList<Object> orgRaffleInfo = DataAccess.getOrganizerRaffleById(this.orgRaffleId);
        ArrayList<Object> orgRaffleInfo = new ArrayList<>();

        // returns [name, numOfWinners, endDate]
        int i;
        for (i = 0; i < this.ORG_DATA_SIZE; i++){
            orgRaffleInfo.add(orgRaffleInfo.get(i));
        }
        return orgRaffleInfo;
        // this is for the 5 for ptcRaffle
    }

    public boolean raffleIdExists(String orgRaffleID){
        // todo uncomment: return DataAccess.getAllOrganizerRaffles().keyset().contains(orgRaffleId);
    }
}
