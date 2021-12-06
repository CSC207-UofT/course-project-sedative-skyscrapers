package main.java.DatabaseRe;

import main.java.DatabaseRe.Mediators.Getters.RaffleGetter;
import main.java.DatabaseRe.Mediators.Getters.TaskGetter;
import main.java.DatabaseRe.Mediators.Getters.UserGetter;
import main.java.RaffleComponent.DataAccessPoint;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

public class AccessData implements DataAccessPoint {
    TaskGetter taskGetter = new TaskGetter();
    UserGetter userGetter = new UserGetter();
    RaffleGetter raffleGetter = new RaffleGetter();


    public AccessData() throws SQLException {
    }

    @Override
    public ArrayList<String> getTakenRaffleIds() throws SQLException {
        return raffleGetter.getUsedRaffleIds();
    }

    /**
     * @param orgRaffleId The RaffleID of the OrganizerRaffle
     * @return ArrayList of details in format: [raffleName="raffle", numberOfWinners=2,
     * rules="this is a string of rules", endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>,
     * ptcIds=ArrayList<String>, winnerIds=ArrayList<String>]
     */
    @Override
    public ArrayList<Object> getOrganizerRaffleById(String orgRaffleId) {
        ArrayList<Object> raffleDetails = null;
        try {
            raffleDetails = raffleGetter.getDetails(orgRaffleId);
            raffleDetails.add(taskGetter.getTaskIDsInRaffle(orgRaffleId));
            raffleDetails.add(userGetter.getParticipantsInRaffle(orgRaffleId));
            raffleDetails.add(raffleGetter.getWinners(orgRaffleId));
            ArrayList<String> organizer = (ArrayList<String>) raffleGetter.getOrganizer(orgRaffleId);
            raffleDetails.add(organizer.get(0));
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
//        raffleDetails.remove(3);
        raffleDetails.remove(0);
        raffleDetails.remove(3);
        Collections.swap(raffleDetails, 0, 1);
        return raffleDetails;
    }

    @Override
    public ArrayList<Object> getParticipantRaffleById(String ptcRaffleID){
        //  [raffleName, numOfWinners, rules, endDate, taskIdList]
        String raffleID = ptcRaffleID.substring(ptcRaffleID.indexOf(":")+1);
        ArrayList<Object> raffleDetails = new ArrayList<>();

        try {
            raffleDetails = raffleGetter.getDetails(raffleID);
            raffleDetails.add(taskGetter.getTaskIDsInRaffle(raffleID));
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return raffleDetails;
    }

    @Override
    public ArrayList<String> getTaskById(String taskId) {
        try {
            return raffleGetter.getTaskDetails(taskId);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getParticipantInfo(String ptcUsername) {
        try {
            return userGetter.getUserInfo(ptcUsername, false);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getOrganizerInfo(String orgUsername) {
        try {
            return userGetter.getUserInfo(orgUsername, true);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getParticipantRaffleIds(String ptcUsername) {
        try {
            return userGetter.getRaffleIdsOfParticipant(ptcUsername);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getOrganizerRaffleIds(String orgUsername) {
        try {
            return userGetter.getRafflesOfOrganizer(orgUsername);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean hasCompletedTask(String ptcRaffleId, String userName, String taskId) throws SQLException {
        return taskGetter.getTaskStatus(ptcRaffleId, taskId);
    }

    @Override
    public ArrayList<String> getTakenOrganizerIds() {
        try {
            return userGetter.getUsedUserIDs(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getTakenParticipantIds() {
        try {
            return userGetter.getUsedUserIDs(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getValidParticipants(String orgRaffleId) {
        try {
            ArrayList<String> tasks = taskGetter.getTaskIDsInRaffle(orgRaffleId);
            int numberOfTasks = taskGetter.getTaskIDsInRaffle(orgRaffleId).size();
            ArrayList<String> validParticipants = new ArrayList<>();
            ArrayList<String> possibleParticipants = userGetter.getParticipantsInRaffle(orgRaffleId);
            int count = 0;
            for (String ptc: possibleParticipants) {
                for (String task: tasks) {
                    if (taskGetter.getTaskStatus(ptc, task)) {
                        count += 1;
                    }
                }
                if (count == numberOfTasks) {
                    validParticipants.add(ptc);
                }
                count = 0;
            }
            return validParticipants;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getRaffleIDByRaffleName(String raffleName) {
        try {
            return raffleGetter.getRaffleIDfromRaffleName(raffleName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getOrgIDByOrganization(String organization) {
        try {
            return userGetter.getOrgIDfromOrganization(organization);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getTakenTaskIds() {
        try {
            return taskGetter.getUsedTaskIDs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
