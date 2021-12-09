package test.java;

import java.util.ArrayList;

import main.java.Helpers.EntityIdGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RaffleIdGeneratorTest {

    String raffleId;
    int highValue;
    int lowValue;
    ArrayList<String> takenIds;
    ArrayList<Integer> takenNumList;
    EntityIdGenerator idGenerator;
    final char RAFFLE_CODE = 'R';

    @Before
    public void setUp() throws Exception {
        // fill all taken Ids except for "R9999"
        takenIds = new ArrayList<>();
        int i;
        for (i = 1000; i < 5000; i++) {
            takenIds.add(String.valueOf(RAFFLE_CODE) + i);
        }
        idGenerator = new EntityIdGenerator(takenIds);
        takenNumList = idGenerator.takenNumList(RAFFLE_CODE);
        highValue = 9999;
        lowValue = 5000;
    }

    @Test(timeout = 60)
    public void TestCorrectRaffleId() {
        int i;
        for (i = 0; i < 100; i++) {
            raffleId = idGenerator.generateEntityId(RAFFLE_CODE, takenNumList);
            int raffleIdNum = Integer.parseInt(raffleId.substring(1));
            assertTrue(raffleIdNum >= lowValue);
            assertTrue(raffleIdNum <= highValue);
            takenNumList.add(raffleIdNum);
        }

    }
}
