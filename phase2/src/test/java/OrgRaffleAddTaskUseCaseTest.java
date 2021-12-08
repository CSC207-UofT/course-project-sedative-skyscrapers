package test.java;

import main.java.DatabaseRe.AccessData;
import main.java.RaffleComponent.OrgRaffleAddTaskUseCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class OrgRaffleAddTaskUseCaseTest {
    OrgRaffleAddTaskUseCase raffleManager;
    String orgRaffleId;
    ArrayList<String> taskIds;
    String taskId1;
    String taskId2;
    AccessData dataAccess;

    @Before
    public void setUp() throws Exception {
    orgRaffleId = "yes";
    taskId1 = "yes";
    taskId2 = "yes";
    ArrayList<String> taskIdsArray = new ArrayList<>();
    taskIdsArray.add(taskId1);
    taskIdsArray.add(taskId2);
    taskIds = taskIdsArray;
    dataAccess = new AccessData();
    raffleManager = new OrgRaffleAddTaskUseCase(orgRaffleId, taskIds);
    }

    @Test(timeout = 60)
    public void TestSuccessfulRun(){
        raffleManager.updateTaskList();
        ArrayList<String> updatedTaskList = (ArrayList<String>)dataAccess.getOrganizerRaffleById(orgRaffleId).get(4);
        // orgRaffle must contain these newly added taskIds
        assertTrue(updatedTaskList.contains(taskId1) && updatedTaskList.contains(taskId2));
    }


}
