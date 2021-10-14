package main.java;

import main.java.Raffle;

import java.util.ArrayList;
import java.util.List;

public class ParticipantRaffle extends Raffle {
// TODO: add the stuff specific to participant raffle according to crc
    private boolean validParticipant = false;
    private List<Task> tasksCompleted = new ArrayList<>();

    public ParticipantRaffle(User participant, int raffleID){

        for (Raffle raffle : Raffle.allRaffles) {
            if (raffle.getRaffleID() == raffleID){  // found raffle in the allRaffles arrayList
                // user would have to implement this setRaffleID method to relate a participant to a Raffle

                // setting up entity relationship
                participant.setRaffleID(raffleID);
                super.addParticipantToList(participant);
                break;
            }
        }

        System.out.println("Invalid raffleID");

    }

    public void setValidParticipant(){
        if (!this.tasksCompleted.isEmpty()){
            this.validParticipant = true;
        }
    }
}
