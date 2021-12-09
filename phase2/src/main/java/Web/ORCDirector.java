package main.java.Web;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class in charge of calling the steps required to build specific OrganizerRaffleController
 * objects with their needed attributes.
 */
public class ORCDirector {

    private OrgRaffleController ORC;

    public ORCDirector() {
        this.reset();
    }

    private void reset() {
        this.ORC = new OrgRaffleController();
    }

    // more organized methods for building could be declared, but even in the case we build something
    // setting a common attribute like orgRaffleId twice, the value should be the same, meaning we can
    // call these in groups too

    public void ORCBuildCreator(String raffleName, int numOfWinners, LocalDate endDate, String orgUsername) {
        this.ORC.setRaffleName(raffleName);
        this.ORC.setNumOfWinners(numOfWinners);
        this.ORC.setEndDate(endDate);
        this.ORC.setOrgUsername(orgUsername);
    }

    public void ORCBuildRuleSetter(String orgRaffleId, String rulesString) {
        this.ORC.setOrgRaffleId(orgRaffleId);
        this.ORC.setRulesString(rulesString);
    }

    public void ORCBuildWinnerGenerator(String orgRaffleId) {
        this.ORC.setOrgRaffleId(orgRaffleId);
    }

    public void ORCBuildTaskEditor(String orgRaffleId, ArrayList<String> tasksToEditIds) {
        this.ORC.setOrgRaffleId(orgRaffleId);
        this.ORC.setTaskIdList(tasksToEditIds);
    }

    public void ORCBuildEditEndDate(String orgRaffleId, LocalDate newEndDate) {
        this.ORC.setOrgRaffleId(orgRaffleId);
        this.ORC.setNewEndDate(newEndDate);
    }

    public OrgRaffleController getORCProduct() {
        return this.ORC;
    }

}
