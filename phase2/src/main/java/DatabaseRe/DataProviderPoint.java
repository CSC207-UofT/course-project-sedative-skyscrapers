package main.java.DatabaseRe;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public interface DataProviderPoint {
    /**
     * Adds a newly created raffle to the database by an organizer.
     */
    void addRaffle(String orgRaffleId, String organizerUsername, String raffleName,
                   Integer numberOfWinners, String raffleRules, LocalDate endDate);

    /**
     * Adds the list of taskIDs to a raffle.
     */
    void addTasks(String raffleID, ArrayList<String> taskIDs);

    void deleteTask(ArrayList<String> taskIdsToDelete);
    /**
     * Adds the list of participants to the Raffle.
     * Note: The participant must already exist.
     */
    void addParticipantsToRaffle(String raffleID, ArrayList<String> participantUsernames);

    /**
     * Adds the winners (who have claimed) to raffle.
     */
    void addWinnersToRaffle(String raffleID, ArrayList<String> participantUsernames) throws SQLException;

    /**
     *  Updates the raffle rules of <raffleId>
     */
    void updateRaffleRules(String raffleId, String newRaffleRules);

    /**
     * Updates the raffle endDate of <raffleId>
     */
    void changeRaffleEndDate(String raffleID, LocalDate newDate);

    /**
     *  Adds the details of the task
     *  Note: The task must already exist.
     */
    void addDetailsOfTask(String taskId, ArrayList<String> taskInfo);

    /**
     *  Adds organizer to the database.
     */
    void addOrganizer(String organizerId, String username, String password, String affiliatedOrganization,
                      String phone, String email);

    /**
     *  Adds organizer to the database.
     */
    void addParticipant(String userId, String username, String password, String firstName,
                        String lastName, String dateOfBirth, String phone, String email, ArrayList<String> rafflesWon);

    /**
     *  Adds a participant to raffle.
     */
    void addRaffleIDtoParticipant(String username, String raffleID);

    /**
     *  Sets the task status of the participant to True (if it has completed the task) or False.
     */
    void setTaskStatus(String ptcRaffleId, String taskCompletedId, boolean status);

    /**
     *  Associates raffle to its organizer.
     */
    void addRaffleIDtoOrganizer(String username, String raffleID);

    /**
     * Updates the information about organizer.
     * @param detailToBeChanged can take values: "affiliatedOrg", "password", "phone", "email", "username"
     */
    void changeOrganizerDetail(String username, String detailToBeChanged, String newValue);

    /**
     * Updates the information about participant.
     * @param detailToBeChanged can take values: "username", "password", "phone", "email"
     */
    void changeParticipantDetail(String username, String detailToBeChanged, String newValue);
    
    /**
     *  Changes the endDate of the Raffle
     */

}


