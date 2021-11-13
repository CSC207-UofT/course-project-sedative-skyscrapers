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

//    private HashMap<String, ArrayList<Object>> orgAllRaffles;  // provided by db
//    private HashMap<String, ArrayList<Object>> ptcAllRaffles; // provided by db


    public RaffleTaskController(String raffleId, HashMap<String, ArrayList<Object>> dbOrgRaffles,
                                HashMap<String, ArrayList<Object>> dbPtcRaffles) {
        this.raffleId = raffleId;
//        this.orgAllRaffles = dbOrgRaffles;
//        this.ptcAllRaffles = dbPtcRaffles;

    }

    public OrgRaffleEditTaskUseCase.OrgTaskEditOutcome runEditOrgTaskList(
            OrgRaffleEditTaskUseCase.TaskEditTypes editToPerform, String taskId) {

        OrgRaffleEditTaskUseCase raffleManager;
        OrgRaffleEditTaskUseCase.OrgTaskEditOutcome result = OrgRaffleEditTaskUseCase.OrgTaskEditOutcome.STANDBY;

        switch (editToPerform){
            case ORGANIZER_ADD:
                // here raffleId IS in orgAllRaffles, since this class is only accessible within a raffle's subpage
                raffleManager = new OrgRaffleEditTaskUseCase(this.raffleId, taskId);
                result = raffleManager.addTask();
                raffleManager.updatePtcRaffles(taskId, editToPerform);

            case ORGANIZER_REMOVE:
                // here raffleId IS in orgAllRaffles, since this class is only accessible within a raffle's subpage
                raffleManager = new OrgRaffleEditTaskUseCase(this.raffleId, taskId);
                result = raffleManager.removeTask();
                raffleManager.updatePtcRaffles(taskId, editToPerform);
        }

        return result;
    }

}
