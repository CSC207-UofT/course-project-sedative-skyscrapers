package test.java;

import main.java.DatabaseRe.AccessData;
import main.java.RaffleComponent.CreateRaffleUseCase;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CreateRaffleUseCaseTest {
    String raffleName;
    int numOfWinners;
    LocalDate endDate;
    String organizerUsername;
    CreateRaffleUseCase raffleManager;
    AccessData dataAccess;

    @Before
    public void setUp() throws Exception {
        raffleName = "SampleRaffle";
        numOfWinners = 1;
        endDate = LocalDate.of(2021, 12, 9);
        organizerUsername = "MicheleOrg";
        raffleManager = new CreateRaffleUseCase(raffleName, numOfWinners, endDate, organizerUsername);
        dataAccess = new AccessData();
    }

    @Test(timeout = 60)
    public void TestSuccessfulRun(){
        raffleManager.runRaffleCreation();
        String orgRaffleId = raffleManager.getGeneratedRaffleId();
        // orgRaffleId must now be storing the created raffle's information
        assertFalse(dataAccess.getOrganizerRaffleById(orgRaffleId).isEmpty());

    }


}
