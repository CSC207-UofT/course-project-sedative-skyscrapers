package test.java;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import main.java.DatabaseRe.AccessData;
import main.java.TaskComponent.CreateTaskUseCase;
import main.java.RaffleComponent.CreateRaffleUseCase;

import static org.junit.Assert.*;

public class CreateTaskUseCaseTest {
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

    @Before
    public void setUp() throws Exception {
        raffleName = "SampleRaffla";
        numOfWinners = 1;
        enddate = LocalDate.of(2021, 12, 10);
        organizerUsername = "TestOra";
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
        assertFalse(dataAccess.getTaskById(taskID).isEmpty());
    }
}
