package main.java.RaffleComponent;

import main.java.database.AddOrganizer;
import main.java.database.DataExtractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RaffleLookupUseCase {

//    private final String orgRaffleId;
//    private final int PTC_DATA_SIZE = 5;
//    private final int ORG_DATA_SIZE = 7;
    private DataExtractor dataAccess;

    public RaffleLookupUseCase(){
        try {
            this.dataAccess = new DataExtractor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, ArrayList<Object>> getAllOrgRaffleInfo(){
        // returns {orgRaffleId: ArrayList<Object>} where ArrayList<Object> refers to a raffle's info
        HashMap<String, ArrayList<Object>> hashMapToReturn = new HashMap<>();

        try {
            hashMapToReturn = this.dataAccess.getAllOrgRaffleInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMapToReturn;
    }

    public ArrayList<Object> getPtcRaffleInfo(String ptcRaffleId){
        ArrayList<Object> ptcRaffleInfo = null;
        try {
            ptcRaffleInfo = this.dataAccess.getPtcRaffleInfo(ptcRaffleId);
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//
//        // returns [name, numOfWinners, endDate]
//        int i;
//        for (i = 0; i < this.PTC_DATA_SIZE; i++){
//            ptcRaffleInfo.add(ptcRaffleInfo.get(i));
//        }
//        return ptcRaffleInfo;
//        // this is for the 5 for ptcRaffle
        return ptcRaffleInfo;
    }

    public ArrayList<Object> getOrgRaffleInfo(String orgRaffleId){
        ArrayList<Object> orgRaffleInfo = null;
        try {
            orgRaffleInfo = this.dataAccess.getOrgRaffleInfo(orgRaffleId);
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        // returns [name, numOfWinners, endDate]
//        int i;
//        for (i = 0; i < this.ORG_DATA_SIZE; i++){
//            orgRaffleInfo.add(orgRaffleInfo.get(i));
//        }
        return orgRaffleInfo;
        // this is for the 7 for ptcRaffle
    }

    public ArrayList<String> getWinnersList(String orgRaffleId){
        ArrayList<Object> orgRaffleInfo = this.getOrgRaffleInfo(orgRaffleId);
        return (ArrayList<String>) orgRaffleInfo.get(6);
    }

    public boolean raffleIdExists(String orgRaffleID) {
        return this.getAllOrgRaffleInfo().containsKey(orgRaffleID);
    }

}
