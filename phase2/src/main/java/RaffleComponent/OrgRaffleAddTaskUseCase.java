package main.java.RaffleComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.ProvideData;
import main.java.Helpers.UseCaseDateFormatter;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrgRaffleAddTaskUseCase {

    private ArrayList<Object> orgRaffleInfo;
    private OrganizerRaffleEntity orgRaffle;
    private final ArrayList<String> taskIds;
    private DataAccessPoint dataAccess;
    private DataProviderPoint dataUploader;

    public OrgRaffleAddTaskUseCase(String raffleId, ArrayList<String> taskIds){

        this.taskIds = taskIds;

        try {
            this.dataAccess = new AccessData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.orgRaffleInfo = this.dataAccess.getOrganizerRaffleById(raffleId);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        try {
            this.dataUploader = new ProvideData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String date = this.orgRaffleInfo.get(3).toString();
        int[] dateData = UseCaseDateFormatter.formatDateIntoStrings(date);
        this.orgRaffle = new OrganizerRaffleEntity((String)this.orgRaffleInfo.get(0),
                Integer.parseInt(this.orgRaffleInfo.get(1).toString()),
                LocalDate.of(dateData[0], dateData[1], dateData[2]),
                (String)this.orgRaffleInfo.get(7));
        this.orgRaffle.setRaffleId(raffleId);
        this.orgRaffle.setRaffleRules((String)this.orgRaffleInfo.get(2));
         this.orgRaffle.setTaskIdList((ArrayList<String>) this.orgRaffleInfo.get(4));
         this.orgRaffle.setParticipantIdList((ArrayList<String>) this.orgRaffleInfo.get(5));
         this.orgRaffle.setOrgUsername((String)this.orgRaffleInfo.get(7));
        // no winners set yet
    }

    public boolean updateTaskList(){
        // add the taskIds to the list of Ids in this task
        if (!this.taskIds.isEmpty()){
            this.orgRaffle.getTaskIdList().addAll(this.taskIds);  // taskIds being set for the first time
            this.dataUploader.addTasks(this.orgRaffle.getRaffleId(), this.orgRaffle.getTaskIdList());
            return true;
        }

        // no task ids provided
        return false;
    }


    // for testing purposes

    public void setOrgRaffle(OrganizerRaffleEntity orgRaffle){
        this.orgRaffle = orgRaffle;
    }

}
