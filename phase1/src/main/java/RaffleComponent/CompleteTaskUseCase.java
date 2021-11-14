package main.java.RaffleComponent;

import main.java.Helpers.PackageRaffleEntityInstance;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompleteTaskUseCase {

    private RaffleEntity ptcRaffle;
    private final String taskId;
    private ArrayList<Object> ptcRaffleInfo;
    private ArrayList<Object> orgRaffleInfo;
    private PackageRaffleEntityInstance dataPackager;

    public CompleteTaskUseCase(String taskToComplete, String raffleId){
        // todo uncomment: this.ptcRaffleInfo = DataAccess.getPtcRaffleById(raffleId)
        // todo uncomment: this.orgRaffleInfo = DataAccess.getOrganizerRaffleById(orgIdFormPtcId(raffleId))
        this.ptcRaffle = new RaffleEntity((String)ptcRaffleInfo.get(0), (Integer)ptcRaffleInfo.get(1),
                (LocalDate)ptcRaffleInfo.get(3));
        this.ptcRaffle.setRaffleId(raffleId);
        this.ptcRaffle.setRaffleRules((String)ptcRaffleInfo.get(2));
        this.ptcRaffle.setTaskIdList((ArrayList<String>) ptcRaffleInfo.get(4));
        this.taskId = taskToComplete;
        this.dataPackager = new PackageRaffleEntityInstance();
    }

    public ArrayList<String> completeTask(){
        ArrayList<String> completedTaskIds;
        if (this.ptcRaffle.getTaskIdList().contains(this.taskId)){
            this.ptcRaffle.getTaskIdList().remove(this.taskId);  // pop tasks from the tasks to do by user

            ArrayList<Object> packagedPtcRaffle = this.dataPackager.packageParticipantRaffle(this.ptcRaffle);
            // todo uncomment: DataAccess.uploadModifiedPtcRaffle(this.ptcRaffle.getRaffleId(), packagedPtcRaffle)
        }

        // compare orgRaffleTaskIdList to ptcRaffleTaskIdList to return completed task Ids
        completedTaskIds = generateCompletedTaskIds(this.ptcRaffle.getTaskIdList(),
                (ArrayList<String>) this.orgRaffleInfo.get(4));

        // despite not currently completing a task, other tasks might have been previously solved, so we still compare
        return completedTaskIds;

    }

    public String orgIdFromPtcId(String ptcRaffleId){
        String[] ptcRaffleIdParts = ptcRaffleId.split(":");
        return ptcRaffleIdParts[1];
    }

    public ArrayList<String> generateCompletedTaskIds(ArrayList<String> ptcTaskIds,  ArrayList<String> orgTaskIds){
        ArrayList<String> completedTaskIds = new ArrayList<>();

        for (String taskId: orgTaskIds){
            if (!ptcTaskIds.contains(taskId)){  // task has been completed and popped
                completedTaskIds.add(taskId);
            }
            // else, task hasn't been completed
        }

        return completedTaskIds;
    }
}
