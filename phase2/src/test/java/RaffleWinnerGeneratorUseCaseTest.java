package test.java;

import main.java.DatabaseRe.AccessData;
import main.java.RaffleComponent.RaffleWinnerGeneratorUseCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RaffleWinnerGeneratorUseCaseTest {
    String orgRaffleId;
    String ptcId1;
    String ptcId2;
    RaffleWinnerGeneratorUseCase raffleManager;
    AccessData dataAccess;

    @Before
    public void setUp() throws Exception{
    orgRaffleId = "yes";
    ptcId1 = "yes";
    ptcId2 = "yes";
    raffleManager = new RaffleWinnerGeneratorUseCase(orgRaffleId);
    }

    @Test(timeout = 60)
    public void TestSuccessfulRun(){
        raffleManager.updateRaffleWinners();
        ArrayList<String> winnerIdList = (ArrayList<String>) dataAccess.getOrganizerRaffleById(orgRaffleId).get(6);
        assertTrue(winnerIdList.contains(ptcId1) || winnerIdList.contains(ptcId2));

    }
}
