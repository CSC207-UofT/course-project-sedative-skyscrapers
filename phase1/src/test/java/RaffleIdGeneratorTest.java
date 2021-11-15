package test.java;

import java.util.ArrayList;

import main.java.Helpers.RaffleIdGenerator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RaffleIdGeneratorTest {

    // todo: test that filling up the takenIds, it still generated the one correct non-takenId
    String raffleId;
    ArrayList<String> takenIds;
    ArrayList<Integer> takenNumList;
    RaffleIdGenerator idGenerator;
    final char RAFFLE_CODE = 'R';

    @Before
    public void setUp() throws Exception{
        // fill all taken Ids except for "R9999"
        int i;
        for(i = 1000; i < 9998; i++){
            takenIds.add(String.valueOf(RAFFLE_CODE) + i);
        }
        idGenerator = new RaffleIdGenerator(takenIds);
        takenNumList = idGenerator.takenNumList(RAFFLE_CODE);
    }

    @Test(timeout = 60)
    public void TestCorrectRaffleId(){
        raffleId = idGenerator.generateEntityId(RAFFLE_CODE, takenNumList);
        assertEquals(raffleId, String.valueOf(RAFFLE_CODE) + 9999);
    }
}
