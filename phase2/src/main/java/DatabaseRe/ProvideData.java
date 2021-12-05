package main.java.DatabaseRe;

import main.java.DatabaseRe.Mediators.Adders.RaffleAdder;
import main.java.DatabaseRe.Mediators.Adders.TaskAdder;
import main.java.DatabaseRe.Mediators.Adders.UserAdder;
import main.java.DatabaseRe.Mediators.DataTools;
import main.java.DatabaseRe.Mediators.Getters.UserGetter;
import main.java.DatabaseRe.Mediators.Modifiers.RaffleModifier;
import main.java.DatabaseRe.Mediators.Modifiers.TaskModifier;
import main.java.RaffleComponent.DataProviderPoint;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ProvideData implements DataProviderPoint {
    RaffleAdder raffleAdder = new RaffleAdder();
    TaskAdder taskAdder = new TaskAdder();
    TaskModifier taskModifier = new TaskModifier();
    UserAdder userAdder = new UserAdder();
    RaffleModifier raffleModifier = new RaffleModifier();
    UserGetter userGetter = new UserGetter();

    public ProvideData() throws SQLException {
    }

    /**
     * Adds a newly created raffle to the database by an organizer.
     */
    @Override
    public void addRaffle(String orgRaffleId, String organizerUsername,
                          String raffleName, Integer numberOfWinners, String raffleRules, Date endDate) {
        try {
            raffleAdder.addRaffleDetails(orgRaffleId, raffleName, numberOfWinners, raffleRules, endDate);
            addRaffleIDtoOrganizer(organizerUsername, orgRaffleId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the list of taskIDs to a raffle.
     */
    @Override
    public void addTasks(String raffleID, ArrayList<String> taskIDs) {
        try {
            taskAdder.addTaskIds(raffleID, taskIDs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the list of participants to the Raffle.
     * Note: The participant must already exist.
     */
    @Override
    public void addParticipantsToRaffle(String raffleID, ArrayList<String> participantUsernames) {
        ArrayList<String> participantIDs = null;
        try {
            participantIDs = DataTools.getUserIds(participantUsernames, false);
            RaffleAdder.addParticipantsToRaffle(raffleID, participantIDs);
            taskAdder.assignParticipantsTaskStatus(raffleID, participantIDs);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds the winners (who have claimed) to raffle.
     */
    @Override
    public void addWinnersToRaffle(String raffleID, ArrayList<String> participantUsernames) throws SQLException {
        ArrayList<String> participantIDs = null;
        participantIDs = DataTools.getUserIds(participantUsernames, false);
        try {
            userAdder.winnersToRaffle(raffleID, participantIDs);
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
     * @param taskInfo contains the information in the format:
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
     * @param detailToBeChanged can take values: "affiliatedOrg", "password", "phone", "email", "username"
     */
    @Override
    public void changeOrganizerDetail(String username, String detailToBeChanged, String newValue) {
    }

    /**
     * Updates the information about participant.
     *
     * @param detailToBeChanged can take values: "username", "password", "phone", "email"
     */
    @Override
    public void changeParticipantDetail(String username, String detailToBeChanged, String newValue) {

    }
}
