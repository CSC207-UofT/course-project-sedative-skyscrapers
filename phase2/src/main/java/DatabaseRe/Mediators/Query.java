package main.java.DatabaseRe.Mediators;

/**
 * Query class contains the queries that need to be made to the database
 */
public abstract class Query {
    public static String usedRaffleIDs = "select raffleID from innodb.RaffleDetails;";
    public static String usedOrganizerIdsQuery = "SELECT OuserID FROM innodb.OrganizerUsernames;";
    public static String usedParticipantIdsQuery = "SELECT PuserID FROM innodb.ParticipantUsernames;";

    public static String getDetailsQuery(String orgRaffleId) {
        return "SELECT RaffleDetails.* FROM innodb.RaffleDetails WHERE innodb.RaffleDetails.raffleID = '" + orgRaffleId +  "';";
    }

    public static String getPTCInRafflesQuery(String raffleID) {
        return "SELECT * FROM innodb.participantsInRaffle where raffleID=\""+ raffleID + "\";";
    }

    public static String getTasksInRafflesQuery(String raffleID) {
        return "select * from innodb.TasksInRaffle where raffleID=\""+ raffleID + "\";";
    }

    public static String getWinnersQuery(String raffleID) {
        return "select * from innodb.Winners where raffleID=\""+ raffleID + "\";";
    }

    public static String getTaskQuery(String taskId) {
        return "SELECT * FROM innodb.TaskDetails where taskID=\"" + taskId + "\";";
    }

    public static String getPTCInfoQuery(String ptcID, boolean organizer) {
        String first = "SELECT * FROM innodb.";
        if (organizer) {
            first += "OuserCred where OuserID=\"" + ptcID +"\";";
        }
        else {
            first += "PuserCred where PuserID=\"" + ptcID +"\";";
        }
        return first;
    }

    public static String getPTCID(String ptcUsername) {
        return "SELECT * FROM innodb.ParticipantUsernames where username=\"" + ptcUsername +"\";";
    }

    public static String getOrgID(String ptcUsername) {
        return "SELECT * FROM innodb.OrganizerUsernames where username=\"" + ptcUsername +"\";";
    }

    public static String getRafflesOfParticipantsQuery(String ptcID) {
        return "SELECT * FROM innodb.participantsInRaffle where PuserID=\"" + ptcID +"\";";
    }

    public static String getRafflesOfOrganizer(String orgID) {
        return "SELECT * FROM innodb.OrganizersRaffles where OuserID=\"" + orgID +"\";";
    }

    public static String getTaskStatusQuery(String ptcRaffleId, String taskId) {
        return "SELECT `TaskStatus`.`taskStatus`" +
                "FROM `innodb`.`TaskStatus`" +
                "WHERE PuserID=\"" + ptcRaffleId + "\" AND taskID=\"" + taskId + "\";";
    }
}
