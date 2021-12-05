package main.java.UserComponent;

import main.java.DatabaseRe.AccessData;
import main.java.UserComponent.Participant;
import main.java.UserComponent.GetParticipantUseCase;
import main.java.UserComponent.Organizer;
import main.java.UserComponent.GetOrganizerUseCase;

import java.sql.SQLException;
import java.time.LocalDate;
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

    public Participant getPtc(String username){
        ArrayList<String> ptcInfo = DataAccess.getParticipantInfo(username);
        String ptcUserId = ptcInfo.get(0);
        String ptcUsername = ptcInfo.get(1);
        String password = ptcInfo.get(2);
        String firstName = ptcInfo.get(3);
        String lastName = ptcInfo.get(4);
        LocalDate doB = LocalDate.parse(ptcInfo.get(5));
        String phone = ptcInfo.get(6);
        String email = ptcInfo.get(7);
        GetParticipantUseCase getParticipantUseCase = new GetParticipantUseCase(ptcUserId, ptcUsername, password,
                firstName, lastName, doB, phone, email);
        return getParticipantUseCase.getParticipant();
    }

    public String getPtcUserId(String username){
        return getPtc(username).getUserId();
    }

    public String getPtcPassword(String username){
        return getPtc(username).getPassword();
    }

    public Organizer getOrg(String username){
        ArrayList<String> orgInfo = DataAccess.getOrganizerInfo(username);
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
}
