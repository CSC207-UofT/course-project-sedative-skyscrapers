package main.java.Web;

import main.java.DatabaseRe.AccessData;
import main.java.database.AddOrganizer;
import main.java.database.DataExtractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class RaffleDataHelper {

    private AccessData dataAccess;


    /**
     * Constructor initializing the use case in charge of extracting data from the database in
     * order to display it to the GUI
     */
    public RaffleDataHelper(){
        try {
            this.dataAccess = new AccessData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches data from all organizer raffle objects in the database
     * @return hashmap of format {orgRaffleId:orgRaffleInfo}
     */
    public HashMap<String, ArrayList<Object>> getAllOrgRaffleInfo(){
        // returns {orgRaffleId: ArrayList<Object>} where ArrayList<Object> refers to a raffle's info

        ArrayList<String> orgRaffleIds = null;
        try {
            orgRaffleIds = this.dataAccess.getTakenRaffleIds();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HashMap<String, ArrayList<Object>> hashMapToReturn = new HashMap<>();

        if (orgRaffleIds != null) {
            for (String orgRaffleId : orgRaffleIds) {
                ArrayList<Object> raffleInfo = null;
                raffleInfo = this.dataAccess.getOrganizerRaffleById(orgRaffleId);
                hashMapToReturn.put(orgRaffleId, raffleInfo);
            }
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
        ptcRaffleInfo = this.dataAccess.getParticipantRaffleById(ptcRaffleId);

        return ptcRaffleInfo;
    }

    /**
     * Fetches data from a specific organizer raffle entity by id
     * @param orgRaffleId the id of the raffle to mine information from
     * @return the arraylist containing the information of the org raffle described by orgRaffleId
     */
    public ArrayList<Object> getOrgRaffleInfo(String orgRaffleId){
        ArrayList<Object> orgRaffleInfo = null;
        orgRaffleInfo = this.dataAccess.getOrganizerRaffleById(orgRaffleId);

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
