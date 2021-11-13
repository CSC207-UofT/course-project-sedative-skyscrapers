package main.java.RaffleComponent;

import java.time.LocalDate;
import java.util.ArrayList;

public class LoginRaffleUseCase {

    private String orgRaffleId;
    private String ptcLogginInId;
    private ArrayList<Object> orgRaffleInfo;
    private RaffleEntity ptcRaffle;
    private OrganizerRaffleEntity orgRaffle;

    public void setOrgRaffleInfo(ArrayList<Object> orgRaffleInfo) {
        this.orgRaffleInfo = orgRaffleInfo;
    }

    public enum LoginResult {
        SUCCESS, RAFFLE_ID_NOT_RECOGNIZED
    }

    public LoginRaffleUseCase(String orgRaffleId, String ptcId) {
        this.orgRaffleId = orgRaffleId;
        this.ptcLogginInId = ptcId;
        // todo uncomment: this.orgRaffleInfo = DataAccess.getOrganizerRaffleById(this.orgRaffleId)
        this.orgRaffle = new OrganizerRaffleEntity((String)this.orgRaffleInfo.get(0),
                (Integer)this.orgRaffleInfo.get(1), (LocalDate)this.orgRaffleInfo.get(3));
        this.orgRaffle.setRaffleId(orgRaffleId);
        this.orgRaffle.setRaffleRules((String)this.orgRaffleInfo.get(2));
        this.orgRaffle.setTaskIdList((ArrayList<String>)this.orgRaffleInfo.get(4));
    }

    public LoginResult runRaffleLogin() {

        if (this.orgRaffleInfo == null){
            // no raffle object corresponding to such id in the database
            return LoginResult.RAFFLE_ID_NOT_RECOGNIZED;
        } else {
            // copy items from array of raffle attributes to a RaffleEntity accessible by this participant
            this.ptcRaffle = new RaffleEntity(this.orgRaffle.getRaffleName(), this.orgRaffle.getNumberOfWinners(),
                    this.orgRaffle.getEndDate());
            this.ptcRaffle.setRaffleId(this.generatePtcRaffleId());
            this.ptcRaffle.setRaffleRules(this.orgRaffle.getRaffleRules());
            this.ptcRaffle.setTaskIdList(this.orgRaffle.getTaskIdList());

            // add this participant to the raffleToCopy participantIdList
            // this makes us depend on two classes (not single responsibility), but the entity is a single one
            this.orgRaffle.getParticipantIdList().add(this.ptcLogginInId);


            // todo uncomment: DataAccess.uploadLoggedInRaffle(this.ptcRaffle)
            // todo uncomment: DataAccess.uploadModifiedOrgRaffle(this.orgRaffle)
            return LoginResult.SUCCESS;
        }


    }

    public String generatePtcRaffleId(){
        String[] orgRaffleIdArray = this.orgRaffleId.split(":");  // of form [orgName, raffleId]
        return this.ptcLogginInId + ":" + orgRaffleIdArray[1];
    }

    // for testing purposes
    public RaffleEntity getRaffle() {
        return ptcRaffle;
    }
}

