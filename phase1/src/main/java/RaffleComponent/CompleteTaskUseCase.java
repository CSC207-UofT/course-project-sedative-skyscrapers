package main.java.RaffleComponent;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompleteTaskUseCase {

    private RaffleEntity ptcRaffle;
    private String taskId;

    public CompleteTaskUseCase(String taskToComplete, String raffleId, ArrayList<Object> ptcRaffleDetails){
        this.ptcRaffle = new RaffleEntity((String)ptcRaffleDetails.get(0), (Integer)ptcRaffleDetails.get(1),
                (LocalDate)ptcRaffleDetails.get(3));
        this.ptcRaffle.setRaffleId(raffleId);
        this.ptcRaffle.setTaskIdList((ArrayList<String>) ptcRaffleDetails.get(4));
        this.taskId = taskToComplete;
    }

    public void completeTask(){
        this.ptcRaffle.getTaskIdList().remove(this.taskId);
    }
}
