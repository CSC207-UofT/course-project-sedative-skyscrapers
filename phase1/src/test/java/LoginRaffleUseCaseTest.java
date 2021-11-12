package test.java;

import main.java.RaffleComponent.LoginRaffleUseCase;
import main.java.RaffleComponent.OrganizerRaffleEntity;

import main.java.RaffleComponent.RaffleEntity;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginRaffleUseCaseTest {

    HashMap<String, ArrayList<Object>> raffleMapping;
    LoginRaffleUseCase correctRaffleManager;
    LoginRaffleUseCase wrongRaffleManager;
    OrganizerRaffleEntity orgRaffle;


    @Before
    public void setUp() throws Exception {

        String raffleId = "R1001";
        String incorrectRaffleId = "R1002";
        orgRaffle = new OrganizerRaffleEntity("TestRaffle",
                2, LocalDate.of(2021, 12, 25));
        orgRaffle.setRaffleId(raffleId);

        ArrayList<Object> orgRaffleDetails = new ArrayList<>(); // todo: after making the object -> info arrayList class

        raffleMapping = new HashMap<String, ArrayList<Object>>();
        raffleMapping.put(raffleId, orgRaffleDetails);

        // login with already registered id
        correctRaffleManager = new LoginRaffleUseCase(raffleId, raffleMapping.getOrDefault(raffleId, null));

        // login with not registered id
        wrongRaffleManager = new LoginRaffleUseCase(incorrectRaffleId,
                raffleMapping.getOrDefault(incorrectRaffleId, null));


    }

    @Test(timeout = 60)
    public void TestSuccessfulLogin(){
        assertEquals(correctRaffleManager.runRaffleLogin(), LoginRaffleUseCase.LoginResult.SUCCESS);
    }

    @Test(timeout = 60)
    public void TestFailedLogin(){
        assertEquals(wrongRaffleManager.runRaffleLogin(), LoginRaffleUseCase.LoginResult.RAFFLE_ID_NOT_RECOGNIZED);
    }

    @Test(timeout = 60)
    public void TestCorrectAttributeCopy(){
        // execute login actions
        correctRaffleManager.runRaffleLogin();
        RaffleEntity ptcRaffle = correctRaffleManager.getRaffle();

        assertEquals(ptcRaffle.getRaffleName(), orgRaffle.getRaffleName());
        assertEquals(ptcRaffle.getRaffleId(), orgRaffle.getRaffleId());
        assertEquals(ptcRaffle.getEndDate(), orgRaffle.getEndDate());
        assertEquals(ptcRaffle.getNumberOfWinners(), orgRaffle.getNumberOfWinners());
    }

// todo
//    @Test(timeout = 60)
//    public void TestOrgRafflePtcListUpdated(){
//
//    }


}

