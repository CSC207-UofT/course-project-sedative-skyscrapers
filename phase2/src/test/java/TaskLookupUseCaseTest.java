package test.java;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import main.java.DatabaseRe.AccessData;
import main.java.TaskComponent.CreateTaskUseCase;
import main.java.RaffleComponent.CreateRaffleUseCase;
import main.java.TaskComponent.TaskLookupUseCase;

import static org.junit.Assert.*;


public class TaskLookupUseCaseTest {
    String raffleID;
    String name;
    String description;
    String link;
    CreateTaskUseCase taskManager;
    AccessData dataAccess;

    //to make raffle
    String raffleName;
    int numOfWinners;
    LocalDate enddate;
    String organizerUsername;
    CreateRaffleUseCase raffleManager;
    TaskLookupUseCase lookupManager;

    @Before
    public void setUp() throws Exception {
        raffleName = "SampleRaffle11";
        numOfWinners = 1;
        enddate = LocalDate.of(2021, 12, 10);
        organizerUsername = "TestOrg";
        raffleManager = new CreateRaffleUseCase(raffleName, numOfWinners, enddate, organizerUsername);
        dataAccess = new AccessData();

        name = "TestTask";
        description = "open the link";
        link = "https://www.google.com";
    }

    @Test(timeout = 100000)
    public void TestSuccessfulRun() throws SQLException {
        raffleManager.runRaffleCreation();
        String orgRaffleId = raffleManager.getGeneratedRaffleId();
        raffleID = orgRaffleId;
        taskManager = new CreateTaskUseCase(raffleID, name, description, link);
        String taskID = taskManager.runTaskCreation();
        lookupManager = new TaskLookupUseCase(taskID);
        ArrayList<String> taskDetails = dataAccess.getTaskById(taskID);
        System.out.println("??" + taskDetails.get(1) + " "+ taskDetails.get(3) + " " + taskDetails.get(2));
        assertEquals("TestTask+", taskDetails.get(1));
        assertEquals("open the link", taskDetails.get(3));
        assertEquals("https://www.google.com", taskDetails.get(2));
    }

}
