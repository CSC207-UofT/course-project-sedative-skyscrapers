package main.java.UserComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.DataAccessPoint;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * the class accesses the database through dependency injection to look up and provide the information of the user.
 */
public class LookUpUser {
    private DataAccessPoint DataAccess;

    public LookUpUser() {
        try {
            this.DataAccess = new AccessData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getPtcInfo(String username) {
        return DataAccess.getParticipantInfo(username);
    }

    /**
     * returns a participant by retrieving participant info from the database
     *
     * @param username particpant username
     * @return Participant object
     */
    public Participant getPtc(String username) {
        ArrayList<String> ptcInfo = getPtcInfo(username);

        if (ptcInfo.isEmpty()) {
            return null;
        }
        String ptcUserId = ptcInfo.get(0);
        String ptcUsername = username;
        String password = ptcInfo.get(1);
        String firstName = ptcInfo.get(2);
        String lastName = ptcInfo.get(3);
        String date = ptcInfo.get(4);
        LocalDate doB = LocalDate.parse(date.substring(0, 10), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String phone = ptcInfo.get(5);
        String email = ptcInfo.get(6);
        GetParticipantUseCase getParticipantUseCase = new GetParticipantUseCase(ptcUserId, ptcUsername, password,
                firstName, lastName, doB, phone, email);
        return getParticipantUseCase.getParticipant();
    }

    /**
     * gets participant userID using username
     */
    public String getPtcUserId(String username) {
        Participant ptc = getPtc(username);
        if (ptc != null) {
            return ptc.getUserId();
        }
        return null;
    }

    /**
     * gets participant password using username
     */
    public String getPtcPassword(String username) {
        Participant ptc = getPtc(username);
        if (ptc != null) {
            return ptc.getPassword();
        }
        return null;
    }

    public ArrayList<String> getOrgInfo(String username) {
        return DataAccess.getOrganizerInfo(username);
    }

    /**
     * returns an organizer by retrieving organizer info from the database
     *
     * @param username organizer username
     * @return Organizer object
     */
    public Organizer getOrg(String username) {
        ArrayList<String> orgInfo = getOrgInfo(username);
        if (orgInfo.isEmpty()) {
            return null;
        }
        String orgUserId = orgInfo.get(0);
        String password = orgInfo.get(1);
        String organization = orgInfo.get(2);
        String phone = orgInfo.get(4);
        String email = orgInfo.get(5);
        GetOrganizerUseCase getOrganizerUseCase = new GetOrganizerUseCase(orgUserId, username, password,
                organization, phone, email);
        return getOrganizerUseCase.getOrganizer();
    }

    /**
     * gets organizer userID using username
     */
    public String getOrgUserId(String username) {
        Organizer org = getOrg(username);
        if (org != null) {
            return org.getUserId();
        }
        return null;
    }

    /**
     * gets organizer password using username
     */
    public String getOrgPassword(String username) {
        Organizer org = getOrg(username);
        if (org != null) {
            return org.getPassword();
        }
        return null;
    }

    /**
     * gets organizer userID using organization name
     */
    public ArrayList<String> getOrgId(String organization) {
        return DataAccess.getOrgIDByOrganization(organization);
    }
}
