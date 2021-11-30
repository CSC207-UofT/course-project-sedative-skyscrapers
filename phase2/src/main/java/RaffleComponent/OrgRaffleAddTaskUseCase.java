package main.java.RaffleComponent;

import main.java.database.AddOrganizer;
import main.java.database.DataExtractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrgRaffleAddTaskUseCase {

//    public enum TaskEditTypes{
//        ORGANIZER_ADD, ORGANIZER_REMOVE
//    }
//
//    public enum OrgTaskEditOutcome{
//        SUCCESSFULLY_ADDED, SUCCESSFULLY_REMOVED, FAILED_TO_REMOVE, STANDBY
//        // the Task class id generator should take care of duplicates so that there's no way
//        // of failing when adding a task id
//    }

    private final String FIELD_TO_BE_CHANGED = "TaskIdList";
    private ArrayList<Object> orgRaffleInfo;
    private OrganizerRaffleEntity orgRaffle;
    private final ArrayList<String> taskIds;
//    private HashMap<String, ArrayList<Object>> ptcAllRaffles;
//    private PackageRaffleEntityInstance dataPackager;
    private DataAccessPoint dataAccess;
    private DataProviderPoint dataUploader;
//    private final TaskEditTypes editToPerform;
//    private OrgTaskEditOutcome taskAdditionResult;

    public OrgRaffleAddTaskUseCase(String raffleId, ArrayList<String> taskIds){

        this.taskIds = taskIds;
//        this.editToPerform = editToPerform;

        try {
            // todo this will be the name of the file khushaal provides
            this.dataAccess = new DataExtractor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            this.orgRaffleInfo = this.dataAccess.getOrganizerRaffleById(raffleId);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            // todo this will be the name of the file khushaal provides
            this.dataUploader = new AddOrganizer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // uncomment: this.ptcAllRaffles = DataAccess.getAllParticipantRafflesAndIds(raffleId)
        // at the moment of task adding, no participant can join the raffle yet

        this.orgRaffle = new OrganizerRaffleEntity((String)this.orgRaffleInfo.get(0),
                (Integer)this.orgRaffleInfo.get(1), (LocalDate)this.orgRaffleInfo.get(3));
        this.orgRaffle.setRaffleId(raffleId);
        this.orgRaffle.setRaffleRules((String)this.orgRaffleInfo.get(2));

         this.orgRaffle.setTaskIdList((ArrayList<String>) this.orgRaffleInfo.get(4));
         this.orgRaffle.setParticipantIdList((ArrayList<String>) this.orgRaffleInfo.get(5));
        // no winners set yet

//        this.dataPackager = new PackageRaffleEntityInstance();
    }

    public void updateTaskList(){
//        if (this.editToPerform.equals(TaskEditTypes.ORGANIZER_ADD)){
//            OrgTaskEditOutcome result = this.addTasks();
//        } else {
//            OrgTaskEditOutcome result = this.removeTasks();
//        }
        // add the taskIds to the list of Ids in this task
        this.orgRaffle.getTaskIdList().addAll(this.taskIds);  // taskIds being set for the first time
//        ArrayList<Object> packagedOrgRaffle = this.dataPackager.packageOrganizerRaffle(this.orgRaffle);

        try {
            this.dataUploader.uploadModifiedOrgRaffle(this.orgRaffle.getRaffleId(), this.FIELD_TO_BE_CHANGED,
                    this.orgRaffle.getTaskIdList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // tasks are set just at the start of the raffle, so they are copied by users joining in as their final form,
        // observer pattern no longer present here
//        this.updatePtcRaffles();
    }

//    public OrgTaskEditOutcome addTasks(){
//        // add the taskIds to the list of Ids in this task
//        this.orgRaffle.getTaskIdList().addAll(this.taskIds);  // taskIds being set for the first time
////        ArrayList<Object> packagedOrgRaffle = this.dataPackager.packageOrganizerRaffle(this.orgRaffle);
//
//        try {
//            this.dataUploader.uploadModifiedOrgRaffle(this.orgRaffle.getRaffleId(), this.FIELD_TO_BE_CHANGED,
//                    this.orgRaffle.getTaskIdList());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        this.updatePtcRaffles();
//        return OrgTaskEditOutcome.SUCCESSFULLY_ADDED;
//    }
//
//    public OrgTaskEditOutcome removeTasks(){
//        for (String taskId: this.taskIds) {
//            if (this.orgRaffle.getTaskIdList().contains(taskId)) {
//                this.orgRaffle.getTaskIdList().remove(taskId);
//            } else {
//                return OrgTaskEditOutcome.FAILED_TO_REMOVE;
//            }
//        }
////        ArrayList<Object> packagedOrgRaffle = this.dataPackager.packageOrganizerRaffle(this.orgRaffle);
//
//        try {
//            this.dataUploader.uploadModifiedOrgRaffle(this.orgRaffle.getRaffleId(), this.FIELD_TO_BE_CHANGED,
//                    this.orgRaffle.getTaskIdList())
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        this.updatePtcRaffles();
//        return OrgTaskEditOutcome.SUCCESSFULLY_REMOVED;
//    }
//
//    public void updatePtcRaffles(){
//        for (String participant : this.orgRaffle.getParticipantIdList()){
//            // create ptcRaffleObject
//            String ptcRaffleId = participant + ":" + this.orgRaffle.getRaffleId();
//
//            try {
//                ArrayList<Object> ptcRaffleInfo = this.dataAccess.getParticipantRaffleById(ptcRaffleId);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                this.dataUploader.uploadModifiedPtcRaffle(ptcRaffleId, this.FIELD_TO_BE_CHANGED,
//                        updatedPtcTaskIdList(ptcRaffleInfo));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public ArrayList<String> updatedPtcTaskIdList(ArrayList<Object> ptcRaffleInfo) {
//
//        ParticipantRaffleEntity ptcRaffleToUpdate = new ParticipantRaffleEntity(this.orgRaffle.getRaffleName(),
//                this.orgRaffle.getNumberOfWinners(), this.orgRaffle.getEndDate());
//        ptcRaffleToUpdate.setRaffleRules(this.orgRaffle.getRaffleRules());
//
//        // now extract the specific arraylist of taskIds for the specific ptcRaffle instance
//        ptcRaffleToUpdate.setTaskIdList((ArrayList<String>)ptcRaffleInfo.get(4));
//
//        // update taskIdList for specific participant raffle through update method of subscriber
//        ptcRaffleToUpdate.updateTaskIdList(this.editToPerform.equals(TaskEditTypes.ORGANIZER_ADD), this.taskIds);
//
//        return ptcRaffleToUpdate.getTaskIdList();
//
//    }

    // for testing purposes
    public void setOrgRaffleInfo(ArrayList<Object> orgRaffleInfo) {
        this.orgRaffleInfo = orgRaffleInfo;
    }

    public void setOrgRaffle(OrganizerRaffleEntity orgRaffle){
        this.orgRaffle = orgRaffle;
    }

}
