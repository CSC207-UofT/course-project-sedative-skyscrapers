package test.java;

import main.java.OrganizerRaffle;
import main.java.ParticipantRaffle;
import main.java.Participant;
import main.java.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ParticipantRaffleTest {

    User participant1;
    User participant2;
    ParticipantRaffle pRaff0;
    ParticipantRaffle pRaff1;
    OrganizerRaffle oRaff0;

    @Before
    public void setUp() throws Exception {
        participant1 = new User("John Doe");
        participant2 = new User("Doe John");

        oRaff0 = new OrganizerRaffle("Raffle0", 5, new Date(2021, 10, 21));

        pRaff0 = new ParticipantRaffle(participant1, 0);
        pRaff1 = new ParticipantRaffle(participant2, 0);
    }

    @Test(timeout = 60)
    public void TestParticipantRaffleID(){
        assertEquals(pRaff0.getRaffleID(), oRaff0.getRaffleID());
        assertEquals(pRaff1.getRaffleID(), oRaff0.getRaffleID());
    }
}
