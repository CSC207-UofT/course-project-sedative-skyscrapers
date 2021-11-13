package test.java;

import main.java.RaffleComponent.LoginRaffleUseCase;
import main.java.RaffleComponent.OrganizerRaffleEntity;

import main.java.RaffleComponent.RaffleEntity;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.IconUIResource;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginRaffleUseCaseTest {

    HashMap<String, ArrayList<Object>> raffleMapping;
    LoginRaffleUseCase correctRaffleManager;
    LoginRaffleUseCase wrongRaffleManager;
    OrganizerRaffleEntity orgRaffle;
    String ptcId;


    @Before
    public void setUp() throws Exception {

        String orgRaffleId = "UsernameSubject1457:R1001";
        ptcId = "UserNameSubject7844";  // the id provided by the participant joining
        String incorrectOrgRaffleId = "UsernameSubject1457:R1002";
        orgRaffle = new OrganizerRaffleEntity("TestRaffle",
                2, LocalDate.of(2021, 12, 25));
        orgRaffle.setRaffleId(orgRaffleId);
        orgRaffle.setRaffleRules("Participant must be human, and preferably live within 10LY from Earth)");

        ArrayList<Object> orgRaffleInfo = new ArrayList<>(); // todo: after making the object -> info arrayList class
        orgRaffleInfo.add(orgRaffle.getRaffleName());
        orgRaffleInfo.add(orgRaffle.getNumberOfWinners());
        orgRaffleInfo.add(orgRaffle.getRaffleRules());
        orgRaffleInfo.add(orgRaffle.getEndDate());
        orgRaffleInfo.add(orgRaffle.getTaskIdList());
        raffleMapping = new HashMap<>();
        raffleMapping.put(orgRaffleId, orgRaffleInfo);

        // login with already registered id
        correctRaffleManager = new LoginRaffleUseCase(orgRaffleId, ptcId);
        correctRaffleManager.setOrgRaffleInfo(raffleMapping.getOrDefault(orgRaffleId, null));

        // login with not registered id
        wrongRaffleManager = new LoginRaffleUseCase(incorrectOrgRaffleId, ptcId);
        wrongRaffleManager.setOrgRaffleInfo(raffleMapping.getOrDefault(incorrectOrgRaffleId, null));

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
        assertEquals(ptcRaffle.getRaffleRules(), orgRaffle.getRaffleRules());
        assertEquals(ptcRaffle.getTaskIdList(), orgRaffle.getTaskIdList());
    }


    @Test(timeout = 60)
    public void TestOrgRafflePtcListUpdated(){
        correctRaffleManager.runRaffleLogin();
        ArrayList<String> ptcsSoFar = new ArrayList<>();
        ptcsSoFar.add(ptcId);
        assertEquals(orgRaffle.getParticipantIdList(), ptcsSoFar);


    }


}

