// USE CASE UNDER MAINTENANCE, TO BE REOPENED IN PHASE2
//
//package test.java;
//
//import main.java.RaffleComponent.OrgRaffleEditTaskUseCase;
//import main.java.RaffleComponent.OrganizerRaffleEntity;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import static org.junit.Assert.assertEquals;
//
//public class OrganizerRaffleEditTaskUseCaseTest {
//
//    String taskIdToAdd;
//    String correctTaskIdToRemove;
//    String wrongTaskIdToRemove;
//    String solePtcId;
//    OrgRaffleEditTaskUseCase correctRaffleManager1;
//    OrgRaffleEditTaskUseCase correctRaffleManager2;
//    OrgRaffleEditTaskUseCase wrongRaffleManager;
//    OrganizerRaffleEntity orgRaffle;
//    HashMap<String, ArrayList<Object>> ptcAllRaffles;
//    UseCaseTestHelpers helper;
//
//
//    @Before
//    public void setUp() throws Exception {
//
//        taskIdToAdd = "T1001";
//        correctTaskIdToRemove = "T1002";
//        wrongTaskIdToRemove = "T1003";
//        solePtcId = "ptc1067";
//
//        orgRaffle = new OrganizerRaffleEntity("TestRaffle",
//                2, LocalDate.of(2021, 12, 25));
//        orgRaffle.setRaffleId("R1001");
//        orgRaffle.setRaffleRules("Some rules POGCHAMP");
//        // "T1002" already in dummy database
//        ArrayList<String> presentTaskIds = new ArrayList<>();
//        presentTaskIds.add(correctTaskIdToRemove);
//        presentTaskIds.add("T1004");
//        orgRaffle.setTaskIdList(presentTaskIds);
//        // ptcIdList and winnerList stay empty
//
//        // setting up the dummy database information
//        helper = new UseCaseTestHelpers();
//        ArrayList<Object> ptcRaffleInfo = helper.setupDummyPtcRaffleInfo(orgRaffle.getRaffleName(),
//                orgRaffle.getNumberOfWinners(), orgRaffle.getRaffleRules(), orgRaffle.getEndDate(),
//                orgRaffle.getTaskIdList());
//        ptcAllRaffles = new HashMap<>();
//        ptcAllRaffles.put(solePtcId, ptcRaffleInfo);
//
//
//        // try to add a task
//        correctRaffleManager1 = new OrgRaffleEditTaskUseCase("R1001", taskIdToAdd);
//        correctRaffleManager1.setOrgRaffle(orgRaffle);
//        correctRaffleManager1.setPtcAllRaffles(ptcAllRaffles);
//
//        // try to remove an existent task
//        correctRaffleManager2 = new OrgRaffleEditTaskUseCase("R1001", correctTaskIdToRemove);
//        correctRaffleManager2.setOrgRaffle(orgRaffle);
//        correctRaffleManager2.setPtcAllRaffles(ptcAllRaffles);
//
//        // try to remove non-existent task
//        wrongRaffleManager = new OrgRaffleEditTaskUseCase("R1001", wrongTaskIdToRemove);
//        wrongRaffleManager.setOrgRaffle(orgRaffle);
//
//    }
//
//    @Test(timeout = 60)
//    public void TestSuccessfulAdd(){
//        assertEquals(correctRaffleManager1.addTask(), OrgRaffleEditTaskUseCase.OrgTaskEditOutcome.SUCCESSFULLY_ADDED);
//    }
//
//    @Test(timeout = 60)
//    public void TestSuccessfulRemove(){
//        assertEquals(correctRaffleManager2.removeTask(),
//                OrgRaffleEditTaskUseCase.OrgTaskEditOutcome.SUCCESSFULLY_REMOVED);
//    }
//
//    @Test(timeout = 60)
//    public void TestWrongRemove(){
//        assertEquals(wrongRaffleManager.removeTask(),
//                OrgRaffleEditTaskUseCase.OrgTaskEditOutcome.FAILED_TO_REMOVE);
//    }
//
//    @Test(timeout = 60)
//    public void TestPtcRafflesUpdated1(){
//        correctRaffleManager1.addTask();
//        correctRaffleManager1.updatePtcRaffles(taskIdToAdd, OrgRaffleEditTaskUseCase.TaskEditTypes.ORGANIZER_ADD);
//        ArrayList<String> updatedTaskIdList =
//                (ArrayList<String>) correctRaffleManager1.getPtcAllRaffles().get(solePtcId).get(4);
//        assertEquals(updatedTaskIdList, orgRaffle.getTaskIdList());
//    }
//
//    @Test(timeout = 60)
//    public void TestPtcRafflesUpdated2(){
//        correctRaffleManager2.removeTask();
//        correctRaffleManager2.updatePtcRaffles(correctTaskIdToRemove,
//                OrgRaffleEditTaskUseCase.TaskEditTypes.ORGANIZER_REMOVE);
//        ArrayList<String> updatedTaskIdList =
//                (ArrayList<String>) correctRaffleManager1.getPtcAllRaffles().get(solePtcId).get(4);
//        assertEquals(updatedTaskIdList, orgRaffle.getTaskIdList());
//    }
//
//}
