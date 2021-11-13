package main.java.RaffleComponent;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompleteTaskUseCase {

    private RaffleEntity ptcRaffle;
    private final String taskId;
    private ArrayList<Object> ptcRaffleInfo;

    public CompleteTaskUseCase(String taskToComplete, String raffleId){
        // todo uncomment: this.ptcRaffleInfo = DataAccess.getPtcRaffleById(raffleId)
        this.ptcRaffle = new RaffleEntity((String)ptcRaffleInfo.get(0), (Integer)ptcRaffleInfo.get(1),
                (LocalDate)ptcRaffleInfo.get(3));
        this.ptcRaffle.setRaffleId(raffleId);
        this.ptcRaffle.setTaskIdList((ArrayList<String>) ptcRaffleInfo.get(4));
        this.taskId = taskToComplete;
    }

    public void completeTask(){
        if (this.ptcRaffle.getTaskIdList().contains(this.taskId)){
            this.ptcRaffle.getTaskIdList().remove(this.taskId);
            // todo uncomment: DataAccess.uploadModifiedPtcRaffle(this.ptcRaffle)
        } else {
            // raise exception
        }

    }
}
