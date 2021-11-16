package main.java.RaffleComponent;

import main.java.Helpers.PackageRaffleEntityInstance;
import main.java.RaffleComponent.OrganizerRaffleEntity;
import main.java.database.AddOrganizer;
import main.java.database.DataExtractor;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    private ArrayList<Object> raffleInfoSoFar;
    private final ArrayList<String> taskIds;
//    private HashMap<String, ArrayList<Object>> ptcAllRaffles;
    private PackageRaffleEntityInstance dataPackager;
//    private DataExtractor dataAccess;
    private AddOrganizer dataUploader;

    public OrgRaffleEditTaskUseCase(String raffleId, ArrayList<String> taskIds, ArrayList<Object> raffleInfoSoFar){

        this.taskIds = taskIds;
        this.raffleInfoSoFar = raffleInfoSoFar; // format [name, numOfWinners, endDate, raffleId, rules]

//        try {
//            this.dataAccess = new DataExtractor();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        try {
            this.dataUploader = new AddOrganizer();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            this.orgRaffleInfo = this.dataAccess.getOrgRaffleInfo(raffleId);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // uncomment: this.ptcAllRaffles = DataAccess.getAllParticipantRafflesAndIds(raffleId)
        // at the moment of task adding, no participant can join the raffle yet

        this.orgRaffle = new OrganizerRaffleEntity((String)this.raffleInfoSoFar.get(0),
                (Integer)this.raffleInfoSoFar.get(1), (LocalDate)this.raffleInfoSoFar.get(2));
        this.orgRaffle.setRaffleId(raffleId);
        this.orgRaffle.setRaffleRules((String)this.raffleInfoSoFar.get(4));

        // at this point of the raffle's existence, users haven't been able to join the raffle, and tasks are just
        // about to get setup

        // this.orgRaffle.setTaskIdList((ArrayList<String>) this.orgRaffleInfo.get(4));
        // this.orgRaffle.setParticipantIdList((ArrayList<String>) this.orgRaffleInfo.get(5));
        // no winners set yet

        this.dataPackager = new PackageRaffleEntityInstance();
    }

    public OrgTaskEditOutcome addTask(){
        // add the taskIds to the list of Ids in this task
        this.orgRaffle.setTaskIdList(this.taskIds);  // taskIds being set for the first time
        ArrayList<Object> packagedOrgRaffle = this.dataPackager.packageOrganizerRaffle(this.orgRaffle);

        try {
            this.dataUploader.uploadCreatedRaffle(this.orgRaffle.getRaffleId(), packagedOrgRaffle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.dataUploader.addOrganizer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return OrgTaskEditOutcome.SUCCESSFULLY_ADDED;
    }

//    removing task functionality postponed

//    public OrgTaskEditOutcome removeTask(){
//        for (String taskId: this.taskIds) {
//            if (this.orgRaffle.getTaskIdList().contains(taskId)) {
//                this.orgRaffle.getTaskIdList().remove(taskId);
//            } else {
//                return OrgTaskEditOutcome.FAILED_TO_REMOVE;
//            }
//        }
//        ArrayList<Object> packagedOrgRaffle = this.dataPackager.packageOrganizerRaffle(this.orgRaffle);
//        DataAccess.uploadModifiedOrgRaffle(this.orgRaffle.getRaffleId(), packagedOrgRaffle)
//        return OrgTaskEditOutcome.SUCCESSFULLY_REMOVED;
//    }

//    ptcRaffles still haven't been able to join the raffle therefore no need to update them

//    public void updatePtcRaffles(ArrayList<String> taskIds, TaskEditTypes editToPerform){
//        for (String ptcRaffleId : this.ptcAllRaffles.keySet()){
//            ArrayList<Object> ptcRaffleInfo = this.ptcAllRaffles.get(ptcRaffleId);
//            ArrayList<String> ptcRaffleTaskList = (ArrayList<String>)ptcRaffleInfo.get(4);
//            if (editToPerform == TaskEditTypes.ORGANIZER_ADD) {
//                ptcRaffleTaskList.addAll(taskIds);
//            } else {
//                ptcRaffleTaskList.removeAll(taskIds);
//            }
//
//            // update ptcAllRaffles respectively
//            ptcRaffleInfo.set(4, ptcRaffleTaskList);
//            // DataAccess.uploadModifiedPtcRaffle(ptcRaffleId, ptcRaffleInfo)
//        }
//
//    }

    // for testing purposes
    public void setRaffleInfoSoFar(ArrayList<Object> orgRaffleInfo) {
        this.raffleInfoSoFar = orgRaffleInfo;
    }

    public void setOrgRaffle(OrganizerRaffleEntity orgRaffle){
        this.orgRaffle = orgRaffle;
    }

//    public HashMap<String, ArrayList<Object>> getPtcAllRaffles(){
//        return this.ptcAllRaffles;
//    };
//
//    public void setPtcAllRaffles(HashMap<String, ArrayList<Object>> ptcAllRaffles) {
//        this.ptcAllRaffles = ptcAllRaffles;
//    }

}
