package test.java;

import main.java.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class UserTest {

    User organizer1;
    User participant1;
    OrganizerRaffle orgRaffle;
    ParticipantRaffle ptcRaffle;

    @Before
    public void setUp() throws Exception {

        organizer1 = new User("Bob_the_gamer_880");
        participant1 = new User("Goth_emoHammer99");

        orgRaffle = organizer1.createRaffle("Raffle0", 5,
                LocalDate.of(2021, 10, 26));
        organizer1.addTask(orgRaffle.getRaffleID(), "2+2", "4");

        ptcRaffle = participant1.joinRaffle(orgRaffle.getRaffleID());
    }

    @Test(timeout = 60)
    public void TestCreateRaffleName(){
        assertEquals("Raffle0", organizer1.createRaffle("Raffle0", 5,
                LocalDate.of(2021, 10, 26)).getRaffleName());
    }

    @Test(timeout = 60)
    public void TestNonEmptyAllRaffles(){
        assertEquals(Raffle.allRaffles.get(orgRaffle.getRaffleID()).getRaffleName(), "Raffle0");
    }

    @Test(timeout = 60)
    public void TestAddTask(){
        assertEquals(orgRaffle.getTaskList().get(0).getAnswer(), "4");
    }

    @Test(timeout = 60)
    public void TestPtcTaskList(){
        assertEquals(orgRaffle.getTaskList().get(0).getAnswer(), ptcRaffle.getTaskList().get(0).getAnswer());
    }

}
