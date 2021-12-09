package test.java;

import main.java.DatabaseRe.AccessData;
import main.java.RaffleComponent.RaffleRuleSetterUseCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RaffleRuleSetterUseCaseTest {
    RaffleRuleSetterUseCase raffleManager;
    String orgRaffleId;
    String updateRuleString;
    AccessData dataAccess;

    @Before
    public void setUp() throws Exception {
        orgRaffleId = "R9097";
        updateRuleString = "These are the new rules B)";
        raffleManager = new RaffleRuleSetterUseCase(orgRaffleId, updateRuleString);
        dataAccess = new AccessData();
    }

    @Test(timeout = 10000)
    public void TestSuccessfulRun() {
        raffleManager.updateRules();
        // this rulesString must not be set as the orgRaffle's rules
        String updatedString = (String) dataAccess.getOrganizerRaffleById(orgRaffleId).get(2);
        assertEquals(updateRuleString, updatedString);


    }
}
