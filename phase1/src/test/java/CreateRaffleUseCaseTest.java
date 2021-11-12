package test.java;

import main.java.RaffleComponent.CreateRaffleUseCase;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

public class CreateRaffleUseCaseTest {
    CreateRaffleUseCase correctRaffleManager;
    ArrayList<String> takenRaffleIds;


    @Before
    public void setUp() throws Exception {

        takenRaffleIds = new ArrayList<>();
        correctRaffleManager = new CreateRaffleUseCase("TestRaffle", 2,
                LocalDate.of(2021, 12, 25), takenRaffleIds);

    }

    @Test(timeout = 60)
    public void TestSuccessfulCreation(){
        assertEquals(correctRaffleManager.runRaffleCreation(), CreateRaffleUseCase.CreationResult.SUCCESS);
        // login with already registered id

    }





}
