package main.java.UserComponent;

import main.java.DatabaseRe.AccessData;
import main.java.RaffleComponent.DataAccessPoint;
import main.java.UserComponent.Participant;
import main.java.UserComponent.GetParticipantUseCase;
import main.java.UserComponent.Organizer;
import main.java.UserComponent.GetOrganizerUseCase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LookUpUser {
    private DataAccessPoint DataAccess;

    public LookUpUser(){
        try {
            this.DataAccess = new AccessData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getPtcInfo(String username){
        return DataAccess.getParticipantInfo(username);
    }

    public Participant getPtc(String username){
        ArrayList<String> ptcInfo = getPtcInfo(username);
        String ptcUserId = ptcInfo.get(0);
        String ptcUsername = ptcInfo.get(1);
        String password = ptcInfo.get(2);
        String firstName = ptcInfo.get(3);
        //String lastName = ptcInfo.get(4);
        String dateRecievedFromDatabase = ptcInfo.get(4);
        LocalDate doB = LocalDate.parse(dateRecievedFromDatabase.substring(0, 10), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String phone = ptcInfo.get(5);
        String email = ptcInfo.get(6);
        GetParticipantUseCase getParticipantUseCase = new GetParticipantUseCase(ptcUserId, ptcUsername, password,
                firstName, "fake", doB, phone, email);
        return getParticipantUseCase.getParticipant();
    }

    public String getPtcUserId(String username){
        return getPtc(username).getUserId();
    }

    public String getPtcPassword(String username){
        return getPtc(username).getPassword();
    }

    public ArrayList<String> getOrgInfo(String username){
        return DataAccess.getOrganizerInfo(username);
    }

    public Organizer getOrg(String username){
        ArrayList<String> orgInfo = getOrgInfo(username);
        String orgUserId = orgInfo.get(0);
        String orgUsername = orgInfo.get(1);
        String password = orgInfo.get(2);
        String organization = orgInfo.get(3);
        String phone = orgInfo.get(4);
        String email = orgInfo.get(5);
        GetOrganizerUseCase getOrganizerUseCase = new GetOrganizerUseCase(orgUserId, orgUsername, password,
                organization, phone, email);
        return getOrganizerUseCase.getOrganizer();
    }

    public String getOrgUserId(String username){
        return getOrg(username).getUserId();
    }

    public String getOrgPassword(String username){
        return getOrg(username).getPassword();
    }

    public ArrayList<String> getOrgId(String organization){
        return DataAccess.getOrgIDByOrganization(organization);
    }
}
