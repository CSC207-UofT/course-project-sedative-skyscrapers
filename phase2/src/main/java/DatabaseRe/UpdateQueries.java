package main.java.DatabaseRe;

public class UpdateQueries {
    public static String raffleRules(String raffleId, String newRaffleRules) {
        return "UPDATE `innodb`.`RaffleDetails`" +
                "SET `raffleRules` = \""+ newRaffleRules + "\"" +
                "WHERE `raffleID` = \"" + raffleId + "\";";
    }

    public static String taskStatus(String ptcRaffleId, String taskCompletedId, Boolean status) {
        return "UPDATE `innodb`.`TaskStatus` SET `taskStatus` = " + status.toString() +" " +
                "WHERE `PuserID` = \""+ptcRaffleId+"\" AND `taskID` = \""+ taskCompletedId+"\";\n";
    }
}


