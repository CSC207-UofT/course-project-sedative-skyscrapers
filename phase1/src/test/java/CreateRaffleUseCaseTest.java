package test.java;

import main.java.RaffleComponent.CreateRaffleUseCase;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CreateRaffleUseCaseTest {
    CreateRaffleUseCase correctRaffleManager;
    ArrayList<String> takenRaffleIds;


    @Before
    public void setUp() throws Exception {

        takenRaffleIds = new ArrayList<>();
        correctRaffleManager = new CreateRaffleUseCase("TestRaffle0", 2,
                LocalDate.of(2021, 12, 25), "OrganizerSubject1006");

    }

    @Test(timeout = 60)
    public void TestSuccessfulCreation(){
        correctRaffleManager.runRaffleCreation();
        assertEquals(correctRaffleManager.getCreationOutcome(), CreateRaffleUseCase.CreationResult.SUCCESS);
        // create with non-registered registered id

    }

    public void TestSuccessfulCreationMessage(){
        correctRaffleManager.runRaffleCreation();
        assertNotNull(correctRaffleManager.runRaffleCreation());
        // verify we get a string

    }

    // there is no possible way for the creation to turn out as a fail due to how Ids are generated to not be repeated





}
