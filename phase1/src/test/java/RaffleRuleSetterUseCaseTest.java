//package test.java;
//
//import main.java.RaffleComponent.OrganizerRaffleEntity;
//import main.java.RaffleComponent.RaffleRuleSetterUseCase;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.Assert.assertEquals;
//
//public class RaffleRuleSetterUseCaseTest {
//
//    String ruleString;
//    String raffleId;
//    OrganizerRaffleEntity orgRaffle;
//    RaffleRuleSetterUseCase raffleManager;
//    UseCaseTestHelpers helper;
//
//    @Before
//    public void setUp() throws Exception{
//        ruleString = "Participants must sleep a minimum of 5 hours a day or/and not be UofT students";
//        raffleId = "R1001";
//
//        orgRaffle = new OrganizerRaffleEntity("TestRaffle",
//                2, LocalDate.of(2021, 12, 25));
//        orgRaffle.setRaffleId(raffleId);
//
//        helper = new UseCaseTestHelpers();
//        //raffleManager = new RaffleRuleSetterUseCase(raffleId, ruleString);
//        raffleManager.setOrgRaffle(orgRaffle);
//    }
//
//    @Test(timeout = 60)
//    public void TestCorrectStringSetUp(){
//        raffleManager.updateRules();
//        assertEquals(raffleManager.getOrgRaffle().getRaffleRules(), ruleString);
//    }
//}
