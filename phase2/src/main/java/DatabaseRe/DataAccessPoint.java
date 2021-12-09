package main.java.DatabaseRe;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface DataAccessPoint {
    /* the interface detailing the methods implemented at the 4th layer, such methods are to be accessed
     by the use cases (directly dependent on this interface) to get the needed information from the database
     */

     ArrayList<String> getTakenRaffleIds() throws SQLException;

     ArrayList<Object> getOrganizerRaffleById(String orgRaffleId) throws SQLException, ParseException;

     ArrayList<Object> getParticipantRaffleById(String ptcRaffleID) throws SQLException, ParseException;

     ArrayList<String> getTaskById(String taskId);


     ArrayList<String> getParticipantInfo(String ptcUsername);
     
     ArrayList<String> getOrganizerInfo(String orgUsername);

     ArrayList<String> getParticipantRaffleIds(String ptcUsername);

     ArrayList<String> getOrganizerRaffleIds(String orgUsername);

     boolean hasCompletedTask(String ptcRaffleId, String taskId) throws SQLException;

     ArrayList<String> getTakenOrganizerIds();

     ArrayList<String> getTakenParticipantIds();


     ArrayList<String> getValidParticipants (String orgRaffleId);

     ArrayList<String> getRaffleIDByRaffleName(String raffleName);

     ArrayList<String> getOrgIDByOrganization(String organization);

     ArrayList<String>  getTakenTaskIds();

     String getParticipantUsernameFromID(String ptcID, boolean organizer);

     String getUserIDFromUsername(String username, boolean organizer) throws Exception;
}

