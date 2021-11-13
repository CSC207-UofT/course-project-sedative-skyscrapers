package test.java;

import main.java.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class RaffleObjectTest {

    User organizer1;
    User organizer2;
    User participant1;
    User participant2;
    ParticipantRaffle pRaff0;
    ParticipantRaffle pRaff1;
    OrganizerRaffle oRaff0;
    OrganizerRaffle oRaff1;
    Task task0;

    @Before
    public void setUp() throws Exception {

        organizer1 = new User("Bob_the_gamer_880");
        organizer2 = new User("Bob_the_gamer_881");
        participant1 = new User("xX_Alice_Kitten_Xx");
        participant2 = new User("x_Alice_Kitten_x");

        task0 = new Task("Task A", "Subscribe to youtube channel", "youtube.com/pewdiepie");

        oRaff0 = organizer1.createRaffle("Raffle0", 5,
                LocalDate.of(2021, 10, 26));

        // to be changed for organizer1.addTask(task0)
        oRaff0.addNewTask(task0);


        oRaff1 = organizer2.createRaffle("Raffle1", 3,
                LocalDate.of(2021, 10, 23));

        // participant 1 joins organizer1's Raffle0
        pRaff0 = participant1.joinRaffle(oRaff0.getRaffleID());
        // participant 2 joins organizer2's Raffle0
        pRaff1 = participant2.joinRaffle(oRaff1.getRaffleID());
    }

    @Test(timeout = 60)
    public void TestSubclassRaffleID(){
        assertEquals(oRaff0.getRaffleID(), pRaff0.getRaffleID());
        assertEquals(oRaff1.getRaffleID(), pRaff1.getRaffleID());
    }

    @Test(timeout = 60)
    public void TestSubclassRaffleTasks(){
        assertEquals(oRaff0.getTaskList(), pRaff0.getTasksReq());
    }

    @Test(timeout = 60)
    public void TestRaffleLinkageV1(){
        // testing whether an attribute other than the User input raffleID gets properly copied.
        assertEquals(oRaff0.getEndDate(), pRaff0.getEndDate());
    }

    @Test(timeout = 60)
    public void TestRaffleLinkageV2(){
        // testing whether an attribute other than the User input raffleID gets properly copied
        assertEquals(oRaff0.getOrganizer().getUsername(), pRaff0.getOrganizer().getUsername());
    }
}

