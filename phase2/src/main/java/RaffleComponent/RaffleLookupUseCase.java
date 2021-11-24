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


    /**
     * Constructor initializing the use case in charge of extracting data from the database in
     * order to display it to the GUI
     */
    public RaffleLookupUseCase(){
        try {
            this.dataAccess = new DataExtractor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches data from all organizer raffle objects in the database
     * @return hashmap of format {orgRaffleId:orgRaffleInfo}
     */
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

    /**
     * Fetches data from a specific participant raffle entity by id
     * @param ptcRaffleId the id of the raffle to mine information from
     * @return the arraylist containing the information of the ptc raffle described by ptcRaffleId
     */
    public ArrayList<Object> getPtcRaffleInfo(String ptcRaffleId){
        ArrayList<Object> ptcRaffleInfo = null;
        try {
            ptcRaffleInfo = this.dataAccess.getPtcRaffleInfo(ptcRaffleId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ptcRaffleInfo;
    }

    /**
     * Fetches data from a specific organizer raffle entity by id
     * @param orgRaffleId the id of the raffle to mine information from
     * @return the arraylist containing the information of the org raffle described by orgRaffleId
     */
    public ArrayList<Object> getOrgRaffleInfo(String orgRaffleId){
        ArrayList<Object> orgRaffleInfo = null;
        try {
            orgRaffleInfo = this.dataAccess.getOrgRaffleInfo(orgRaffleId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return orgRaffleInfo;
        // this is for the 7 for ptcRaffle
    }

    /**
     * Fetches the winnerIdList attribute of an organizer raffle entity
     * @param orgRaffleId describes the raffle object from which to mine the winnerIdList
     * @return the arraylist of strings referring to the usernames of the raffle's winners
     */
    public ArrayList<String> getWinnersList(String orgRaffleId){
        ArrayList<Object> orgRaffleInfo = this.getOrgRaffleInfo(orgRaffleId);
        return (ArrayList<String>) orgRaffleInfo.get(6);
    }

    public boolean raffleIdExists(String orgRaffleID) {
        return this.getAllOrgRaffleInfo().containsKey(orgRaffleID);
    }

}
