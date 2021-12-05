package main.java.DatabaseRe;

import main.java.DatabaseRe.Mediators.Getters.UserGetter;

import java.sql.SQLException;

public class UpdateQueries {
    static UserGetter userGetter;

    static {
        try {
            userGetter = new UserGetter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UpdateQueries() throws SQLException {
    }

    public static String raffleRules(String raffleId, String newRaffleRules) {
        return "UPDATE `innodb`.`RaffleDetails`" +
                "SET `raffleRules` = \""+ newRaffleRules + "\"" +
                "WHERE `raffleID` = \"" + raffleId + "\";";
    }

    public static String taskStatus(String ptcRaffleId, String taskCompletedId, Boolean status) {
        return "UPDATE `innodb`.`TaskStatus` SET `taskStatus` = " + status.toString() +" " +
                "WHERE `PuserID` = \""+ptcRaffleId+"\" AND `taskID` = \""+ taskCompletedId+"\";\n";
    }


    public static String changeOrg(String username, String detailToBeChanged, String newValue) throws Exception {
        String userID = userGetter.getUserID(username, true);
        if ("username".equals(detailToBeChanged)) {
            return "UPDATE `innodb`.`OrganizerUsernames` " +
                    "SET " +
                    "`username` = '"+ newValue +"' " +
                    "WHERE `OuserID` = '" + userID +"';";
        }
        return "UPDATE `innodb`.`OuserCred` " +
                "SET " +
                "`"+ detailToBeChanged +"` =\"" + newValue + "\" " +
                "WHERE `OuserID` = \"" + userID + "\";";



    }

    public static String changeParticipant(String username, String detailToBeChanged, String newValue) throws Exception {
        String userID = userGetter.getUserID(username, false);
        if ("username".equals(detailToBeChanged)) {
            return "UPDATE `innodb`.`ParticipantUsernames` " +
                    "SET " +
                    "`username` = '"+ newValue +"' " +
                    "WHERE `PuserID` = '" + userID +"';";
        }
        return "UPDATE `innodb`.`PuserCred` " +
                "SET " +
                "`"+ detailToBeChanged +"` =\"" + newValue + "\" " +
                "WHERE `PuserID` = \"" + userID + "\";";
    }
}


