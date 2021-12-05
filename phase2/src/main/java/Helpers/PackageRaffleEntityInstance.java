package main.java.Helpers;

import main.java.RaffleComponent.OrganizerRaffleEntity;
import main.java.RaffleComponent.ParticipantRaffleEntity;

import java.time.LocalDate;
import java.util.ArrayList;

public class PackageRaffleEntityInstance implements PackageRaffleObject {

    private final ArrayList<Object> raffleToReturn;

    public PackageRaffleEntityInstance(){
        this.raffleToReturn = new ArrayList<>();
    }

    @Override
    public ArrayList<Object> packageParticipantRaffle(ParticipantRaffleEntity raffle) {
        commonPackager(raffle.getRaffleName(), raffle.getNumberOfWinners(), raffle.getRaffleRules(),
                raffle.getEndDate(), raffle.getTaskIdList());

        return this.raffleToReturn;
    }

    @Override
    public ArrayList<Object> packageOrganizerRaffle(OrganizerRaffleEntity raffle){
        commonPackager(raffle.getRaffleName(), raffle.getNumberOfWinners(), raffle.getRaffleRules(),
                raffle.getEndDate(), raffle.getTaskIdList());
        this.raffleToReturn.add(raffle.getParticipantIdList());
        this.raffleToReturn.add(raffle.getWinnerList());

        return this.raffleToReturn;
    }

    private void commonPackager(String raffleName, int numberOfWinners, String raffleRules, LocalDate endDate,
                                ArrayList<String> taskIdList) {
        if (!this.raffleToReturn.isEmpty()) {
            this.raffleToReturn.clear();
        }

        this.raffleToReturn.add(raffleName);
        this.raffleToReturn.add(numberOfWinners);
        this.raffleToReturn.add(raffleRules);
        this.raffleToReturn.add(endDate);
        this.raffleToReturn.add(taskIdList);
    }

}
