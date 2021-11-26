package main.java.Helpers;

import main.java.RaffleComponent.OrganizerRaffleEntity;
import main.java.RaffleComponent.RaffleEntity;

import java.util.ArrayList;

public interface PackageRaffleObject {

    public ArrayList<Object> packageParticipantRaffle(RaffleEntity ptcRaffle);

    public ArrayList<Object> packageOrganizerRaffle(OrganizerRaffleEntity orgRaffle);
}
