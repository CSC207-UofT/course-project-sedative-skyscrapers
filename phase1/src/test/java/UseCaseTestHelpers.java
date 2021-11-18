package test.java;

import main.java.RaffleComponent.OrganizerRaffleEntity;

import java.time.LocalDate;
import java.util.ArrayList;

public class UseCaseTestHelpers {

    /**
     * Class intended to help set up dummy info for raffle testing
     * UPDATE: with testing currently disabled, this class is not used, however, don't let this keep
     * you from looking through the tests, without the specific calls to the database methods in our
     * use cases these test would be working
     */
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
