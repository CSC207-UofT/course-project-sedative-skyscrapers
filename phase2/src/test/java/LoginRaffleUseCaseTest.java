package test.java;

 import main.java.DatabaseRe.AccessData;
 import main.java.RaffleComponent.LoginRaffleUseCase;

import org.junit.Before;
import org.junit.Test;

 import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class LoginRaffleUseCaseTest {

    LoginRaffleUseCase raffleManager;
    String orgRaffleIdToJoin;
    String ptcId;
    AccessData dataAccess;

    @Before
    public void setup() throws Exception{
        orgRaffleIdToJoin = "yes";
        ptcId = "yes";
        raffleManager = new LoginRaffleUseCase(orgRaffleIdToJoin, ptcId);
        dataAccess = new AccessData();
    }

    @Test(timeout = 60)
    public void TestSuccessfulRun(){
        raffleManager.runRaffleLogin();
        // this ptcId must now be stored inside the organizer raffle
        ArrayList<String> updatedPtcIds = (ArrayList<String>)dataAccess.getOrganizerRaffleById(orgRaffleIdToJoin).get(5);

        assertTrue(updatedPtcIds.contains(ptcId));
        // must be true
    }

}

