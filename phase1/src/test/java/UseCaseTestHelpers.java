package test.java;

import main.java.RaffleComponent.OrganizerRaffleEntity;

import java.time.LocalDate;
import java.util.ArrayList;

public class UseCaseTestHelpers {

    public UseCaseTestHelpers(){

    }

    public ArrayList<Object> setupDummyOrgRaffleInfo(OrganizerRaffleEntity orgRaffle){
        ArrayList<Object> orgRaffleInfo = new ArrayList<>();
        orgRaffleInfo.add(orgRaffle.getRaffleName());
        orgRaffleInfo.add(orgRaffle.getNumberOfWinners());
        orgRaffleInfo.add(orgRaffle.getRaffleRules());
        orgRaffleInfo.add(orgRaffle.getEndDate());
        orgRaffleInfo.add(orgRaffle.getTaskIdList());
        orgRaffleInfo.add(orgRaffle.getParticipantIdList());  // empty list
        orgRaffleInfo.add(orgRaffle.getWinnerList()); // empty list

        return orgRaffleInfo;
    }

    public ArrayList<Object> setupDummyPtcRaffleInfo(String raffleName, int numOfWinners, String raffleRules,
                                                     LocalDate endDate, ArrayList<String> taskIdList){
        ArrayList<Object> ptcRaffleInfo = new ArrayList<>();
        ptcRaffleInfo.add(raffleName);
        ptcRaffleInfo.add(numOfWinners);
        ptcRaffleInfo.add(raffleRules);
        ptcRaffleInfo.add(endDate);
        ptcRaffleInfo.add(taskIdList);  // empty list

        return ptcRaffleInfo;
    }
}
