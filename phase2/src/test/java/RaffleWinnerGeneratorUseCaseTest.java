package test.java;

import main.java.DatabaseRe.AccessData;
import main.java.RaffleComponent.RaffleWinnerGeneratorUseCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RaffleWinnerGeneratorUseCaseTest {
    String orgRaffleId;
    RaffleWinnerGeneratorUseCase raffleManager;
    AccessData dataAccess;

    @Before
    public void setUp() throws Exception{
    orgRaffleId = "R5870";
    raffleManager = new RaffleWinnerGeneratorUseCase(orgRaffleId);
    dataAccess = new AccessData();
    }

    @Test(timeout = 10000)
    public void TestSuccessfulRun(){
        raffleManager.updateRaffleWinners();
        // no ptcs signed up
        ArrayList<String> winnerIdList = (ArrayList<String>)dataAccess.getOrganizerRaffleById(orgRaffleId).get(6);
        assertTrue(winnerIdList.isEmpty());

    }
}
