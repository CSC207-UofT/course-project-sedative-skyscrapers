package main.java.RaffleComponent;

import main.java.RaffleComponent.OrganizerRaffleEntity;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrgRaffleEditTaskUseCase {

    public enum OrgTaskEditOutcome{
        SUCCESSFULLY_ADDED, SUCCESSFULLY_REMOVED, FAILED_TO_REMOVE, STANDBY
        // the Task class id generator should take care of duplicates so that there's no way
        // of failing when adding a task id
    }

    private OrganizerRaffleEntity orgRaffle;
    private String taskId;

    public OrgRaffleEditTaskUseCase(String raffleId, String taskId, ArrayList<Object> orgRaffleDetails){

        this.orgRaffle = new OrganizerRaffleEntity((String)orgRaffleDetails.get(0), (Integer)orgRaffleDetails.get(1),
                (LocalDate)orgRaffleDetails.get(3));
        this.orgRaffle.setRaffleId(raffleId);
        this.orgRaffle.setTaskIdList((ArrayList<String>) orgRaffleDetails.get(4));
        this.taskId = taskId;
    }

    public OrgTaskEditOutcome addTask(){
        // add the taskId to the list of Ids in this task
        this.orgRaffle.getTaskIdList().add(this.taskId);
        return OrgTaskEditOutcome.SUCCESSFULLY_ADDED;
    }

    public OrgTaskEditOutcome removeTask(){
        if (this.orgRaffle.getTaskIdList().contains(this.taskId)){
            this.orgRaffle.getTaskIdList().remove(this.taskId);
            return OrgTaskEditOutcome.SUCCESSFULLY_REMOVED;
        } else {
            return OrgTaskEditOutcome.FAILED_TO_REMOVE;
        }
    }

}
