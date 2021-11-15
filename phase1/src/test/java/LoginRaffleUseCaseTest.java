package test.java;

import main.java.RaffleComponent.LoginRaffleUseCase;
import main.java.RaffleComponent.OrganizerRaffleEntity;
import test.java.UseCaseTestHelpers;

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
    String ptcId;
    UseCaseTestHelpers helper;


    @Before
    public void setUp() throws Exception {

        String orgRaffleId = "R1001";
        String incorrectOrgRaffleId = "R1002";

        ptcId = "UserNameSubject7844";  // the username of the participant joining

        orgRaffle = new OrganizerRaffleEntity("TestRaffle",
                2, LocalDate.of(2021, 12, 25));
        orgRaffle.setRaffleId(orgRaffleId);
        orgRaffle.setRaffleRules("Participant must be human, and preferably live within 10LY from Earth)");

        // setting up the dummy database information
        helper = new UseCaseTestHelpers();
        ArrayList<Object> orgRaffleInfo = helper.setupDummyOrgRaffleInfo(orgRaffle);
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
        correctRaffleManager.runRaffleLogin();
        assertEquals(correctRaffleManager.getLoginResult(), LoginRaffleUseCase.LoginResult.SUCCESS);
    }

    @Test(timeout = 60)
    public void TestFailedLogin(){
        wrongRaffleManager.runRaffleLogin();
        assertEquals(wrongRaffleManager.getLoginResult(), LoginRaffleUseCase.LoginResult.RAFFLE_ID_NOT_RECOGNIZED);
    }

    @Test(timeout = 60)
    public void TestCorrectAttributeCopy(){
        correctRaffleManager.runRaffleLogin();
        RaffleEntity ptcRaffle = correctRaffleManager.getRaffle();

        assertEquals(ptcRaffle.getRaffleName(), orgRaffle.getRaffleName());
        assertEquals(ptcRaffle.getEndDate(), orgRaffle.getEndDate());
        assertEquals(ptcRaffle.getNumberOfWinners(), orgRaffle.getNumberOfWinners());
        assertEquals(ptcRaffle.getRaffleRules(), orgRaffle.getRaffleRules());
        assertEquals(ptcRaffle.getTaskIdList(), orgRaffle.getTaskIdList());
    }

    @Test(timeout = 60)
    public void TestCorrectPtcRaffleId(){
        String ptcRaffleId = correctRaffleManager.runRaffleLogin();
        assertEquals(ptcRaffleId, ptcId + ":" + orgRaffle.getRaffleId());
    }

    @Test(timeout = 60)
    public void TestOrgRafflePtcListUpdated(){
        correctRaffleManager.runRaffleLogin();
        ArrayList<String> participantsSoFar = new ArrayList<>();
        participantsSoFar.add(ptcId);
        assertEquals(orgRaffle.getParticipantIdList(), participantsSoFar);


    }


}

