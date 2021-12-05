package main.java.Helpers;

import main.java.RaffleComponent.OrganizerRaffleEntity;
import main.java.RaffleComponent.ParticipantRaffleEntity;

import java.util.ArrayList;

public interface PackageRaffleObject {

    public ArrayList<Object> packageParticipantRaffle(ParticipantRaffleEntity ptcRaffle);

    public ArrayList<Object> packageOrganizerRaffle(OrganizerRaffleEntity orgRaffle);
}
