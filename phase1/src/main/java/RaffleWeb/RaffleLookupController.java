package main.java.RaffleWeb;

import main.java.RaffleComponent.RaffleLookupUseCase;

import java.util.ArrayList;
import java.util.HashMap;

public class RaffleLookupController {
    // in charge of passing DB data to the GUI

    private final String orgRaffleId;
    private final RaffleLookupUseCase raffleInfoManager;


    public RaffleLookupController(String orgRaffleId) {
        this.orgRaffleId = orgRaffleId;
        this.raffleInfoManager = new RaffleLookupUseCase(this.orgRaffleId);
    }

    public HashMap<String, ArrayList<Object>> runLookupAllRaffleInfo() {
        return RaffleLookupUseCase.getAllOrgRaffleInfo();
    }

    public ArrayList<Object> runLookupPtcRaffleInfo() {
        return this.raffleInfoManager.getPtcRaffleInfo();
    }

    /* todo: for getting raffle Info from a particular organizer, just use the user class' method to
     return the raffleIds they are involved in, and call runLookupPtcRaffleInfo for each of them
     */
}