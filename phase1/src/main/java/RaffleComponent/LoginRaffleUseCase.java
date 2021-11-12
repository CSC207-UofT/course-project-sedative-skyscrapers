package main.java.RaffleComponent;

import java.time.LocalDate;
import java.util.ArrayList;

public class LoginRaffleUseCase {

    private String raffleId;
    private ArrayList<Object> orgRaffleInfo;
    private RaffleEntity raffle;

    public enum LoginResult {
        SUCCESS, RAFFLE_ID_NOT_RECOGNIZED
    }

    public LoginRaffleUseCase(String raffleId, ArrayList<Object> raffleToCopyDetails) {
        this.raffleId = raffleId;
        this.orgRaffleInfo = raffleToCopyDetails;
    }

    public LoginResult runRaffleLogin() {

        if (orgRaffleInfo == null){
            // no raffle object corresponding to such id in the database
            return LoginResult.RAFFLE_ID_NOT_RECOGNIZED;
        } else {
            // copy items from array of raffle attributes to a RaffleEntity accessible by this participant
            this.raffle = new RaffleEntity((String) this.orgRaffleInfo.get(0), (Integer) this.orgRaffleInfo.get(1),
                    (LocalDate) this.orgRaffleInfo.get(3));
            this.raffle.setRaffleId(this.raffleId);

            // todo inside controller
            // add this participant to the raffleToCopy participantIdList
            // raffleToCopy.getParticipantIdList().add(this.ptcId);

            return LoginResult.SUCCESS;
        }


    }


    // for testing purposes
    public RaffleEntity getRaffle() {
        return raffle;
    }
}

