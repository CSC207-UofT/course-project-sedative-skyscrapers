package main.java.UserComponent;

import main.java.DatabaseRe.AccessData;
import main.java.RaffleComponent.DataAccessPoint;
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

    public ArrayList<String> getPtcInfo(String username){
        return DataAccess.getParticipantInfo(username);
    }

    public Participant getPtc(String username){
        ArrayList<String> ptcInfo = getPtcInfo(username);

        if (ptcInfo.isEmpty()){
            return null;
        }
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
        Participant ptc = getPtc(username);
        if (ptc != null){
            return ptc.getUserId();
        }
        return null;
    }

    public String getPtcPassword(String username){
        Participant ptc = getPtc(username);
        if (ptc != null){
            return ptc.getPassword();
        }
        return null;
    }

    public ArrayList<String> getOrgInfo(String username){
        return DataAccess.getOrganizerInfo(username);
    }

    public Organizer getOrg(String username){
        ArrayList<String> orgInfo = getOrgInfo(username);
        if (orgInfo.isEmpty()){
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

    public String getOrgUserId(String username){
        Organizer org = getOrg(username);
        if (org != null){
            return org.getUserId();
        }
        return null;
    }

    public String getOrgPassword(String username){
        Organizer org = getOrg(username);
        if (org != null){
            return org.getPassword();
        }
        return null;
    }

    public ArrayList<String> getOrgId(String organization){
        return DataAccess.getOrgIDByOrganization(organization);
    }
}
