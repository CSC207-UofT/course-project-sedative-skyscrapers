package test.java;

import main.java.OrganizerRaffle;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class OrganizerRaffleTest {

    OrganizerRaffle raff0;
    OrganizerRaffle raff1;

    @Before
    public void setUp() throws Exception {
        raff0 = new OrganizerRaffle("Raffle0", 5, new Date(2021, 10, 21));
        raff1 = new OrganizerRaffle("Raffle1", 3, new Date(2021, 11, 27));
    }

    @Test(timeout = 50)
    public void TestRaffleID(){
        assertEquals(0, raff0.getRaffleID());
        assertEquals(0, raff1.getRaffleID());
    }

}
