package main.java.DatabaseRe.Mediators.Adders;

import main.java.DatabaseRe.Mediators.Getters.UserGetter;
import main.java.DatabaseRe.Mediators.InsertQueries;
import main.java.DatabaseRe.TalkToDatabase.InsertUpdateQuery;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserAdder {
    UserGetter userGetter = new UserGetter();
    static InsertUpdateQuery insertUpdateQuery = new InsertUpdateQuery();
    public UserAdder() throws SQLException {
    }

    public void winnersToRaffle(String raffleID, ArrayList<String> participantIDs) throws Exception {
        for (String ptcID : participantIDs) {
            boolean userExists = userGetter.checkParticipantID(ptcID);
            if (userExists) {
                addRaffleWinner(raffleID, ptcID);
            }
            else {
                throw new Exception("Invalid Username " + ptcID + ". Please Check.");
            }
        }
    }

    public void addRaffleWinner(String raffleID, String ptcID) throws SQLException {
        String query = InsertQueries.addRaffleWinner(raffleID, ptcID);
        insertUpdateQuery.run(query);
        insertUpdateQuery.close();
    }

    public void raffleToOrganizer(String userID, String raffleID) throws SQLException {
        String query = InsertQueries.organizerToRaffle(userID, raffleID);
        insertUpdateQuery.run(query);
        insertUpdateQuery.close();
    }

    public void addOrganizerID(String organizerId, String username) throws SQLException {
        String query = InsertQueries.organizerID(organizerId, username);
        insertUpdateQuery.run(query);
        insertUpdateQuery.close();
    }

    public void addOrganizerDetails(String organizerId, String password,
                                    String affiliatedOrganization, String phone, String email) throws SQLException {
        String query = InsertQueries.organizerDetails(organizerId, password, affiliatedOrganization, phone, email);
        insertUpdateQuery.run(query);
        insertUpdateQuery.close();
    }

    public void addParticipantID(String userId, String username) throws SQLException {
        String query = InsertQueries.participantID(userId, username);
        insertUpdateQuery.run(query);
        insertUpdateQuery.close();
    }

    public void addParticipantDetails(String userId, String password, String firstName, String lastName,
                                      String dateOfBirth, String phone, String email) throws SQLException {
        String query = InsertQueries.participantDetails(userId, password, firstName, lastName, dateOfBirth, phone, email);
        insertUpdateQuery.run(query);
        insertUpdateQuery.close();
    }
}
