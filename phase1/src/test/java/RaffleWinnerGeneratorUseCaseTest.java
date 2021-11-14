package test.java;

import main.java.RaffleComponent.OrganizerRaffleEntity;
import main.java.RaffleComponent.RaffleWinnerGeneratorUseCase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;

public class RaffleWinnerGeneratorUseCaseTest {

    //todo: check that generating two winners with two participants you get both, and generating 1, you get
    // a single string inside the winnerPtcList

    String raffleId;
    OrganizerRaffleEntity orgRaffle;
    RaffleWinnerGeneratorUseCase raffleManager;

    @Before
    public void setUp() throws Exception{

        raffleId = "R1001";

        orgRaffle = new OrganizerRaffleEntity("TestRaffle",
                2, LocalDate.of(2021, 12, 25));
        orgRaffle.setRaffleId(raffleId);
        orgRaffle.setRaffleRules("This is just text, not the law");
        // empty taskIdList since we are only concerned with the participantList
        ArrayList<String> raffleParticipantIds = new ArrayList<>();
        raffleParticipantIds.add("SubjectxXKittenXx");
        raffleParticipantIds.add("SubjectEdgyPomegranate99");
        raffleParticipantIds.add("SubjectLadaNiva1969");
        orgRaffle.setParticipantIdList(raffleParticipantIds);

        raffleManager = new RaffleWinnerGeneratorUseCase(raffleId);
        raffleManager.setOrgRaffle(orgRaffle);
    }

    @Test(timeout = 60)
    public void TestProperWinnerGeneration(){
        // 2 winners out of 3 participants, any two are valid
        ArrayList<String> winnerIdList = raffleManager.updateRaffleWinners();
        assertEquals(winnerIdList.size(), 2); // correct length
        assertNotEquals(winnerIdList.get(0), winnerIdList.get(1)); // no repeats
    }
}
