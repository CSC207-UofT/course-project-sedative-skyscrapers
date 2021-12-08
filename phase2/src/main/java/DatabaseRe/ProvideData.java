package main.java.DatabaseRe;

import main.java.DatabaseRe.Mediators.Adders.RaffleAdder;
import main.java.DatabaseRe.Mediators.Adders.TaskAdder;
import main.java.DatabaseRe.Mediators.Adders.UserAdder;
import main.java.DatabaseRe.Mediators.DataTools;
import main.java.DatabaseRe.Mediators.Getters.UserGetter;
import main.java.DatabaseRe.Mediators.Modifiers.RaffleModifier;
import main.java.DatabaseRe.Mediators.Modifiers.TaskModifier;
import main.java.DatabaseRe.Mediators.Modifiers.UserModifier;
import main.java.RaffleComponent.DataProviderPoint;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ProvideData implements DataProviderPoint {
    RaffleAdder raffleAdder = new RaffleAdder();
    TaskAdder taskAdder = new TaskAdder();
    TaskModifier taskModifier = new TaskModifier();
    UserAdder userAdder = new UserAdder();
    RaffleModifier raffleModifier = new RaffleModifier();
    UserGetter userGetter = new UserGetter();
    UserModifier userModifier = new UserModifier();

    public ProvideData() throws SQLException {
    }

    /**
     * Adds a newly created raffle to the database by an organizer.
     */
    @Override
    public void addRaffle(String orgRaffleId, String organizerUsername,
                          String raffleName, Integer numberOfWinners, String raffleRules, LocalDate endDate) {

        raffleAdder.addRaffleDetails(orgRaffleId, raffleName, numberOfWinners, raffleRules, endDate);
        addRaffleIDtoOrganizer(organizerUsername, orgRaffleId);
    }

    public void addRaffleFake(String orgRaffleId, String organizerUsername,
                          String raffleName, Integer numberOfWinners, String raffleRules, LocalDate endDate) {

        raffleAdder.addRaffleDetails(orgRaffleId, raffleName, numberOfWinners, raffleRules, endDate);
    }

    /**
     * Adds the list of taskIDs to a raffle.
     */
    @Override
    public void addTasks(String raffleID, ArrayList<String> taskIDs) {
        taskAdder.addTaskIds(raffleID, taskIDs);
    }

    /**
     * Deletes the tasks provided from the raffle
     * Note: all the particpants in the task will also be removed from the tasks
     */
    @Override
    public void deleteTask(ArrayList<String> taskIDs) {
        taskModifier.removeTaskIds(taskIDs);
    }

    /**
     * Adds the list of participants to the Raffle.
     * Note: The participant must already exist.
     */
    @Override
    public void addParticipantsToRaffle(String raffleID, ArrayList<String> participantUsernames) {
        ArrayList<String> participantIDs = null;
        try {
            //todo: check for other errors
//            participantIDs = DataTools.getUserIds(participantUsernames, false);
            RaffleAdder.addParticipantsToRaffle(raffleID, participantUsernames);
            taskAdder.assignParticipantsTaskStatus(raffleID, participantUsernames);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds the winners (who have claimed) to raffle.
     */
    @Override
    public void addWinnersToRaffle(String raffleID, ArrayList<String> participantUserIDs) throws SQLException {
//        ArrayList<String> participantIDs = null;
//        participantIDs = DataTools.getUserIds(participantUsernames, false);
        try {
            // if it gets participantUsernames, then convert to ids first
            userAdder.winnersToRaffle(raffleID, participantUserIDs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the raffle rules of <raffleId>
     */
    @Override
    public void updateRaffleRules(String raffleId, String newRaffleRules) {
        try {
            raffleModifier.raffleRules(raffleId, newRaffleRules);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the details of the task
     * Note: The task must already exist.
     *
     * @param taskInfo contains the information in the format: [taskName, link, description]
     */
    @Override
    public void addDetailsOfTask(String taskId, ArrayList<String> taskInfo) {
        taskAdder.addDetails(taskId, taskInfo);
    }

    /**
     * Adds organizer to the database.
     */
    @Override
    public void addOrganizer(String organizerId, String username, String password,
                             String affiliatedOrganization, String phone, String email) {
        try {
            userAdder.addOrganizerID(organizerId, username);
            userAdder.addOrganizerDetails(organizerId, password, affiliatedOrganization, phone, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds organizer to the database.
     */
    @Override
    public void addParticipant(String userId, String username, String password,
                               String firstName, String lastName, String dateOfBirth, String phone, String email, ArrayList<String> rafflesWon) {
        try {
            userAdder.addParticipantID(userId, username);
            userAdder.addParticipantDetails(userId, password, firstName, lastName, dateOfBirth, phone, email);
            for (String raffle : rafflesWon) {
                userAdder.addRaffleWinner(raffle, userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds a participant to raffle.
     */
    @Override
    public void addRaffleIDtoParticipant(String username, String raffleID) {
        AccessData ad = null;
        try {
            ad = new AccessData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("PD ln 164 username: " + username);
//        String username = ad.getParticipantUsernameFromID(ptcUserId, false);
        System.out.println("PD ln 166 PtcUseId: " + username);
        System.out.println("Provide Data, line 157, should be username?: " + username);
        ArrayList<String> temp = new ArrayList<>();
        temp.add(username);
        addParticipantsToRaffle(raffleID, temp);
    }

    /**
     * Sets the task status of the participant to True (if it has completed the task) or False.
     */
    @Override
    public void setTaskStatus(String ptcRaffleId, String taskCompletedId, boolean status) {
        try {
            taskModifier.setStatus(ptcRaffleId, taskCompletedId, status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Associates raffle to its organizer.
     */
    @Override
    public void addRaffleIDtoOrganizer(String username, String raffleID) {
        String userID = null;
        try {
            userID = userGetter.getUserID(username, true);
            userAdder.raffleToOrganizer(userID, raffleID);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Updates the information about organizer.
     *
     * @param detailToBeChanged can take values: "organization", "password", "mobile", "email", "username"
     */
    @Override
    public void changeOrganizerDetail(String username, String detailToBeChanged, String newValue) {
        try {
            userModifier.changeOrgDetail(username, detailToBeChanged, newValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the information about participant.
     *
     * @param detailToBeChanged can take values: "username", "password", "mobile", "email", "firstName", "lastName"
     */
    @Override
    public void changeParticipantDetail(String username, String detailToBeChanged, String newValue) {
        try {
            userModifier.changeParticipantDetail(username, detailToBeChanged, newValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the endDate of the Raffle
     */
    @Override
    public void changeRaffleEndDate(String raffleID, LocalDate newData) {
        raffleModifier.changeEndDate(raffleID, newData);
    }
}
