package main.java.RaffleWeb;

import main.java.RaffleComponent.RaffleLookupUseCase;

import java.util.ArrayList;
import java.util.HashMap;

public class RaffleLookupController {
    // in charge of passing DB data to the GUI

//    private final String orgRaffleId;
    private final RaffleLookupUseCase raffleInfoManager;


    public RaffleLookupController() {
//        this.orgRaffleId = orgRaffleId;
        this.raffleInfoManager = new RaffleLookupUseCase();
    }

    public HashMap<String, ArrayList<Object>> runLookupAllRaffleInfo() {
        return RaffleLookupUseCase.getAllOrgRaffleInfo();
    }

    public ArrayList<Object> runRaffleIdExists(String orgRaffleId) {
        return this.raffleInfoManager.raffleIdExists(orgRaffleId);
    }

    public ArrayList<Object> runLookupPtcRaffleInfo(String ptcRaffleId) {
        return this.raffleInfoManager.getPtcRaffleInfo(ptcRaffleId);
    }

    public ArrayList<Object> runLookupOrgRaffleInfo(String orgRaffleId) {
        return this.raffleInfoManager.getOrgRaffleInfo(orgRaffleId);
    }



    /* todo: for getting raffle Info from a particular organizer, just use the user class' method to
     return the raffleIds they are involved in, and call runLookupPtcRaffleInfo for each of them
     */
}