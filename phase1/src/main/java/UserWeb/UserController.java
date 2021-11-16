package main.java.UserWeb;

import main.java.database.*;
import main.java.UserComponent.GetOrganizerUseCase;
import main.java.UserComponent.GetParticipantUseCase;
import main.java.UserComponent.Organizer;
import main.java.UserComponent.Participant;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;


//User Controller
public class UserController {
    private DataExtractor dataExtractor;
    private AddParticipant participantUploader;
    private AddOrganizer organizerUploader;

    public UserController() {
        try {
            this.dataExtractor = new DataExtractor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.participantUploader = new AddParticipant();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.organizerUploader = new AddOrganizer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new Participant object and upload the new participant's information to database
     * @param username username
     * @param password password
     * @param firstName first name
     * @param lastName last name
     * @param dateOfBirth date of birth
     * @param phone phone
     * @param email email
     * @return a Participant object
     */
    public Participant createNewParticipant(String username, String password, String firstName, String lastName,
                                            LocalDate dateOfBirth, String phone, String email) {
        GetParticipantUseCase getParticipantUseCase = new GetParticipantUseCase(username, password, firstName,
                    lastName, dateOfBirth, phone, email);
        Participant newParticipant = getParticipantUseCase.getParticipant();
        try {
            participantUploader.updateParticipantPool(username, password, firstName, lastName, dateOfBirth.toString(),
                    phone, email);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newParticipant;
    }

    public Participant getExistedParticipant(String username) {
        String[] ptcInfo = new String[5];
        try {
            ptcInfo = dataExtractor.getUserDetails(username, "P");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String password = ptcInfo[0];
        String firstName = ptcInfo[1];
        String lastName = ptcInfo[2];
        LocalDate doB = LocalDate.parse(ptcInfo[3]);
        String phone = ptcInfo[4];
        String email = ptcInfo[5];
        GetParticipantUseCase getParticipantUseCase = new GetParticipantUseCase(username, password, firstName,
                lastName, doB, phone, email);
        return getParticipantUseCase.getParticipant();
    }

//    //phase2 extension
//    public void updateParticipantPool() {
//
//    }

    /**
     * Create a new Organizer object and upload the new organizer's information to database
     * @param username username
     * @param password password
     * @param orgName affiliated organization name
     * @param phone phone
     * @param email email
     * @return an Organizer object
     */
    public Organizer createNewOrganizer(String username, String password, String orgName, String phone,
                                        String email) {
        GetOrganizerUseCase getOrganizerUseCase = new GetOrganizerUseCase(username, password, orgName,
                    phone, email);
        Organizer newOrganizer = getOrganizerUseCase.getOrganizer();
        organizerUploader.updateOrganizerPool(username, password, orgName, phone, email);
        return newOrganizer;
    }

    public Organizer getExistedOrganizer(String username) {
        String[] orgInfo = new String[3];
        try {
            orgInfo = dataExtractor.getUserDetails(username, "O");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String password = orgInfo[0];
        String orgName = orgInfo[1];
        String phone = orgInfo[2];
        String email = orgInfo[3];
        GetOrganizerUseCase getOrganizerUseCase = new GetOrganizerUseCase(username, password, orgName, phone, email);
        return getOrganizerUseCase.getOrganizer();
    }
}
