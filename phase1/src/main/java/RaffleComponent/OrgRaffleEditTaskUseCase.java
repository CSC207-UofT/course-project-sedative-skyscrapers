package main.java.RaffleComponent;

import main.java.RaffleComponent.OrganizerRaffleEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class OrgRaffleEditTaskUseCase {

    public enum TaskEditTypes{
        ORGANIZER_ADD, ORGANIZER_REMOVE
    }

    public enum OrgTaskEditOutcome{
        SUCCESSFULLY_ADDED, SUCCESSFULLY_REMOVED, FAILED_TO_REMOVE, STANDBY
        // the Task class id generator should take care of duplicates so that there's no way
        // of failing when adding a task id
    }

    private OrganizerRaffleEntity orgRaffle;
    private ArrayList<Object> orgRaffleInfo;
    private final String taskId;
    private ArrayList<ArrayList<Object>> ptcAllRaffles;

    public OrgRaffleEditTaskUseCase(String raffleId, String taskId){

        this.taskId = taskId;
        // todo uncomment: this.orgRaffleInfo = DataAccess.getOrganizerRaffleById()
        // todo uncomment: this.orgRaffleInfo = DataAccess.getAllParticipantRaffles(raffleId)
        this.orgRaffle = new OrganizerRaffleEntity((String)this.orgRaffleInfo.get(0),
                (Integer)this.orgRaffleInfo.get(1), (LocalDate)this.orgRaffleInfo.get(3));
        this.orgRaffle.setRaffleId(raffleId);
        this.orgRaffle.setRaffleRules((String)this.orgRaffleInfo.get(2));

        // at this point of the raffle's existence, users might already have joined the raffle, so we create the object
        // accordingly
        this.orgRaffle.setTaskIdList((ArrayList<String>) this.orgRaffleInfo.get(4));
        this.orgRaffle.setParticipantIdList((ArrayList<String>) this.orgRaffleInfo.get(5));

    }

    public OrgTaskEditOutcome addTask(){
        // add the taskId to the list of Ids in this task
        this.orgRaffle.getTaskIdList().add(this.taskId);
        // todo uncomment: DataAccess.uploadModifiedOrgRaffle(this.orgRaffle)
        return OrgTaskEditOutcome.SUCCESSFULLY_ADDED;
    }

    public OrgTaskEditOutcome removeTask(){
        if (this.orgRaffle.getTaskIdList().contains(this.taskId)){
            this.orgRaffle.getTaskIdList().remove(this.taskId);
            // todo uncomment: DataAccess.uploadModifiedOrgRaffle(this.orgRaffle)
            return OrgTaskEditOutcome.SUCCESSFULLY_REMOVED;
        } else {
            return OrgTaskEditOutcome.FAILED_TO_REMOVE;
        }
    }

    public void updatePtcRaffles(String taskId, TaskEditTypes editToPerform){
        for (ArrayList<Object> ptcRaffleInfo: this.ptcAllRaffles){

            ArrayList<String> ptcRaffleTaskList = (ArrayList<String>)ptcRaffleInfo.get(4);
            if (editToPerform == TaskEditTypes.ORGANIZER_ADD) {
                ptcRaffleTaskList.add(taskId);
            } else {
                ptcRaffleTaskList.remove(taskId);
            }

            // update ptcAllRaffles respectively
            ptcRaffleInfo.set(4, ptcRaffleTaskList);
            // todo uncomment: DataAccess.uploadModifiedPtcRaffle(ptcRaffle)
        }
    }

}
