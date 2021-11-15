package main.java.RaffleComponent;

import main.java.Helpers.PackageRaffleEntityInstance;

import java.time.LocalDate;
import java.util.ArrayList;

public class LoginRaffleUseCase {

    private String orgRaffleId;
    private String ptcLogginInId;
    private ArrayList<Object> orgRaffleInfo;
    private RaffleEntity ptcRaffle;
    private OrganizerRaffleEntity orgRaffle;
    private LoginResult loginResult;
    private PackageRaffleEntityInstance dataPackager;


    public enum LoginResult {
        SUCCESS, RAFFLE_ID_NOT_RECOGNIZED
    }

    public LoginRaffleUseCase(String orgRaffleId, String ptcId) {
        this.orgRaffleId = orgRaffleId;  // pureRaffleId provided by user
        this.ptcLogginInId = ptcId;  // provided by system
        // todo uncomment: this.orgRaffleInfo = DataAccess.getOrganizerRaffleById(this.orgRaffleId)
        this.orgRaffle = new OrganizerRaffleEntity((String)this.orgRaffleInfo.get(0),
                (Integer)this.orgRaffleInfo.get(1), (LocalDate)this.orgRaffleInfo.get(3));
        this.orgRaffle.setRaffleId(orgRaffleId);
        this.orgRaffle.setRaffleRules((String)this.orgRaffleInfo.get(2));
        this.orgRaffle.setTaskIdList((ArrayList<String>)this.orgRaffleInfo.get(4));
        this.orgRaffle.setParticipantIdList((ArrayList<String>)this.orgRaffleInfo.get(5));
        // winnerList automatically set by constructor to empty, as it should stay while participants can log in

        this.dataPackager = new PackageRaffleEntityInstance();

    }

    public String runRaffleLogin() {

        if (this.orgRaffleInfo == null){
            // no raffle object corresponding to such id in the database
            this.loginResult = LoginResult.RAFFLE_ID_NOT_RECOGNIZED;
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

            ArrayList<Object> packagedPtcRaffle = this.dataPackager.packageParticipantRaffle(this.ptcRaffle);
            ArrayList<Object> packagedOrgRaffle = this.dataPackager.packageParticipantRaffle(this.orgRaffle);

            // todo uncomment: DataAccess.uploadLoggedInRaffle(this.ptcRaffle.getRaffleId(), packagedPtcRaffle)
            // todo uncomment: DataAccess.uploadModifiedOrgRaffle(this.orgRaffle.getRaffleId(), packagedOrgRaffle)
            this.loginResult = LoginResult.SUCCESS;
            return this.ptcRaffle.getRaffleId();
        }

        return null;
    }

    public String generatePtcRaffleId(){
        return this.ptcLogginInId + ":" + this.orgRaffleId;  // orgRaffleId is pure
    }

    public LoginResult getLoginResult() {
        return loginResult;
    }

    // for testing purposes

    public void setOrgRaffleInfo(ArrayList<Object> orgRaffleInfo) {
        this.orgRaffleInfo = orgRaffleInfo;
    }

    public RaffleEntity getRaffle() {
        return ptcRaffle;
    }

}

