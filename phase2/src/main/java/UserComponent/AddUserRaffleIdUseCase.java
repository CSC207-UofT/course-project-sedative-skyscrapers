package main.java.UserComponent;

public class AddUserRaffleIdUseCase {

    public void addRaffleIdToPtc(String username, String raffleId){
        DataProviderPoint.addRaffleIDtoParticipant(username, raffleId);
    }
}
