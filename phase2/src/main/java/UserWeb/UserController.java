package main.java.UserWeb;

import main.java.UserComponent.AddUserRaffleIdUseCase;
import main.java.UserComponent.CheckUserUsernameUseCase;
import main.java.UserComponent.CreateUserUseCase;
import main.java.UserComponent.LookUpUser;
import src.main.java.UserComponent.GetUserRaffleIdUseCase;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserController {
    private final CreateUserUseCase userCreator;
    private final GetUserRaffleIdUseCase userRaffleIdGetter;
    private final AddUserRaffleIdUseCase userRaffleIdAdder;
    private final CheckUserUsernameUseCase usernameChecker;
    private final LookUpUser userLookUp;

    public UserController() {
        this.userCreator = new CreateUserUseCase();
        this.userRaffleIdGetter = new GetUserRaffleIdUseCase();
        this.userRaffleIdAdder = new AddUserRaffleIdUseCase();
        this.usernameChecker = new CheckUserUsernameUseCase();
        this.userLookUp = new LookUpUser();
    }

    /**
     * Store the new participant into the database
     * @param username username
     * @param password password
     * @param firstName first name
     * @param lastName last name
     * @param dateOfBirth date of birth
     * @param phone phone
     * @param email email
     */
    public void createNewParticipant(String username, String password, String firstName, String lastName,
                                            LocalDate dateOfBirth, String phone, String email) {
        userCreator.storeParticipant(username, password, firstName, lastName, dateOfBirth.toString(), phone, email);
    }

    public ArrayList<String> getExistedParticipantInfo(String username) {
        return userLookUp.getPtcInfo(username);
    }

    /**
     * Store the new organizer to the database
     * @param username username
     * @param password password
     * @param orgName name of affiliated organization
     * @param phone phone
     * @param email email
     */
    public void createNewOrganizer(String username, String password, String orgName, String phone,
                                        String email) {
        userCreator.storeOrganizer(username, password, orgName, phone, email);
    }

    public ArrayList<String> getExistedOrganizerInfo(String username){
        return userLookUp.getOrgInfo(username);
    }


//    public Organizer getExistedOrganizer(String username) {
//        String[] orgInfo = new String[3];
//        try {
//            orgInfo = dataExtractor.getOrganizerDetails(username);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String password = orgInfo[0];
//        String orgName = orgInfo[1];
//        String phone = orgInfo[2];
//        String email = orgInfo[3];
//        GetOrganizerUseCase getOrganizerUseCase = new GetOrganizerUseCase(username, password, orgName, phone, email);
//        return getOrganizerUseCase.getOrganizer();
//    }

    /**
     * Get a list of raffleIDs of the raffles that the participant is a part of
     * @param username participant username
     * @return a list of raffleIDs
     */
    public ArrayList<String> getParticipantRaffleID(String username) {
        return this.userRaffleIdGetter.getPtcRaffleIdsFromDatabase(username);
    }

    /**
     * Get the raffleID of the raffle that the organizer created
     * @param username organizer username
     * @return a raffleID String
     */
    public String[] getOrganizerRaffleID(String username) {
        return this.userRaffleIdGetter.getOrgRafflesIdsFromDatabase(username);
    }

    /**
     * Upload the raffleID of the raffle the participant joined to database
     * @param username participant username
     * @param raffleID the raffleID of the raffle the participant joined
     */
    public void addRaffleIDToParticipant(String username, String raffleID) {
        this.userRaffleIdAdder.addRaffleIdToPtc(username, raffleID);
    }

    /**
     * Check whether a username is already registered
     * @param username username that a user trying to register
     * @return false if the username is not registered yet, true if the username is already used
     */
    public boolean userNameUsed(String username) {
        return usernameChecker.checkUserNameUsed(username);
    }

    /**
     * Check whether the participant username is already registered and match the password
     * @param username username of the participant
     * @param password password of the organizer
     * @return true if the username is registered as a participant and matches the password, false otherwise
     */
    public boolean participantUsernameMatchPassword(String username, String password) {
        return usernameChecker.checkPtcUsernameMatchPassword(username, password);
    }

    /**
     * Check whether the organizer username is already registered and match the password
     * @param username username of the organizer
     * @param password password of the organizer
     * @return true if the username is registered as an organizer and matches the password, false otherwise
     */
    public boolean organizerUsernameMatchPassword(String username, String password) {
        return this.usernameChecker.checkOrgUsernameMatchPassword(username, password);
    }

    public String getUserUserId(String username, String userType) {
        if (userType.equals("O")) {
            return userLookUp.getOrgUserId(username);
        } else if (userType.equals("P")){
            return userLookUp.getPtcUserId(username);
        }
        return null;
    }

    }