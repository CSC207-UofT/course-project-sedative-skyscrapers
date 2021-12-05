package main.java.DatabaseRe.Mediators;

import java.time.LocalDate;
import java.util.Date;

public abstract class InsertQueries {

    public static String raffleDetails(String orgRaffleId, String raffleName, Integer numberOfWinners,
                                       String raffleRules, LocalDate endDate) {
        String date = endDate.toString();
        return "INSERT INTO `innodb`.`RaffleDetails` (`raffleID`,`noOfWinners`,`raffleName`,`raffleRules`," +
                "`startDate`,`endDate`) VALUES (\"" + orgRaffleId + "\", " + String.valueOf(numberOfWinners) + ", " +
                "\"" + raffleName + "\"," +
                " \"" + raffleRules + "\", \"2021-11-21\", \"" + date + "\");";
    }


    public static String addTaskIdToRaffle(String orgRaffleId, String taskID) {
        return "INSERT INTO `innodb`.`TasksInRaffle` (`raffleID`, `taskID`) VALUES (\"" + orgRaffleId + "\", \""
                + taskID + "\");";
    }

    public static String participantToRaffle(String orgRaffleId, String ptcID) {
        return "INSERT INTO `innodb`.`participantsInRaffle` (`PuserID`, `raffleID`) VALUES (\""+ ptcID +"\", \""
                + orgRaffleId + "\");";
    }

    public static String addRaffleWinner(String raffleID, String ptcID) {
        return "INSERT INTO `innodb`.`Winners` (`raffleID`,`PuserID`) VALUES (\"" + raffleID + "\",\"" + ptcID + "\")";
    }

    public static String assignTaskStatusToParticipant(String participant, String task, Boolean b) {
        return "INSERT INTO `innodb`.`TaskStatus`(`PuserID`,`taskID`,`taskStatus`) VALUES (" +
                "\"" + participant + "\"," +
                "\"" + task + "\"," +
                b.toString() +
                ");";
    }

    public static String organizerToRaffle(String userID, String raffleID) {
        return "INSERT INTO `innodb`.`OrganizersRaffles`(`OuserID`,`raffleID`) VALUES (\""+
                userID +"\",\""+ raffleID +"\");";
    }

    public static String organizerID(String organizerId, String username) {
        return "INSERT INTO `innodb`.`OrganizerUsernames`(`OuserID`,`username`) VALUES (\""
                +organizerId+"\",\""+username+"\");";
    }

    public static String organizerDetails(String organizerId, String password, String affiliatedOrganization,
                                          String phone, String email) {
        return "INSERT INTO `innodb`.`OuserCred` (`OuserID`,`password`,`organization`,`dateOfBirth`,`mobile`,`email`)" +
                "VALUES (" +
                "\""+ organizerId + "\" ," +
                "\""+ password + "\" ," +
                "\""+ affiliatedOrganization + "\" ," +
                "\"2001-11-21\" ," +
                "\""+ phone  + "\" ," +
                "\""+ email + "\" " +
                ");";
    }

    public static String participantID(String userId, String username) {
        return "INSERT INTO `innodb`.`ParticipantUsernames`(`PuserID`,`username`) VALUES (\""
                +userId+"\",\""+username+"\");";
    }

    public static String participantDetails(String userId, String password, String firstName, String lastName,
                                            String dateOfBirth, String phone, String email) {
        return "INSERT INTO `innodb`.`PuserCred` (`PuserID`,`password`,`firstName`,`lastName`,`dateOfBirth`,`mobile`,`email`)" +
                "VALUES (" +
                "\""+ userId + "\" ," +
                "\""+ password + "\" ," +
                "\""+ firstName + "\" ," +
                "\""+ lastName + "\" ," +
                "\""+ dateOfBirth + "\" ," +
                "\""+ phone  + "\" ," +
                "\""+ email + "\" " +
                ");";
    }
}
