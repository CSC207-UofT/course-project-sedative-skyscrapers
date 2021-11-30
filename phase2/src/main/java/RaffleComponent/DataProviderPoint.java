package main.java.RaffleComponent;

import java.util.ArrayList;

public interface DataProviderPoint {

    void uploadCreatedRaffle(String orgRaffleId, ArrayList<Object> raffleCreatedInfo);
    // update of the takenIds after an organizer raffle object is created, along with the new organizer Raffle
    // information that was just input by the used.
    // RaffleCreatedInfo format: [raffleName="raffle", numberOfWinners=2, rules="this is a string of rules",
    //        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
    //        winnerIds=ArrayList<String>]

    void uploadModifiedOrgRaffle(String orgRaffleId, ArrayList<Object> raffleModifiedInfo);
    // sends updated orgRaffle information data to DB under the provided Id, at DB all data related to the raffle
    // is updated according to raffleModifiedInfo, this would be just like uploadCreatedRaffle, but some values
    // are to be replaced/added according to how the raffle was modified

    void uploadLoggedInRaffle(String ptcRaffleId, ArrayList<Object> raffleJoinedInfo);
    // sends data of newly logged in participant raffle object to the database to be stored under the raffleId provided
    // the raffleJoinedInfo has the format:
    // [raffleName="raffle", numberOfWinners=2, rules="this is a string of rules",
    //        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>]

    void uploadModifiedPtcRaffle(String ptcRaffleId, ArrayList<Object> raffleModifiedInfo);
    // sends updated ptcRaffle information data to DB under the provided Id, at DB all data related to the raffle
    // is updated according to raffleModifiedInfo, this would be just like uploadLoggedInRaffle, but some values
    // are to be replaced/added according to how the raffle was modified

    void uploadCreatedTask(String taskId, ArrayList<String> taskInfo);

    // add the organizer info
    void updateOrganizerPool(String username, String password, String affiliatedOrganization, String phone,
                             String email);
    // need to be able to check whether the username is in organizerPool
    // need a getter method to get the rest of the organizer info by using the username
    // need a getter method to get particularly password using the username

    // should be able to check whether the participant is a new user or it is an update of the participant info
    void updateParticipantPool(String username, String password, String firstName, String lastName, String dateOfBirth,
                               String phone, String email);


    // todo not implemented?
    // add the raffleID to the list of raffleIDs the username is a part of
    void addRaffleIDtoParticipant(String username, String raffleID);
    // need a getter method to get the list of raffleIDs associated with the username

    // add the raffleId to the list of raffleIDs the username created
    void addRaffleIDtoOrganizer(String username, String raffleID);
    // need a getter method to get the list of raffleIDs associated with the username


}
