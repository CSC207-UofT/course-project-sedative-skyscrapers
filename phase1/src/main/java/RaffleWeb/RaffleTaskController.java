package main.java.RaffleWeb;

import main.java.RaffleComponent.OrgRaffleEditTaskUseCase;

import java.util.ArrayList;
import java.util.HashMap;

public class RaffleTaskController {

//    public enum TaskEditTypes{
//        ORGANIZER_ADD, ORGANIZER_REMOVE
//    }

    private String raffleId;  // provided by System which will never be null as long as you are inside a raffle subpage

    /* orgAllRaffles is a hashmap from raffleId to an array of objects that are contained in an orgRaffle object
    EG:
    key: "R1002"; corresponding value: [raffleName="raffle", numberOfWinners=2, rules="Age > 18",
        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
        winnerIds=ArrayList<String>]
    ... and we get this hashmap for all existing raffles in the program (through a method in db)
    */

    public ArrayList<Object> raffleInfoSoFar;


    public RaffleTaskController(String raffleId, ArrayList<Object> raffleInfoSoFar) {
        this.raffleId = raffleId;
        this.raffleInfoSoFar = raffleInfoSoFar;

    }

    public OrgRaffleEditTaskUseCase.OrgTaskEditOutcome runEditOrgTaskList(
            OrgRaffleEditTaskUseCase.TaskEditTypes editToPerform, ArrayList<String> taskIds) {

        OrgRaffleEditTaskUseCase raffleManager;
        OrgRaffleEditTaskUseCase.OrgTaskEditOutcome result = OrgRaffleEditTaskUseCase.OrgTaskEditOutcome.STANDBY;

        switch (editToPerform){
            case ORGANIZER_ADD:
                // here raffleId IS in orgAllRaffles, since this class is only accessible within a raffle's subpage
                raffleManager = new OrgRaffleEditTaskUseCase(this.raffleId, taskIds, this.raffleInfoSoFar);
                result = raffleManager.addTask();
                if (result.equals(OrgRaffleEditTaskUseCase.OrgTaskEditOutcome.SUCCESSFULLY_ADDED)){
                    raffleManager.updatePtcRaffles(taskIds, editToPerform);
                }

            case ORGANIZER_REMOVE:
                // here raffleId IS in orgAllRaffles, since this class is only accessible within a raffle's subpage
                raffleManager = new OrgRaffleEditTaskUseCase(this.raffleId, taskIds, this.raffleInfoSoFar);
                result = raffleManager.removeTask();
                if (result.equals(OrgRaffleEditTaskUseCase.OrgTaskEditOutcome.SUCCESSFULLY_REMOVED)){
                    raffleManager.updatePtcRaffles(taskIds, editToPerform);
                }

        }

        return result;
    }

}
