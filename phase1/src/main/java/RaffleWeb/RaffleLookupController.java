package main.java.RaffleWeb;

import main.java.RaffleComponent.RaffleLookupUseCase;

import java.util.ArrayList;
import java.util.HashMap;

public class RaffleLookupController {
    // in charge of passing DB data to the GUI

//    private final String orgRaffleId;
    private final RaffleLookupUseCase raffleInfoManager;

    /**
     * Constructor of the controller handling the data extraction and transportation to the GUI from the database
     */
    public RaffleLookupController() {
//        this.orgRaffleId = orgRaffleId;
        this.raffleInfoManager = new RaffleLookupUseCase();
    }

    /**
     * Fetches data from all organizer raffle objects in the database
     * @return hashmap of format {orgRaffleId:orgRaffleInfo}
     */
    public HashMap<String, ArrayList<Object>> runLookupAllRaffleInfo() {
        return this.raffleInfoManager.getAllOrgRaffleInfo();
    }

    /**
     * Determines whether an organizer raffle id exists
     * @param orgRaffleId the id whose existence is in question
     * @return a bool representing the answer to whether the orgRaffleId input exists or not
     */
    public boolean runRaffleIdExists(String orgRaffleId) {
        return this.raffleInfoManager.raffleIdExists(orgRaffleId);
    }

    /**
     * Fetches data from a specific participant raffle entity by id
     * @param ptcRaffleId the id of the raffle to mine information from
     * @return the arraylist containing the information of the ptc raffle described by ptcRaffleId
     */
    public ArrayList<Object> runLookupPtcRaffleInfo(String ptcRaffleId) {
        return this.raffleInfoManager.getPtcRaffleInfo(ptcRaffleId);
    }

    /**
     * Fetches data from a specific organizer raffle entity by id
     * @param orgRaffleId the id of the raffle to mine information from
     * @return the arraylist containing the information of the org raffle described by orgRaffleId
     */
    public ArrayList<Object> runLookupOrgRaffleInfo(String orgRaffleId) {
        return this.raffleInfoManager.getOrgRaffleInfo(orgRaffleId);
    }

    /**
     * Fetches the winnerIdList attribute of an organizer raffle entity
     * @param orgRaffleId describes the raffle object from which to mine the winnerIdList
     * @return the arraylist of strings referring to the usernames of the raffle's winners
     */
    public ArrayList<String> runLookUpRaffleWinners(String orgRaffleId){
        return this.raffleInfoManager.getWinnersList(orgRaffleId);
    }

}