package main.java.DatabaseRe.Mediators;

/**
 * Query class contains the queries that need to be made to the database
 */
public abstract class Query {
    public static String usedRaffleIDs = "select raffleID from innodb.RaffleDetails;";

    public static String detailsQuery(String orgRaffleId) {
        return "SELECT RaffleDetails.* FROM innodb.RaffleDetails WHERE innodb.RaffleDetails.raffleID = '" + orgRaffleId +  "';";
    }
}
