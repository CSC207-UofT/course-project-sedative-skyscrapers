package main.java.Helpers;

import main.java.RaffleComponent.OrganizerRaffleEntity;
import main.java.RaffleComponent.RaffleEntity;

import java.util.ArrayList;

public class PackageRaffleEntityInstance implements PackageRaffleObject {

    private ArrayList<Object> raffleToReturn;

    public PackageRaffleEntityInstance(){
        this.raffleToReturn = new ArrayList<Object>();
    }

    @Override
    public ArrayList<Object> packageParticipantRaffle(RaffleEntity raffle) {
        if (!this.raffleToReturn.isEmpty()) {
            this.raffleToReturn.clear();
        }

        this.raffleToReturn.add(raffle.getRaffleName());
        this.raffleToReturn.add(raffle.getNumberOfWinners());
        this.raffleToReturn.add(raffle.getRaffleRules());
        this.raffleToReturn.add(raffle.getEndDate());
        this.raffleToReturn.add(raffle.getTaskIdList());

        return this.raffleToReturn;
    }

    @Override
    public ArrayList<Object> packageOrganizerRaffle(OrganizerRaffleEntity raffle){
        if (!this.raffleToReturn.isEmpty()) {
            this.raffleToReturn.clear();
        }

        this.packageParticipantRaffle(raffle);
        this.raffleToReturn.add(raffle.getParticipantIdList());
        this.raffleToReturn.add(raffle.getWinnerList());

        return this.raffleToReturn;
    }

}
