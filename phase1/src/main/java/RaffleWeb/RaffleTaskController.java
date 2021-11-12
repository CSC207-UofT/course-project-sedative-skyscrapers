package main.java.RaffleWeb;

import main.java.RaffleComponent.OrgRaffleEditTaskUseCase;

import java.util.ArrayList;
import java.util.HashMap;

public class RaffleTaskController {

    public enum TaskEditTypes{
        ORGANIZER_ADD, ORGANIZER_REMOVE
    }

    private String raffleId;  // provided by System which will never be null as long as you are inside a raffle subpage

    /* orgAllRaffles is a hashmap from raffleId to an array of objects that are contained in an orgRaffle object
    EG:
    key: "R1002"; corresponding value: [raffleName="raffle", numberOfWinners=2, rules="Age > 18",
        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
        winnerIds=ArrayList<String>]
    ... and we get this hashmap for all existing raffles in the program (through a method in db)
    */

    private HashMap<String, ArrayList<Object>> orgAllRaffles;  // provided by db
    private HashMap<String, ArrayList<Object>> ptcAllRaffles; // provided by db


    public RaffleTaskController(String raffleId, HashMap<String, ArrayList<Object>> dbOrgRaffles,
                                HashMap<String, ArrayList<Object>> dbPtcRaffles) {
        this.raffleId = raffleId;
        this.orgAllRaffles = dbOrgRaffles;
        this.ptcAllRaffles = dbPtcRaffles;

    }

    public OrgRaffleEditTaskUseCase.OrgTaskEditOutcome runEditOrgTaskList(TaskEditTypes editToPerform, String taskId) {

        OrgRaffleEditTaskUseCase RaffleManager;
        OrgRaffleEditTaskUseCase.OrgTaskEditOutcome result = OrgRaffleEditTaskUseCase.OrgTaskEditOutcome.STANDBY;

        switch (editToPerform){
            case ORGANIZER_ADD:
                // here raffleId IS in orgAllRaffles, since this class is only accessible within a raffle's subpage
                RaffleManager = new OrgRaffleEditTaskUseCase(this.raffleId, taskId,
                        this.orgAllRaffles.get(this.raffleId));
                result = RaffleManager.addTask();
            case ORGANIZER_REMOVE:
                // here raffleId IS in orgAllRaffles, since this class is only accessible within a raffle's subpage
                RaffleManager = new OrgRaffleEditTaskUseCase(this.raffleId, taskId,
                        this.orgAllRaffles.get(this.raffleId));
                result = RaffleManager.removeTask();
        }


        // todo
        // store these changes to the OrganizerRaffle object in the database
        // and reflect these changes in the RaffleEntity objects of participants
        this.updatePtcRaffles(taskId);
        return result;
    }



    public void updatePtcRaffles(String taskId){
        for (String idKey: this.ptcAllRaffles.keySet()){
            // idKey for this.ptcAllRaffles has the format: "<userId> : <raffleId>"
            String[] idArray = idKey.split(":"); // yield the userId as index 0 and raffleId as index 1

            // if the special raffleId for ptcRaffles refers to the correct raffle, add the taskId
            if (idArray[1].equals(this.raffleId)){
                ArrayList<String> ptcRaffleTaskList = (ArrayList<String>)this.ptcAllRaffles.get(idKey).get(4);
                ptcRaffleTaskList.add(taskId);
                // update ptcAllRaffles respectively
                this.ptcAllRaffles.get(idKey).set(4, ptcRaffleTaskList);
            }
        }
        // todo: consider taking this out into a use case

    }

//    public ArrayList<Object> getRaffleInfo(){
//
//        ArrayList<Object> result = null;
//
//        for (String idKey: this.orgAllRaffles.keySet()){
//            if (idKey.equals(this.raffleId)){
//                result = this.orgAllRaffles.get(this.raffleId);
//            }
//        }
//
//        // if result stays as null, this case is handled in the use case
//        return result;
//    }
}
