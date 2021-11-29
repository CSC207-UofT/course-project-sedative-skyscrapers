package main.java.DatabaseRe.Mediators.Getters;

import main.java.DatabaseRe.Mediators.Query;
import main.java.DatabaseRe.TalkToDatabase.ConfigConstants;
import main.java.DatabaseRe.TalkToDatabase.SelectQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

public class RaffleGetter {
    SelectQuery selectQuery = new SelectQuery();

    public RaffleGetter() throws SQLException {
    }

    /** gets the details about the raffle through raffleID
     * @param orgRaffleId The OrganizerRaffleID
     * @return An array of strings containing raffle details in format: [raflleName, noOfWinners, rafflRules, endDate]
     */
    public ArrayList<Object> getDetails(String orgRaffleId) throws SQLException, ParseException {
        String query = Query.detailsQuery(orgRaffleId);
        ResultSet results = selectQuery.getResultSet(query);
        return sortResult(results);
    }

    /** gets the raffleIDs that are already used by in the system
     * @return ArrayList of used raffleIDs
     */
    public ArrayList<String> getUsedRaffleIds() throws SQLException {
        ArrayList<String> usedIDs = new ArrayList<>();
        String query = Query.usedRaffleIDs;
        ResultSet results = selectQuery.getResultSet(query);

        while (results.next()) {
            usedIDs.add(results.getString("raffleID"));
        }

        return usedIDs;
    }

    private ArrayList<Object> sortResult(ResultSet results) throws SQLException, ParseException {
        ArrayList<Object> details = new ArrayList<>();
        if (results != null) {
            while (results.next()) {
                ResultSetMetaData resultSetMetaData = results.getMetaData();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    int type = resultSetMetaData.getColumnType(i);
                    ArrayList<Integer> possibleDataTypes = ConfigConstants.getTypes();
                    if (possibleDataTypes.contains(type)) {
                        if (type == Types.DATE) {
                            String date = results.getString(i);
                            String modified = date.substring(8,10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
                            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(modified);
                            details.add(date2);
                        }
                        else {
                            details.add(results.getString(i));
                        }
                    }
                }
            }
        }
        else {
            throw new RuntimeException("Empty Row found");
        }
        return details;
    }

}
