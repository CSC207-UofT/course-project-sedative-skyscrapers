package test.java;

import main.java.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class RaffleObjectTest {

    User participant1;
    User participant2;
    ParticipantRaffle pRaff0;
    OrganizerRaffle oRaff0;
    Task task0;

    @Before
    public void setUp() throws Exception {
        participant1 = new User("Bob_the_gamer_880");
        participant2 = new User("xX_Alice_Kitten_Xx");

        task0 = new Task("Whats 2 + 2", "5");

        oRaff0 = new OrganizerRaffle(participant1, "Raffle0", 5, new Date(2021, 10, 21));
        oRaff0.addNewTask(task0);

        pRaff0 = new ParticipantRaffle(participant2, 0);  // participant 2 joins participant1's Raffle0
    }

    @Test(timeout = 60)
    public void TestSubclassRaffleID(){
        assertEquals(pRaff0.getRaffleID(), oRaff0.getRaffleID());
    }

    @Test(timeout = 60)
    public void TestSubclassRaffleTasks(){
        assertEquals(oRaff0.getTaskList(), pRaff0.getTasksToComplete());
    }
}
