package test.java;

import main.java.DatabaseRe.AccessData;
import main.java.RaffleComponent.CompleteTaskUseCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CompleteTaskUseCaseTest {
    CompleteTaskUseCase raffleManager;
    String ptcRaffleId;
    String taskIdToComplete;
    AccessData dataAccess;

    @Before
    public void setUp() throws Exception{
        ptcRaffleId = "yes"; //<idGenerated>;
        taskIdToComplete = "yes"; // <idGenerated>
        raffleManager = new CompleteTaskUseCase(ptcRaffleId, taskIdToComplete);
        dataAccess = new AccessData();
    }

    @Test(timeout = 60)
    public void TestSuccessfulRun(){
        raffleManager.completeTask();
        // this taskId must now not be available for this raffle
        ArrayList<String> updatedTaskList = (ArrayList<String>) dataAccess.getParticipantRaffleById(this.ptcRaffleId).get(6);

        assertFalse(updatedTaskList.contains(taskIdToComplete));
        // musrat be false
    }

}